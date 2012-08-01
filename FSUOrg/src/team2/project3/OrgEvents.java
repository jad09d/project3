package team2.project3;

import android.app.ListActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;

public class OrgEvents extends ListActivity {
	
	String[] eventNames;

	@Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    
	    // Also get event name of this organization from mobDB
	    // The below is for testing
	    eventNames = new String[5];
	    for(int i = 0; i < 5; i++)
	    	eventNames[i] = "Event Number " + (i+1);
	    
		setListAdapter(new ArrayAdapter<String>(OrgEvents.this, android.R.layout.simple_list_item_1, eventNames));
	}
}
