package dao;



	import java.sql.Connection;
	import java.sql.PreparedStatement;
	import java.sql.ResultSet;
	import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;
	import java.util.LinkedList;
	import javax.naming.Context;
	import javax.naming.InitialContext;
	import javax.naming.NamingException;
	import javax.sql.DataSource;
import bean.Product_in_orderBean;

	public class Product_in_orderDaoDataSource implements IBeanDao<Product_in_orderBean> {

		private static DataSource ds;

		static {
			try {
				Context initCtx = new InitialContext();
				Context envCtx = (Context) initCtx.lookup("java:comp/env");

				ds = (DataSource) envCtx.lookup("jdbc/storage");

			} catch (NamingException e) {
				System.out.println("Error:" + e.getMessage());
			}
		}

		private static final String TABLE_NAME = "PRODUCT_IN_ORDER";

		@Override
		public synchronized void doSave(Product_in_orderBean product_in_order) throws SQLException {

			Connection connection = null;
			PreparedStatement preparedStatement = null;

			String insertSQL = "INSERT INTO " + Product_in_orderDaoDataSource.TABLE_NAME
					+ " (IDORDER, IDPRODUCT, QUANTITY, PRICE) VALUES (?, ?, ?, ?)";

			try {
				connection = ds.getConnection();
				preparedStatement = connection.prepareStatement(insertSQL, Statement.RETURN_GENERATED_KEYS);
				preparedStatement.setInt(1, product_in_order.getIdOrder());
				preparedStatement.setInt(2, product_in_order.getProduct().getId());
				preparedStatement.setInt(3, product_in_order.getQuantity());
				preparedStatement.setDouble(4, product_in_order.getPrice());
				

				preparedStatement.executeUpdate();
                ResultSet rs=preparedStatement.getGeneratedKeys();
				
				if(rs.next()) {
					product_in_order.setId_product_in_order(rs.getInt(1));
					
				}
			} finally {
				try {
					if (preparedStatement != null)
						preparedStatement.close();
				} finally {
					if (connection != null)
						connection.close();
				}
			}
		}

		@Override
		public synchronized Product_in_orderBean doRetrieveByKey(Object o_id) throws SQLException {
			Connection connection = null;
			PreparedStatement preparedStatement = null;
	        int id=(Integer)o_id;
	        
			Product_in_orderBean bean = null;

			String selectSQL = "SELECT * FROM " + Product_in_orderDaoDataSource.TABLE_NAME + " WHERE ID_PRODUCT_IN_ORDER = ?";

			try {
				connection = ds.getConnection();
				preparedStatement = connection.prepareStatement(selectSQL);
				preparedStatement.setInt(1, id);

				ResultSet rs = preparedStatement.executeQuery();
				ProductDaoDataSource ds=new ProductDaoDataSource();
				while (rs.next()) {
					bean=new Product_in_orderBean();
					bean.setId_product_in_order(rs.getInt("ID_PRODUCT_IN_ORDER"));
					bean.setIdOrder(rs.getInt("IDORDER"));
					int idProduct=rs.getInt("IDPRODUCT");
					bean.setProduct(ds.doRetrieveByKey(idProduct));
					bean.setQuantity(rs.getInt("QUANTITY"));
					bean.setPrice(rs.getDouble("PRICE"));
			}

			} finally {
				try {
					if (preparedStatement != null)
						preparedStatement.close();
				} finally {
					if (connection != null)
						connection.close();
				}
			}
			return bean;
		}

		@Override
		public synchronized boolean doDelete(Object o_id) throws SQLException {
			Connection connection = null;
			PreparedStatement preparedStatement = null;
	        int id=(Integer)o_id;
	        
			int result = 0;

			String deleteSQL = "DELETE FROM " + Product_in_orderDaoDataSource.TABLE_NAME + " WHERE ID_PRODUCT_IN_ORDER = ?";

			try {
				connection = ds.getConnection();
				preparedStatement = connection.prepareStatement(deleteSQL);
				preparedStatement.setInt(1, id);

				result = preparedStatement.executeUpdate();

			} finally {
				try {
					if (preparedStatement != null)
						preparedStatement.close();
				} finally {
					if (connection != null)
						connection.close();
				}
			}
			return (result != 0);
		}

		@Override
		public synchronized Collection<Product_in_orderBean> doRetrieveAll(String order) throws SQLException {
			Connection connection = null;
			PreparedStatement preparedStatement = null;

			Collection<Product_in_orderBean> products_in_order = new LinkedList<Product_in_orderBean>();

			String selectSQL = "SELECT * FROM " + Product_in_orderDaoDataSource.TABLE_NAME;

			if (order != null && !order.equals("")) {
				selectSQL += " ORDER BY " + order;
			}

			try {
				connection = ds.getConnection();
				preparedStatement = connection.prepareStatement(selectSQL);

				ResultSet rs = preparedStatement.executeQuery();
				ProductDaoDataSource ds=new ProductDaoDataSource();
				while (rs.next()) {
					Product_in_orderBean bean = new Product_in_orderBean();

					bean.setId_product_in_order(rs.getInt("ID_PRODUCT_IN_ORDER"));
					bean.setIdOrder(rs.getInt("IDORDER"));
					int idProduct=rs.getInt("IDPRODUCT");
					bean.setProduct(ds.doRetrieveByKey(idProduct));
					bean.setQuantity(rs.getInt("QUANTITY"));
					bean.setPrice(rs.getDouble("PRICE"));
					products_in_order.add(bean);
				}

			} finally {
				try {
					if (preparedStatement != null)
						preparedStatement.close();
				} finally {
					if (connection != null)
						connection.close();
				}
			}
			return products_in_order;
		}
		
		
		public synchronized ArrayList<Product_in_orderBean> doRetrieveByIdOrder(int id) throws SQLException {
			Connection connection = null;
			PreparedStatement preparedStatement = null;

			ArrayList<Product_in_orderBean> order=new ArrayList<Product_in_orderBean>();

			String selectSQL = "SELECT * FROM " + Product_in_orderDaoDataSource.TABLE_NAME + " WHERE IDORDER = ?";
            

			try {
				connection = ds.getConnection();
				preparedStatement = connection.prepareStatement(selectSQL);
				preparedStatement.setInt(1, id);
				ResultSet rs = preparedStatement.executeQuery();
				ProductDaoDataSource ds=new ProductDaoDataSource();
				while (rs.next()) {
					Product_in_orderBean bean = new Product_in_orderBean();
					
					bean.setId_product_in_order(rs.getInt("ID_PRODUCT_IN_ORDER"));
					bean.setIdOrder(rs.getInt("IDORDER"));
					int idProduct=rs.getInt("IDPRODUCT");
					bean.setProduct(ds.doRetrieveByKey(idProduct));
					bean.setQuantity(rs.getInt("QUANTITY"));
					bean.setPrice(rs.getDouble("PRICE"));
					
					order.add(bean);
				}

			} finally {
				try {
					if (preparedStatement != null)
						preparedStatement.close();
				} finally {
					if (connection != null)
						connection.close();
				}
			}
			return order;
		}
		
	public Product_in_orderDaoDataSource() {
		// TODO Auto-generated constructor stub
	}

}
