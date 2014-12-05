package jamie.ardis.waitperson;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import android.support.v7.app.ActionBarActivity;
import android.content.Context;
import android.os.Bundle;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class SettingsActivity extends ActionBarActivity {

	ArrayList<Table> tables = new ArrayList<Table>();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_settings);
		TextView tvTest = (TextView) findViewById(R.id.tvTest);

		Table t = new Table("100");
		tables.add(t);
		writeTables(tables);
		ArrayList<Table> tables = readTables();

		// String filename = "diners.ser";
		// // save the object to file
		// FileOutputStream fos = null;
		//
		// ObjectOutputStream out = null;
		// try {
		//
		// fos = openFileOutput(filename, Context.MODE_PRIVATE);
		// out = new ObjectOutputStream(fos);
		// out.writeObject(d);
		// d = new Diner(3);
		// out.close();
		// fos.close();
		// } catch (Exception ex) {
		// ex.printStackTrace();
		// }
		// // read the object from file
		// // save the object to file
		// FileInputStream fis = null;
		// ObjectInputStream in = null;
		// try {
		//
		// fis = openFileInput(filename);
		// in = new ObjectInputStream(fis);
		// d = (Diner) in.readObject();
		// in.close();
		// } catch (Exception ex) {
		// ex.printStackTrace();
		// }
		tvTest.setText(tables.get(0).toString());
	}

	public ArrayList<Table> readTables() {

		String filename = "tables.ser";
		// read the object from file

		FileInputStream fis = null;
		ObjectInputStream in = null;
		try {
			fis = openFileInput(filename);
			in = new ObjectInputStream(fis);
			ArrayList<Table> tables = (ArrayList<Table>) in.readObject();
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
