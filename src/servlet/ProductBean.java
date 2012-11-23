package servlet;

public class ProductBean {
	private int _id;
	private String _name;
	private String _description;
	private int _price;
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
	
}
