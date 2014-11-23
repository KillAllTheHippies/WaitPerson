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

	ArrayList<Diner> diners = new ArrayList<Diner>();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_settings);
		TextView tvTest = (TextView) findViewById(R.id.tvTest);

		Diner d = new Diner(1, getApplicationContext());
		diners.add(d);
		writeDiners(diners);
		ArrayList<Diner> diners1 = readDiners();

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
		tvTest.setText(diners1.get(0).toString());
	}

	public ArrayList<Diner> readDiners() {

		String filename = "diners.ser";
		// read the object from file

		FileInputStream fis = null;
		ObjectInputStream in = null;
		try {
			fis = openFileInput(filename);
			in = new ObjectInputStream(fis);
			ArrayList<Diner> diners = (ArrayList<Diner>) in.readObject();
			in.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return diners;

	}

	private void writeDiners(ArrayList<Diner> diners) {
		// TODO Auto-generated method stub

		String filename = "diners.ser";
		// save the object to file
		FileOutputStream fos = null;
		ObjectOutputStream out = null;
		try {
			fos = openFileOutput(filename, Context.MODE_PRIVATE);
			out = new ObjectOutputStream(fos);
			out.writeObject(diners);

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
