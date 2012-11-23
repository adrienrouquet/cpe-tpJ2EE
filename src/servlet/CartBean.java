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
		
	 public void finalize()
     {
          _ltb.closeConn();   
          _ptb.closeConn();
     }
	
	public Map<Integer, Integer> getMapProduct() {
		return _productMap;
	}
	
	public ResultSet getProducts()
    {
		return _ltb.getProducts();		
    }
	
	public ResultSet getProduct(int productId)
    {
		return _ltb.getProductRecord(productId);
    }
	
	public int getDelay(int productId)
    {
		return _ptb.getDelay(productId);
    }
	
	public Integer getQuantity(int productId)
    {
		if(_productMap.get(productId) == null)
			return 0;
		return _productMap.get(productId);
    }
	
	public Integer getStockQuantity(int productId)
    {		
		return _ptb.getStockQuantity(productId); 	
    }

	public void updateCart(int id, int quantity) {		
		if(id != 0)
		{
			if (quantity == 0) {
				deleteFromCart(id);
				return;
			}
			this._productMap.put(id, quantity);
		}
		else
			System.out.println("Error in CartBean.updateCart: productId is null");
	}
	
	public void deleteFromCart(int id) {
		this._productMap.remove(id);
	}
	
}
