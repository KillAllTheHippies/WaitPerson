package jamie.ardis.waitperson;

import java.util.ArrayList;

public class Table {
	private int tableNum;
	ArrayList<Diner> diners = new ArrayList<Diner>();

	// Constructor
	public Table(int tableNum) {
		setTableNum(tableNum);
		
	}

	public int getTableNum() {
		return tableNum;
	}

	public void setTableNum(int tableID) {
		this.tableNum = tableID;
	}


	public ArrayList<Diner> getDiners() {
		return diners;
	}

	public void setDiners(ArrayList<Diner> diners) {
		this.diners = diners;
	}

	

}
