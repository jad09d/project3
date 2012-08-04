package team2.project3;

import java.util.HashMap;
import java.util.Vector;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract.Profile;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.mobdb.android.GetRowData;
import com.mobdb.android.InsertRowData;
import com.mobdb.android.MobDB;
import com.mobdb.android.MobDBResponseListener;

public class MainActivity extends Activity {

	// MobDB constants
	final String APP_KEY = "dhTii1-5T3-WoBi0DpadReeum39O1iPixXx-cutCsWBLUcBJWoW";
	final String USER_TABLE_NAME = "orgpop";
	final String USER_LOGIN = "login";
	final String USER_PASSWORD = "password";
	// End MobDB constants

	EditText loginName, password;
	Button login, register, browse;
	

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.login_screen);

		loginName = (EditText) findViewById(R.id.organization);
		password = (EditText) findViewById(R.id.password_et);
		login = (Button) findViewById(R.id.login);
		register = (Button) findViewById(R.id.register);
		browse = (Button) findViewById(R.id.browse);

		// Login database call
		login.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {

				final String un = loginName.getText().toString();
				final String pw = password.getText().toString();

				GetRowData getRowData = new GetRowData(USER_TABLE_NAME);
				getRowData.whereEqualsTo(USER_LOGIN, un);

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
									if(array.getJSONObject(i).getString(USER_PASSWORD).equals(pw)){
										clear();
										Intent intent = new Intent(getApplicationContext(), OrganizationsTab.class);
										Bundle extras = new Bundle();
										extras.putString("Username", array.getJSONObject(i).getString("name"));
										extras.putString("numbers", array.getJSONObject(i).getString("numbers"));
										extras.putString("photo", array.getJSONObject(i).getString("pik1"));
										extras.putString("descript", array.getJSONObject(i).getString("descript"));
										extras.putString("un", un);
										extras.putString("pw", pw);
										intent.putExtras(extras);
										startActivity(intent);
									}
								}	
							}
							else{
								loginName.setError("Username does not exist");
								password.setError("Password does not exist");
							}
						} catch (JSONException e) {
							e.printStackTrace();
						}
					}

					public void mobDBFileResponse(String fileName, byte[] fileData) {}           
					public void mobDBErrorResponse(Integer errValue, String errMsg) {}
				});
			}
		});

		// Register database call
		register.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				//create entry in mobDB with this information
				final String un2 = loginName.getText().toString();
				final String pw2 = password.getText().toString();

				GetRowData getRowData2 = new GetRowData(USER_TABLE_NAME);
				getRowData2.whereEqualsTo(USER_LOGIN, un2);

				MobDB.getInstance().execute(APP_KEY, getRowData2, null, false, new MobDBResponseListener() {

					public void mobDBSuccessResponse() {
						Toast toast = Toast.makeText(getApplicationContext(), "Get Success!", Toast.LENGTH_SHORT);
						toast.show();
					}           
					public void mobDBResponse(Vector<HashMap<String, Object[]>> result) {}           

					public void mobDBResponse(String jsonStr) {
						try {
							JSONObject response = new JSONObject(jsonStr);
							int status = response.getInt("status");

							if(status == 101 && response.getJSONArray("row").length() == 0) {
								AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(MainActivity.this);
								final EditText orgName = new EditText(MainActivity.this); 
						 
								alertDialogBuilder.setTitle("Register");
						 
								alertDialogBuilder
										.setMessage("Enter a name for your organization!")
										.setView(orgName)
										.setCancelable(true)
										.setPositiveButton("Save",new DialogInterface.OnClickListener() {
											public void onClick(DialogInterface dialog,int id) {
												InsertRowData insertRowData = new InsertRowData(USER_TABLE_NAME);
												insertRowData.setValue(USER_LOGIN, un2);
												insertRowData.setValue(USER_PASSWORD, pw2);
												insertRowData.setValue("name", orgName.getText().toString());	
												final String name = orgName.getText().toString();

												MobDB.getInstance().execute(APP_KEY, insertRowData, null, false, new MobDBResponseListener() {

													public void mobDBSuccessResponse() {
														Toast toast = Toast.makeText(getApplicationContext(), "Insert Success!", Toast.LENGTH_SHORT);
														toast.show();

														Intent intent = new Intent(getApplicationContext(), OrganizationsTab.class);
														Bundle extras = new Bundle();
														extras.putString("Username", name );
														extras.putString("numbers", null);
														intent.putExtras(extras);
														startActivity(intent);
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
							else {
								password.setError("Password already exists");
								loginName.setError("Username already exists");
							}
						}
						catch (JSONException e) {
							e.printStackTrace();
						}
					}

					public void mobDBFileResponse(String fileName, byte[] fileData) {}           
					public void mobDBErrorResponse(Integer errValue, String errMsg) {}
				});
			}
		});
		
		browse.setOnClickListener(new OnClickListener() {
			
			public void onClick(View v) {
				Intent intent = new Intent(getApplicationContext(), OrgList.class);
				startActivity(intent);
			}
		});
		
	}

	public void clear(){

		EditText loginName, password;
		loginName = (EditText) findViewById(R.id.organization);
		password = (EditText) findViewById(R.id.password_et);

		loginName.setText("");
		password.setText("");
	}
}