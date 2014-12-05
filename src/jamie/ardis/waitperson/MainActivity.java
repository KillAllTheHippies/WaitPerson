package jamie.ardis.waitperson;





import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import android.support.v7.app.ActionBarActivity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MainActivity extends ActionBarActivity {

	
	ArrayList<Table> tables = new ArrayList<Table>();
	ArrayAdapter<Table> adapter;
	//Table table;
	Table selectedTable;
	ArrayList<Diner> diners;
	private ListView lv;
	private int tableIndex;
	//TablesList list = new TablesList();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		lv = (ListView) findViewById(R.id.lvTables);
		
		for (int i = 0; i < 5; i++) {
			Table t = new Table("" + (i+1));
			tables.add(t);
			
		}
		//writeTables(tables);
		

		TablesAdapter adapter = new TablesAdapter(this, tables);

//		ArrayAdapter<Table> arrayAdapter = new ArrayAdapter<Table>(this,
//				android.R.layout.simple_list_item_1, tables);

		lv.setAdapter(adapter);

		lv.setClickable(true);
		lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1,
					int position, long arg3) {
				tables = readTables();
				
				//selectedTable = (Table) lv.getItemAtPosition(position);
				selectedTable = tables.get(position);
				tableIndex = position;
			
				//launchTableActivity((Table)lv.getItemAtPosition(position));
				launchTableActivity(selectedTable);
				

			}

		});
	}
	@Override
	protected void onStart() {
		super.onStart();
		
		
		tables = readTables();
		
	}
	
	
	public void launchSettingsActivity(View v)
    {
    	Intent intent = new Intent(this, SettingsActivity.class);
    	//intent.putExtra("table", table.getTableNum());
    	startActivity(intent);
    }
	

	public void launchTableActivity(Table table)
    {
    	Intent intent = new Intent(this, TableActivity.class);
    	intent.putExtra("table", table);
    	startActivityForResult(intent,777);
    	
    }
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, requestCode, data);
		// Check which request we're responding to
		if (requestCode == 777) {
			// Make sure the request was successful
			if (resultCode == RESULT_OK) {

				
				
				//get the table from the previous intent
				selectedTable = (Table) data.getSerializableExtra("table");
				//add the table to the arraylist
				tables.set(tableIndex, selectedTable);
				// commit the arraylist of tables to disk
				writeTables(tables);
				
				//tables.remove(tableIndex);
				//tables.add(tableIndex, selectedTable);
				//t.setDiners(diners);
				//selectedTable = t;
				
			}
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
	
	public ArrayList<Table> readTables() {

		String filename = "tables.ser";
		// read the object from file

		FileInputStream fis = null;
		ObjectInputStream in = null;
		try {
			fis = openFileInput(filename);
			in = new ObjectInputStream(fis);
			tables = (ArrayList<Table>) in.readObject();
			in.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return tables;

	}

	private void writeTables(ArrayList<Table> tables) {
		// TODO Auto-generated method stub

		String filename = "tables.ser";
		// save the object to file
		FileOutputStream fos = null;
		ObjectOutputStream out = null;
		try {
			fos = openFileOutput(filename, Context.MODE_PRIVATE);
			out = new ObjectOutputStream(fos);
			out.writeObject(tables);

			out.close();
			fos.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	
}
