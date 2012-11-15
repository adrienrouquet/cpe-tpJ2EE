package servlet;

import java.sql.ResultSet;

public class AdminBean
{
	
	public ResultSet getUsers()
    {
		return DBUserToolbox.getUsers();		
    }
    
}

//public ArrayList getUsers()
//{
//	
//	String query = "SELECT u.id as 'userId', u.name as 'userName', u.login, u.password, u.rightTypeId as 'rightTypeId', rt.name as 'rightTypeName' from users as u inner join rightTypes as rt on u.rightTypeId = rt.id";
//
//}