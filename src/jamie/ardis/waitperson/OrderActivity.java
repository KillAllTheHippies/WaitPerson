package jamie.ardis.waitperson;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.TextView;

public class OrderActivity extends ActionBarActivity {

	Order order = new Order();
	MenuItems menu = new MenuItems();
	ArrayList<OrderItem> activeList;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_order);
		TextView tvDinersDisplay = (TextView) findViewById(R.id.tvDinerDisplay);
		// addMenuToSpinners();
		order = (Order) getIntent().getSerializableExtra("order");
		activeList = menu.getStarters();
		final GridView menuGrid = (GridView) findViewById(R.id.menuGrid);

		RadioButton rdbStarters = (RadioButton) findViewById(R.id.rdbStarters);
		
//		OnClickListener imageListener = new OnClickListener() {
//		public void onClick(View v) {
//			// put some functionality here for imagebutton
//			order.addItem(new OrderItem("test",1.99));
//
//		}
//	};
//	imgBtn.setOnClickListener(imageListener);
//	
		
		OnClickListener startersListener = new OnClickListener() {
			public void onClick(View v) {
				activeList = menu.getStarters();
				menuGrid.setAdapter(new GridAdapter(getApplication(),activeList));

			}
		};

		RadioButton rdbMains = (RadioButton) findViewById(R.id.rdbMains);
		OnClickListener mainsListener = new OnClickListener() {
			public void onClick(View v) {
				activeList = menu.getMains();
				menuGrid.setAdapter(new GridAdapter(getApplication(),activeList));

			}
		};

		RadioButton rdbDeserts = (RadioButton) findViewById(R.id.rdbDeserts);
		OnClickListener desertsListener = new OnClickListener() {
			public void onClick(View v) {
				activeList = menu.getDeserts();
				menuGrid.setAdapter(new GridAdapter(getApplication(),activeList));

			}
		};

		RadioButton rdbDrinks = (RadioButton) findViewById(R.id.rdbDrinks);
		OnClickListener drinksListener = new OnClickListener() {
			public void onClick(View v) {
				activeList = menu.getDrinks();
				menuGrid.setAdapter(new GridAdapter(getApplication(),activeList));

			}
		};

		// final GridView menuGrid = (GridView) findViewById(R.id.menuGrid);

		OnItemClickListener mOnClickGridCell = new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				OrderItem item = (OrderItem) menuGrid
						.getItemAtPosition(position);
				order.addItem(item);
			}
		};
		
		menuGrid.setAdapter(new GridAdapter(this,activeList));
		menuGrid.setOnItemClickListener(mOnClickGridCell);
		
		
		rdbStarters.setOnClickListener(startersListener);
		rdbMains.setOnClickListener(mainsListener);
		rdbDeserts.setOnClickListener(desertsListener);
		rdbDrinks.setOnClickListener(drinksListener);

//		 menuGrid.setOnItemClickListener(new
//		 android.widget.AdapterView.OnItemClickListener() {
//		 @Override
//		 public void onItemClick(AdapterView<?> parent, View view,int
//		 position, long id) {
//		
//		 OrderItem item = (OrderItem) menuGrid.getItemAtPosition(position);
//		 order.addItem(item);
//		
//		 }
//		 });

		

		

	}

	// public void addStarter(View v)
	// {
	// Spinner spStarters = (Spinner) findViewById(R.id.spStarters);
	// order.addItem((OrderItem)spStarters.getSelectedItem());
	// }
	//
	// public void addMain(View v)
	// {
	// Spinner spMains = (Spinner) findViewById(R.id.spMains);
	// order.addItem((OrderItem)spMains.getSelectedItem());
	// }
	// public void addDesert(View v)
	// {
	//
	// Spinner spDeserts = (Spinner) findViewById(R.id.spDeserts);
	// order.addItem((OrderItem)spDeserts.getSelectedItem());
	//
	// }
	// public void addDrink(View v)
	// {
	//
	// Spinner spDrinks = (Spinner) findViewById(R.id.spDrinks);
	// order.addItem((OrderItem)spDrinks.getSelectedItem());
	//
	// }

	public void returnInfo(View v) {
		// test code
		// order.addItem(new OrderItem("Coffee", 2.50));
		// order.addItem(new OrderItem("Cake", 2.99));
		// order.addItem(new OrderItem("Pigs Head", 15.99));

		Intent returnIntent = new Intent();
		returnIntent.putExtra("order", order);
		setResult(Activity.RESULT_OK, returnIntent);
		finish();
	}

	// public void addMenuToSpinners() {
	//
	// Spinner spStarters = (Spinner) findViewById(R.id.spStarters);
	// Spinner spMains = (Spinner) findViewById(R.id.spMains);
	// Spinner spDeserts = (Spinner) findViewById(R.id.spDeserts);
	// Spinner spDrinks = (Spinner) findViewById(R.id.spDrinks);
	//
	// MenuAdapter starterAdapter = new MenuAdapter(this, menu.getStarters());
	// starterAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
	// spStarters.setAdapter(starterAdapter);
	//
	// MenuAdapter mainAdapter = new MenuAdapter(this, menu.getMains());
	// mainAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
	// spMains.setAdapter(mainAdapter);
	//
	// MenuAdapter desertAdapter = new MenuAdapter(this, menu.getDeserts());
	// desertAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
	// spDeserts.setAdapter(desertAdapter);
	//
	// MenuAdapter drinkAdapter = new MenuAdapter(this, menu.getDrinks());
	// drinkAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
	// spDrinks.setAdapter(drinkAdapter);
	// }

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.order, menu);
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

	public class GridAdapter extends BaseAdapter {

		private ArrayList<OrderItem> mItems;
		private int mCount;
		private Context mContext;

		/**
		 * Default constructor
		 * 
		 * @param items
		 *            to fill data to
		 */
		public GridAdapter(Context context,final ArrayList<OrderItem> items) {

			mCount = items.size();
			mItems = items;
			mContext = context; 

		}

		@Override
		public int getCount() {
			return mCount;
		}

		@Override
		public Object getItem(final int position) {
			return mItems.get(position);
		}

		@Override
		public long getItemId(final int position) {
			return position;
		}

		@Override
		public View getView(final int position, final View convertView,
				final ViewGroup parent) {

			View view = convertView;

			if (view == null) {
				view = LayoutInflater.from(parent.getContext()).inflate(
						R.layout.menu_item_layout, parent, false);
			}

			TextView text = (TextView) view.findViewById(R.id.itemPrice);

			try {
				text.setText("€" + mItems.get(position).getPrice());

				text = (TextView) view.findViewById(R.id.itemName);

				text.setText(mItems.get(position).getName());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			return view;
		}
	}
}
