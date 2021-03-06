package myDBPackage;

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
	   
	    try {
	    	
			_conn = DBDriver.getConnection("jdbc:mysql://82.67.37.180:80/"+_dbName, _connectionProps);
		} catch (Exception e) {
			
			System.err.println("Error in DBHandler constructor: " + e.getMessage());
		} 
	   
			    
	}
	
	public ResultSet executeQueryRS(String query)
	{
		try
		{
			_query = _conn.createStatement();	
			return _query.executeQuery(query);

		}catch (Exception e) {
			System.err.println("Error in executeQueryRS:" + e.getMessage());
			return null;
		}
	}
	
	public boolean executeQuery(String query)
	{
		try
		{
			_query = _conn.createStatement();
			_query.executeUpdate(query);
			return true;

		}catch (Exception e) {
			System.err.println("Error in executeQuery:" + e.getMessage());
			return false;
		}
		
	}
	
	public boolean closeConn()
	{
		try {
			_conn.close();
			return true;
		}catch (Exception e) {
			System.err.println("Error in closeConn:" + e.getMessage());
			return false;
		}
		
	}
}