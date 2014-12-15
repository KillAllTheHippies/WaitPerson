package jamie.ardis.waitperson;

import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class AddTablesActivity extends Activity {

	private ArrayList<Table> tables;
	private int numTables;
	private EditText txtNumTables;
	private TextView tvAddTables;
	private static final int MY_NOTIFICATION_ID=1;
	private NotificationManager notificationManager;
	private Notification myNotification;

	private final String myBlog = "http://android-er.blogspot.com/";
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add_tables);
		txtNumTables = (EditText) findViewById(R.id.txtNumTables);
		tvAddTables = (TextView) findViewById(R.id.tvAddTables);
		

		tables = (ArrayList<Table>) getIntent().getSerializableExtra("tables");
		TextView tvAddTables = (TextView) findViewById(R.id.tvAddTables);
		TextView tvAddTables2 = (TextView) findViewById(R.id.tvAddTables2);
		Button b = (Button) findViewById(R.id.btnOverwriteTables);
		tvAddTables.setText("There are " + tables.size() + " tables");
		tvAddTables2.setText("Input number of tables to add");
		b.setText("Add Tables");
	}

	public void addTables(View v) {
		if (!txtNumTables.getText().toString().equals("")) {

			numTables = Integer.parseInt(txtNumTables.getText().toString());
			
			for (int i = 0; i < numTables; i++) {
				Table t = new Table("" + (tables.size() + 1));
				tables.add(t);

			}
			tvAddTables.setText("There are " + tables.size() + " tables");
			writeTables(tables);
			sendNotification();

		}
		

	}
	private void sendNotification()
	{
		// TODO Auto-generated method stub
		   notificationManager =
		    (NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);
		   myNotification = new Notification(R.drawable.ic_launcher,
		     "Notification!",
		     System.currentTimeMillis());
		   Context context = getApplicationContext();
		   String notificationTitle = "Successfully Added Tables!";
		   String notificationText = "Tap to return to main";
		   Intent myIntent = new Intent(this, MainActivity.class);
		   PendingIntent pendingIntent
		     = PendingIntent.getActivity(AddTablesActivity.this,
		       0, myIntent,
		       Intent.FLAG_ACTIVITY_NEW_TASK);
		   myNotification.defaults |= Notification.DEFAULT_SOUND;
		   myNotification.flags |= Notification.FLAG_AUTO_CANCEL;
		   myNotification.setLatestEventInfo(context,
		      notificationTitle,
		      notificationText,
		      pendingIntent);
		   notificationManager.notify(MY_NOTIFICATION_ID, myNotification);
		  
		  
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


	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.add_tables, menu);
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
