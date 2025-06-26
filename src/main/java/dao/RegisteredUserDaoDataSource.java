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

import bean.RegisteredUser;


	public class RegisteredUserDaoDataSource implements IBeanDao<RegisteredUser> {

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

		private static final String TABLE_NAME = "registereduser";

		@Override
		public synchronized void doSave(RegisteredUser user) throws SQLException {

			Connection connection = null;
			PreparedStatement preparedStatement = null;

			String insertSQL = "INSERT INTO " + RegisteredUserDaoDataSource.TABLE_NAME
					+ " (USERNAME, PASSWORD, NAME, LASTNAME, EMAIL, TELEPHONENUMBER, IDUSER) VALUES (?, ?, ?, ?, ?, ?, ?)";

			try {
				connection = ds.getConnection();
				preparedStatement = connection.prepareStatement(insertSQL);
	            preparedStatement.setString(1, user.getUsername());
				preparedStatement.setString(2, user.getPassword());
				preparedStatement.setString(3, user.getName());
				preparedStatement.setString(4, user.getLastName());
				preparedStatement.setString(5, user.getEmail());
				preparedStatement.setString(6, user.getTelephone());
				preparedStatement.setInt(7, user.getId());
			

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
		public synchronized RegisteredUser doRetrieveByKey(Object o_username) throws SQLException {
			Connection connection = null;
			PreparedStatement preparedStatement = null;
			String username=(String)o_username;

			RegisteredUser bean = new RegisteredUser();

			String selectSQL = "SELECT * FROM " + RegisteredUserDaoDataSource.TABLE_NAME + " WHERE USERNAME = ?";

			try {
				connection = ds.getConnection();
				preparedStatement = connection.prepareStatement(selectSQL);
				preparedStatement.setString(1, username);

				ResultSet rs = preparedStatement.executeQuery();

				while (rs.next()) {
					bean.setUsername(rs.getString("USERNAME"));
					bean.setPassword(rs.getString("PASSWORD"));
					bean.setName(rs.getString("NAME"));
					bean.setLastName(rs.getString("LASTNAME"));
					bean.setEmail(rs.getString("EMAIL"));
					bean.setTelephone(rs.getString("TELEPHONE"));
					bean.setId(rs.getInt("IDUSER"));
			
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
		public synchronized boolean doDelete(Object o_username) throws SQLException {
			Connection connection = null;
			PreparedStatement preparedStatement = null;
			String username=(String)o_username;

			int result = 0;

			String deleteSQL = "DELETE FROM " + RegisteredUserDaoDataSource.TABLE_NAME + " WHERE USERNAME = ?";

			try {
				connection = ds.getConnection();
				preparedStatement = connection.prepareStatement(deleteSQL);
				preparedStatement.setString(1, username);

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
		public synchronized Collection<RegisteredUser> doRetrieveAll(String order) throws SQLException {
			Connection connection = null;
			PreparedStatement preparedStatement = null;

			Collection<RegisteredUser> users = new LinkedList<RegisteredUser>();

			String selectSQL = "SELECT * FROM " + RegisteredUserDaoDataSource.TABLE_NAME;

			if (order != null && !order.equals("")) {
				selectSQL += " ORDER BY " + order;
			}

			try {
				connection = ds.getConnection();
				preparedStatement = connection.prepareStatement(selectSQL);

				ResultSet rs = preparedStatement.executeQuery();

				while (rs.next()) {
					RegisteredUser bean = new RegisteredUser();

					bean.setUsername(rs.getString("USERNAME"));
					bean.setPassword(rs.getString("PASSWORD"));
					bean.setName(rs.getString("NAME"));
					bean.setLastName(rs.getString("LASTNAME"));
					bean.setEmail(rs.getString("EMAIL"));
					bean.setTelephone(rs.getString("TELEPHONE"));
					bean.setId(rs.getInt("IDUSER"));
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


	}



