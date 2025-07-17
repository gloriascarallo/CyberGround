package dao;


	import java.sql.Connection;
import java.sql.Date;
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

import model.Product_situatedin_cartBean;

	public class Product_situatedin_cartDaoDataSource implements IBeanDao<Product_situatedin_cartBean> {

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

		private static final String TABLE_NAME = "PRODUCT_SITUATEDIN_CART";

		@Override
		public synchronized void doSave(Product_situatedin_cartBean product_situatedin_cart) throws SQLException {

			Connection connection = null;
			PreparedStatement preparedStatement = null;

			String insertSQL = "INSERT INTO " + Product_situatedin_cartDaoDataSource.TABLE_NAME
					+ " (IDCART, IDPRODUCT, DATEADDED, QUANTITY) VALUES (?, ?, ?, ?)";

			try {
				connection = ds.getConnection();
				preparedStatement = connection.prepareStatement(insertSQL, Statement.RETURN_GENERATED_KEYS);
				preparedStatement.setInt(1, product_situatedin_cart.getIdCart());
				preparedStatement.setInt(2, product_situatedin_cart.getIdProduct());
				preparedStatement.setDate(3, product_situatedin_cart.getDateAdded());
				preparedStatement.setInt(4, product_situatedin_cart.getQuantity());
				

				preparedStatement.executeUpdate();
				ResultSet rs=preparedStatement.getGeneratedKeys();
				
				if(rs.next()) {
					product_situatedin_cart.setIdSituatedIn(rs.getInt(1));
					
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
		public synchronized Product_situatedin_cartBean doRetrieveByKey(Object o_id) throws SQLException {
			Connection connection = null;
			PreparedStatement preparedStatement = null;
	        int id=(Integer)o_id;
	        
			Product_situatedin_cartBean bean = null;

			String selectSQL = "SELECT * FROM " + Product_situatedin_cartDaoDataSource.TABLE_NAME + " WHERE ID_SITUATEDIN = ?";

			try {
				connection = ds.getConnection();
				preparedStatement = connection.prepareStatement(selectSQL);
				preparedStatement.setInt(1, id);

				ResultSet rs = preparedStatement.executeQuery();
				ProductDaoDataSource ds=new ProductDaoDataSource();
				while (rs.next()) {
					bean=new Product_situatedin_cartBean();
					bean.setIdSituatedIn(rs.getInt("ID_SITUATEDIN"));
					bean.setIdCart(rs.getInt("IDCART"));
					int idProduct=rs.getInt("IDPRODUCT");
					bean.setProduct(ds.doRetrieveByKey(idProduct));
					bean.setDateAdded(rs.getDate("DATEADDED"));
					bean.setQuantity(rs.getInt("QUANTITY"));
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

			String deleteSQL = "DELETE FROM " + Product_situatedin_cartDaoDataSource.TABLE_NAME + " WHERE ID_SITUATEDIN = ?";

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
		public synchronized Collection<Product_situatedin_cartBean> doRetrieveAll(String order) throws SQLException {
			Connection connection = null;
			PreparedStatement preparedStatement = null;

			Collection<Product_situatedin_cartBean> products_situatedin_carts = new LinkedList<Product_situatedin_cartBean>();

			String selectSQL = "SELECT * FROM " + Product_situatedin_cartDaoDataSource.TABLE_NAME;

			if (order != null && !order.equals("")) {
				selectSQL += " ORDER BY " + order;
			}

			try {
				connection = ds.getConnection();
				preparedStatement = connection.prepareStatement(selectSQL);

				ResultSet rs = preparedStatement.executeQuery();
				ProductDaoDataSource ds=new ProductDaoDataSource();
				while (rs.next()) {
					Product_situatedin_cartBean bean = new Product_situatedin_cartBean();

					bean.setIdSituatedIn(rs.getInt("ID_SITUATEDIN"));
					bean.setIdCart(rs.getInt("IDCART"));
					int idProduct=rs.getInt("IDPRODUCT");
					bean.setProduct(ds.doRetrieveByKey(idProduct));
					bean.setDateAdded(rs.getDate("DATEADDED"));
					bean.setQuantity(rs.getInt("QUANTITY"));
					products_situatedin_carts.add(bean);
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
			return products_situatedin_carts;
		}

		public synchronized ArrayList<Product_situatedin_cartBean> doRetrieveByIdCart(int id) throws SQLException{
			Connection connection = null;
			PreparedStatement preparedStatement = null;
			
			String selectSQL = "SELECT * FROM " + Product_situatedin_cartDaoDataSource.TABLE_NAME + " WHERE IDCART= ?";
			ArrayList<Product_situatedin_cartBean> products_situatedin_cart=new ArrayList<Product_situatedin_cartBean>();
			
			try {
				connection = ds.getConnection();
				preparedStatement = connection.prepareStatement(selectSQL);
				preparedStatement.setInt(1, id);

				ResultSet rs = preparedStatement.executeQuery();

				while (rs.next()) {
					Product_situatedin_cartBean bean=new Product_situatedin_cartBean();
					bean.setIdSituatedIn(rs.getInt("ID_SITUATEDIN"));
					bean.setIdCart(rs.getInt("IDCART"));
					int idProduct=rs.getInt("IDPRODUCT");
					ProductDaoDataSource ds=new ProductDaoDataSource();
					bean.setProduct(ds.doRetrieveByKey(idProduct));
					bean.setDateAdded(rs.getDate("DATEADDED"));
					bean.setQuantity(rs.getInt("QUANTITY"));
					products_situatedin_cart.add(bean);
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
			return products_situatedin_cart;
		}
			
		
		public synchronized ArrayList<Product_situatedin_cartBean> doRetrieveByDateAdded(int id, Date date) throws SQLException{
			Connection connection = null;
			PreparedStatement preparedStatement = null;
			
			String selectSQL = "SELECT * FROM " + Product_situatedin_cartDaoDataSource.TABLE_NAME + " WHERE IDCART= ? AND DATEADDED = ?";
			ArrayList<Product_situatedin_cartBean> products_situatedin_cart=new ArrayList<Product_situatedin_cartBean>();
			
			try {
				connection = ds.getConnection();
				preparedStatement = connection.prepareStatement(selectSQL);
				preparedStatement.setInt(1, id);
				preparedStatement.setDate(2,  date);

				ResultSet rs = preparedStatement.executeQuery();
				ProductDaoDataSource ds=new ProductDaoDataSource();
				while (rs.next()) {
					Product_situatedin_cartBean bean=new Product_situatedin_cartBean();
					bean.setIdSituatedIn(rs.getInt("ID_SITUATEDIN"));
					bean.setIdCart(rs.getInt("IDCART"));
					int idProduct=rs.getInt("IDPRODUCT");
					bean.setProduct(ds.doRetrieveByKey(idProduct));
					bean.setDateAdded(rs.getDate("DATEADDED"));
					bean.setQuantity(rs.getInt("QUANTITY"));
					products_situatedin_cart.add(bean);
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
			return products_situatedin_cart;
		}
		
		public synchronized ArrayList<Product_situatedin_cartBean> doRetrieveByIdCartAndPriceRange(int idCart, double minPrice, double maxPrice) throws SQLException {
		    Connection connection = null;
		    PreparedStatement preparedStatement = null;

		    String selectSQL = "SELECT c.* FROM " + Product_situatedin_cartDaoDataSource.TABLE_NAME + " c " +
		                       "JOIN product p ON c.IDPRODUCT = p.ID " +
		                       "WHERE c.IDCART = ? AND p.PRICE BETWEEN ? AND ?";

		    ArrayList<Product_situatedin_cartBean> productsInRange = new ArrayList<Product_situatedin_cartBean>();

		    try {
		        connection = ds.getConnection();
		        preparedStatement = connection.prepareStatement(selectSQL);
		        preparedStatement.setInt(1, idCart);
		        preparedStatement.setDouble(2, minPrice);
		        preparedStatement.setDouble(3, maxPrice);

		        ResultSet rs = preparedStatement.executeQuery();
		        ProductDaoDataSource ds = new ProductDaoDataSource();
		        while (rs.next()) {
		            Product_situatedin_cartBean bean = new Product_situatedin_cartBean();
		            bean.setIdSituatedIn(rs.getInt("ID_SITUATEDIN"));
		            bean.setIdCart(rs.getInt("IDCART"));
		            int idProduct = rs.getInt("IDPRODUCT");
		            bean.setProduct(ds.doRetrieveByKey(idProduct));
		            bean.setDateAdded(rs.getDate("DATEADDED"));
		            bean.setQuantity(rs.getInt("QUANTITY"));

		            productsInRange.add(bean);
		        }

		    } finally {
		        try {
		            if (preparedStatement != null) preparedStatement.close();
		        } finally {
		            if (connection != null) connection.close();
		        }
		    }

		    return productsInRange;
		}
		
		
		public synchronized boolean decreaseQuantity(int id) throws SQLException{
			Connection connection = null;
			PreparedStatement preparedStatement = null;
			int result=0;
			
			String updateSQL = "UPDATE " + Product_situatedin_cartDaoDataSource.TABLE_NAME + " SET QUANTITY = QUANTITY - 1 WHERE ID_SITUATEDIN = ?";
			try {
				connection = ds.getConnection();
				preparedStatement = connection.prepareStatement(updateSQL);
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
			return (result!=0);
		}
		
		public synchronized boolean increaseQuantity(int id) throws SQLException{
			Connection connection = null;
			PreparedStatement preparedStatement = null;
			int result=0;
			
			String updateSQL = "UPDATE " + Product_situatedin_cartDaoDataSource.TABLE_NAME + " SET QUANTITY = QUANTITY + 1 WHERE ID_SITUATEDIN = ?";
			try {
				connection = ds.getConnection();
				preparedStatement = connection.prepareStatement(updateSQL);
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
			return (result!=0);
		}
		
		}
		

