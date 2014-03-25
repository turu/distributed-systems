package agh.sr.dtransactions.orderprocessing.logic;

import agh.sr.dtransactions.orderprocessing.dao.ProductDao;

import javax.transaction.*;
import java.sql.SQLException;

/**
 * Author: Piotr Turek
 */
public class WarehouseManagerImpl_GlobalTx implements WarehouseManagerService {
    private ProductDao productDao;

    private UserTransaction userTransaction;

    public WarehouseManagerImpl_GlobalTx(ProductDao productDao, UserTransaction userTransaction) {
        this.productDao = productDao;
        this.userTransaction = userTransaction;
    }


    @Override
    public int prepareProductsForShipment(Order order, Customer customer) throws OrderWarhouseException {
        try {
            userTransaction.begin();
            if (!productDao.checkIfProductsAvailable(order)) {
                throw new OrderWarhouseException("Not all ordered...");
            }
            final int totalPrice = productDao.calculateProductsTotalPrice(order);
            productDao.logLocationAndTotalPrice(customer, totalPrice);
            if (totalPrice > customer.getBalance()) {
                throw new OrderWarhouseException("Not enough");
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

    @Override
    public void setProductDao(ProductDao productDao) {
        this.productDao = productDao;
    }
}
