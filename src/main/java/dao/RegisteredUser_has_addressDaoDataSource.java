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

import model.RegisteredUser_has_addressBean;

	public class RegisteredUser_has_addressDaoDataSource implements IBeanDao<RegisteredUser_has_addressBean> {

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

		private static final String TABLE_NAME = "REGISTEREDUSER_HAS_ADDRESS";

		@Override
		public synchronized void doSave(RegisteredUser_has_addressBean registereduser_has_address) throws SQLException {

			Connection connection = null;
			PreparedStatement preparedStatement = null;

			String insertSQL = "INSERT INTO " + RegisteredUser_has_addressDaoDataSource.TABLE_NAME
					+ " (IDREGISTEREDUSER, NAMEADDRESS) VALUES (?, ?)";

			try {
				connection = ds.getConnection();
				preparedStatement = connection.prepareStatement(insertSQL, Statement.RETURN_GENERATED_KEYS);
				
				preparedStatement.setInt(1, registereduser_has_address.getIdRegisteredUser());
				preparedStatement.setString(2, registereduser_has_address.getNameAddress());
				preparedStatement.executeUpdate();
				
ResultSet rs=preparedStatement.getGeneratedKeys();
				
				if(rs.next()) {
					registereduser_has_address.setId_has_address(rs.getInt(1));
					
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
		public synchronized RegisteredUser_has_addressBean doRetrieveByKey(Object o_id) throws SQLException {
			Connection connection = null;
			PreparedStatement preparedStatement = null;
	        int id=(Integer)o_id;
	        
			RegisteredUser_has_addressBean bean = null;

			String selectSQL = "SELECT * FROM " + RegisteredUser_has_addressDaoDataSource.TABLE_NAME + " WHERE ID_HAS_ADDRESS = ?";

			try {
				connection = ds.getConnection();
				preparedStatement = connection.prepareStatement(selectSQL);
				preparedStatement.setInt(1, id);

				ResultSet rs = preparedStatement.executeQuery();

				while (rs.next()) {
					bean=new RegisteredUser_has_addressBean();
					bean.setId_has_address(rs.getInt("ID_HAS_ADDRESS"));
					bean.setIdRegisteredUser(rs.getInt("IDREGISTEREDUSER"));
					bean.setNameAddress(rs.getString("NAMEADDRESS"));
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
		
		public synchronized ArrayList<RegisteredUser_has_addressBean> doRetrieveByIdRegisteredUser(int id) throws SQLException {
			Connection connection = null;
			PreparedStatement preparedStatement = null;
	        
	        
			ArrayList<RegisteredUser_has_addressBean> user_addresses = new ArrayList<RegisteredUser_has_addressBean>();

			String selectSQL = "SELECT * FROM " + RegisteredUser_has_addressDaoDataSource.TABLE_NAME + " WHERE IDREGISTEREDUSER = ?";

			try {
				connection = ds.getConnection();
				preparedStatement = connection.prepareStatement(selectSQL);
				preparedStatement.setInt(1, id);

				ResultSet rs = preparedStatement.executeQuery();

				while (rs.next()) {
					RegisteredUser_has_addressBean bean=new RegisteredUser_has_addressBean();
					bean.setId_has_address(rs.getInt("ID_HAS_ADDRESS"));
					bean.setIdRegisteredUser(rs.getInt("IDREGISTEREDUSER"));
					bean.setNameAddress(rs.getString("NAMEADDRESS"));
					user_addresses.add(bean);
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
			return user_addresses;
		}


		@Override
		public synchronized boolean doDelete(Object o_id) throws SQLException {
			Connection connection = null;
			PreparedStatement preparedStatement = null;
	        int id=(Integer)o_id;
	        
			int result = 0;

			String deleteSQL = "DELETE FROM " + RegisteredUser_has_addressDaoDataSource.TABLE_NAME + " WHERE ID_HAS_ADDRESS = ?";

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
		public synchronized Collection<RegisteredUser_has_addressBean> doRetrieveAll(String order) throws SQLException {
			Connection connection = null;
			PreparedStatement preparedStatement = null;

			Collection<RegisteredUser_has_addressBean> registeredusers_have_addresses = new LinkedList<RegisteredUser_has_addressBean>();

			String selectSQL = "SELECT * FROM " + RegisteredUser_has_addressDaoDataSource.TABLE_NAME;

			if (order != null && !order.equals("")) {
				selectSQL += " ORDER BY " + order;
			}

			try {
				connection = ds.getConnection();
				preparedStatement = connection.prepareStatement(selectSQL);

				ResultSet rs = preparedStatement.executeQuery();

				while (rs.next()) {
					RegisteredUser_has_addressBean bean = new RegisteredUser_has_addressBean();

					bean.setId_has_address(rs.getInt("ID_HAS_ADDRESS"));
					bean.setIdRegisteredUser(rs.getInt("IDREGISTEREDUSER"));
					bean.setNameAddress(rs.getString("NAMEADDRESS"));
					registeredusers_have_addresses.add(bean);
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
			return registeredusers_have_addresses;
		}

		public boolean existsByUserAndAddress(int userId, String address) throws SQLException {
			Connection connection = null;
			PreparedStatement preparedStatement = null;
			ResultSet rs=null;
		        String selectSQL = "SELECT 1 FROM " + RegisteredUser_has_addressDaoDataSource.TABLE_NAME +" WHERE IDREGISTEREDUSER = ? AND NAMEADDRESS = ?";
		        try {
					connection = ds.getConnection();
					preparedStatement = connection.prepareStatement(selectSQL);
					 preparedStatement.setInt(1, userId);
			            preparedStatement.setString(2, address);
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
