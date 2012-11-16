package servlet;

import java.sql.ResultSet;

public class AdminBean
{
	
	public ResultSet getUsers()
    {
		DBLocalToolbox ltb = new DBLocalToolbox();
		return ltb.getUsers();		
    }
    
}