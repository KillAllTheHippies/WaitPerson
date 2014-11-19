package jamie.ardis.waitperson;

import java.util.ArrayList;

public class Order {

	ArrayList<OrderItem> items = new ArrayList<OrderItem>();

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
