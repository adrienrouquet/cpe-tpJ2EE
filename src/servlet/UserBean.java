package servlet;

public class UserBean
{
	private int _id = 0;
	private String _name = "";
	private String _login = "";
	private String _password = "";
	private int _rightTypeId = 0;
	
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
    	return 	DBUserToolbox.isUserValid(_login,_password);
    }
    
    public boolean isUserAdmin()
    {
    	return DBUserToolbox.isAdmin(this._id);
    }
    
    public void getUserRecord()
    {
    	
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
