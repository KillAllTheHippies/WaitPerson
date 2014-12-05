package jamie.ardis.waitperson;

import java.io.Serializable;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;



public class Diner implements Serializable{

	private Order order = new Order();
	private int dinerNum; 
	
	
	
	
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
		return "Diner " + dinerNum +  " " +getOrder() ;
	}
	
	
		
	
	
}