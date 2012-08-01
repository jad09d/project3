package team2.project3;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends Activity {

	Button mButtonBrowse;
	Button mButtonSignIn;
	Button mButtonRegister;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        mButtonBrowse = (Button) findViewById(R.id.button1);
        mButtonSignIn = (Button) findViewById(R.id.button3);
        mButtonRegister = (Button) findViewById(R.id.button2);
        
        mButtonBrowse.setOnClickListener(new OnClickListener(){

			public void onClick(View v) {
				Intent myIntent = new Intent(MainActivity.this, OrgList.class);
				startActivity(myIntent);
			}        	
        });
        
    }
}