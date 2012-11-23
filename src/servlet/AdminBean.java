package servlet;

import java.sql.ResultSet;

public class AdminBean
{
	private DBLocalToolbox _ltb = new DBLocalToolbox();
	public ResultSet getUsers()
    {
		return _ltb.getUsers();	
    }
    
}