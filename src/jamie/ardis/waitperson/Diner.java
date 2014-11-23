package jamie.ardis.waitperson;

import java.io.Serializable;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;



public class Diner implements Serializable{

	private Order order;
	private int dinerNum; 
	private Context context;
	
	
	
	public Diner(int dinerNum, Context context) 
	{
		setDinerNum(dinerNum);
		this.context = context;
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
	
	@SuppressWarnings("static-access")
	public void takeOrder() // Launch an intent to select order items to add to order
	{
		Intent intent = new Intent(context, OrderActivity.class);
		//Bundle bundle = new Bundle();
		
		//bundle.putSerializable("order", order); // add the (empty) order to a bundle
    	intent.putExtra("order", order); // Add the empty order to the next activity (to be populated with an order)
		intent.addFlags(intent.FLAG_ACTIVITY_NEW_TASK); // add this flag for some reason, Computers! *throws hands in air*
    	context.startActivity(intent);
	}
		
	
	
}