package servlet;

import java.sql.ResultSet;
import java.util.Properties;



public class DBHandler{
	
	private String _dbName = "";
	private java.sql.Connection _conn = null;
	private java.sql.Statement _query = null;
	private Properties _connectionProps = null;
	
	public DBHandler(String dbName)
	{
		_dbName = dbName;
		_connectionProps = new Properties();
	    _connectionProps.put("user", "tpJ2EE");
	    _connectionProps.put("password", "tpJ2EEPass");
	}
	
	public ResultSet executeQueryRS(String query)
	{
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
			
			_conn = java.sql.DriverManager.getConnection("jdbc:mysql://172.16.64.130:3306/"+_dbName, _connectionProps);
			_query = _conn.createStatement();
			return _query.executeQuery(query);

		}catch (Exception e) {
			System.out.println("Error in executeQueryRS:" + e.getMessage());
			return null;
		}
	}
	
	public boolean executeQuery(String query)
	{
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
			
			_conn = java.sql.DriverManager.getConnection("jdbc:mysql://82.67.37.180:3306/"+_dbName, _connectionProps);
			_query = _conn.createStatement();
			_query.executeUpdate(query);
			return true;

		}catch (Exception e) {
			System.out.println("Error in executeQuery:" + e.getMessage());
			return false;
		}
		
	}
	
	public boolean closeConn()
	{
		try {
			_conn.close();
			return true;
		}catch (Exception e) {
			System.out.println("Error in closeConn:" + e.getMessage());
			return false;
		}
		
	}
}