package servlet;

import java.sql.ResultSet;
import java.sql.SQLException;

public abstract class DBUserToolbox extends DBToolbox
{
	private DBUserToolbox()
	{
		super();
		_dbName = "tpJ2EE_LocalBase";
		_dbHandler = new DBHandler(_dbName);
	}
	
	public static boolean isUserValid(String login, String password)
	{
		String query = "SELECT * FROM users WHERE login='" + login + "' AND password='" + password + "';";
		
		return hasResult(query);
	}

	public static boolean isAdmin(int id)
	{
		boolean result = false;
		String query = "SELECT rightTypeId FROM users WHERE id='" + id + "';";
		
		try
		{
			if (hasResult(query))
			{
				ResultSet rs = getResult(query);
				while (rs.next())
				{
					if (rs.getString("rightTypeId").equals("1"))
					{
						result = true;
					}
				}
			}
		}
		catch (SQLException e)
		{
			System.err.println("Error in isAdmin:" + e.getMessage());
		}
		
		return result;
	}
	
	public static boolean getRecord(int id)
	{
		boolean success = false;
		
		try
		{
			
		}
		catch (SQLException e)
		{
			System.err.println("Error in getRecord:" + e.getMessage());
		}
		
		return success;
	}
	
	private static boolean hasResult(String query)
	{
		boolean result = false;
		
		try
		{
			ResultSet rs = getResult(query);
			
			if (rs != null)
			{
				while(rs.next())
				{
					result = true;
				}
			}
			
			_dbHandler.closeConn();
		}
		catch (SQLException e)
		{
			System.err.println("Error in hasResult:" + e.getMessage());
		}
		
		return result;
	}
	
	private static ResultSet getResult(String query)
	{
		return _dbHandler.executeQueryRS(query);
	}
	
}
