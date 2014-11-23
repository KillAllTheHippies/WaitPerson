package jamie.ardis.waitperson;

import android.support.v7.app.ActionBarActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class OrderActivity extends ActionBarActivity {
	
	Order order;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_order);
		TextView tvDinersDisplay = (TextView) findViewById(R.id.tvDinerDisplay);
		
		
		if (savedInstanceState == null) {
		    Bundle bundle = getIntent().getExtras();
		    if(bundle == null) {
		        order= null;
		    } else {
		    	Intent intent = getIntent();
		        order= (Order) intent.getSerializableExtra("order");
		    }
		} else {
		    order= (Order) savedInstanceState.getSerializable("order");
		}
		
		//tvDinersDisplay.setText(order.toString()); // crashes herez

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
