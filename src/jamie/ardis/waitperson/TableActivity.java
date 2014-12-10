package jamie.ardis.waitperson;

import java.util.ArrayList;

import android.support.v7.app.ActionBarActivity;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class TableActivity extends ActionBarActivity {

	private Spinner spDiners;
	private Button btnTakeOrder;
	private Button btnAddDiner;
	ArrayList<Diner> diners = new ArrayList<Diner>();
	ArrayAdapter<Diner> adapter;
	private Order order = new Order();
	private Table table;
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_table);
		
		
		TextView tvTable = (TextView) findViewById(R.id.tvTable);
		
		addListenerOnBtnAddDiner();
		addListenerOnSpinnerItemSelection();
		addListenerOnBtnTakeOrder();

		if (savedInstanceState == null) {
			Bundle extras = getIntent().getExtras();
			if (extras == null) {
				table = null;
			} else {
				table = (Table) extras.getSerializable("table");
			}
		} else {
			table = (Table) savedInstanceState.getSerializable("table");
		}

		tvTable.setText("Table " + table.getTableNum() + "\tDiners: " + diners.size());
		
		populateDinersToSpinner(table.getDiners());
		diners = table.getDiners();
		refreshDinersList();
		
		

	}
	
	public  void refreshDinersList()
	{
		// put the order for the selected diner in the listview
		TextView tvTable = (TextView) findViewById(R.id.tvTable);
		 ListView lvDiners = (ListView) findViewById(R.id.lvDiners);
		 Diner d =(Diner) spDiners.getSelectedItem();
	        OrderListAdapter adapter = new OrderListAdapter(this, d.getOrder().getItems());
			lvDiners.setAdapter(adapter);
			
			tvTable.setText("Table " + table.getTableNum() + "\tDiners: " + diners.size());
	}
	 @Override
	    protected void onStart() {
	        super.onStart();
	       refreshDinersList();
	    }

	public void launchOrderActivity(View v) {
		Intent intent = new Intent(this, OrderActivity.class);
		// intent.putExtra("table", table.getTableNum());
		startActivity(intent);
	}


	

	public void addDinerToSpinner(ArrayList<Diner> diners) {

		spDiners = (Spinner) findViewById(R.id.spDiners);

		if(diners.size() == 0)
		{
			Diner diner = new Diner(1);
			diners.add(diner);
		}
		else
		{
			Diner diner = new Diner(diners.size() + 1);
			diners.add(diner);
		}
		
		

		DinersAdapter adapter = new DinersAdapter(this, diners);
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

		spDiners.setAdapter(adapter);
		refreshDinersList();
	}
	public void populateDinersToSpinner(ArrayList<Diner> diners) {

		spDiners = (Spinner) findViewById(R.id.spDiners);
		DinersAdapter adapter = new DinersAdapter(this, diners);
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

		spDiners.setAdapter(adapter);
		
	}

	public void addListenerOnSpinnerItemSelection() {
		spDiners = (Spinner) findViewById(R.id.spDiners);
		spDiners.setOnItemSelectedListener(new DinerSelectionListener(){
			@Override
			public void onItemSelected(AdapterView<?> parent, View view, int pos,long id)
			  {
				refreshDinersList();
			  }
		});
	}

	public void addListenerOnBtnAddDiner() {

		btnAddDiner = (Button) findViewById(R.id.btnAddDiner);

		btnAddDiner.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				addDinerToSpinner(diners);
				 refreshDinersList();

			}

		});
	}

	public void addListenerOnBtnTakeOrder() {

		btnTakeOrder = (Button) findViewById(R.id.btnTakeOrder);

		btnTakeOrder.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				// Diner d =(Diner) spDiners.getSelectedItem();
				takeOrder();

			}

		});
	}

	public void takeOrder() {
		Intent intent = new Intent(this, OrderActivity.class);
		Diner d =(Diner) spDiners.getSelectedItem();
		intent.putExtra("order", d.getOrder());

		this.startActivityForResult(intent, 666);

	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, requestCode, data);
		// Check which request we're responding to
		if (requestCode == 666) {
			// Make sure the request was successful
			if (resultCode == RESULT_OK) {

				order = (Order) data.getSerializableExtra("order");
				Diner d = (Diner) spDiners.getSelectedItem();
				d.setOrder(order);
				 refreshDinersList();
			}
		}
	}
	
	public void returnInfo(View v)
	{
		//test code
//		order.addItem(new OrderItem("Coffee", 2.50));
//		order.addItem(new OrderItem("Cake", 2.99));
//		order.addItem(new OrderItem("Pigs Head", 15.99));
		
		Intent returnIntent = new Intent();
		//returnIntent.putExtra("diners", diners);
		table.setDiners(diners);
		returnIntent.putExtra("table", table);
		
		setResult(Activity.RESULT_OK, returnIntent);
		finish();
		
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
