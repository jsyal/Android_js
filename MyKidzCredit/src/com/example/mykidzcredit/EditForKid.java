/*----------------------------------------------------------------
 *  Author:        Jyotika Syal
 *  Written:       12/5/2014
 *  
 *  EditForKid allows the kid to edit his/her information
 *
 *----------------------------------------------------------------*/


package com.example.mykidzcredit;

import android.app.Activity;
import android.app.ListActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import android.os.Bundle;
import android.util.Log;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;



import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

public class EditForKid extends Activity{
	EditText kidName;
	EditText taskName;
	Button btnaddtask;
	Button btnshowTask;
	DBController controller = new DBController(this);
	 @Override
	    public void onCreate(Bundle savedInstanceState) {
		 	super.onCreate(savedInstanceState);
			setContentView(R.layout.activity_editforkid);
			kidName = (EditText) findViewById(R.id.kidName);
			btnaddtask=(Button)findViewById(R.id.button3);
			btnshowTask=(Button)findViewById(R.id.button4);
			Intent objIntent = getIntent();
			Intent objIntent1 = getIntent();
			Intent objIntent2 = getIntent();
			//Intent objIntent3 = getIntent();
			String kidId = objIntent.getStringExtra("kidId");
			Log.d("Reading: ", "Reading all kids..");
			HashMap<String, String> kidList = controller.getKidInfo(kidId);
			Log.d("kidName",kidList.get("kidName"));
			if(kidList.size()!=0) {
			kidName.setText(kidList.get("kidName"));
			}
	    
			btnaddtask.setOnClickListener(new View.OnClickListener() {
				public void onClick(View v) {
					// TODO Auto-generated method stub
					//Create Intent for AddTask  and Start The Activity
									  
					Intent  objIndent = new Intent(getApplicationContext(),EditKid.class);
					Intent objIntent1=new Intent(getApplicationContext(),AddTask.class);
					Intent objIntent2=new Intent(getApplicationContext(),TaskList.class);
					kidName = (EditText) findViewById(R.id.kidName);
					Intent objIntent3 = getIntent();
					String kidId = objIntent3.getStringExtra("kidId");
					objIntent1.putExtra("kidId", kidId);
					startActivity(objIntent1);
					}
				});
			
				btnshowTask.setOnClickListener(new View.OnClickListener() {
				public void onClick(View v) {
					// TODO Auto-generated method stub
					// Create Intent for AddTask  and Start The Activity
									  
					Intent  objIndent = new Intent(getApplicationContext(),EditKid.class);
					Intent objIntent2=new Intent(getApplicationContext(),TaskList.class);
					Intent objIntent3 = getIntent();
					String kidId = objIntent3.getStringExtra("kidId");
					objIntent2.putExtra("kidId", kidId);
					startActivity(objIntent2);
					}
				});
			}
			
		public void editKid(View view) {
		HashMap<String, String> queryValues =  new  HashMap<String, String>();
		kidName = (EditText) findViewById(R.id.kidName);
		Intent objIntent = getIntent();
		String kidId = objIntent.getStringExtra("kidId");
		queryValues.put("kidId", kidId);
		queryValues.put("kidName", kidName.getText().toString());
		controller.updateKid(queryValues);
		this.callHomeActivity(view);
		}
	
		public void removeKid(View view) {
		Intent objIntent = getIntent();
		String kidId = objIntent.getStringExtra("kidId");
		controller.deleteKid(kidId);
		this.callHomeActivity(view);
		}
		
		public void callHomeActivity(View view) {
		Intent objIntent1 = new Intent(getApplicationContext(), ParentHomePage.class);
		startActivity(objIntent1);
		}
	
		public void showTaskList(View view) {
		HashMap<String, String> queryValues =  new  HashMap<String, String>();
		taskName = (EditText) findViewById(R.id.taskName);
		Intent objIntent = getIntent();
		String kidId = objIntent.getStringExtra("kidId");
		queryValues.put("kidId", kidId);
		queryValues.put("kidName", kidName.getText().toString());
		controller.updateKid(queryValues);
		this.callHomeActivity(view);
		}	
	
	/* Initiating Menu XML file (menu.xml) */
    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }
    
    /**
     * Event Handling for Individual menu item selected
     * Identify single menu item by it's id
     * */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        //When Kid Info action item is clicked
        if (id == R.id.menu_homepage) {
            //Create Intent for parent home page 
            Intent parentIntent = new Intent(this,ParentHomePage.class); 
            //Start parent home Activity
            startActivity(parentIntent);
            return true;
            }
       return super.onOptionsItemSelected(item);
    }
}	
	


