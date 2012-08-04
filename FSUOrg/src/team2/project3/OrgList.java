package team2.project3;

import java.util.HashMap;
import java.util.Vector;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.mobdb.android.GetRowData;
import com.mobdb.android.MobDB;
import com.mobdb.android.MobDBResponseListener;

import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

public class OrgList extends ListActivity {
	
	String[] names;
	String[] descript;
	String[] numbers;
	String[] event;
	Bitmap[] pics;
	
	final String APP_KEY = "dhTii1-5T3-WoBi0DpadReeum39O1iPixXx-cutCsWBLUcBJWoW";
	final String USER_TABLE_NAME = "orgpop";
	
	ListView lv;

	@Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    
	    // Find out how many organizations we have and initialize the arrays (from mobDB)
	    // The below is for testing
	    /*names = new String[15];
	    pics = new Bitmap[15];
	    
	    for(int i = 0; i < 15; i++) {
	    	names[i] = "Organization Number " + (i+1);
	    	pics[i] = BitmapFactory.decodeResource(getResources(), R.drawable.org_image);
	    }*/
	    
	    lv = getListView();
 

		GetRowData getRowData = new GetRowData(USER_TABLE_NAME);

		MobDB.getInstance().execute(APP_KEY, getRowData, null, false, new MobDBResponseListener() {

			public void mobDBSuccessResponse() { }

			public void mobDBErrorResponse(Integer errValue, String errMsg) { }

			public void mobDBResponse(String jsonObj) {
			try {	
				JSONObject response = new JSONObject(jsonObj);
				int status = response.getInt("status");
				JSONArray array = response.getJSONArray("row");
				if(status == 101 && response.getJSONArray("row").length() != 0) {

				names = new String[array.length()];	
				descript = new String[array.length()];
				numbers = new String[array.length()];
				event = new String[array.length()];
				pics = new Bitmap[array.length()];	
					
					for(int i = 0; i < array.length(); i++) {
						names[i] = array.getJSONObject(i).getString("name");
						descript[i] =  array.getJSONObject(i).getString("descript");
						numbers[i] = array.getJSONObject(i).getString("numbers");
						event[i] = array.getJSONObject(i).getString("event1");
						
					}
				}
								
				
			} catch (JSONException e) {
				e.printStackTrace();
			}
			
			setListView();

			}

			public void mobDBFileResponse(String fileName, byte[] fileData) { }

			public void mobDBResponse(Vector<HashMap<String, Object[]>> result) { }
			
		});
	    
	}

    private void setListView() {
	    lv.setAdapter(new MyCustomAdapter(OrgList.this, R.layout.orglistrow, names));
		
		lv.setOnItemClickListener(new OnItemClickListener() {

			public void onItemClick(AdapterView<?> parent, View v, int pos, long arg3) {
                  Intent myIntent = new Intent(OrgList.this, OrgTab.class);
                  Bundle extras = new Bundle();
                  extras.putString("name",names[pos]);
                  extras.putString("descript", descript[pos]);
                  extras.putString("numbers", numbers[pos]);
				  extras.putString("event1", event[pos]);
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
