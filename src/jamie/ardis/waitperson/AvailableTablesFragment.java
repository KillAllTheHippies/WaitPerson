package jamie.ardis.waitperson;

import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.util.ArrayList;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;

public class AvailableTablesFragment extends Fragment {

	// Initialise the menu
	ArrayList<Table> tables;
	private static final int FRAGMENT_RESULT = 10;

	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		View view = inflater.inflate(R.layout.menu_fragment, container, false);

		super.onCreate(savedInstanceState);
		final GridView grid = (GridView) view.findViewById(R.id.menuGrid);
		OnItemClickListener mOnClickGridCell = new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				Table table = (Table) grid.getItemAtPosition(position);
				MainActivity.tableIndex = position;
				launchTableActivity(table);

			}
		};

		tables = readTables();

		grid.setAdapter(new FragAdapter(tables));
		grid.setOnItemClickListener(mOnClickGridCell);

		return view;

	}

	public void launchTableActivity(Table table) {
		Intent intent = new Intent(getActivity(), TableActivity.class);
		intent.putExtra("table", table);
		// launch table activity which will close and return to settings activity
		getActivity().startActivityForResult(intent, FRAGMENT_RESULT);
		

	}

	public ArrayList<Table> readTables() {

		String filename = "tables.ser";
		// read the object from file

		FileInputStream fis = null;
		ObjectInputStream in = null;
		try {
			fis = getActivity().openFileInput(filename);
			in = new ObjectInputStream(fis);
			tables = (ArrayList<Table>) in.readObject();
			in.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return tables;

	}

	private static final class FragAdapter extends BaseAdapter {

		final ArrayList<Table> mItems;
		final int mCount;

		/**
		 * Default constructor
		 * 
		 * @param items
		 *            to fill data to
		 */
		private FragAdapter(final ArrayList<Table> items) {

			mCount = items.size();
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
						R.layout.table_frag_layout, parent, false);
			}

			TextView text = (TextView) view.findViewById(R.id.tableNumber);

			try {
				text.setText(mItems.get(position).getTableNum());

				Table table = (Table) getItem(position);
				if (table.getDiners().isEmpty())
					text.setBackgroundColor(Color.GREEN);

			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			return view;
		}
	}

}