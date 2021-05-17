package org.sm.conn;

import java.sql.Connection;
import java.sql.SQLException;

public class ConnectionUtils
{
	public static Connection getConnection() throws ClassNotFoundException, SQLException
	{
		System.out.println("Connection utils : getConnection");
		return MySQLConnUtils.getMySqlConnection();
	}
	
	public static void closeQuietly(Connection conn)
	{
		try
		{
			System.out.println("Connection utils : close quietly");
			conn.close();
		}
		catch(Exception e)
		{
		}
	}
	
	public static void rollbackQuietly(Connection conn)
	{
		try
		{
			System.out.println("Connection utils : rollbackquietly");
			conn.close();
		}
		catch(Exception e)
		{
		}
	}
}
