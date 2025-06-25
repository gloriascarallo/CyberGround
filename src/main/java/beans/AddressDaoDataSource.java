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


	public class AddressDaoDataSource implements IBeanDao<Address> {

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

		private static final String TABLE_NAME = "address";

		@Override
		public synchronized void doSave(Address address) throws SQLException {

			Connection connection = null;
			PreparedStatement preparedStatement = null;

			String insertSQL = "INSERT INTO " + AddressDaoDataSource.TABLE_NAME
					+ " (NAME) VALUES (?)";

			try {
				connection = ds.getConnection();
				preparedStatement = connection.prepareStatement(insertSQL);
	            preparedStatement.setString(1, address.getName());
				

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
		public synchronized Address doRetrieveByKey(Object o_name) throws SQLException {
			Connection connection = null;
			PreparedStatement preparedStatement = null;
			String name=(String)o_name;

			Address bean = new Address();

			String selectSQL = "SELECT * FROM " + AddressDaoDataSource.TABLE_NAME + " WHERE NAME = ?";

			try {
				connection = ds.getConnection();
				preparedStatement = connection.prepareStatement(selectSQL);
				preparedStatement.setString(1, name);

				ResultSet rs = preparedStatement.executeQuery();

				while (rs.next()) {
					bean.setName(rs.getString("NAME"));
					
			
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
		public synchronized boolean doDelete(Object o_name) throws SQLException {
			Connection connection = null;
			PreparedStatement preparedStatement = null;
			String name=(String)o_name;

			int result = 0;

			String deleteSQL = "DELETE FROM " + AddressDaoDataSource.TABLE_NAME + " WHERE NAME = ?";

			try {
				connection = ds.getConnection();
				preparedStatement = connection.prepareStatement(deleteSQL);
				preparedStatement.setString(1, name);

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
		public synchronized Collection<Address> doRetrieveAll(String order) throws SQLException {
			Connection connection = null;
			PreparedStatement preparedStatement = null;

			Collection<Address> addresses = new LinkedList<Address>();

			String selectSQL = "SELECT * FROM " + AddressDaoDataSource.TABLE_NAME;

			if (order != null && !order.equals("")) {
				selectSQL += " ORDER BY " + order;
			}

			try {
				connection = ds.getConnection();
				preparedStatement = connection.prepareStatement(selectSQL);

				ResultSet rs = preparedStatement.executeQuery();

				while (rs.next()) {
					Address bean = new Address();

					bean.setName(rs.getString("NAME"));
					addresses.add(bean);
					
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
			return addresses;
		}


	}



