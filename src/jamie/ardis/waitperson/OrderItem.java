package jamie.ardis.waitperson;

import java.io.Serializable;

public class OrderItem implements Serializable{

	
	String extraInfo;
	String name;
	int quantity;
	double price;
	
	
	public OrderItem(String name, double price) {
		
		setName(name);
		setPrice(price);
	}
	
	public String getExtraInfo() {
		return extraInfo;
	}
	public void setExtraInfo(String extraInfo) {
		this.extraInfo = extraInfo;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	@Override
	public String toString() {
		return "" + name + ", €" + price + "]";
	}
	
	
}
