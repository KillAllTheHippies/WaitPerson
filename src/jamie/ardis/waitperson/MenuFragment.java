package jamie.ardis.waitperson;

import java.util.ArrayList;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.TextView;

public class MenuFragment extends Fragment {

//Initialise the menu
MenuItems m = new MenuItems();

	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		View view = inflater.inflate(R.layout.menu_fragment, container, false);

		super.onCreate(savedInstanceState);
		GridView grid = (GridView) view.findViewById(R.id.menuGrid);


		grid.setAdapter(new GridAdapter(m.getStarters()));

		return view;
		
	}
	
	

	
	

	private static final class GridAdapter extends BaseAdapter {

		final ArrayList<OrderItem> mItems;
		final int mCount;

		/**
		 * Default constructor
		 * 
		 * @param items
		 *            to fill data to
		 */
		private GridAdapter(final ArrayList<OrderItem> items) {

			mCount = items.size() ;
			mItems = items;

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

			TextView text = (TextView) view
					.findViewById(R.id.itemPrice);

			try {
				text.setText("€" + mItems.get(position).getPrice());

				text = (TextView) view
						.findViewById(R.id.itemName);

				text.setText(mItems.get(position).getName());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			return view;
		}
	}

}