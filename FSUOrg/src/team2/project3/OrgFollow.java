package team2.project3;

import java.util.HashMap;
import java.util.Vector;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.mobdb.android.MobDB;
import com.mobdb.android.MobDBResponseListener;
import com.mobdb.android.UpdateRowData;

public class OrgFollow extends Activity {
	
    Button submit,  reset;
    EditText fName, lName, phone, email;
    
	final String APP_KEY = "dhTii1-5T3-WoBi0DpadReeum39O1iPixXx-cutCsWBLUcBJWoW";
	final String USER_TABLE_NAME = "orgpop";

	@Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.follow);
	    
	    fName = (EditText) findViewById(R.id.editText4);
	    lName = (EditText) findViewById(R.id.editText3);
	    phone = (EditText) findViewById(R.id.editText2);
	    email = (EditText) findViewById(R.id.editText1);

	    submit = (Button) findViewById(R.id.button1);
	    reset = (Button) findViewById(R.id.button2);
	    
	    submit.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				String num = ", " + phone.getText();
				String numbers = getIntent().getExtras().getString("numbers") + num;
				
				
				Toast.makeText(getApplicationContext(), "Submittion successful!", Toast.LENGTH_SHORT);
				
				UpdateRowData updateRowData = new UpdateRowData(USER_TABLE_NAME);
				updateRowData.setValue("numbers", numbers);
				updateRowData.whereEqualsTo("name", getIntent().getExtras().getString("name"));

				MobDB.getInstance().execute(APP_KEY, updateRowData, null, false, new MobDBResponseListener() {

					public void mobDBSuccessResponse() {
						Toast toast = Toast.makeText(getApplicationContext(), "You are now following this organization!", Toast.LENGTH_SHORT);
						toast.show();

					}

					public void mobDBResponse(Vector<HashMap<String, Object[]>> result) {}

					public void mobDBResponse(String jsonStr) {}

					public void mobDBFileResponse(String fileName, byte[] fileData) {}

					public void mobDBErrorResponse(Integer errValue, String errMsg) {}
				});
				
			}
	    	
	    });

	    reset.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				
				fName.setText("");
				lName.setText("");
				phone.setText("");
				email.setText("");

				Toast.makeText(getApplicationContext(), "Info reset!", Toast.LENGTH_SHORT);
				
			}
	    	
	    });
	    
	}

}
