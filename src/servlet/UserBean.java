package servlet;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserBean
{
	private int _id = 0;
	private String _name = "";
	private String _login = "";
	private String _password = "";
	private int _rightTypeId = 0;
	private boolean _isConnected = false;
	
	public int getId() {
		return _id;
	}
	public void setId(int _id) {
		this._id = _id;
	}
	
	public String getName() {
		return _name;
	}
	public void setName(String name) {
		this._name = name;
	}
	
	public String getLogin() {
		return _login;
	}
	public void setLogin(String login) {
		this._login = login;
	}
	
	public String getPassword() {
		return _password;
	}
	public void setPassword(String password) {
		this._password = password;
	}
	
	public int getRightTypeId() {
		return _rightTypeId;
	}
	public void setRightTypeId(int rightTypeId) {
		this._rightTypeId = rightTypeId;
	}
	
	public boolean getIsConnected() {
		return _isConnected;
	}
	public void setIsConnected(boolean isConnected) {
		this._isConnected = isConnected;
	}
	
    public UserBean()
    {
        super();
    }
    public UserBean(int id, String name, String login, String password, int rightTypeId)
    {
        super();
        
        setId(id);
        setName(name);
        setLogin(login);
        setPassword(password);
        setRightTypeId(rightTypeId);
    }
	
    public boolean isUserValid()
    {
    	boolean result = false;
    	DBUserToolbox utb = new DBUserToolbox();
    	
    	result = utb.isUserValid(_login,_password);
    	
    	utb.closeConn();
    	
    	return result;	
    }
    
    public boolean isUserAdmin()
    {
    	boolean result = false;
    	DBUserToolbox utb = new DBUserToolbox();
    	
    	result = utb.isAdmin(this._id);
    	
    	utb.closeConn();
    	
    	return result;
    }
    
    public void getUserRecord()
    {
    	DBUserToolbox utb = new DBUserToolbox();
    	ResultSet rs = utb.getRecord(this._id);
    	
    	try
    	{
    		while (rs.next())
    		{
    			setName(rs.getString("name"));
                setLogin(rs.getString("login"));
                setPassword(rs.getString("password"));
                setRightTypeId(Integer.parseInt( rs.getString("rightTypeId")));
    		}
    	}
    	catch (SQLException e)
    	{
    		System.err.println("Error in getUserRecord:" + e.getMessage());
    	}	
    	finally
    	{
    		utb.closeConn();
    	}
    }
    
    public void createUserRecord()
    {
    	
    }
    public void updateUserRecord()
    {
    	
    }
    public void deleteUserRecord()
    {
    	
    }
	
    
}
