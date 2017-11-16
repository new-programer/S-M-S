package sms.db;

import java.sql.Connection;
import java.sql.DriverManager;

public class DbConn
{
	public static Connection getConn()
	{
		Connection conn = null;
		String driver = "oracle.jdbc.driver.OracleDriver";
		String url = "jdbc:oracle:thin:@localhost:1521:orcl"; //orcl为oracle数据库的实例名字
		String username = "system";
		String password = "gbh123";
		
		try
		{
			Class.forName(driver);
			conn = DriverManager.getConnection(url,username,password);
		} catch (Exception e)
		{
			e.printStackTrace();
		}
		return conn;
	}
}
