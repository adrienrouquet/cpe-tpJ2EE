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
		boolean isValid = false;
		String query = "SELECT * FROM users WHERE login='" + login + "' AND password='" + password + "';";
		
		try
		{
			ResultSet rs = _dbHandler.executeQueryRS(query);
			
			if (rs != null)
			{
				while(rs.next())
				{
					isValid = true;
				}
			}
			
			_dbHandler.closeConn();
		}
		catch (SQLException e)
		{
			System.err.println("Error in isUserValid:" + e.getMessage());
		}
		
		return isValid;
	}

	public static void commonMethod()
	{
		
	}
	
}
