package servlet;

import java.util.Map;

public class CartBean {
	// Map ProductId:Quantity
	private Map<Integer, Integer> _mapProduct;

	public Map<Integer, Integer> getMapProduct() {
		return _mapProduct;
	}

	public void setMapProduct(Map<Integer, Integer> mapProduct) {
		this._mapProduct = mapProduct;
	}

	public void updateCart(int id, int quantity) {		
		if (quantity == 0) {
			deleteFromCart(id);
			return;
		}
		this._mapProduct.put(id, quantity);
	}
	
	public void deleteFromCart(int id) {
		this._mapProduct.remove(id);
	}
	
}
