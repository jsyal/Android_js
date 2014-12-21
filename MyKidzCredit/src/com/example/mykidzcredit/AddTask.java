/*----------------------------------------------------------------
 *  Author:        Jyotika Syal
 *  Written:       11/29/2014
 *  
 *  AddTask allows to add a task to kid's account with a task name
 *
 *----------------------------------------------------------------*/


package com.example.mykidzcredit;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;
import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.SimpleAdapter;
import android.widget.Spinner;

//this class is used for adding tasks for each kid
public class AddTask extends Activity{
	EditText taskName;
	DBController controller = new DBController(this);
	
	 @Override
	    public void onCreate(Bundle savedInstanceState) 
	 	{
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.activity_addtask);
	    taskName = (EditText) findViewById(R.id.taskName);
	    }
	
	 //add new task method
	 public void addNewTask(View view) 
	 	{
		HashMap<String, String> queryValues =  new  HashMap<String, String>();
		Intent objIntent1 = getIntent();
		String kidId = objIntent1.getStringExtra("kidId");
		queryValues.put("taskName", taskName.getText().toString());
		queryValues.put("taskStatus","Incomplete");
		queryValues.put("taskScore", "0");
		queryValues.put("kidnum", kidId);
		controller.insertTask(queryValues);
		Intent objIntent2=new Intent(getApplicationContext(),TaskList.class);		
		objIntent2.putExtra("kidId", kidId);
		startActivity(objIntent2);
	 	}
	
	
	/* Initiating Menu XML file (menu.xml) */
    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    	{
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_addtask, menu);
        return super.onCreateOptionsMenu(menu);
    	}
    
    	/**
    	 * Event Handling for Individual menu item selected
    	 * Identify single menu item by it's id
    	 * */
    	@Override
    	public boolean onOptionsItemSelected(MenuItem item) 
    	{
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        //When Parent Home Page action item is clicked
        	if (id == R.id.menu_homepage) 
        	{
            //Create Intent for parenthome Activity
            Intent parenthome = new Intent(this,ParentHomePage.class); 
            //Start parenthome Activity
            startActivity(parenthome);
            return true;
            }
      return super.onOptionsItemSelected(item);
}
}	
	



	
	
	

