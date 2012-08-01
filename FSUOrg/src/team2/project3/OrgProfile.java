package team2.project3;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class OrgProfile extends Activity {

    TextView OrgName;
	TextView OrgDescription;
	ImageView OrgPic;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.profile);
	    
	    OrgName = (TextView) findViewById(R.id.textView1);
	    OrgDescription = (TextView) findViewById(R.id.textView2);
	    OrgPic = (ImageView) findViewById(R.id.imageView1);
	    
	    Intent myIntent = getIntent();
	    Bundle extras = myIntent.getExtras();
	    
	    if(extras != null) {
	      OrgName.setText(extras.getString("OrgName"));
	      //set OrgPic 
	    }
	}

}
