package team2.project3;

import android.app.Activity;
import android.app.LocalActivityManager;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;

@SuppressWarnings("deprecation")
public class OrganizationsTab extends Activity {

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
	    
	    tabIntent = new Intent(OrganizationsTab.this, OrganizationsProfile.class);
	    tabIntent.putExtras(getIntent().getExtras());
	    spec = tabhost.newTabSpec("Profile");
	    spec.setIndicator("Profile");
	    spec.setContent(tabIntent);
	    tabhost.addTab(spec);
	    
	    tabIntent = new Intent(OrganizationsTab.this, OrganizationsEvents.class);
	    tabIntent.putExtras(getIntent().getExtras());
	    spec = tabhost.newTabSpec("Events");
	    spec.setIndicator("Events");
	    spec.setContent(tabIntent);
	    tabhost.addTab(spec);
	    
	    tabIntent = new Intent(OrganizationsTab.this, OrganizationsNotify.class);
	    tabIntent.putExtras(getIntent().getExtras());
	    spec = tabhost.newTabSpec("Notify");
	    spec.setIndicator("Notify");
	    spec.setContent(tabIntent);
	    tabhost.addTab(spec);
	    
	}
}
