package team2.project3;

import android.app.Activity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class OrganizationsNotify extends Activity{
	
	Button sendMessage;
	String message;
	EditText writeMessage;
	String[] phones;
	String delims = "[,]";
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.orgnotify);
	    
	    sendMessage = (Button) findViewById(R.id.button1);
	    writeMessage = (EditText) findViewById(R.id.editText1);
	    
	    Bundle extras = getIntent().getExtras();
	    String numbers = extras.getString("numbers");
	    	    
	    if(numbers != null) {
	    phones = numbers.split(delims);
	    	    
	    sendMessage.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {				
				
				message = writeMessage.getText().toString(); 
				for(int i=0; i < phones.length; i++) {
					if(phones[i] != null) {
				
				  try {
						SmsManager smsManager = SmsManager.getDefault();
						smsManager.sendTextMessage(phones[i], null, message, null, null);
						Toast.makeText(getApplicationContext(), "Message Sent!", Toast.LENGTH_LONG).show();
			      } catch (Exception e) {
						Toast.makeText(getApplicationContext(), "Message was not sent... Try again", Toast.LENGTH_LONG).show();
						e.printStackTrace();
				  }
					}
				}
			}
	    	
	    });
	   }
	    else {
	    	TextView notice = (TextView) findViewById(R.id.textView1);
	    	notice.setText("There is no one to notify right now. Get followers!");
	    	writeMessage.setVisibility(View.GONE);
	    	sendMessage.setVisibility(View.GONE);
	    }
	    	
	}

}
