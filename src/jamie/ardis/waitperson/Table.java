package jamie.ardis.waitperson;

import java.io.Serializable;
import java.util.ArrayList;

public class Table implements Serializable{
	

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String tableNum;
	ArrayList<Diner> diners = new ArrayList<Diner>();
	private int numDiners;

	
	// Constructor
	public Table(String tableNum) {
		setTableNum(tableNum);
		
	}
	public void clearTable()
	{
		//reset the number of diners
		numDiners = 0;
		// overwrite the array of diners with a blank array.
		diners = new ArrayList<Diner>();
	}
	
	public int getNumDiners() {
		return numDiners;
	}
	public void setNumDiners(int numDiners) {
		this.numDiners = numDiners;
	}
	
	@Override
	public String toString() {
		return "Table " + tableNum + diners.toString();
	}

	public String getTableNum() {
		return tableNum;
	}

	public void setTableNum(String tableNum) {
		this.tableNum = tableNum;
	}


	public ArrayList<Diner> getDiners() {
		return diners;
	}

	public void setDiners(ArrayList<Diner> diners) {
		this.diners = diners;
	}

	public void addDiner(Diner diner)
	{
		diners.add(diner);
	}
	
	public void removeDiner(int pos)
	{
		diners.remove(pos);
	}
	

}
