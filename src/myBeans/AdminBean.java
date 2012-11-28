package myBeans;

import java.sql.ResultSet;
import myClasses.DBLocalToolbox;

public class AdminBean
{
	private DBLocalToolbox _ltb = new DBLocalToolbox();
	public ResultSet getUsers()
    {
		return _ltb.getUsers();	
    }
    
}