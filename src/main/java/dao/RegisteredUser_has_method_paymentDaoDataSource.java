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
					+ " (IDREGISTEREDUSER, PANMETHODPAYMENT, EXPIRATIONDATEMETHODPAYMENT, CVCMETHODPAYMENT) VALUES (?, ?, ?, ?)";

			try {
				connection = ds.getConnection();
				preparedStatement = connection.prepareStatement(insertSQL, Statement.RETURN_GENERATED_KEYS);
				preparedStatement.setInt(1, registereduser_has_method_payment.getIdRegisteredUser());
				preparedStatement.setString(2, registereduser_has_method_payment.getPan());
				preparedStatement.setString(3, registereduser_has_method_payment.getExpirationDate());
				preparedStatement.setString(4, registereduser_has_method_payment.getCvc());
				preparedStatement.executeUpdate();
				
				ResultSet rs=preparedStatement.getGeneratedKeys();
				
				if(rs.next()) {
					registereduser_has_method_payment.setId_has_method_payment(rs.getInt(1));
					
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
		public synchronized RegisteredUser_has_method_paymentBean doRetrieveByKey(Object o_id) throws SQLException {
			Connection connection = null;
			PreparedStatement preparedStatement = null;
	        int id=(Integer)o_id;
	        
			RegisteredUser_has_method_paymentBean bean = null;

			String selectSQL = "SELECT * FROM " + RegisteredUser_has_method_paymentDaoDataSource.TABLE_NAME + " WHERE ID_HAS_METHOD_PAYMENT = ?";

			try {
				connection = ds.getConnection();
				preparedStatement = connection.prepareStatement(selectSQL);
				preparedStatement.setInt(1, id);

				ResultSet rs = preparedStatement.executeQuery();

				while (rs.next()) {
					bean=new RegisteredUser_has_method_paymentBean();
					bean.setId_has_method_payment(rs.getInt("ID_HAS_METHOD_PAYMENT"));
					bean.setIdRegisteredUser(rs.getInt("IDREGISTEREDUSER"));
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

		public synchronized ArrayList<RegisteredUser_has_method_paymentBean> doRetrieveByIdRegisteredUser(int id) throws SQLException {
			Connection connection = null;
			PreparedStatement preparedStatement = null;
	        
	        
			ArrayList<RegisteredUser_has_method_paymentBean> user_methods_payment = new ArrayList<RegisteredUser_has_method_paymentBean>();

			String selectSQL = "SELECT * FROM " + RegisteredUser_has_method_paymentDaoDataSource.TABLE_NAME + " WHERE IDREGISTEREDUSER = ?";

			try {
				connection = ds.getConnection();
				preparedStatement = connection.prepareStatement(selectSQL);
				preparedStatement.setInt(1, id);

				ResultSet rs = preparedStatement.executeQuery();

				while (rs.next()) {
					RegisteredUser_has_method_paymentBean bean=new RegisteredUser_has_method_paymentBean();
					bean.setId_has_method_payment(rs.getInt("ID_HAS_METHOD_PAYMENT"));
					bean.setIdRegisteredUser(rs.getInt("IDREGISTEREDUSER"));
					bean.setPan(rs.getString("PANMETHODPAYMENT"));
					bean.setExpirationDate(rs.getString("EXPIRATIONDATEMETHODPAYMENT"));
					bean.setCvc(rs.getString("CVCMETHODPAYMENT"));
					user_methods_payment.add(bean);
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
			return user_methods_payment;
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
					bean.setIdRegisteredUser(rs.getInt("IDREGISTEREDUSER"));
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
		
		public boolean existsByUserAndPanAndExpirationDateAndCvc(int userId, String pan, String expirationDate, String cvc) throws SQLException {
			Connection connection = null;
			PreparedStatement preparedStatement = null;
			ResultSet rs=null;
		        String selectSQL = "SELECT 1 FROM " + RegisteredUser_has_method_paymentDaoDataSource.TABLE_NAME +" WHERE IDREGISTEREDUSER = ? AND PAN = ? AND EXPIRATIONDATE = ? AND CVC = ?";
		        try {
					connection = ds.getConnection();
					preparedStatement = connection.prepareStatement(selectSQL);
					 preparedStatement.setInt(1, userId);
			            preparedStatement.setString(2, pan);
			            preparedStatement.setString(3, expirationDate);
			            preparedStatement.setString(4, cvc);
					rs = preparedStatement.executeQuery();
					return rs.next(); 
		        }
		        finally {
		        	
					try {
						if(rs!=null) {
							rs.close();
							
						}
						if (preparedStatement != null)
							preparedStatement.close();
					} finally {
						if (connection != null)
							connection.close();
					}
		        }
		            
		     
		}


}
