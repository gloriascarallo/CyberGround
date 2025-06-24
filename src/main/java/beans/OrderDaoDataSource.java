package beans;

	import java.sql.SQLException;
    import javax.naming.Context;
	import javax.naming.InitialContext;
	import javax.naming.NamingException;
	import javax.sql.DataSource;
	import java.sql.Connection;
	import java.sql.PreparedStatement;
	import java.sql.ResultSet;
	import java.util.Collection;
	import java.util.LinkedList;

	public class OrderDaoDataSource implements IBeanDao<Order> {

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

		private static final String TABLE_NAME = "order";

		@Override
		public synchronized void doSave(Order order) throws SQLException {

			Connection connection = null;
			PreparedStatement preparedStatement = null;

			String insertSQL = "INSERT INTO " + OrderDaoDataSource.TABLE_NAME
					+ " (ID_ORDER, ID, DATEPURCHASE, DATEDELIVERY, DATESHIPPING, IDCART) VALUES (?, ?, ?, ?, ?, ?)";

			try {
				connection = ds.getConnection();
				preparedStatement = connection.prepareStatement(insertSQL);
				preparedStatement.setInt(1, order.getId_Order());
				preparedStatement.setInt(2, order.getId());
				preparedStatement.setDate(3, order.getDatePurchase());
				preparedStatement.setDate(4, order.getDateDelivery());
				preparedStatement.setDate(5, order.getDateShipping());
				preparedStatement.setInt(6, order.getIdCart());
				
				

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
		public synchronized Order doRetrieveByKey(Object o_id) throws SQLException {
			Connection connection = null;
			PreparedStatement preparedStatement = null;
	        int id=(Integer)o_id;
	        
			Order bean = new Order();

			String selectSQL = "SELECT * FROM " + OrderDaoDataSource.TABLE_NAME + " WHERE ID_ORDER = ?";

			try {
				connection = ds.getConnection();
				preparedStatement = connection.prepareStatement(selectSQL);
				preparedStatement.setInt(1, id);

				ResultSet rs = preparedStatement.executeQuery();

				while (rs.next()) {
					bean.setId(rs.getInt("ID_ORDER"));
					bean.setId(rs.getInt("ID"));
					bean.setDatePurchase(rs.getDate("DATEPURCHASE"));
					bean.setDateDelivery(rs.getDate("DATEDELIVERY"));
					bean.setDateShipping(rs.getDate("DATESHIPPING"));
					bean.setIdCart(rs.getInt("IDCART"));
					
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

			String deleteSQL = "DELETE FROM " + OrderDaoDataSource.TABLE_NAME + " WHERE ID_ORDER = ?";

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
		public synchronized Collection<Order> doRetrieveAll(String order) throws SQLException {
			Connection connection = null;
			PreparedStatement preparedStatement = null;

			Collection<Order> orders = new LinkedList<Order>();

			String selectSQL = "SELECT * FROM " + OrderDaoDataSource.TABLE_NAME;

			if (order != null && !order.equals("")) {
				selectSQL += " ORDER BY " + order;
			}

			try {
				connection = ds.getConnection();
				preparedStatement = connection.prepareStatement(selectSQL);

				ResultSet rs = preparedStatement.executeQuery();

				while (rs.next()) {
					Order bean = new Order();
					bean.setId_Order(rs.getInt("ID_ORDER"));
					bean.setId(rs.getInt("ID"));
					bean.setDatePurchase(rs.getDate("DATEPURCHASE"));
					bean.setDateDelivery(rs.getDate("DATEDELIVERY"));
					bean.setDateShipping(rs.getDate("DATESHIPPING"));
					bean.setIdCart(rs.getInt("IDCART"));
					
					orders.add(bean);
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
			return orders;
		}



}