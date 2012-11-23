package servlet;

import java.sql.ResultSet;
import java.sql.SQLException;

public class DBProductToolbox extends DBToolbox
{
	public DBProductToolbox()
	{
		super();
		_dbName = "tpJ2EE_RemoteProductBase";
		_dbHandler = new DBHandler(_dbName);
	}
	
	private ResultSet getResult(String query)
	{
		return _dbHandler.executeQueryRS(query);
	}
	
	private boolean executeQuery(String query)
	{
		return _dbHandler.executeQuery(query);
	}
	
	public void closeConn()
	{
		_dbHandler.closeConn();
	}
	
	
	public int getStockQuantity(int productId)
	{		
		String query = "SELECT stockQuantity FROM productStocks WHERE productId='" + productId + "';";
		try
		{
			ResultSet rs = getResult(query);			
			if (hasResult(rs))
				{
					rs.first();
					return Integer.parseInt(rs.getString("stockQuantity"));
				}
		}
		catch (SQLException e)
		{
			System.err.println("Error in DBProductToolbox.getStockQuantity: " + e.getMessage());
		}
		return 0;
	}
	
	public int getDelay(int productId)
	{		
		String query = "SELECT delay FROM productStocks WHERE productId='" + productId + "';";
		try
		{
			ResultSet rs = getResult(query);			
			if (hasResult(rs))
				{
					rs.first();
					return Integer.parseInt(rs.getString("delay"));
				}
		}
		catch (SQLException e)
		{
			System.err.println("Error in DBProductToolbox.getDelay: " + e.getMessage());
		}
		return 0;
	}
	
	public ResultSet getRecord(int productId)
	{		
		String query = "SELECT * FROM productStocks WHERE productId='" + productId + "';";
		try
		{
			ResultSet rs = getResult(query);			
			if (hasResult(rs))
				{
					rs.first();
					return rs;
				}
		}
		catch (SQLException e)
		{
			System.err.println("Error in DBProductToolbox.getRecord: " + e.getMessage());
		}				
		return null;
	}
	
	public boolean updateRecord(int productId, int stockQuantity)
	{
		String query = "UPDATE productStocks SET stockQuantity='" + stockQuantity + "' WHERE productId='" + productId + "';";
		return executeQuery(query);
	}
	
	public boolean deleteRecord(int productId)
	{
		String query = "DELETE FROM users WHERE productId='" + productId + "';";
		return executeQuery(query);
	}
	
	private boolean hasResult(ResultSet rs)
	{
		try
		{
			if (rs != null && rs.next())
			{
				rs.first();
				do 
				{
					return true;
				}while(rs.next());
			}
		}
		catch (SQLException e)
		{
			System.err.println("Error in DBProductToolbox.hasResult: " + e.getMessage());
		}	
		return false;
	}
	
	
}
