package dao;

	import java.sql.SQLException;
import java.sql.Statement;

import javax.naming.Context;
	import javax.naming.InitialContext;
	import javax.naming.NamingException;
	import javax.sql.DataSource;

import bean.OrderBean;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
	import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Collection;
	import java.util.LinkedList;

	public class OrderDaoDataSource implements IBeanDao<OrderBean> {

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

		private static final String TABLE_NAME = "ORDERS";

		@Override
		public synchronized void doSave(OrderBean order) throws SQLException {

			Connection connection = null;
			PreparedStatement preparedStatement = null;

			String insertSQL = "INSERT INTO " + OrderDaoDataSource.TABLE_NAME
					+ " (DATEPURCHASE, DATEDELIVERY, DATESHIPPING, IDCART) VALUES (?, ?, ?, ?)";

			try {
				connection = ds.getConnection();
				preparedStatement = connection.prepareStatement(insertSQL, Statement.RETURN_GENERATED_KEYS);
				if (order.getDatePurchase() != null)
		            preparedStatement.setDate(1, order.getDatePurchase());
		        else
		            preparedStatement.setNull(1, java.sql.Types.DATE);

		        if (order.getDateDelivery() != null)
		            preparedStatement.setDate(2, order.getDateDelivery());
		        else
		            preparedStatement.setNull(2, java.sql.Types.DATE);

		        if (order.getDateShipping() != null)
		            preparedStatement.setDate(3, order.getDateShipping());
		        else
		            preparedStatement.setNull(3, java.sql.Types.DATE);
				preparedStatement.setInt(4, order.getIdCart());
				
				

				preparedStatement.executeUpdate();
				ResultSet rs=preparedStatement.getGeneratedKeys();
				
				if(rs.next()) {
					order.setIdOrder(rs.getInt(1));
					
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
		public synchronized OrderBean doRetrieveByKey(Object o_id) throws SQLException {
			Connection connection = null;
			PreparedStatement preparedStatement = null;
	        int id=(Integer)o_id;
	        
			OrderBean bean = null;

			String selectSQL = "SELECT * FROM " + OrderDaoDataSource.TABLE_NAME + " WHERE IDORDER = ?";

			try {
				connection = ds.getConnection();
				preparedStatement = connection.prepareStatement(selectSQL);
				preparedStatement.setInt(1, id);

				ResultSet rs = preparedStatement.executeQuery();

				while (rs.next()) {
					bean=new OrderBean();
					bean.setIdOrder(rs.getInt("IDORDER"));
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

			String deleteSQL = "DELETE FROM " + OrderDaoDataSource.TABLE_NAME + " WHERE IDORDER = ?";

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
		public synchronized Collection<OrderBean> doRetrieveAll(String order) throws SQLException {
			Connection connection = null;
			PreparedStatement preparedStatement = null;

			Collection<OrderBean> orders = new LinkedList<OrderBean>();

			String selectSQL = "SELECT * FROM " + OrderDaoDataSource.TABLE_NAME;

			if (order != null && !order.equals("")) {
				selectSQL += " ORDER BY " + order;
			}

			try {
				connection = ds.getConnection();
				preparedStatement = connection.prepareStatement(selectSQL);
				

				ResultSet rs = preparedStatement.executeQuery();

				while (rs.next()) {
					OrderBean bean = new OrderBean();
					bean.setIdOrder(rs.getInt("IDORDER"));
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

		
		public synchronized ArrayList<OrderBean> doRetrieveByIdCart(int id) throws SQLException {
			Connection connection = null;
			PreparedStatement preparedStatement = null;

			ArrayList<OrderBean> orders=new ArrayList<OrderBean>();

			String selectSQL = "SELECT * FROM " + OrderDaoDataSource.TABLE_NAME + " WHERE IDCART = ?";
            

			try {
				connection = ds.getConnection();
				preparedStatement = connection.prepareStatement(selectSQL);
				preparedStatement.setInt(1, id);
				ResultSet rs = preparedStatement.executeQuery();

				while (rs.next()) {
					OrderBean bean = new OrderBean();
					
					bean.setIdOrder(rs.getInt("IDORDER"));
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
		
		public synchronized ArrayList<OrderBean> doRetrieveByIdCartAndDatePurchaseRange(int id, Date dateX, Date dateY) throws SQLException {
			Connection connection = null;
			PreparedStatement preparedStatement = null;

			ArrayList<OrderBean> orders=new ArrayList<OrderBean>();

			String selectSQL = "SELECT * FROM " + OrderDaoDataSource.TABLE_NAME + " WHERE IDCART = ? AND DATEPURCHASE BETWEEN ? AND ?";
            

			try {
				connection = ds.getConnection();
				preparedStatement = connection.prepareStatement(selectSQL);
				preparedStatement.setInt(1, id);
				preparedStatement.setDate(2, dateX);
				preparedStatement.setDate(3, dateY);
				ResultSet rs = preparedStatement.executeQuery();

				while (rs.next()) {
					OrderBean bean = new OrderBean();
					
					bean.setIdOrder(rs.getInt("IDORDER"));
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