package dao;


	import java.sql.Connection;
	import java.sql.PreparedStatement;
	import java.sql.ResultSet;
	import java.sql.SQLException;
	import java.sql.Statement;
	import java.util.Collection;
	import java.util.LinkedList;
	import javax.naming.Context;
	import javax.naming.InitialContext;
	import javax.naming.NamingException;
	import javax.sql.DataSource;
import bean.AdminBean;


	public class AdminDaoDataSource implements IBeanDao<AdminBean> {

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

		private static final String TABLE_NAME = "ADMIN";

		@Override
		public synchronized void doSave(AdminBean admin) throws SQLException {

			Connection connection = null;
			PreparedStatement preparedStatement = null;

			String insertSQL = "INSERT INTO " + AdminDaoDataSource.TABLE_NAME
					+ " (USERNAME, PASSWORD) VALUES (?, ?)";

			try {
				connection = ds.getConnection();
				preparedStatement = connection.prepareStatement(insertSQL, Statement.RETURN_GENERATED_KEYS);
				preparedStatement.setString(1, admin.getUsername());
				preparedStatement.setString(2, admin.getPassword());
				preparedStatement.executeUpdate();

				ResultSet rs=preparedStatement.getGeneratedKeys();
				
				if(rs.next()) {
					admin.setId(rs.getInt(1));
					
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
		public synchronized AdminBean doRetrieveByKey(Object o_id) throws SQLException {
			Connection connection = null;
			PreparedStatement preparedStatement = null;
			int id=(Integer)o_id;

			AdminBean bean = null;

			String selectSQL = "SELECT * FROM " + AdminDaoDataSource.TABLE_NAME + " WHERE ID = ?";

			try {
				connection = ds.getConnection();
				preparedStatement = connection.prepareStatement(selectSQL);
				preparedStatement.setInt(1, id);

				ResultSet rs = preparedStatement.executeQuery();

				while (rs.next()) {
					bean=new AdminBean();
					bean.setId(rs.getInt("ID"));
					bean.setUsername(rs.getString("USERNAME"));
					bean.setPassword(rs.getString("PASSWORD"));
			
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

			String deleteSQL = "DELETE FROM " + AdminDaoDataSource.TABLE_NAME + " WHERE ID = ?";

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
		public synchronized Collection<AdminBean> doRetrieveAll(String order) throws SQLException {
			Connection connection = null;
			PreparedStatement preparedStatement = null;

			Collection<AdminBean> admins = new LinkedList<AdminBean>();

			String selectSQL = "SELECT * FROM " + AdminDaoDataSource.TABLE_NAME;

			if (order != null && !order.equals("")) {
				selectSQL += " ORDER BY " + order;
			}

			try {
				connection = ds.getConnection();
				preparedStatement = connection.prepareStatement(selectSQL);

				ResultSet rs = preparedStatement.executeQuery();

				while (rs.next()) {
					AdminBean bean = new AdminBean();

					bean.setId(rs.getInt("ID"));
					bean.setUsername(rs.getString("USERNAME"));
					bean.setPassword(rs.getString("PASSWORD"));
					admins.add(bean);
					
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
			return admins;
		}

		public synchronized AdminBean doRetrieveByUsername(Object o_id) throws SQLException {
			Connection connection = null;
			PreparedStatement preparedStatement = null;
			String username=(String)o_id;

			AdminBean bean = null;

			String selectSQL = "SELECT * FROM " + AdminDaoDataSource.TABLE_NAME + " WHERE USERNAME = ?";

			try {
				connection = ds.getConnection();
				preparedStatement = connection.prepareStatement(selectSQL);
				preparedStatement.setString(1, username);

				ResultSet rs = preparedStatement.executeQuery();

				while (rs.next()) {
					bean=new AdminBean();
					bean.setId(rs.getInt("ID"));
					bean.setUsername(rs.getString("USERNAME"));
					bean.setPassword(rs.getString("PASSWORD"));
			
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
		
	
	public AdminDaoDataSource() {
		
	}

}
