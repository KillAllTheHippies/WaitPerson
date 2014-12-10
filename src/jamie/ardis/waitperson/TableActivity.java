package jamie.ardis.waitperson;

import java.util.ArrayList;
import java.util.Locale;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
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
	private static final int MY_DIALOG_ID = 2;

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

		tvTable.setText("Table " + table.getTableNum() + "\tDiners: "
				+ table.getDiners().size());

		populateDinersToSpinner();
		// diners = table.getDiners();
		refreshDinersOrder();

	}

	public void refreshDinersOrder() {
		// put the order for the selected diner in the listview
		TextView tvTable = (TextView) findViewById(R.id.tvTable);
		ListView lvDiners = (ListView) findViewById(R.id.lvDiners);

		if (spDiners.getSelectedItem() != null) {
			Diner d = (Diner) spDiners.getSelectedItem();
			OrderListAdapter adapter = new OrderListAdapter(this, d.getOrder()
					.getItems());
			lvDiners.setAdapter(adapter);
		}
		else // if no diner is selected (or if the table has been cleared..)
		{
			//create a dummy diner with no order and populate the list with its order
			OrderListAdapter adapter = new OrderListAdapter(this, new Diner(0).getOrder()
					.getItems());
			lvDiners.setAdapter(adapter);
		}

		tvTable.setText("Table " + table.getTableNum() + "\tDiners: "
				+ table.getDiners().size());
	}

	@Override
	protected void onStart() {
		super.onStart();
		refreshDinersOrder();
	}

	public void removeDiner(View v) {
		showDialog(MY_DIALOG_ID);

	}

	public void clearTable(View v) {
		table.clearTable();

		populateDinersToSpinner();
		refreshDinersOrder();
	}

	public void refreshTable() {

	}

	public void launchOrderActivity(View v) {
		Intent intent = new Intent(this, OrderActivity.class);
		// intent.putExtra("table", table.getTableNum());
		startActivity(intent);
	}

	public void addDiner() {

		spDiners = (Spinner) findViewById(R.id.spDiners);

		if (table.getDiners().size() == 0) {
			Diner diner = new Diner(1);
			table.addDiner(diner);
			// diners.add(diner);
			table.setNumDiners(1);
		} else if (table.getDiners().size() == 1 && table.getNumDiners() == 0) {
			table.setNumDiners(1);
			Diner diner = new Diner(table.getNumDiners() + 1);
			table.addDiner(diner);
			// diners.add(diner);
			table.setNumDiners(table.getNumDiners() + 1);
		} else {

			Diner diner = new Diner(table.getNumDiners() + 1);
			table.addDiner(diner);
			// diners.add(diner);
			table.setNumDiners(table.getNumDiners() + 1);
		}

		DinersAdapter adapter = new DinersAdapter(this, table.getDiners());
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

		spDiners.setAdapter(adapter);
		refreshDinersOrder();
	}

	public void populateDinersToSpinner() {

		spDiners = (Spinner) findViewById(R.id.spDiners);
		DinersAdapter adapter = new DinersAdapter(this, table.getDiners());
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

		spDiners.setAdapter(adapter);

	}

	public void addListenerOnSpinnerItemSelection() {
		spDiners = (Spinner) findViewById(R.id.spDiners);
		spDiners.setOnItemSelectedListener(new DinerSelectionListener() {
			@Override
			public void onItemSelected(AdapterView<?> parent, View view,
					int pos, long id) {
				refreshDinersOrder();
			}
		});
	}

	public void addListenerOnBtnAddDiner() {

		btnAddDiner = (Button) findViewById(R.id.btnAddDiner);

		btnAddDiner.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				addDiner();
				refreshDinersOrder();

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
		Diner d = (Diner) spDiners.getSelectedItem();
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
				refreshDinersOrder();
			}
		}
	}

	public void returnInfo(View v) {
		// test code
		// order.addItem(new OrderItem("Coffee", 2.50));
		// order.addItem(new OrderItem("Cake", 2.99));
		// order.addItem(new OrderItem("Pigs Head", 15.99));

		Intent returnIntent = new Intent();
		// returnIntent.putExtra("diners", diners);
		// table.setDiners(diners);
		returnIntent.putExtra("table", table);

		setResult(Activity.RESULT_OK, returnIntent);
		finish();

	}

	// dialog for making sure user wants to delete diner
	protected Dialog onCreateDialog(int id) {

		if (id == MY_DIALOG_ID) {
			AlertDialog.Builder builder2 = new AlertDialog.Builder(this);
			builder2.setTitle(R.string.dialog_title);
			builder2.setIcon(android.R.drawable.btn_star);
			builder2.setMessage("");

			// add click listeners to the buttons
			builder2.setPositiveButton(android.R.string.ok,
					new DialogInterface.OnClickListener() {
						public void onClick(DialogInterface dialog, int which) {

							table.removeDiner(spDiners
									.getSelectedItemPosition());
							// diners.remove(spDiners.getSelectedItemPosition());
							populateDinersToSpinner();
							return;
						}
					});

			builder2.setNegativeButton(android.R.string.cancel,
					new DialogInterface.OnClickListener() {
						public void onClick(DialogInterface dialog, int which) {
							Toast.makeText(getApplicationContext(),
									"Clicked Cancel!", Toast.LENGTH_SHORT)
									.show();
							return;
						}
					});

			return builder2.create();

		}// end if
		else
			return null;
	}

	@Override
	protected void onPrepareDialog(int id, Dialog dialog) {
		super.onPrepareDialog(id, dialog);

		AlertDialog myDialog = (AlertDialog) dialog;

		Locale.getDefault();
		myDialog.setMessage("Are you sure you want to remove Diner "
				+ ((Diner) spDiners.getSelectedItem()).getDinerNum());

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
