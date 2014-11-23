package jamie.ardis.waitperson;

import java.util.ArrayList;

public class Menu {

	ArrayList<OrderItem> mains = new ArrayList<OrderItem>();
	ArrayList<OrderItem> drinks = new ArrayList<OrderItem>();
	ArrayList<OrderItem> starters = new ArrayList<OrderItem>();
	ArrayList<OrderItem> deserts = new ArrayList<OrderItem>();
	
	public Menu() {
		super();
		mains.add(new OrderItem("Chicken", 10.99));
		mains.add(new OrderItem("Pork", 11.99));
		mains.add(new OrderItem("Lamb", 12.99));
		mains.add(new OrderItem("Fish", 15.99));
		starters.add(new OrderItem("Spring roll", 4.99));
		starters.add(new OrderItem("Chicken Wings", 5.99));
		starters.add(new OrderItem("Olives", 4.99));
		starters.add(new OrderItem("Pappadoms", 2.99));
		drinks.add(new OrderItem("Wine", 5.99));
		drinks.add(new OrderItem("Coffee", 2.99));
		drinks.add(new OrderItem("Beer", 4.99));
		drinks.add(new OrderItem("Sparking Water", 1.99));
		deserts.add(new OrderItem("Chocolate Cake",6.99));
		deserts.add(new OrderItem("Fruit Salad",5.99));
		deserts.add(new OrderItem("Ice cream",3.99));
	
	}
	
	
	public ArrayList<OrderItem> getMains() {
		return mains;
	}


	public void setMains(ArrayList<OrderItem> mains) {
		this.mains = mains;
	}


	public ArrayList<OrderItem> getDrinks() {
		return drinks;
	}


	public void setDrinks(ArrayList<OrderItem> drinks) {
		this.drinks = drinks;
	}


	public ArrayList<OrderItem> getStarters() {
		return starters;
	}


	public void setStarters(ArrayList<OrderItem> starters) {
		this.starters = starters;
	}


	public ArrayList<OrderItem> getDeserts() {
		return deserts;
	}


	public void setDeserts(ArrayList<OrderItem> deserts) {
		this.deserts = deserts;
	}


	public void addMain(OrderItem item)
	{
		mains.add(item);
	}
	public void removeMain(int index)
	{
		mains.remove(index);
	}
	
	public void addDrink(OrderItem item)
	{
		drinks.add(item);
	}
	public void removeDrink(int index)
	{
		drinks.remove(index);
	}
	public void addDesert(OrderItem item)
	{
		deserts.add(item);
	}
	public void removeDesert(int index)
	{
		deserts.remove(index);
	}
	public void addStarter(OrderItem item)
	{
		starters.add(item);
	}
	public void removeStarter(int index)
	{
		starters.remove(index);
	}
}
