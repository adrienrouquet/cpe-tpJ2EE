package servlet;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

public class UserBean
{
	private int _id = 0;
	private String _name = "";
	private String _login = "";
	private String _password = "";
	private int _rightTypeId = 0;
	private boolean _isConnected = false;
	private String _action = "view";
	
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
	
	public String getAction() {
		return _action;
	}
	public void setAction(String action) {
		this._action = action;
	}
	
    public UserBean()
    {
        super();
    }
    public UserBean(int id)
    {
        super();
        
        setId(id);
    }
    public UserBean(String name, String password)
    {
    	super();
    	
    	setName(name);
    	setPassword(password);
    }
	
    public boolean isUserValid()
    {
    	boolean result = false;
    	DBLocalToolbox ltb = new DBLocalToolbox();
    	
    	result = ltb.isUserValid(_login,_password);
    	
    	ltb.closeConn();
    	
    	return result;	
    }
    
    public boolean userExists()
    {
    	boolean result = false;
    	DBLocalToolbox ltb = new DBLocalToolbox();
    	
    	result = ltb.userExists(this._login);
    	
    	ltb.closeConn();
    	
    	return result;	
    }
    
    public boolean isAdmin()
    {
    	boolean result = false;
    	DBLocalToolbox ltb = new DBLocalToolbox();
    	
    	result = ltb.isAdmin(this._id);
    	
    	ltb.closeConn();
    	
    	return result;
    }

    public void createUserMap(Map<String, String[]> userMap)
    {
    	String[] id = userMap.get("userId");
    	if (id == null)
    	{
    		setId(0);
    	}
    	else
    	{
        	setId(Integer.parseInt(id[0]));
    	}
    	
    	String[] rightTypeId = userMap.get("userId");
    	if (rightTypeId == null)
    	{
    		setRightTypeId(0);
    	}
    	else
    	{
    		setRightTypeId(Integer.parseInt(rightTypeId[0]));
    	}
    	
    	String[] name = userMap.get("userName");
    	if(name != null)
    		setName(name[0]);
    	
    	String[] login = userMap.get("login");
    	if(login != null)
    		setLogin(login[0]);
    	
    	String[] password = userMap.get("password");
    	if(password != null)
    		setPassword(password[0]);
    }
    
    public boolean getRecord()
    {
    	boolean done = false;
    	DBLocalToolbox ltb = new DBLocalToolbox();
    	ResultSet rs = null;
    	
    	if (this._id >0)
    	{
    		rs = ltb.getRecord(this._id);
    	}
    	else if (isUserValid())
    	{
    		rs = ltb.getRecord(this._login, this._password);
    	}
    	else return false;
    	
    	try
    	{
    		rs.first();
    		do
    		{
    			setName(rs.getString("name"));
                setLogin(rs.getString("login"));
                setPassword(rs.getString("password"));
                setRightTypeId(Integer.parseInt( rs.getString("rightTypeId")));
    		}while (rs.next());
    		
    		done = true;
    	}
    	catch (SQLException e)
    	{
    		System.err.println("Error in getUserRecord:" + e.getMessage());
    	}	
    	finally
    	{
    		ltb.closeConn();
    	}
    	
    	return done;
    }
    
    public boolean addRecord()
    {
    	DBLocalToolbox ltb = new DBLocalToolbox();
    	if(this._id != 0 && this._name != "" && this._login != "" && this._password != "" && this._rightTypeId != 0)
    	{
    		return ltb.createRecord(this._name, this._login, this._password, this._rightTypeId);    		
    	}
    	return false;
    }
    
    public boolean updateRecord()
    {
    	DBLocalToolbox ltb = new DBLocalToolbox();
    	if(this._id != 0 && this._name != "" && this._login != "" && this._password != "" && this._rightTypeId != 0)
    	{
    		return ltb.updateRecord(this._id, this._name, this._login, this._password, this._rightTypeId);
    	}
    	return false;
    }
    
    public boolean deleteRecord()
    {
    	DBLocalToolbox ltb = new DBLocalToolbox();
    	return ltb.deleteRecord(this._id);
    }
}
