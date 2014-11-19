package jamie.ardis.waitperson;

import java.util.ArrayList;

public class Table {
	private int tableID, tableSize;
	ArrayList<Diner> diners = new ArrayList<Diner>();

	// Constructor
	public Table(int tableID, int tableSize) {
		super();
		setTableID(tableID);
		setTableSize(tableSize);
	}

	public int getTableID() {
		return tableID;
	}

	public void setTableID(int tableID) {
		this.tableID = tableID;
	}

	public int getTableSize() {
		return tableSize;
	}

	public void setTableSize(int tableSize) {
		this.tableSize = tableSize;
	}

	public ArrayList<Diner> getDiners() {
		return diners;
	}

	public void setDiners(ArrayList<Diner> diners) {
		this.diners = diners;
	}

	

}
