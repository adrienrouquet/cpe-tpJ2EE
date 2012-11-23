package servlet;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import com.sun.org.apache.bcel.internal.generic.NEW;

public class CartBean {
	// Map ProductId:Quantity
	private Map<Integer, Integer> _productMap = new HashMap<Integer, Integer>();
	private DBLocalToolbox _ltb = new DBLocalToolbox();
	private DBProductToolbox _ptb = new DBProductToolbox();
	
	public CartBean()
	{
		initializeMap();
	}
	
	 public void finalize()
     {
          _ltb.closeConn();   
          _ptb.closeConn();
     }
	
	public Map<Integer, Integer> getMapProduct() {
		return _productMap;
	}
	
	public void initializeMap()
	{		
		ResultSet rs = _ltb.getProductIds();
		if(rs != null)
		{
			try {
				rs.first();			
				do{
						_productMap.put(Integer.parseInt(rs.getString("productId")), 0);
				}while(rs.next());
			}
			catch (Exception e)
			{
				System.err.println("Error in CartBean.initializeMap: " + e.getMessage());
			}
				
		}
	}
	
	public ResultSet getProducts()
    {
		return _ltb.getProducts();		
    }
	
	public ResultSet getProduct(int productId)
    {
		return _ltb.getProductRecord(productId);
    }
	
	public Integer getQuantity(int productId)
    {
		return _productMap.get(productId);	
    }
	
	public Integer getStockQuantity(int productId)
    {		
		return _ptb.getStockQuantity(productId); 	
    }

	public void updateCart(int id, int quantity) {		
		if (quantity == 0) {
			deleteFromCart(id);
			return;
		}
		this._productMap.put(id, quantity);
	}
	
	public void deleteFromCart(int id) {
		this._productMap.remove(id);
	}
	
}
