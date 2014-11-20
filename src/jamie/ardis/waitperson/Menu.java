package jamie.ardis.waitperson;

import java.util.ArrayList;

public class Menu {

	ArrayList<OrderItem> menu = new ArrayList<OrderItem>();
	
	public Menu(ArrayList<OrderItem> menu) {
		super();
		this.menu = menu;
	}
	
	
	public void addItem(OrderItem item)
	{
		menu.add(item);
	}
	public void removeItem(int index)
	{
		menu.remove(index);
	}
	
}
