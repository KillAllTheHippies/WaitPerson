package jamie.ardis.waitperson;



import java.util.ArrayList;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;


public class MainActivity extends ActionBarActivity {

	ArrayList<Table> tables;
	ArrayAdapter<Table> adapter;
	private ListView lv;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        lv = (ListView) findViewById(R.id.lvTables);
        ArrayList<Table> tArray = new ArrayList<Table>();
        ArrayList<String> tabList = new ArrayList<String>();
        
        //create some tables and feic them into the arraylist
        //also populate the table numbers list
        for (int i = 0; i<5; i++)
        {
        	Table t = new Table(i);
        	tArray.add(t);
        	tabList.add("Table: " + i);
        }
        	
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(
                this, 
                android.R.layout.simple_list_item_1,
                tabList );

        lv.setAdapter(arrayAdapter); 
   }
        
    


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
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
