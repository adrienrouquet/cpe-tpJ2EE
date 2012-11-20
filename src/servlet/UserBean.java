package servlet;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

import sun.misc.BASE64Encoder;

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
	public void setPassword(String password, Boolean notEncrypted) {
		if(notEncrypted)
		{
			MessageDigest md = null;
			try {
				md = MessageDigest.getInstance("SHA");
			} catch (NoSuchAlgorithmException e) {
				System.out.println("Error in UserBean.setPassword " + e.getMessage());
			}
			try {
				md.update(password.getBytes("UTF-8"));
			} catch (UnsupportedEncodingException e) {
				System.out.println("Error in UserBean.setPassword " + e.getMessage());
			}
			byte digest[] = md.digest();
			this._password = (new BASE64Encoder()).encode(digest);
		}
		else
		{
			this._password = password;
		}
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
		System.out.println("SETACTION: //" + action + "");
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
    	setPassword(password,true);
    }
	
    public boolean isValid()
    {
    	boolean result = false;
    	DBLocalToolbox ltb = new DBLocalToolbox();
    	
    	result = ltb.isValid(_login,_password);
    	
    	ltb.closeConn();
    	
    	return result;	
    }
    
    public boolean userExists()
    {
    	boolean result = false;
    	DBLocalToolbox ltb = new DBLocalToolbox();
    	
    	result = ltb.userExists(this._login);
    	if(result)
    		System.out.println("Error in UserBean.userExists: User already exists");
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
    	
    	String[] rightTypeId = userMap.get("rightTypeId");
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
    		setPassword(password[0],true);
    }
    
    public boolean getRecord()
    {
    	DBLocalToolbox ltb = new DBLocalToolbox();
    	ResultSet rs = null;
    	
    	if (this._id >0)
    	{
    		rs = ltb.getRecord(this._id);
    	}
    	else if (isValid())
    	{
    		rs = ltb.getRecord(this._login, this._password);
    	}
    	else 
    	{
    		System.err.println("Error in UserBean.getRecord: fields are not correct. Cannot get record");
    		return false;
    	}    
    	
    	
    	//In case we did try to get a record, we are going to fetch the data into the UserBean
    	try
    	{
    		rs.first();
    		do
    		{
    			setId(Integer.parseInt( rs.getString("id")));
    			setName(rs.getString("name"));
                setLogin(rs.getString("login"));
                setPassword(rs.getString("password"),false);
                setRightTypeId(Integer.parseInt( rs.getString("rightTypeId")));
    		}while (rs.next());  
    		ltb.closeConn();
    		return true;
    	}
    	catch (SQLException e)
    	{
    		System.err.println("Error in UserBean.getRecord:" + e.getMessage());
    	} 	
    	return false;
    }
    
    public boolean addRecord()
    {    	
    	DBLocalToolbox ltb = new DBLocalToolbox();
    	if(this._name != "" && this._login != "" && this._password != "" && this._rightTypeId != 0)
    	{
    		if(ltb.createRecord(this._name, this._login, this._password, this._rightTypeId))
    		{
    			ltb.closeConn();
    			return true;
    		}
    	}
    	System.err.println("Error in UserBean.addRecord: No record added");
    	return false;
    }
    
    public boolean updateRecord()
    {
    	boolean done = false;
    	
    	DBLocalToolbox ltb = new DBLocalToolbox();
    	if(this._id != 0 && this._name != "" && this._login != "" && this._rightTypeId != 0)
    	{
    		done = ltb.updateRecord(this._id, this._name, this._login, this._rightTypeId);
    	}
    	
    	ltb.closeConn();
    	
    	return done;
    }
    
    public boolean deleteRecord()
    {
    	boolean done = false;
    	
    	DBLocalToolbox ltb = new DBLocalToolbox();
    	done = ltb.deleteRecord(this._id);
    	
    	ltb.closeConn();
    	
    	return done;
    }
}
