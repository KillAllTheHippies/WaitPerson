package jamie.ardis.waitperson;



public class Diner {

	private Order order;
	private int dinerNum; //initialised for testing
	
	public Diner(int dinerNum) 
	{
		setDinerNum(dinerNum);
	} // constructor
	
	public int getDinerNum() {
		return dinerNum;
	}

	public void setDinerNum(int dinerNum) {
		this.dinerNum = dinerNum;
	}

	

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	@Override
	public String toString() {
		return "Diner " + dinerNum ;
	}
	
	
		
	
	
}