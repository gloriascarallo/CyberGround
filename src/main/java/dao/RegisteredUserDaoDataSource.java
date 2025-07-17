package dao;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import model.RegisteredUserBean;


	public class RegisteredUserDaoDataSource implements IBeanDao<RegisteredUserBean> {

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

		private static final String TABLE_NAME = "REGISTEREDUSER";

		@Override
		public synchronized void doSave(RegisteredUserBean user) throws SQLException {

			Connection connection = null;
			PreparedStatement preparedStatement = null;

			String insertSQL = "INSERT INTO " + RegisteredUserDaoDataSource.TABLE_NAME
					+ " (IDUSER, USERNAME, PASSWORD, NAME, LASTNAME, EMAIL, TELEPHONENUMBER) VALUES (?, ?, ?, ?, ?, ?, ?)";

			try {
				connection = ds.getConnection();
				preparedStatement = connection.prepareStatement(insertSQL);
				preparedStatement.setInt(1, user.getId());
	            preparedStatement.setString(2, user.getUsername());
				preparedStatement.setString(3, user.getPassword());
				preparedStatement.setString(4, user.getName());
				preparedStatement.setString(5, user.getLastName());
				preparedStatement.setString(6, user.getEmail());
				preparedStatement.setString(7, user.getTelephone());
				
			

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
		public synchronized RegisteredUserBean doRetrieveByKey(Object o_id) throws SQLException {
			Connection connection = null;
			PreparedStatement preparedStatement = null;
		   int id=(Integer)o_id;

			RegisteredUserBean bean = null;

			String selectSQL = "SELECT * FROM " + RegisteredUserDaoDataSource.TABLE_NAME + " WHERE IDUSER = ?";

			try {
				connection = ds.getConnection();
				preparedStatement = connection.prepareStatement(selectSQL);
				preparedStatement.setInt(1, id);

				ResultSet rs = preparedStatement.executeQuery();

				while (rs.next()) {
					bean=new RegisteredUserBean();
					bean.setId(rs.getInt("IDUSER"));
					bean.setUsername(rs.getString("USERNAME"));
					bean.setPassword(rs.getString("PASSWORD"));
					bean.setName(rs.getString("NAME"));
					bean.setLastName(rs.getString("LASTNAME"));
					bean.setEmail(rs.getString("EMAIL"));
					bean.setTelephone(rs.getString("TELEPHONENUMBER"));
					
			
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

			String deleteSQL = "DELETE FROM " + RegisteredUserDaoDataSource.TABLE_NAME + " WHERE IDUSER = ?";

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
		public synchronized ArrayList<RegisteredUserBean> doRetrieveAll(String order) throws SQLException {
			Connection connection = null;
			PreparedStatement preparedStatement = null;

			ArrayList<RegisteredUserBean> users = new ArrayList<RegisteredUserBean>();

			String selectSQL = "SELECT * FROM " + RegisteredUserDaoDataSource.TABLE_NAME;

			if (order != null && !order.equals("")) {
				selectSQL += " ORDER BY " + order;
			}

			try {
				connection = ds.getConnection();
				preparedStatement = connection.prepareStatement(selectSQL);

				ResultSet rs = preparedStatement.executeQuery();

				while (rs.next()) {
					RegisteredUserBean bean = new RegisteredUserBean();
					bean.setId(rs.getInt("IDUSER"));
					bean.setUsername(rs.getString("USERNAME"));
					bean.setPassword(rs.getString("PASSWORD"));
					bean.setName(rs.getString("NAME"));
					bean.setLastName(rs.getString("LASTNAME"));
					bean.setEmail(rs.getString("EMAIL"));
					bean.setTelephone(rs.getString("TELEPHONENUMBER"));
					users.add(bean);
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
			return users;
		}

	
	public synchronized ArrayList<RegisteredUserBean> doRetrieveUsersByUsername(String o_username) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
	   String username=(String)o_username;

		ArrayList<RegisteredUserBean> users = new ArrayList<>();
		RegisteredUserBean bean=null;
		String selectSQL = "SELECT * FROM " + RegisteredUserDaoDataSource.TABLE_NAME + " WHERE USERNAME LIKE ?";

		try {
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			preparedStatement.setString(1, "%"+ username +"%");

			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				bean= new RegisteredUserBean();
				bean.setId(rs.getInt("IDUSER"));
				bean.setUsername(rs.getString("USERNAME"));
				bean.setPassword(rs.getString("PASSWORD"));
				bean.setName(rs.getString("NAME"));
				bean.setLastName(rs.getString("LASTNAME"));
				bean.setEmail(rs.getString("EMAIL"));
				bean.setTelephone(rs.getString("TELEPHONENUMBER"));
				users.add(bean);
		
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
		return users;
	}

	public synchronized RegisteredUserBean doRetrieveByUsername(String username) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
	   

		RegisteredUserBean bean = null;

		String selectSQL = "SELECT * FROM " + RegisteredUserDaoDataSource.TABLE_NAME + " WHERE USERNAME = ?";

		try {
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			preparedStatement.setString(1, username);

			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				bean=new RegisteredUserBean();
				bean.setId(rs.getInt("IDUSER"));
				bean.setUsername(rs.getString("USERNAME"));
				bean.setPassword(rs.getString("PASSWORD"));
				bean.setName(rs.getString("NAME"));
				bean.setLastName(rs.getString("LASTNAME"));
				bean.setEmail(rs.getString("EMAIL"));
				bean.setTelephone(rs.getString("TELEPHONENUMBER"));
				
		
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
	
	}



