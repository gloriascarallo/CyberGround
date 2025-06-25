package beans;


	import java.sql.Connection;
	import java.sql.PreparedStatement;
	import java.sql.ResultSet;
	import java.sql.SQLException;
	import java.util.Collection;
	import java.util.LinkedList;
	import javax.naming.Context;
	import javax.naming.InitialContext;
	import javax.naming.NamingException;
	import javax.sql.DataSource;

	public class Product_situatedin_cartDaoDataSource implements IBeanDao<Product_situatedin_cart> {

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

		private static final String TABLE_NAME = "product_situatedin_cart";

		@Override
		public synchronized void doSave(Product_situatedin_cart product_situatedin_cart) throws SQLException {

			Connection connection = null;
			PreparedStatement preparedStatement = null;

			String insertSQL = "INSERT INTO " + Product_situatedin_cartDaoDataSource.TABLE_NAME
					+ " (ID_SITUATEDIN, IDCART, IDPRODUCT, DATEADDED) VALUES (?, ?, ?, ?)";

			try {
				connection = ds.getConnection();
				preparedStatement = connection.prepareStatement(insertSQL);
				preparedStatement.setInt(1, product_situatedin_cart.getId_SituatedIn());
				preparedStatement.setInt(2, product_situatedin_cart.getIdCart());
				preparedStatement.setInt(3, product_situatedin_cart.getIdProduct());
				preparedStatement.setDate(4, product_situatedin_cart.getDateAdded());
				
				

				preparedStatement.executeUpdate();

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
		public synchronized Product_situatedin_cart doRetrieveByKey(Object o_id) throws SQLException {
			Connection connection = null;
			PreparedStatement preparedStatement = null;
	        int id=(Integer)o_id;
	        
			Product_situatedin_cart bean = new Product_situatedin_cart();

			String selectSQL = "SELECT * FROM " + Product_situatedin_cartDaoDataSource.TABLE_NAME + " WHERE ID_SITUATEDIN = ?";

			try {
				connection = ds.getConnection();
				preparedStatement = connection.prepareStatement(selectSQL);
				preparedStatement.setInt(1, id);

				ResultSet rs = preparedStatement.executeQuery();

				while (rs.next()) {
					bean.setId_SituatedIn(rs.getInt("ID_SITUATEDIN"));
					bean.setIdCart(rs.getInt("IDCART"));
					bean.setIdProduct(rs.getInt("IDPRODUCT"));
					bean.setDateAdded(rs.getDate("DATEADDED"));
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

			String deleteSQL = "DELETE FROM " + Product_situatedin_cartDaoDataSource.TABLE_NAME + " WHERE ID_SITUATEDIN = ?";

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
		public synchronized Collection<Product_situatedin_cart> doRetrieveAll(String order) throws SQLException {
			Connection connection = null;
			PreparedStatement preparedStatement = null;

			Collection<Product_situatedin_cart> products_situatedin_carts = new LinkedList<Product_situatedin_cart>();

			String selectSQL = "SELECT * FROM " + Product_situatedin_cartDaoDataSource.TABLE_NAME;

			if (order != null && !order.equals("")) {
				selectSQL += " ORDER BY " + order;
			}

			try {
				connection = ds.getConnection();
				preparedStatement = connection.prepareStatement(selectSQL);

				ResultSet rs = preparedStatement.executeQuery();

				while (rs.next()) {
					Product_situatedin_cart bean = new Product_situatedin_cart();

					bean.setId_SituatedIn(rs.getInt("ID_SITUATEDIN"));
					bean.setIdCart(rs.getInt("IDCART"));
					bean.setIdProduct(rs.getInt("IDPRODUCT"));
					bean.setDateAdded(rs.getDate("DATEADDED"));
					products_situatedin_carts.add(bean);
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
			return products_situatedin_carts;
		}


}
