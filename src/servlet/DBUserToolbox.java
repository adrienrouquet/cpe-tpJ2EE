package servlet;

import java.sql.ResultSet;
import java.sql.SQLException;

public class DBUserToolbox extends DBToolbox
{
	public DBUserToolbox()
	{
		super();
		_dbName = "tpJ2EE_LocalBase";
		_dbHandler = new DBHandler(_dbName);
	}
	
	public void closeConn()
	{
		_dbHandler.closeConn();
	}
	
	public boolean isUserValid(String login, String password)
	{
		String query = "SELECT * FROM users WHERE login='" + login + "' AND password='" + password + "';";
		ResultSet rs = getResult(query);
		return hasResult(rs);
	}

	public boolean isAdmin(int id)
	{
		boolean result = false;
		String query = "SELECT rightTypeId FROM users WHERE id='" + id + "';";
		ResultSet rs = getResult(query);
		try
		{
			if (hasResult(rs))
			{
				rs.first();
				do 
				{
					if (rs.getString("rightTypeId").equals("1"))
					{
						result = true;
					}
				}while (rs.next());
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
		ResultSet result = null;
		String query = "SELECT * FROM users WHERE id='" + id + "';";
		
		try
		{
			ResultSet rs = getResult(query);
			
			if (hasResult(rs))
				{
					rs.first();
					result = rs;
				}
		}
		catch (SQLException e)
		{
			System.err.println("Error in getRecord:" + e.getMessage());
		}
				
		return result;
	}
	
	public ResultSet getRecord(String login, String password)
	{
		ResultSet result = null;
		String query = "SELECT * FROM users WHERE login='" + login + "' AND password='" + password + "';";
		
		try
		{
			ResultSet rs = getResult(query);
			
			if (hasResult(rs))
				{
					rs.first();
					result = rs;
				}
		}
		catch (SQLException e)
		{
			System.err.println("Error in getRecord:" + e.getMessage());
		}
				
		return result;
	}
	
	public void createRecord(String name, String login, String password, int rightTypeId)
	{
		String query = "INSERT INTO users VALUES ('" + name + "','" + login + "','" + password + "','" + rightTypeId + "');";
		
		try
		{
			
		}
	}
	
	public ResultSet getUsers()
	{
		ResultSet result = null; 
		String query = "SELECT u.id as 'userId', u.name as 'userName', u.login, u.password, u.rightTypeId as 'rightTypeId', rt.name as 'rightTypeName' from users as u inner join rightTypes as rt on u.rightTypeId = rt.id";
		
		try
		{
			ResultSet rs = getResult(query);
			
			if (hasResult(rs))
				{
					rs.first();
					result = rs;
				}
		}
		catch (SQLException e)
		{
			System.err.println("Error in getUsers:" + e.getMessage());
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
				do 
				{
					result = true;
				}while(rs.next());
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
		
		
		return result;
	}
	
	private void executeQuery(String query)
	{
		
	}
}
