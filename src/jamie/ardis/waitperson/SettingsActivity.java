package jamie.ardis.waitperson;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.GridView;
import android.widget.TextView;

public class SettingsActivity extends ActionBarActivity {

	ArrayList<Table> tables;

	private static final int FRAGMENT_RESULT = 10;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_settings);
		

//		 GridView gridview = (GridView) findViewById(R.id.menuGrid);
//	     gridview.setAdapter(new ImageAdapter(this));
		
		tables = readTables();

		
	}
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, requestCode, data);
		// Check which request we're responding to
		if (requestCode == FRAGMENT_RESULT) {
			// Make sure the request was successful
			if (resultCode == RESULT_OK) {

				
				
				//get the table from the previous intent
				Table selectedTable = (Table) data.getSerializableExtra("table");
				// read the tables from the file
				//tables = readTables();
						
				//add the table to the arraylist
				tables.set(MainActivity.tableIndex, selectedTable);
				// commit the arraylist of tables to disk
				writeTables(tables);
				Intent returnIntent = new Intent();
				returnIntent.putExtra("table", selectedTable);

				setResult(Activity.RESULT_OK, returnIntent);
				finish();
				
				
			}
		}
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

		String filename = "diners.ser";
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

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.settings, menu);
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
}
