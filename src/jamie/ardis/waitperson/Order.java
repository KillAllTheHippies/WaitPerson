package jamie.ardis.waitperson;

import java.io.Serializable;
import java.util.ArrayList;

public class Order implements Serializable{

	ArrayList<OrderItem> items = new ArrayList<OrderItem>();
	
	

	@Override
	public String toString() {
		return "" + items.toString();
	}

	public Order() {
		
	}

	public ArrayList<OrderItem> getItems() {
		return items;
	}

	public void setItems(ArrayList<OrderItem> items) {
		this.items = items;
	}
	
	public void addItem(OrderItem item)
	{
		items.add(item);
	}
	public void removeItem(int index)
	{
		items.remove(index);
	}
}
