package jamie.ardis.waitperson;

import java.util.ArrayList;

import android.support.v7.app.ActionBarActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

public class TableActivity extends ActionBarActivity {

	private Spinner spDiners;
	private Button btnAddDiner;
	ArrayList<Diner> diners = new ArrayList<Diner>();
	private int dinerNum = 1;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_table);

		// addItemsOnSpinner2();
		addListenerOnButton();
		addListenerOnSpinnerItemSelection();

	}
	
	public void launchOrderActivity(View v)
    {
    	Intent intent = new Intent(this, OrderActivity.class);
    	//intent.putExtra("table", table.getTableNum());
    	startActivity(intent);
    }

	public ArrayList<Diner> getDiners() {
		return diners;
	}

	public void setDiners(ArrayList<Diner> diners) {
		this.diners = diners;
	}

	
	public int getDinerNum() {
		return dinerNum;
	}

	public void setDinerNum(int dinerNum) {
		this.dinerNum = dinerNum;
	}

	public void addDinerToSpinner(ArrayList<Diner> diners, int dinerNum) {

		spDiners = (Spinner) findViewById(R.id.spDiners);
		
		Diner diner = new Diner(dinerNum);
		dinerNum++;
		setDinerNum(dinerNum);
		diners.add(diner);
		

		DinersAdapter adapter = new DinersAdapter(this, diners);

//		 ArrayAdapter<Diner> adapter = new ArrayAdapter<Diner>(this,
//		 android.R.layout.simple_spinner_item, diners);
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

		spDiners.setAdapter(adapter);
	}

	public void addListenerOnSpinnerItemSelection() {
		spDiners = (Spinner) findViewById(R.id.spDiners);
		spDiners.setOnItemSelectedListener(new DinerSelectionListener());
	}

	// get the selected dropdown list value
	public void addListenerOnButton() {

		//spDiners = (Spinner) findViewById(R.id.spDiners);
		btnAddDiner = (Button) findViewById(R.id.btnAddDiner);

		btnAddDiner.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				addDinerToSpinner(diners, dinerNum);
				 Toast.makeText(
				 TableActivity.this,
				 "OnClickListener : " + "\nSpinner 2 : "
				 + String.valueOf(spDiners.getSelectedItem()),
				 Toast.LENGTH_SHORT).show();
			}

		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.table, menu);
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
