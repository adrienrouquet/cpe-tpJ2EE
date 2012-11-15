package servlet;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

public class DBUserToolbox extends DBToolbox
{
	public DBUserToolbox()
	{
		super();
		_dbName = "tpJ2EE_LocalBase";
		_dbHandler = new DBHandler(_dbName);
	}
	
	public boolean connect()
	{
		boolean isConnected = false;
		
		return isConnected;
	}
	
	public boolean isUserValid(String login, String password)
	{
		String query = "SELECT * FROM users WHERE login='" + login + "' AND password='" + password + "';";
		
		return hasResult(query);
	}

	public boolean isAdmin(int id)
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
	
	public ResultSet getRecord(int id)
	{
		String query = "SELECT * FROM users WHERE id='" + id + "';";
		ResultSet result = getResult(query);
		
		if (hasResult(result))
			{
				result = getResult(query);
			}

		return result;
	}
	
	private boolean hasResult(ResultSet rs)
	{
		boolean result = false;
		
		try
		{
			if (rs != null)
			{
				while(rs.next())
				{
					result = true;
				}
			}
		}
		catch (SQLException e)
		{
			System.err.println("Error in hasResult:" + e.getMessage());
		}
		
		return result;
	}
	
	private ResultSet getResult(String query)
	{
		ResultSet result = _dbHandler.executeQueryRS(query);
		_dbHandler.closeConn();
		
		return result;
	}
	
	public ResultSet getUsers()
	{
		ResultSet result = null;
		
		return result;
	}
	//{
	//	
	//		String query = "SELECT u.id as 'userId', u.name as 'userName', u.login, u.password, u.rightTypeId as 'rightTypeId', rt.name as 'rightTypeName' from users as u inner join rightTypes as rt on u.rightTypeId = rt.id";
	//
	//}
	
}
