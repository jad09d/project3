package team2.project3;

import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

public class OrgList extends ListActivity {
	
	String[] names;
	Bitmap[] pics;

	@Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    
	    // Find out how many organizations we have and initialize the arrays (from mobDB)
	    // The below is for testing
	    names = new String[15];
	    pics = new Bitmap[15];
	    
	    for(int i = 0; i < 15; i++) {
	    	names[i] = "Organization Number " + (i+1);
	    	pics[i] = BitmapFactory.decodeResource(getResources(), R.drawable.org_image);
	    }
	    
	    setListAdapter(new MyCustomAdapter(OrgList.this, R.layout.orglistrow, names));
		
		ListView lv = getListView();
		lv.setOnItemClickListener(new OnItemClickListener() {

			public void onItemClick(AdapterView<?> parent, View v, int pos, long arg3) {
                  Intent myIntent = new Intent(OrgList.this, OrgTab.class);
                  Bundle extras = new Bundle();
                  extras.putString("name",names[pos]);
                  
                  myIntent.putExtras(extras);
                  startActivity(myIntent);                  
			}
			
		});
	}
	
	public class MyCustomAdapter extends ArrayAdapter<String> {

		public MyCustomAdapter(Context context, int textViewResourceId, String[] objects) {
			super(context, textViewResourceId, objects);
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			LayoutInflater inflater=getLayoutInflater();
			View row=inflater.inflate(R.layout.orglistrow, parent, false);
			TextView label=(TextView)row.findViewById(R.id.orgName);
			label.setText(names[position]);
			ImageView icon=(ImageView)row.findViewById(R.id.orgPic);
			icon.setImageBitmap(pics[position]);
			
			return row;
		}
	}

}
