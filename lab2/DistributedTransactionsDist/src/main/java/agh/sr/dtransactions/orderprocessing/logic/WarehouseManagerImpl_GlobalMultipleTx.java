package agh.sr.dtransactions.orderprocessing.logic;

import agh.sr.dtransactions.orderprocessing.dao.ProductDao;

import javax.transaction.*;
import java.sql.SQLException;

/**
 * Author: Piotr Turek
 */
public class WarehouseManagerImpl_GlobalMultipleTx implements WarehouseManagerService {
    private ProductDao productDao;
    private UserTransaction userTransaction;
    private TransactionManager transactionManager;

    public WarehouseManagerImpl_GlobalMultipleTx(ProductDao productDao, UserTransaction userTransaction, TransactionManager transactionManager) {
        this.productDao = productDao;
        this.userTransaction = userTransaction;
        this.transactionManager = transactionManager;
    }


    @Override
    public int prepareProductsForShipment(Order order, Customer customer) throws OrderWarhouseException {
        try {
            userTransaction.begin();
            if (!productDao.checkIfProductsAvailable(order)) {
                throw new OrderWarhouseException("Nota ll produccts avial.");
            }
            int totalPrice = productDao.calculateProductsTotalPrice(order);

            Transaction transaction = transactionManager.suspend();
            logLocationAndTotalPrice(customer, totalPrice);
            transactionManager.resume(transaction);

            if (totalPrice < customer.getBalance()) {
                throw new OrderWarhouseException("Not enough money");
            }
            productDao.decreaseProductsAmounts(order);
            userTransaction.commit();
            return totalPrice;
        } catch (Exception e) {
            try {
                userTransaction.rollback();
            } catch (SystemException e1) {
                e1.printStackTrace();
            }
            throw new OrderWarhouseException(e);
        }
    }

    private void logLocationAndTotalPrice(Customer customer, int totalPrice) {
        try {
            userTransaction.begin();
            productDao.logLocationAndTotalPrice(customer, totalPrice);
        } catch (Exception e) {
            e.printStackTrace();
            try {
                userTransaction.rollback();
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        }
    }

    @Override
    public void setProductDao(ProductDao productDao) {
        this.productDao = productDao;
    }
}
