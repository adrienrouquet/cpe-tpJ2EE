package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UserBean
{
	private static final long serialVersionUID = 1L;
     
	private int _id = 0;
	private String _name = "";
	private String _login = "";
	private String _password = "";
	private int _rights = 0;
	
    public UserBean()
    {
        super();
    }
    public UserBean(int id, String name, String login, String password, int rights)
    {
        super();
        
        _id = id;
        _name = name;
        _login = login;
        _password = password;
        _rights = rights;
    }

}
