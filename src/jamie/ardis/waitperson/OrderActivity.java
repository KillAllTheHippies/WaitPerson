package jamie.ardis.waitperson;

import java.util.ArrayList;

import android.support.v7.app.ActionBarActivity;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Spinner;
import android.widget.TextView;

public class OrderActivity extends ActionBarActivity {
	
	Order order = new Order();
	MenuItems menu = new MenuItems();
	

	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_order);
		TextView tvDinersDisplay = (TextView) findViewById(R.id.tvDinerDisplay);
		addMenuToSpinners();
		order = (Order) getIntent().getSerializableExtra("order");
		
//		if (savedInstanceState == null) {
//		    Bundle bundle = getIntent().getExtras();
//		    if(bundle == null) {
//		        order= null;
//		    } else {
//		    	Intent intent = getIntent();
//		        order= (Order) intent.getSerializableExtra("order");
//		    }
//		} else {
//		    order= (Order) savedInstanceState.getSerializable("order");
//		}
		
		//tvDinersDisplay.setText(order.toString()); // crashes herez

	}
	public void addStarter(View v)
	{
		Spinner spStarters = (Spinner) findViewById(R.id.spStarters);
		order.addItem((OrderItem)spStarters.getSelectedItem());
	}
	
	public void addMain(View v)
	{
		Spinner spMains = (Spinner) findViewById(R.id.spMains);
		order.addItem((OrderItem)spMains.getSelectedItem());
	}
	public void addDesert(View v)
	{
	
		Spinner spDeserts = (Spinner) findViewById(R.id.spDeserts);
		order.addItem((OrderItem)spDeserts.getSelectedItem());
		
	}
	public void addDrink(View v)
	{
	
		Spinner spDrinks = (Spinner) findViewById(R.id.spDrinks);
		order.addItem((OrderItem)spDrinks.getSelectedItem());
		
	}
	
	public void returnInfo(View v)
	{
		//test code
//		order.addItem(new OrderItem("Coffee", 2.50));
//		order.addItem(new OrderItem("Cake", 2.99));
//		order.addItem(new OrderItem("Pigs Head", 15.99));
		
		Intent returnIntent = new Intent();
		returnIntent.putExtra("order", order);
		setResult(Activity.RESULT_OK, returnIntent);
		finish();
	}
	
	public void addMenuToSpinners() {

		Spinner spStarters = (Spinner) findViewById(R.id.spStarters);
		Spinner spMains = (Spinner) findViewById(R.id.spMains);
		Spinner spDeserts = (Spinner) findViewById(R.id.spDeserts);
		Spinner spDrinks = (Spinner) findViewById(R.id.spDrinks);
		
		MenuAdapter starterAdapter = new MenuAdapter(this, menu.getStarters());
		starterAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spStarters.setAdapter(starterAdapter);
		
		MenuAdapter mainAdapter = new MenuAdapter(this, menu.getMains());
		mainAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spMains.setAdapter(mainAdapter);
		
		MenuAdapter desertAdapter = new MenuAdapter(this, menu.getDeserts());
		desertAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spDeserts.setAdapter(desertAdapter);
		
		MenuAdapter drinkAdapter = new MenuAdapter(this, menu.getDrinks());
		drinkAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spDrinks.setAdapter(drinkAdapter);
	}

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
}
