package agh.sr.dtransactions.orderprocessing.logic;

import agh.sr.dtransactions.orderprocessing.dao.ProductDao;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

/**
 * Author: Piotr Turek
 */
public class WarehouseManagerImpl_SpringTx implements WarehouseManagerService {
    private ProductDao productDao;

    private PlatformTransactionManager springTransactionManager;

    public WarehouseManagerImpl_SpringTx(ProductDao productDao, PlatformTransactionManager springTransactionManager) {
        this.productDao = productDao;
        this.springTransactionManager = springTransactionManager;
    }

    @Override
    public int prepareProductsForShipment(Order order, Customer customer) throws OrderWarhouseException {
        TransactionStatus transactionStatus = null;
        try {
            DefaultTransactionDefinition transactionDefinition = new DefaultTransactionDefinition();

            transactionStatus = springTransactionManager.getTransaction(transactionDefinition);

            if (!productDao.checkIfProductsAvailable(order)) {
                throw new OrderWarhouseException("Not all avail.");
            }
            int totalPrice = productDao.calculateProductsTotalPrice(order);
            productDao.logLocationAndTotalPrice(customer, totalPrice);
            if (totalPrice > customer.getBalance()) {
                throw new OrderWarhouseException("Not enough money");
            }

            productDao.decreaseProductsAmounts(order);

            springTransactionManager.commit(transactionStatus);
            return totalPrice;
        } catch (Exception e) {
            try {
                springTransactionManager.rollback(transactionStatus);
            } catch (Exception e1) {
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
