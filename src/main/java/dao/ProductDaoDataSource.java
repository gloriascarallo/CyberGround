package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import bean.ProductBean;

public class ProductDaoDataSource implements IBeanDao<ProductBean> {

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

	private static final String TABLE_NAME = "product";

	@Override
	public synchronized void doSave(ProductBean product) throws SQLException {

		Connection connection = null;
		PreparedStatement preparedStatement = null;

		String insertSQL = "INSERT INTO " + ProductDaoDataSource.TABLE_NAME
				+ " (NAME, PRICE, DISCOUNTPERCENTAGE, DATEEXPIRATIONDISCOUNT, DESCRIPTION, DATEUPLOAD, SUPPLIER, CATEGORYNAME, IMAGEPATH, QUANTITYAVAILABLE) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

		try {
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(insertSQL, Statement.RETURN_GENERATED_KEYS);
			preparedStatement.setString(1, product.getName());
			preparedStatement.setDouble(2, product.getPrice());
			preparedStatement.setDouble(3, product.getDiscountPercentage());
			preparedStatement.setDate(4, product.getDateExpirationDiscount());
			preparedStatement.setString(5, product.getDescription());
			preparedStatement.setDate(6, product.getDateUpload());
			preparedStatement.setString(7, product.getSupplier());
			preparedStatement.setString(8, product.getCategoryName());
			preparedStatement.setString(9, product.getImagePath());
			preparedStatement.setInt(10, product.getQuantityAvailable());

			preparedStatement.executeUpdate();
           ResultSet rs=preparedStatement.getGeneratedKeys();
			
			if(rs.next()) {
				product.setId(rs.getInt(1));
				
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
	public synchronized ProductBean doRetrieveByKey(Object o_id) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
        int id=(Integer)o_id;
        
		ProductBean bean = new ProductBean();

		String selectSQL = "SELECT * FROM " + ProductDaoDataSource.TABLE_NAME + " WHERE ID = ?";

		try {
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			preparedStatement.setInt(1, id);

			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				bean.setId(rs.getInt("ID"));
				bean.setName(rs.getString("NAME"));
				bean.setDescription(rs.getString("DESCRIPTION"));
				bean.setPrice(rs.getDouble("PRICE"));
				double value = rs.getDouble("DISCOUNTPERCENTAGE");
				if (rs.wasNull()) {
				    bean.setDiscountPercentage(null);
				} else {
				    bean.setDiscountPercentage(value);
				}
				bean.setDateExpirationDiscount(rs.getDate("DATEEXPIRATIONDISCOUNT"));
				bean.setCategoryName(rs.getString("CATEGORYNAME"));
				bean.setSupplier(rs.getString("SUPPLIER"));
				bean.setDateUpload(rs.getDate("DATEUPLOAD"));
				bean.setImagePath(rs.getString("IMAGEPATH"));
				bean.setQuantityAvailable(rs.getInt("QUANTITYAVAILABLE"));
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

		String deleteSQL = "DELETE FROM " + ProductDaoDataSource.TABLE_NAME + " WHERE ID = ?";

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
	public synchronized Collection<ProductBean> doRetrieveAll(String order) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		Collection<ProductBean> products = new LinkedList<ProductBean>();

		String selectSQL = "SELECT * FROM " + ProductDaoDataSource.TABLE_NAME;

		if (order != null && !order.equals("")) {
			selectSQL += " ORDER BY " + order;
		}

		try {
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);

			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				ProductBean bean = new ProductBean();

				bean.setId(rs.getInt("ID"));
				bean.setName(rs.getString("NAME"));
				bean.setDescription(rs.getString("DESCRIPTION"));
				bean.setPrice(rs.getDouble("PRICE"));
				double value = rs.getDouble("DISCOUNTPERCENTAGE");
				if (rs.wasNull()) {
				    bean.setDiscountPercentage(null);
				} else {
				    bean.setDiscountPercentage(value);
				}
				bean.setDateExpirationDiscount(rs.getDate("DATEEXPIRATIONDISCOUNT"));
				bean.setCategoryName(rs.getString("CATEGORYNAME"));
				bean.setSupplier(rs.getString("SUPPLIER"));
				bean.setDateUpload(rs.getDate("DATEUPLOAD"));
				bean.setImagePath(rs.getString("IMAGEPATH"));
				bean.setQuantityAvailable(rs.getInt("QUANTITYAVAILABLE"));
				products.add(bean);
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
		return products;
	}
	
	
	public synchronized boolean decreaseQuantityAvailable(int o_id) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
        int id=(Integer)o_id;
        
		int result = 0;

		String deleteSQL = "UPDATE" + ProductDaoDataSource.TABLE_NAME + " SET QUANTITYAVAILABLE= QUANTITYAVAILABLE-1 WHERE ID = ?";

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

	
	public synchronized ArrayList<ProductBean> doRetrievebySupplier(String supplier) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		ArrayList<ProductBean> products = new ArrayList<ProductBean>();

		String selectSQL = "SELECT * FROM " + ProductDaoDataSource.TABLE_NAME + " WHERE SUPPLIER LIKE= ?";
        

		try {
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
            preparedStatement.setString(1, "%" + supplier + "%");
			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				ProductBean bean = new ProductBean();

				bean.setId(rs.getInt("ID"));
				bean.setName(rs.getString("NAME"));
				bean.setDescription(rs.getString("DESCRIPTION"));
				bean.setPrice(rs.getDouble("PRICE"));
				double value = rs.getDouble("DISCOUNTPERCENTAGE");
				if (rs.wasNull()) {
				    bean.setDiscountPercentage(null);
				} else {
				    bean.setDiscountPercentage(value);
				}
				bean.setDateExpirationDiscount(rs.getDate("DATEEXPIRATIONDISCOUNT"));
				bean.setCategoryName(rs.getString("CATEGORYNAME"));
				bean.setSupplier(rs.getString("SUPPLIER"));
				bean.setDateUpload(rs.getDate("DATEUPLOAD"));
				bean.setImagePath(rs.getString("IMAGEPATH"));
				bean.setQuantityAvailable(rs.getInt("QUANTITYAVAILABLE"));
				products.add(bean);
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
		return products;
	}
	
	
	public synchronized ArrayList<ProductBean> doRetrievebyPriceRange(double priceMin, double priceMax) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		ArrayList<ProductBean> products = new ArrayList<ProductBean>();

		String selectSQL = "SELECT * FROM " + ProductDaoDataSource.TABLE_NAME + " WHERE PRICE BETWEEN ? AND ?";
        

		try {
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
            preparedStatement.setDouble(1, priceMin);
            preparedStatement.setDouble(2, priceMax);
			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				ProductBean bean = new ProductBean();

				bean.setId(rs.getInt("ID"));
				bean.setName(rs.getString("NAME"));
				bean.setDescription(rs.getString("DESCRIPTION"));
				bean.setPrice(rs.getDouble("PRICE"));
				double value = rs.getDouble("DISCOUNTPERCENTAGE");
				if (rs.wasNull()) {
				    bean.setDiscountPercentage(null);
				} else {
				    bean.setDiscountPercentage(value);
				}
				bean.setDateExpirationDiscount(rs.getDate("DATEEXPIRATIONDISCOUNT"));
				bean.setCategoryName(rs.getString("CATEGORYNAME"));
				bean.setSupplier(rs.getString("SUPPLIER"));
				bean.setDateUpload(rs.getDate("DATEUPLOAD"));
				bean.setImagePath(rs.getString("IMAGEPATH"));
				bean.setQuantityAvailable(rs.getInt("QUANTITYAVAILABLE"));
				products.add(bean);
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
		return products;
	}
	
	
	public synchronized ArrayList<ProductBean> doRetrievebyYearUpload(int year) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		ArrayList<ProductBean> products = new ArrayList<ProductBean>();

		String selectSQL = "SELECT * FROM " + ProductDaoDataSource.TABLE_NAME + " WHERE DATEUPLOAD BETWEEN ? AND ?";
        

		try {
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
            preparedStatement.setString(1, year + "-01-01");
            preparedStatement.setString(2, year + "-12-31");
			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				ProductBean bean = new ProductBean();

				bean.setId(rs.getInt("ID"));
				bean.setName(rs.getString("NAME"));
				bean.setDescription(rs.getString("DESCRIPTION"));
				bean.setPrice(rs.getDouble("PRICE"));
				double value = rs.getDouble("DISCOUNTPERCENTAGE");
				if (rs.wasNull()) {
				    bean.setDiscountPercentage(null);
				} else {
				    bean.setDiscountPercentage(value);
				}
				bean.setDateExpirationDiscount(rs.getDate("DATEEXPIRATIONDISCOUNT"));
				bean.setCategoryName(rs.getString("CATEGORYNAME"));
				bean.setSupplier(rs.getString("SUPPLIER"));
				bean.setDateUpload(rs.getDate("DATEUPLOAD"));
				bean.setImagePath(rs.getString("IMAGEPATH"));
				bean.setQuantityAvailable(rs.getInt("QUANTITYAVAILABLE"));
				products.add(bean);
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
		return products;
	}
	
	public synchronized ArrayList<ProductBean> doRetrievebyName(String name) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		ArrayList<ProductBean> products = new ArrayList<ProductBean>();

		String selectSQL = "SELECT * FROM " + ProductDaoDataSource.TABLE_NAME + " WHERE NAME LIKE= ?";
        

		try {
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
            preparedStatement.setString(1, "%" + name + "%");
			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				ProductBean bean = new ProductBean();

				bean.setId(rs.getInt("ID"));
				bean.setName(rs.getString("NAME"));
				bean.setDescription(rs.getString("DESCRIPTION"));
				bean.setPrice(rs.getDouble("PRICE"));
				double value = rs.getDouble("DISCOUNTPERCENTAGE");
				if (rs.wasNull()) {
				    bean.setDiscountPercentage(null);
				} else {
				    bean.setDiscountPercentage(value);
				}
				bean.setDateExpirationDiscount(rs.getDate("DATEEXPIRATIONDISCOUNT"));
				bean.setCategoryName(rs.getString("CATEGORYNAME"));
				bean.setSupplier(rs.getString("SUPPLIER"));
				bean.setDateUpload(rs.getDate("DATEUPLOAD"));
				bean.setImagePath(rs.getString("IMAGEPATH"));
				bean.setQuantityAvailable(rs.getInt("QUANTITYAVAILABLE"));
				products.add(bean);
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
		return products;
	}
	
	public synchronized ArrayList<ProductBean> doRetrievebyCategoryName(String category) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		ArrayList<ProductBean> products = new ArrayList<ProductBean>();

		String selectSQL = "SELECT * FROM " + ProductDaoDataSource.TABLE_NAME + " WHERE CATEGOTYNAME LIKE= ?";
        

		try {
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
            preparedStatement.setString(1, "%" + category + "%");
			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				ProductBean bean = new ProductBean();

				bean.setId(rs.getInt("ID"));
				bean.setName(rs.getString("NAME"));
				bean.setDescription(rs.getString("DESCRIPTION"));
				bean.setPrice(rs.getDouble("PRICE"));
				double value = rs.getDouble("DISCOUNTPERCENTAGE");
				if (rs.wasNull()) {
				    bean.setDiscountPercentage(null);
				} else {
				    bean.setDiscountPercentage(value);
				}
				bean.setDateExpirationDiscount(rs.getDate("DATEEXPIRATIONDISCOUNT"));
				bean.setCategoryName(rs.getString("CATEGORYNAME"));
				bean.setSupplier(rs.getString("SUPPLIER"));
				bean.setDateUpload(rs.getDate("DATEUPLOAD"));
				bean.setImagePath(rs.getString("IMAGEPATH"));
				bean.setQuantityAvailable(rs.getInt("QUANTITYAVAILABLE"));
				products.add(bean);
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
		return products;
	}
	
	public synchronized ArrayList<ProductBean> doRetrieveDiscountedProducts(Double discount) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		ArrayList<ProductBean> products = new ArrayList<ProductBean>();

		String selectSQL = "SELECT * FROM " + ProductDaoDataSource.TABLE_NAME + " WHERE DISCOUNT = ? AND (DATEEXPIRATIONDISCOUNT IS NULL OR DATEEXPIRATIONDISCOUNT > CURRENT_DATE)";
        

		try {
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
            preparedStatement.setDouble(1, discount);
			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				ProductBean bean = new ProductBean();

				bean.setId(rs.getInt("ID"));
				bean.setName(rs.getString("NAME"));
				bean.setDescription(rs.getString("DESCRIPTION"));
				bean.setPrice(rs.getDouble("PRICE"));
				double value = rs.getDouble("DISCOUNTPERCENTAGE");
				if (rs.wasNull()) {
				    bean.setDiscountPercentage(null);
				} else {
				    bean.setDiscountPercentage(value);
				}
				bean.setDateExpirationDiscount(rs.getDate("DATEEXPIRATIONDISCOUNT"));
				bean.setCategoryName(rs.getString("CATEGORYNAME"));
				bean.setSupplier(rs.getString("SUPPLIER"));
				bean.setDateUpload(rs.getDate("DATEUPLOAD"));
				bean.setImagePath(rs.getString("IMAGEPATH"));
				bean.setQuantityAvailable(rs.getInt("QUANTITYAVAILABLE"));
				products.add(bean);
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
		return products;
	}
	
	
	public synchronized void doUpdate(ProductBean product) throws SQLException {

		Connection connection = null;
		PreparedStatement preparedStatement = null;

		String updateSQL = "UPDATE " + ProductDaoDataSource.TABLE_NAME
				+ " SET NAME = ?, PRICE = ?, DISCOUNTPERCENTAGE = ?, DATEEXPIRATIONDISCOUNT = ?, DESCRIPTION = ?, DATEUPLOAD = ?, SUPPLIER = ?, CATEGORYNAME = ?, IMAGEPATH = ?, QUANTITYAVAILABLE = ? WHERE ID = ?";

		try {
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(updateSQL);
			preparedStatement.setString(1, product.getName());
			preparedStatement.setDouble(2, product.getPrice());
			if (product.getDiscountPercentage() != null) {
			    preparedStatement.setDouble(3, product.getDiscountPercentage());
			} else {
			    preparedStatement.setNull(3, Types.DOUBLE);
			}
			if (product.getDateExpirationDiscount() != null) {
			    preparedStatement.setDate(4, product.getDateExpirationDiscount());
			} else {
			    preparedStatement.setNull(4, Types.DATE);
			}
			preparedStatement.setString(5, product.getDescription());
			preparedStatement.setDate(6, product.getDateUpload());
			preparedStatement.setString(7, product.getSupplier());
			preparedStatement.setString(8, product.getCategoryName());
			preparedStatement.setString(9, product.getImagePath());
			preparedStatement.setInt(10, product.getQuantityAvailable());
			preparedStatement.setInt(11, product.getId());

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
	
	
	
	
	
	
	
	
	
	

}