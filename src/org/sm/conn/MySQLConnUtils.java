package org.sm.conn;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySQLConnUtils 
{
	public static Connection getMySqlConnection() throws ClassNotFoundException, SQLException
	{
		String hostName = "localhost";
		String dbName   = "store_management";
		String userName = "sarath555";
		String password = "sarath555";
		System.out.println("MySQLConnUtils : getMySqlConnection "+ " hostName = "+hostName+ " dbName = " +dbName+ " userName = " + userName+ "  password = "+ password);
		return getMySqlConnection(hostName, dbName, userName, password);
	}
	
	public static Connection getMySqlConnection(String hostName, String dbName, String userName, String password)throws SQLException, ClassNotFoundException
	{
			Class.forName("com.mysql.jdbc.Driver");
			String connectionURL = "jdbc:mysql://"+hostName+":3306/"+dbName;
			Connection conn = DriverManager.getConnection(connectionURL, userName, password);
			System.out.println("MySQLConnUtils : getMySqlConnection " + connectionURL + "  conn:: =="+conn);
			return conn;
	}
}
