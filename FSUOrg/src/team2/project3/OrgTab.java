package team2.project3;

import android.app.Activity;
import android.app.LocalActivityManager;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;

@SuppressWarnings("deprecation")
public class OrgTab extends Activity {

	TabHost tabhost;
	TabSpec spec;
	Intent tabIntent;
    LocalActivityManager lam;
	
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.tab);

	    tabhost = (TabHost) findViewById(android.R.id.tabhost);
	    

	    lam = new LocalActivityManager(this, false);
	    lam.dispatchCreate(savedInstanceState);
	    tabhost.setup(lam);
	    
	    tabIntent = new Intent(OrgTab.this, OrgProfile.class);
	    spec = tabhost.newTabSpec("Profile");
	    spec.setIndicator("Profile");
	    spec.setContent(tabIntent);
	    tabhost.addTab(spec);
	    
	    tabIntent = new Intent(OrgTab.this, OrgEvents.class);
	    spec = tabhost.newTabSpec("Events");
	    spec.setIndicator("Events");
	    spec.setContent(tabIntent);
	    tabhost.addTab(spec);
	    
	    tabIntent = new Intent(OrgTab.this, OrgFollow.class);
	    spec = tabhost.newTabSpec("Follow");
	    spec.setIndicator("Follow");
	    spec.setContent(tabIntent);
	    tabhost.addTab(spec);
	
	}
	
	@Override
	public void onResume() {
		super.onResume();
        lam.dispatchResume(); 
	}
	
	@Override
	public void onPause() {
		super.onPause();
		lam.dispatchPause(isFinishing());
	}

}
