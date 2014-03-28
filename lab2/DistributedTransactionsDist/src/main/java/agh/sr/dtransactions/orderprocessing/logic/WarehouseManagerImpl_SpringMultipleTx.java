package agh.sr.dtransactions.orderprocessing.logic;

import agh.sr.dtransactions.orderprocessing.dao.ProductDao;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import java.sql.SQLException;

/**
 * Author: Piotr Turek
 */
public class WarehouseManagerImpl_SpringMultipleTx implements WarehouseManagerService {
    private ProductDao productDao;

    private PlatformTransactionManager transactionManager;

    public WarehouseManagerImpl_SpringMultipleTx(ProductDao productDao, PlatformTransactionManager transactionManager) {
        this.productDao = productDao;
        this.transactionManager = transactionManager;
    }

    @Override
    public int prepareProductsForShipment(Order order, Customer customer) throws OrderWarhouseException {
        TransactionStatus transaction = null;
        try {
            DefaultTransactionDefinition transactionDefinition = new DefaultTransactionDefinition();
            transaction = transactionManager.getTransaction(transactionDefinition);
            if (!productDao.checkIfProductsAvailable(order)) {
                throw new OrderWarhouseException("Not all avail.");
            }
            int totalPrice = productDao.calculateProductsTotalPrice(order);

            logLocationAndPrice(customer, totalPrice);

            if (totalPrice > customer.getBalance()) {
                throw new OrderWarhouseException("Not enough money");
            }
            productDao.decreaseProductsAmounts(order);
            transactionManager.commit(transaction);
            return totalPrice;
        } catch (Exception e) {
            try {
                transactionManager.rollback(transaction);
            } catch (Exception e1) {
                e1.printStackTrace();
            }
            throw new OrderWarhouseException(e);
        }
    }

    private void logLocationAndPrice(Customer customer, int totalPrice) {
        TransactionStatus transaction = null;
        try {
            DefaultTransactionDefinition transactionDefinition = new DefaultTransactionDefinition();
            transactionDefinition.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRES_NEW);

            transaction = transactionManager.getTransaction(transactionDefinition);
            productDao.logLocationAndTotalPrice(customer, totalPrice);
            transactionManager.commit(transaction);
        } catch (Exception e) {
            e.printStackTrace();
            try {
                transactionManager.rollback(transaction);
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
