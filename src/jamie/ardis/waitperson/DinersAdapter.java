package jamie.ardis.waitperson;




import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface.OnClickListener;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;



/*
 * https://github.com/codepath/android_guides/wiki/Using-an-ArrayAdapter-with-ListView
 */

public class DinersAdapter extends ArrayAdapter<Diner> {
    public DinersAdapter(Context context, ArrayList<Diner> diners) {
       super(context, 0, diners);
    }

	@Override
    public View getView(int position, View convertView, ViewGroup parent) {
       // Get the data item for this position
       Diner diner = getItem(position);    

       // Check if an existing view is being reused, otherwise inflate the view
       if (convertView == null) {
          convertView = LayoutInflater.from(getContext()).inflate( R.layout.item_diner, parent, false);
          
       }

       TextView tvDiner = (TextView) convertView.findViewById(R.id.dinerNum);
       
       // Populate the data into the template view using the data object
       tvDiner.setText("Diner: " + diner.getDinerNum());
       
       

       // Return the completed view to render on screen
       return convertView;
   }
}





