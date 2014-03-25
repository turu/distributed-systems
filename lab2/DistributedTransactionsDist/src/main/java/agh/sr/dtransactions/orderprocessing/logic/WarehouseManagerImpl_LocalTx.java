package agh.sr.dtransactions.orderprocessing.logic;

import agh.sr.dtransactions.orderprocessing.dao.connpassing.ProductDao_ConnPassing;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * Author: Piotr Turek
 */
public class WarehouseManagerImpl_LocalTx implements WarehouseManagerService_ConnPassing {
    private DataSource productDS;

    private ProductDao_ConnPassing productDao;

    public WarehouseManagerImpl_LocalTx(DataSource productDS,
                                      ProductDao_ConnPassing productDao) {
        this.productDS = productDS;
        this.productDao = productDao;
    }

    public int prepareProductsForShipment(Order order, Customer customer)
            throws OrderWarhouseException {
        Connection conn = null;
        try {
            conn = productDS.getConnection();
            conn.setAutoCommit(false);

            if (!productDao.checkIfProductsAvailable(order, conn)) {
                throw new OrderWarhouseException(
                        "Not all ordered products are available");
            }
            int totalPrice = productDao
                    .calculateProductsTotalPrice(order, conn);
            productDao.logLocationAndTotalPrice(customer, totalPrice, conn);
            if (totalPrice > customer.getBalance()) {
                throw new OrderWarhouseException(
                        "Customer does not have enough money");
            }
            productDao.decreaseProductsAmounts(order, conn);

            conn.commit();
            return totalPrice;
        } catch (Exception e) {
            if (conn != null) {
                try {
                    conn.rollback();
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }
            }
            throw new OrderWarhouseException(e);
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    public void setProductDao(ProductDao_ConnPassing productDao) {
        this.productDao = productDao;
    }
}
