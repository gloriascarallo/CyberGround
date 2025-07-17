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

import model.Method_paymentBean;


		public class Method_paymentDaoDataSource implements IBeanDao<Method_paymentBean> {

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

			private static final String TABLE_NAME = "METHOD_PAYMENT";

			@Override
			public synchronized void doSave(Method_paymentBean method_payment) throws SQLException {

				Connection connection = null;
				PreparedStatement preparedStatement = null;

				String insertSQL = "INSERT INTO " + Method_paymentDaoDataSource.TABLE_NAME
						+ " (PAN, EXPIRATIONDATE, CVC) VALUES (?, ?, ?)";

				try {
					connection = ds.getConnection();
					preparedStatement = connection.prepareStatement(insertSQL);
		            preparedStatement.setString(1, method_payment.getPan());
		            preparedStatement.setString(2, method_payment.getExpirationDate());
		            preparedStatement.setString(3, method_payment.getCvc());
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
			public synchronized Method_paymentBean doRetrieveByKey(Object o_pan) throws SQLException {
				Connection connection = null;
				PreparedStatement preparedStatement = null;
				String pan=(String)o_pan;

				Method_paymentBean bean = null;

				String selectSQL = "SELECT * FROM " + Method_paymentDaoDataSource.TABLE_NAME + " WHERE PAN = ?";

				try {
					connection = ds.getConnection();
					preparedStatement = connection.prepareStatement(selectSQL);
					preparedStatement.setString(1, pan);
					ResultSet rs = preparedStatement.executeQuery();

					while (rs.next()) {
						bean=new Method_paymentBean();
						bean.setPan(rs.getString("PAN"));
						bean.setExpirationDate(rs.getString("EXPIRATIONDATE"));
						bean.setCvc(rs.getString("CVC"));
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
			public synchronized boolean doDelete(Object o_pan) throws SQLException {
				Connection connection = null;
				PreparedStatement preparedStatement = null;
				String pan=(String)o_pan;

				int result = 0;

				String deleteSQL = "DELETE FROM " + Method_paymentDaoDataSource.TABLE_NAME + " WHERE PAN = ?";

				try {
					connection = ds.getConnection();
					preparedStatement = connection.prepareStatement(deleteSQL);
					preparedStatement.setString(1, pan);

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
			public synchronized Collection<Method_paymentBean> doRetrieveAll(String order) throws SQLException {
				Connection connection = null;
				PreparedStatement preparedStatement = null;

				Collection<Method_paymentBean> methods_payment = new LinkedList<Method_paymentBean>();

				String selectSQL = "SELECT * FROM " + Method_paymentDaoDataSource.TABLE_NAME;

				if (order != null && !order.equals("")) {
					selectSQL += " ORDER BY " + order;
				}

				try {
					connection = ds.getConnection();
					preparedStatement = connection.prepareStatement(selectSQL);

					ResultSet rs = preparedStatement.executeQuery();

					while (rs.next()) {
						Method_paymentBean bean = new Method_paymentBean();

						bean.setPan(rs.getString("PAN"));
						bean.setExpirationDate(rs.getString("EXPIRATIONDATE"));
						bean.setCvc(rs.getString("CVC"));
						methods_payment.add(bean);
						
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
				return methods_payment;
			}



}
