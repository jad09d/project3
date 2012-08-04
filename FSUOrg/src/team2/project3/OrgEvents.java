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
import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;

public class OrgEvents extends ListActivity {

	String[] eventNames;


	@Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    
	    // Also get event name of this organization from mobDB
	    // The below is for testing
	    //eventNames = new String[5];
	    //for(int i = 0; i < 5; i++)
	    	//eventNames[i] = "Event Number " + (i+1);

	    eventNames = new String[1];
	    eventNames[0] = getIntent().getExtras().getString("event1");
	    
	    if(eventNames[0] != null)
		setListAdapter(new ArrayAdapter<String>(OrgEvents.this, android.R.layout.simple_list_item_1, eventNames));
	}
}
