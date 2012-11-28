package servlet;

import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Map;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import ejb.DBProductToolboxRemote;

public class CartBean {
	// Map ProductId:Quantity
	private Map<Integer, Integer> _productMap = new HashMap<Integer, Integer>();
	private DBLocalToolbox _ltb = new DBLocalToolbox();
	private DBProductToolboxRemote _ptb = null;
	
	private Context cnt = null;
	
	public CartBean() throws NamingException
	{
		cnt = new InitialContext();
		_ptb = (DBProductToolboxRemote) cnt.lookup("DBProductToolbox");
	}
    
	
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
