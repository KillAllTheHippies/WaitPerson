package jamie.ardis.waitperson;



public class Diner {

	private Order order;
	private String dinerNum = "1"; //initialised for testing
	
	public String getDinerNum() {
		return dinerNum;
	}

	public void setDinerNum(String dinerNum) {
		this.dinerNum = dinerNum;
	}

	public Diner() {} // constructor

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}
	
	
		
	
	
}