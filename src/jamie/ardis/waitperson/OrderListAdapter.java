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

public class OrderListAdapter extends ArrayAdapter<OrderItem> {
    public OrderListAdapter(Context context, ArrayList<OrderItem> order) {
       super(context, 0, order);
    }

	@Override
    public View getView(int position, View convertView, ViewGroup parent) {
       // Get the data item for this position
       OrderItem item = getItem(position);    

       // Check if an existing view is being reused, otherwise inflate the view
       if (convertView == null) {
          convertView = LayoutInflater.from(getContext()).inflate( R.layout.item_order, parent, false);
          
       }

       TextView tvOrder = (TextView) convertView.findViewById(R.id.tvOrder);
       
       // Populate the data into the template view using the data object
       tvOrder.setText(item.toString());
       
       

       // Return the completed view to render on screen
       return convertView;
   }
}





