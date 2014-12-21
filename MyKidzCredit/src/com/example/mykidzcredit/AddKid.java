/*----------------------------------------------------------------
 *  Author:        Jyotika Syal
 *  Written:       11/29/2014
 *  
 *  AddKid allows to add a kid to kid table with a assigned name and Id
 *
 *----------------------------------------------------------------*/

package com.example.mykidzcredit;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.SimpleAdapter;
import android.widget.Spinner;

//this class is used for adding a kid into the database with columns Kid Id (primary key) and first name
public class AddKid extends Activity{
	EditText kidName;
	DBController controller = new DBController(this);
	
	 @Override
	    public void onCreate(Bundle savedInstanceState) 
	 		{
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.activity_addkid);
	        kidName = (EditText) findViewById(R.id.kidName);
	 		}
	 //method to add new kid
	public void addNewKid(View view) 
		{
		HashMap<String, String> queryValues =  new  HashMap<String, String>();
		queryValues.put("kidName", kidName.getText().toString());
		controller.insertKid(queryValues);
		this.callHomeActivity(view);
		}
	
	public void callHomeActivity(View view) {
		Intent objIntent = new Intent(getApplicationContext(), ParentHomePage.class);
		startActivity(objIntent);
	}	



	/* Initiating Menu XML file (menu_addkid.xml) */
	@Override
	public boolean onCreateOptionsMenu(Menu menu)
		{
		MenuInflater menuInflater = getMenuInflater();
		menuInflater.inflate(R.menu.menu_addkid, menu);
		return super.onCreateOptionsMenu(menu);
		}

		/**
		 * Event Handling for Individual menu item selected
		 * Identify single menu item by it's id
		 * */
		@Override
		public boolean onOptionsItemSelected(MenuItem item) 
		{
		int id = item.getItemId();
		//When Parent Home action item is clicked
		if (id == R.id.menu_h) {
        //Create Intent for Product Activity
        Intent parentintent = new Intent(this,ParentHomePage.class); 
        //Start Product Activity
        startActivity(parentintent);
        return true;
        }
    
    return super.onOptionsItemSelected(item);
}
}	


