package team2.project3;

import java.util.HashMap;
import java.util.Vector;

import com.mobdb.android.GetFile;
import com.mobdb.android.MobDB;
import com.mobdb.android.MobDBResponseListener;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class OrganizationsProfile extends Activity{
	
	String APP_KEY = "dhTii1-5T3-WoBi0DpadReeum39O1iPixXx-cutCsWBLUcBJWoW";
	
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
	    
	    OrgName.setText(getIntent().getExtras().getString("Username"));
	    OrgDescription.setText(getIntent().getExtras().getString("descript"));
	    
	    if(getIntent().getExtras().getString("pik1") != null && !getIntent().getExtras().getString("pik1").equals("")) {
	    	GetFile getFile = new GetFile(getIntent().getExtras().getString("pik1"));
	    	
	        MobDB.getInstance().execute(APP_KEY, getFile, null, false, new MobDBResponseListener() {

			 public void mobDBSuccessResponse() { }

			 public void mobDBErrorResponse(Integer errValue, String errMsg) { }

			 public void mobDBResponse(String jsonObj) { }

			 public void mobDBFileResponse(String fileName, byte[] fileData) { 
				Bitmap bm =BitmapFactory.decodeByteArray(fileData, 0, fileData.length);
				OrgPic.setImageBitmap(bm);
			
			 }

			 public void mobDBResponse(Vector<HashMap<String, Object[]>> result) { }
	    	
	      });
       }
	}
}
