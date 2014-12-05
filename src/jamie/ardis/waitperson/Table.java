package jamie.ardis.waitperson;

import java.io.Serializable;
import java.util.ArrayList;

public class Table implements Serializable{
	

	private String tableNum;
	ArrayList<Diner> diners = new ArrayList<Diner>();

	// Constructor
	public Table(String tableNum) {
		setTableNum(tableNum);
		
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

	

}
