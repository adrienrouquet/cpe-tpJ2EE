package servlet;

import java.sql.ResultSet;
import java.sql.SQLException;

public class RightTypeBean
{
	private int _id = 0;
	private String _name = "";
	
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
	    
    public ResultSet getRightTypes()
    {
    	DBLocalToolbox ltb = new DBLocalToolbox();
    	return ltb.getUserRightTypes();
    }
	
    
}
