package org.sm.utils;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.sm.beans.Attachment;
import org.sm.beans.Product;
import org.sm.beans.UserAccount;

public class DBUtils
{
	
	//find user with name, password, gender, image
	public static UserAccount findUser(Connection conn, String userName, String password) throws SQLException
	{
		String sql = "Select a.User_Name, a.Password, a.Gender, a.User_ID, a.Image_data  from User_Account a "
				+ "where a.User_Name = ? and a.password=?";
		
		PreparedStatement pstm = conn.prepareStatement(sql);
		pstm.setString(1, userName);
		pstm.setString(2, password);
		ResultSet rs = pstm.executeQuery();
		
		if(rs.next())
		{
			String gender = rs.getString("Gender");
			Long userId = rs.getLong("User_ID");
			byte[] imageData = null;
			if (rs.getBytes("IMAGE_DATA") != null)
				imageData = rs.getBytes("IMAGE_DATA");
			UserAccount user = new UserAccount();
			user.setUserName(userName);
			user.setPassword(password);
			user.setGender(gender);
			user.setUserId(userId);
			user.setImageData(imageData);
			return user;
		}
		return null;
	}
	
	//find user with name
	public static UserAccount findUser(Connection conn, String userName) throws SQLException
	{
		System.out.println("DBUtils :: findUser :: conn = "+conn+" userName = "+userName);
		String sql = "Select a.User_Name, a.Password, a.Gender from User_Account a"
				+ " where a.User_Name = ?";
		PreparedStatement pstm = conn.prepareStatement(sql);
		pstm.setString(1, userName);
		
		ResultSet rs = pstm.executeQuery();
		
		if(rs.next())
		{
			String password = rs.getString("Password");
			String gender = rs.getString("Gender");
			UserAccount user = new UserAccount();
			user.setUserName(userName);
			user.setPassword(password);
			user.setGender(gender);
			return user;
		}
		return null;
	}
	
	
	//To find user Image
	public static UserAccount findImage(Connection conn, Long userId) throws SQLException
	{
		System.out.println("DBUtils  :: findUser  ::  conn = "+conn );
		String sql = "Select a.User_Name, a.Password, a.Gender,"
				+ " a.IMAGE_DATA, a.IMAGE_FILE_NAME "
				+ "FROM User_Account a "
				+ "WHERE a.User_ID = ?";
		
		PreparedStatement pstm = conn.prepareStatement(sql);
		pstm.setLong(1, userId);
		ResultSet rs = pstm.executeQuery();
		
		if(rs.next())
		{
			String userName = rs.getString("USER_NAME");
			String password = rs.getString("Password");
			String gender = rs.getString("Gender");
			byte[] imageData = rs.getBytes("IMAGE_DATA");
			String imageFileName = rs.getString("IMAGE_FILE_NAME");
			UserAccount user = new UserAccount();
			user.setUserName(userName);
			user.setPassword(password);
			user.setGender(gender);
			user.setImageData(imageData);
			user.setImageFileName(imageFileName);
			return user;
		}
		return null;
	}
	
	//Upload File into DB
	public static void uploadFilesToDB(Connection conn, Long userId, String fileName, InputStream is, String description)throws SQLException
	{
		String sql = "Insert into Attachment(USER_ID, FILE_ID, FILE_NAME, FILE_DATA, DESCRIPTION)"
				+ "values(?,?,?,?,?)";
		PreparedStatement pstm = conn.prepareStatement(sql);
		Long fileId = getMaxAttachmentId(conn) +1;
		pstm.setLong(1, userId);
		pstm.setLong(2, fileId);
		pstm.setString(3, fileName);
		pstm.setBlob(4, is);
		pstm.setString(5, description);
		pstm.executeUpdate();
	}
	
	
	//Get the maximum and add 1.
	public static Long getMaxAttachmentId(Connection conn) throws SQLException
	{
		String sql = "Select max(a.FILE_ID) from Attachment a";
		PreparedStatement pstm = conn.prepareStatement(sql);
		ResultSet rs = pstm.executeQuery();
		if(rs.next())
		{
			long max = rs.getLong(1);
			return max;
		}
		return 0L;
	}
	
	//Get all files to show
	public static List<Attachment> getAllAttachmentFromDB(Connection conn, Long user_id) throws SQLException, IOException
	{
		String sql = "Select a.File_Id, a.File_Name, a.Description "
				+ " From Attachment a Where a.User_Id = ?";
		PreparedStatement pstm = conn.prepareStatement(sql);
		pstm.setLong(1, user_id);
		ResultSet rs = pstm.executeQuery();
		List<Attachment> list = new ArrayList<Attachment>();
		while(rs.next())
		{
			Long file_id = rs.getLong("File_Id");
			String file_Name = rs.getString("File_Name");
			String description = rs.getString("Description");
			Attachment attachment = new Attachment(file_id, file_Name, description);
			list.add(attachment);
		}
		return list;
	}
	
	//Get file to download
	public static Attachment downloadFileFromDB(Connection conn, Long user_id, Long File_id)throws SQLException, IOException
	{
		String sql = "Select a.File_Id, a.File_Name, a.File_Data, a.Description"
				+ " From Attachment a Where a.User_Id=? and a.File_Id=?";
		PreparedStatement pstm = conn.prepareStatement(sql);
		pstm.setLong(1, user_id);
		pstm.setLong(2, File_id);
		ResultSet rs = pstm.executeQuery();
		if(rs.next())
		{
			Long fileID = rs.getLong("File_Id");
			String fileName = rs.getString("File_Name");
			Blob fileData = rs.getBlob("File_Data");
			String description = rs.getString("Description");
			return new Attachment(fileID, fileName, fileData, description);
		}
		return null;
	}
	
	//find products in db
	public static List<Product> queryProduct(Connection conn) throws SQLException
	{
		String sql = "Select a.Code, a.Name, a.Price from Product a";
		PreparedStatement pstm = conn.prepareStatement(sql);
		
		ResultSet rs = pstm.executeQuery();
		List<Product> list = new ArrayList<Product>();
		while(rs.next())
		{
			String code = rs.getString("Code");
			String name = rs.getString("Name");
			float price = rs.getFloat("Price");
			Product product  = new Product();
			product.setCode(code);
			product.setName(name);
			product.setPrice(price);
			list.add(product);
		}
		return list;
	}
	
	//To find the product
	public static Product findProduct(Connection conn, String code) throws SQLException
	{
		System.out.println("DBUtils :: findProduct");
		String sql = "Select a.Code, a.Name, a.Price from Product a where a.Code = ?";
		
		PreparedStatement pstm = conn.prepareStatement(sql);
		pstm.setString(1, code);
		ResultSet rs = pstm.executeQuery();
		
		while(rs.next())
		{
			String name = rs.getString("Name");
			float price = rs.getFloat("Price");
			Product product = new Product(code, name, price);
			return product;
		}
		return null;
	}
	
	public static void updateProduct(Connection conn, Product product) throws SQLException
	{
		System.out.println("DBUtils :: updateProduct");
		String sql = "Update Product set Name = ?, Price = ? where Code = ?";
		
		PreparedStatement pstm = conn.prepareStatement(sql);
		pstm.setString(1, product.getName());
		pstm.setString(2, product.getName());
		pstm.setFloat(3, product.getPrice());
		
		pstm.executeUpdate();
	}
	
	public static void insertProduct(Connection conn, Product product) throws SQLException
	{
		System.out.println("DBUtils :: insertProduct");
		String sql = "insert into Product(Code,Name, Price) values (?,?,?)";
		
		PreparedStatement pstm = conn.prepareStatement(sql);
		
		pstm.setString(1, product.getCode());
		pstm.setString(2, product.getName());
		pstm.setFloat(3, product.getPrice());
		
		pstm.executeUpdate();
	}
	
	public static void deleteProduct(Connection conn, String code) throws SQLException
	{
		System.out.println("DBUtils :: deleteProduct");
		String sql = "Delete from product where Code = ?";
		
		PreparedStatement pstm = conn.prepareStatement(sql);
		
		pstm.setString(1, code);
		pstm.executeUpdate();
	}
}
