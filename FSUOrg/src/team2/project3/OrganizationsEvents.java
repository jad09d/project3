package team2.project3;

import java.util.HashMap;
import java.util.Vector;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.mobdb.android.GetRowData;
import com.mobdb.android.InsertRowData;
import com.mobdb.android.MobDB;
import com.mobdb.android.MobDBResponseListener;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

public class OrganizationsEvents extends Activity {
	
	String APP_KEY = "dhTii1-5T3-WoBi0DpadReeum39O1iPixXx-cutCsWBLUcBJWoW";
	String USER_TABLE_NAME = "orgpop";
	
	ListView lv;
	Button addEvent;
	String[] events;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.orgevents);
	    
	    events = new String[1];
	    lv = (ListView) findViewById(R.id.listView1);
	    addEvent = (Button) findViewById(R.id.button1);
	    	    
	    addEvent.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(OrganizationsEvents.this);
				final EditText eventName = new EditText(OrganizationsEvents.this); 
		 
				alertDialogBuilder.setTitle("Event");
		 
				alertDialogBuilder
						.setMessage("Enter a name for your event!")
						.setView(eventName)
						.setCancelable(true)
						.setPositiveButton("Save",new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog,int id) {
								InsertRowData insertRowData = new InsertRowData(USER_TABLE_NAME);
								insertRowData.setValue("event1", eventName.getText().toString());

								MobDB.getInstance().execute(APP_KEY, insertRowData, null, false, new MobDBResponseListener() {

									public void mobDBSuccessResponse() {
										Toast toast = Toast.makeText(getApplicationContext(), "Event added!", Toast.LENGTH_SHORT);
										toast.show();

									}

									public void mobDBResponse(Vector<HashMap<String, Object[]>> result) {}

									public void mobDBResponse(String jsonStr) {}

									public void mobDBFileResponse(String fileName, byte[] fileData) {}

									public void mobDBErrorResponse(Integer errValue, String errMsg) {}
								});
						    }
					     })
						.setNegativeButton("Cancel",new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog,int id) {
								dialog.cancel();
							}
						});
		 
				AlertDialog alertDialog = alertDialogBuilder.create();
		 
				alertDialog.show();
				
			}
	    	
	    });
	    
		final String un = getIntent().getExtras().getString("un");
		final String pw = getIntent().getExtras().getString("pw");

		GetRowData getRowData = new GetRowData(USER_TABLE_NAME);
		getRowData.whereEqualsTo("login", un);

		MobDB.getInstance().execute(APP_KEY, getRowData, null, false, new MobDBResponseListener() {

			public void mobDBSuccessResponse() {}           
			public void mobDBResponse(Vector<HashMap<String, Object[]>> result) {}           

			public void mobDBResponse(String jsonStr) {
				try {
					JSONObject response = new JSONObject(jsonStr);
					int status = response.getInt("status");
					JSONArray array = response.getJSONArray("row");
					if(status == 101 && response.getJSONArray("row").length() != 0) {

						for(int i = 0; i < array.length(); i++){
							// row -> column -> password
							if(array.getJSONObject(i).getString("password").equals(pw)){
								events[0] = array.getJSONObject(i).getString("event1");
							}
						}
						
					}
				} catch (JSONException e) {
					e.printStackTrace();
				}
				
				if(events[0] != null) {
					addEvent.setEnabled(false);
				    lv.setAdapter(new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, events));
				}
			}

			public void mobDBFileResponse(String fileName, byte[] fileData) {}           
			public void mobDBErrorResponse(Integer errValue, String errMsg) {}
		});
	}
}
