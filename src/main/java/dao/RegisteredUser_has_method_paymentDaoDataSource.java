package dao;

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

import bean.RegisteredUser_has_method_paymentBean;

	public class RegisteredUser_has_method_paymentDaoDataSource implements IBeanDao<RegisteredUser_has_method_paymentBean> {

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

		private static final String TABLE_NAME = "registereduser_has_method_payment";

		@Override
		public synchronized void doSave(RegisteredUser_has_method_paymentBean registereduser_has_method_payment) throws SQLException {

			Connection connection = null;
			PreparedStatement preparedStatement = null;

			String insertSQL = "INSERT INTO " + RegisteredUser_has_method_paymentDaoDataSource.TABLE_NAME
					+ " (ID_HAS_METHOD_PAYMENT, USERNAMEREGISTEREDUSER, PANMETHODPAYMENT, EXPIRATIONDATEMETHODPAYMENT, CVCMETHODPAYMENT) VALUES (?, ?, ?, ?, ?)";

			try {
				connection = ds.getConnection();
				preparedStatement = connection.prepareStatement(insertSQL);
				preparedStatement.setInt(1, registereduser_has_method_payment.getId_has_method_payment());
				preparedStatement.setString(2, registereduser_has_method_payment.getUsernameRegisteredUser());
				preparedStatement.setString(3, registereduser_has_method_payment.getPan());
				preparedStatement.setString(4, registereduser_has_method_payment.getExpirationDate());
				preparedStatement.setString(5, registereduser_has_method_payment.getCvc());
				

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
		public synchronized RegisteredUser_has_method_paymentBean doRetrieveByKey(Object o_id) throws SQLException {
			Connection connection = null;
			PreparedStatement preparedStatement = null;
	        int id=(Integer)o_id;
	        
			RegisteredUser_has_method_paymentBean bean = new RegisteredUser_has_method_paymentBean();

			String selectSQL = "SELECT * FROM " + RegisteredUser_has_method_paymentDaoDataSource.TABLE_NAME + " WHERE ID_HAS_METHOD_PAYMENT = ?";

			try {
				connection = ds.getConnection();
				preparedStatement = connection.prepareStatement(selectSQL);
				preparedStatement.setInt(1, id);

				ResultSet rs = preparedStatement.executeQuery();

				while (rs.next()) {
					bean.setId_has_method_payment(rs.getInt("ID_HAS_METHOD_PAYMENT"));
					bean.setUsernameRegisteredUser(rs.getString("USERNAMEREGISTEREDUSER"));
					bean.setPan(rs.getString("PANMETHODPAYMENT"));
					bean.setExpirationDate(rs.getString("EXPIRATIONDATEMETHODPAYMENT"));
					bean.setCvc(rs.getString("CVCMETHODPAYMENT"));
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

			String deleteSQL = "DELETE FROM " + RegisteredUser_has_method_paymentDaoDataSource.TABLE_NAME + " WHERE ID_HAS_METHOD_PAYMENT = ?";

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
		public synchronized Collection<RegisteredUser_has_method_paymentBean> doRetrieveAll(String order) throws SQLException {
			Connection connection = null;
			PreparedStatement preparedStatement = null;

			Collection<RegisteredUser_has_method_paymentBean> registeredusers_have_methods_payment = new LinkedList<RegisteredUser_has_method_paymentBean>();

			String selectSQL = "SELECT * FROM " + RegisteredUser_has_method_paymentDaoDataSource.TABLE_NAME;

			if (order != null && !order.equals("")) {
				selectSQL += " ORDER BY " + order;
			}

			try {
				connection = ds.getConnection();
				preparedStatement = connection.prepareStatement(selectSQL);

				ResultSet rs = preparedStatement.executeQuery();

				while (rs.next()) {
					RegisteredUser_has_method_paymentBean bean = new RegisteredUser_has_method_paymentBean();

					bean.setId_has_method_payment(rs.getInt("ID_HAS_METHOD_PAYMENT"));
					bean.setUsernameRegisteredUser(rs.getString("USERNAMEREGISTEREDUSER"));
					bean.setPan(rs.getString("PANMETHODPAYMENT"));
					bean.setExpirationDate(rs.getString("EXPIRATIONDATEMETHODPAYMENT"));
					bean.setCvc(rs.getString("CVCMETHODPAYMENT"));
					registeredusers_have_methods_payment.add(bean);
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
			return registeredusers_have_methods_payment;
		}


}
