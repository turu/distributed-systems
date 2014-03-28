package agh.sr.dtransactions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.sql.DataSource;

import agh.sr.dtransactions.orderprocessing.logic.*;
import org.springframework.jdbc.datasource.TransactionAwareDataSourceProxy;
import org.springframework.transaction.PlatformTransactionManager;

import agh.sr.dtransactions.orderprocessing.dao.ProductDao;
import agh.sr.dtransactions.test.ProductDao_ExOnDecrease;
import agh.sr.dtransactions.test.ProductDao_ExOnLogging;

public class RunnerWarehouseSpring {

	private WarehouseManagerService warehouseService;

	private DataSource productDS;

	private PlatformTransactionManager springTransactionManager;

	private void printBreakLine() {
		System.out
				.println("-----------------------------------------------------------------------");
	}

	public void setupLocalTransactionManager_Badly() throws Exception {
		productDS = DBTxConfiguration
				.createPooledDataSource(DBTxConfiguration.PRODUCT_DB_NAME);
		springTransactionManager = DBTxConfiguration
				.getSpringTransactionManager_WithJDBC(productDS);
	}

	public void setupLocalTransactionManager() throws Exception {
		productDS = DBTxConfiguration
				.createPooledDataSource(DBTxConfiguration.PRODUCT_DB_NAME);
		productDS = new TransactionAwareDataSourceProxy(productDS);
		springTransactionManager = DBTxConfiguration
				.getSpringTransactionManager_WithJDBC(productDS);
	}

	public void setupGlobalTransactionManager() throws Exception {
		productDS = DBTxConfiguration
				.createXaDataSource(DBTxConfiguration.PRODUCT_DB_NAME);
		// !!!! Never ever do something like this because if will break
		// smartness of the native JTA connection pooling smartness !!!!!
		// productDS = new TransactionAwareDataSourceProxy(productDS);
		springTransactionManager = DBTxConfiguration
				.getSpringTransactionManager_WithJTA();
	}

	public void configureSpringTx() throws Throwable {
//		warehouseService = new WarehouseManagerImpl_SpringTx(new ProductDao(
//				productDS), springTransactionManager);
	}

	public void configureSpringMultipleTx() throws Throwable {
		warehouseService = new WarehouseManagerImpl_SpringMultipleTx(
				new ProductDao(productDS), springTransactionManager);
	}

	private Customer getCustomer() {
		return new Customer(6, "Jaroslaw", "Wolski", "Ruczaj", 100000);
	}

	private Order getOrder() {
		List<OrderItem> orderItems = Arrays.asList(new OrderItem(1, 3),
				new OrderItem(2, 4), new OrderItem(3, 5));
		return new Order("OrderID", 1, orderItems);
	}

	public void testCommit() throws Throwable {
		Utils.printProductDB(productDS);
		printBreakLine();
		try {
			warehouseService.setProductDao(new ProductDao(productDS));
			warehouseService.prepareProductsForShipment(getOrder(),
					getCustomer());
		} catch (Exception e) {
			e.printStackTrace();
		}
		Utils.printProductDB(productDS);
	}

	public void testExOnDecrease() throws Throwable {
		Utils.printProductDB(productDS);
		printBreakLine();
		try {
			warehouseService.setProductDao(new ProductDao_ExOnDecrease(
					productDS));
			warehouseService.prepareProductsForShipment(getOrder(),
					getCustomer());
		} catch (Exception e) {
			e.printStackTrace();
		}
		Utils.printProductDB(productDS);
	}

	public void testExOnLogging() throws Throwable {
		Utils.printProductDB(productDS);
		printBreakLine();
		try {
			warehouseService
					.setProductDao(new ProductDao_ExOnLogging(productDS));
			warehouseService.prepareProductsForShipment(getOrder(),
					getCustomer());
		} catch (Exception e) {
			e.printStackTrace();
		}
		Utils.printProductDB(productDS);
	}

	public class SpringTx {

		public void makeCommit() throws Throwable {
			configureSpringTx();
			testCommit();
		}

		public void makeExOnDecrease() throws Throwable {
			configureSpringTx();
			testExOnDecrease();
		}

		public void makeExOnLogging() throws Throwable {
			configureSpringTx();
			testExOnLogging();
		}
	}

	public class SpringMultipleTx {

		public void makeCommit() throws Throwable {
			configureSpringMultipleTx();
			testCommit();
		}

		public void makeExOnDecrease() throws Throwable {
			configureSpringMultipleTx();
			testExOnDecrease();
		}

		public void makeExOnLogging() throws Throwable {
			configureSpringMultipleTx();
			testExOnLogging();
		}
	}

	public void testSpring_Local_Badly() throws Throwable {
		setupLocalTransactionManager_Badly();

		new SpringTx().makeCommit(); /* ok */
		new SpringTx().makeExOnDecrease(); /* logging should not be
		// persistent and it is! Why? */
	}

	public void testSpring_Local() throws Throwable {
		setupLocalTransactionManager();

//		new SpringTx().makeCommit(); /* ok */
//		new SpringTx().makeExOnDecrease(); /* will rollback all */
//		new SpringTx().makeExOnLogging(); /* will rollback all */

		new SpringMultipleTx().makeCommit(); /* ok */
		new SpringMultipleTx().makeExOnDecrease(); /* will rollback all
		beside logging */
		new SpringMultipleTx().makeExOnLogging(); /* will rollback only audit
		// */
	}

	public void testSpring_Global() throws Throwable {
		setupGlobalTransactionManager();

//		new SpringTx().makeCommit(); /* ok */
//		new SpringTx().makeExOnDecrease(); /* will rollback all */
//		new SpringTx().makeExOnLogging(); /* will rollback all */

		new SpringMultipleTx().makeCommit(); /* ok */
		new SpringMultipleTx().makeExOnDecrease(); /* will rollback all
		beside logging */
		new SpringMultipleTx().makeExOnLogging(); /* will rollback only audit
		// */
	}

	public void test() throws Throwable {
//		testSpring_Local_Badly();
//		testSpring_Local();
		testSpring_Global();
	}

	public void runSimultaneousThreads(int workerThreadsAmount,
			final int interationsInThread) throws Throwable {
		Runnable doTransactionTask = new Runnable() {
			@Override
			public void run() {
				try {
					for (int i = 0; i < interationsInThread; i++) {
						warehouseService.prepareProductsForShipment(getOrder(),
								getCustomer());
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		};
		List<Thread> workerThreads = new ArrayList<Thread>();
		for (int i = 0; i < workerThreadsAmount; i++) {
			Thread t = new Thread(doTransactionTask);
			workerThreads.add(t);
			t.start();
		}
		for (Thread t : workerThreads) {
			t.join();
		}
	}
	
	public void measureLocal(int workerThreadsAmount, int interationsInThread)
			throws Throwable {
		setupLocalTransactionManager();
		configureSpringMultipleTx();
		long start = System.currentTimeMillis();
		runSimultaneousThreads(workerThreadsAmount, interationsInThread);
		long stop = System.currentTimeMillis();
		System.out.println(stop - start);
	}
	
	public void measureGlobal(int workerThreadsAmount, int interationsInThread)
			throws Throwable {
		setupGlobalTransactionManager();
		configureSpringMultipleTx();
		long start = System.currentTimeMillis();
		runSimultaneousThreads(workerThreadsAmount, interationsInThread);
		long stop = System.currentTimeMillis();
		System.out.println(stop - start);
	}		

	public static void main(String[] args) throws Throwable {
		RunnerWarehouseSpring runner = new RunnerWarehouseSpring();
		runner.test();
//		runner.measureLocal(10, 500);
		runner.measureGlobal(10, 500);
	}
}
