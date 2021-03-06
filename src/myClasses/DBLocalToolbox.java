package myClasses;

import java.sql.ResultSet;
import java.sql.SQLException;

import myDBPackage.*;

public class DBLocalToolbox extends DBToolbox
{
	public DBLocalToolbox()
	{
		super();
		_dbName = "tpJ2EE_LocalBase";
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
	
	public boolean isValid(String login, String password)

	{
		String query = "SELECT * FROM users WHERE login='" + login + "' AND password='" + password + "';";
		try
		{
			ResultSet rs = getResult(query);
			return hasResult(rs);
		}
		catch (Exception e)
		{
			System.err.println("Error in DBLocalToolbox.isValid: " + e.getMessage());
		}
		return false;
	}
	
	public boolean userExists(String login)
	{
		String query="SELECT * FROM users WHERE login='" + login + "';";
		try
		{
			ResultSet rs = getResult(query);
			return hasResult(rs);
		}
		catch (Exception e)
		{
			System.err.println("Error in DBLocalToolbox.userExists: " + e.getMessage());
		}
		return false;
	}

	public boolean isAdmin(int id)
	{
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
						return true;
					}
				}while (rs.next());
			}
		}
		catch (SQLException e)
		{
			System.err.println("Error in DBLocalToolbox.isAdmin: " + e.getMessage());
		}		
		return false;
	}
	
	public ResultSet getUserRecord(int id)
	{		
		String query = "SELECT * FROM users WHERE id='" + id + "';";
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
			System.err.println("Error in DBLocalToolbox.getUserRecord: " + e.getMessage());
		}				
		return null;
	}
	
	public ResultSet getProductRecord(int id)
	{		
		String query = "SELECT * FROM products WHERE id='" + id + "';";
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
			System.err.println("Error in DBLocalToolbox.getProductRecord: " + e.getMessage());
		}				
		return null;
	}
	
	public ResultSet getProducts()
	{
		String query = "SELECT p.id as 'productId', p.name as 'productName', p.description as 'description', p.price as 'price', p.ImgUrl as 'imgUrl' from products as p;";
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
			System.err.println("Error in DBLocalToolbox.getProducts: " + e.getMessage());
		}
		return null;
	}
	
	public ResultSet getProductIds()
	{
		String query = "SELECT p.id as 'productId' from products as p;";
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
			System.err.println("Error in DBLocalToolbox.getProducts: " + e.getMessage());
		}
		return null;
	}
	
	public ResultSet getUserRecord(String login, String password)
	{
		String query = "SELECT * FROM users WHERE login='" + login + "' AND password='" + password + "';";
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
			System.err.println("Error in DBLocalToolbox.getUserRecord: " + e.getMessage());
		}				
		return null;
	}
	
	public boolean createUserRecord(String name, String login, String password, int rightTypeId)
	{
		String query = "INSERT INTO users(name, login, password, rightTypeId) VALUES ('" + name + "','" + login + "','" + password + "','" + rightTypeId + "');";
		return executeQuery(query);
	}
	
	public boolean updateUserRecord(int id, String name, String login, int rightTypeId)
	{
		String query = "UPDATE users SET name='" + name + "', login='" + login + "', rightTypeId='" + rightTypeId + "' WHERE id='" + id + "';";
		return executeQuery(query);
	}
	
	public boolean deleteUserRecord(int id)
	{
		String query = "DELETE FROM users WHERE id='" + id + "';";
		return executeQuery(query);
	}
	
	public ResultSet getUsers()
	{
		String query = "SELECT u.id as 'userId', u.name as 'userName', u.login, u.password, u.rightTypeId as 'rightTypeId', rt.name as 'rightTypeName' from users as u inner join rightTypes as rt on u.rightTypeId = rt.id";
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
			System.err.println("Error in DBLocalToolbox.getUsers: " + e.getMessage());
		}
		return null;
	}
	
	public ResultSet getUserRightTypes()
	{
		String query = "SELECT * FROM rightTypes;";		
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
			System.err.println("Error in DBLocalToolbox.getUserRightTypes: " + e.getMessage());
		}
		
		return null;
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
			System.err.println("Error in DBLocalToolbox.hasResult: " + e.getMessage());
		}	
		return false;
	}
	
	
}
