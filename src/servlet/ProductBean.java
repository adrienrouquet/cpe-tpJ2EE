package servlet;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ProductBean {
	private int _id;
	private String _name;
	private String _description;
	private int _price;
	private String _imgUrl;
	private int _stockQuantity;
	private int _delay;
	
	public ProductBean() {
		
	}
	
	public int getId() {
		return _id;
	}
	public void setId(int id) {
		this._id = id;
	}
	public String getName() {
		return _name;
	}
	public void setName(String name) {
		this._name = name;
	}
	public String getDescription() {
		return _description;
	}
	public void setDescription(String description) {
		this._description = description;
	}
	public int getPrice() {
		return _price;
	}
	public void setPrice(int price) {
		this._price = price;
	}
	
	public String getImgUrl() {
		return _imgUrl;
	}
	public void setImgUrl(String imgUrl) {
		this._imgUrl = imgUrl;
	}
	
	public int getStockQuantity() {
		return _stockQuantity;
	}

	public void setStockQuantity(int _stockQuantity) {
		this._stockQuantity = _stockQuantity;
	}

	public int getDelay() {
		return _delay;
	}

	public void setDelay(int delay) {
		this._delay = delay;
	}
	
	public boolean getRecord()
    {
    	DBLocalToolbox ltb = new DBLocalToolbox();
    	DBProductToolbox utb = new DBProductToolbox();
    	ResultSet rs1 = null;
    	ResultSet rs2 = null;
    	Boolean query1 = false;
    	Boolean query2 = false;
    	
    	if (this._id >0)
    	{
    		rs1 = ltb.getProductRecord(this._id);
    		rs2 = utb.getRecord(this._id);
    	}
    	else
    	{
    		System.err.println("Error in ProductBean.getRecord: fields are not correct. Cannot get record");
    		return false;
    	} 
    	
    	
    	//In case we did try to get a record, we are going to fetch the data into the ProductBean
    	try
    	{
    		rs1.first();
    		do
    		{
    			setId(Integer.parseInt( rs1.getString("productId")));
    			setName(rs1.getString("productName"));
                setDescription(rs1.getString("description"));
                setPrice(Integer.parseInt(rs1.getString("price")));
                setImgUrl(rs1.getString("imgUrl"));
    		}while (rs1.next());
    		ltb.closeConn();
    		query1 = true;
    		
    		rs2.first();
    		do
    		{
    			setStockQuantity(Integer.parseInt( rs2.getString("stockQuantity")));
    			setDelay(Integer.parseInt(rs2.getString("delay")));
    		}while (rs2.next());
    		utb.closeConn();
    		query2 = true;
    	}
    	catch (SQLException e)
    	{
    		System.err.println("Error in ProductBean.getRecord:" + e.getMessage());
    	}
    	return (query1 && query2);
    }
	
	public ResultSet getProducts()
    {
    	DBLocalToolbox ltb = new DBLocalToolbox();
    	ResultSet rs = null;
    	
    	if (this._id >0)
    	{
    		rs = ltb.getProducts();
    	}
    	else
    	{
    		System.err.println("Error in ProductBean.getProducts: fields are not correct. Cannot get record");
    		return null;
    	} 
    	
    	
    	//In case we did try to get a record, we are going to fetch the data into the ProductBean
    	try
    	{
    		rs1.first();
    		do
    		{
    			rs2 = utb.getRecord(Integer.parseInt(rs1.getString("productId")));
    			
    		}while (rs1.next());
    		ltb.closeConn();
    		query1 = true;
    		
    		rs2.first();
    		do
    		{
    			setStockQuantity(Integer.parseInt( rs2.getString("stockQuantity")));
    			setDelay(Integer.parseInt(rs2.getString("delay")));
    		}while (rs2.next());
    		utb.closeConn();
    		query2 = true;
    	}
    	catch (SQLException e)
    	{
    		System.err.println("Error in ProductBean.getRecord:" + e.getMessage());
    	}
    	return (query1 && query2);
    }
}
