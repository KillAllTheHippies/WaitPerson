package jamie.ardis.waitperson;

import java.util.ArrayList;
import java.util.HashMap;

public class TablesList {
	
	ArrayList<Table> tables;
	HashMap <String, Table> lookup;//A hashmap works like a lookup table
	
	public TablesList(){
		tables= new ArrayList<Table>();
		lookup = new HashMap<String, Table>();
		//TODO hook into SqlLite database instead
		
		// create some tables and feck them into the arraylist
				
				for (int i = 0; i < 5; i++) {
					Table t = new Table("" + (i+1));
					tables.add(t);
					
				}
		
		createLookupTable();
	}

	private void createLookupTable() {
		
		for(Table t : tables) 
			//get the name (a string), and make this name the key 
			//by which the table can be looked up
			lookup.put(t.getTableNum(), t);
	}

	public ArrayList<Table> getTables() {
		return tables;
	}
	
	public Table getTable(String tableNum){
		//get a User object from the dictionary, dictionary key = tableNUm
		Table t = lookup.get(tableNum);
		return t;
	}
		
}