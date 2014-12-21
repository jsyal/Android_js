/*----------------------------------------------------------------
 *  Author:        Jyotika Syal
 *  Written:       11/14/2014
 *  
 *  MyTaskList allows the kid to view their task information(task name, status, and score)
 *
 *----------------------------------------------------------------*/
package com.example.mykidzcredit;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.HashMap;


import android.os.Bundle;
import android.app.ListActivity;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListAdapter;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.ListView;


public class MyTaskList extends ListActivity {
	Intent intent;
	TextView taskId;
	DBController controller = new DBController(this);
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_mytasklist);
		Intent objIntent1 = getIntent();
		String kidId = objIntent1.getStringExtra("kidId");
		
		ArrayList<HashMap<String, String>> taskList =  controller.getOneKidTaskInfo(kidId);
		if(taskList.size()!=0) {
			ListView lv = getListView();
			lv.setOnItemClickListener(new OnItemClickListener() {
				  @Override 
				  public void onItemClick(AdapterView<?> parent, View view,int position, long id) {
					  taskId = (TextView) view.findViewById(R.id.taskId);
					  String valtaskId = taskId.getText().toString();					  
					  Intent  objIndent = new Intent(getApplicationContext(),UpdateTask.class);
					  objIndent.putExtra("taskId", valtaskId); 
					  startActivity(objIndent); 
				  }
			}); 
			ListAdapter adapter = new SimpleAdapter( MyTaskList.this,taskList, R.layout.activity_viewtaskentry, new String[] { "taskId","taskName","taskStatus","taskScore"}, new int[] {R.id.taskId, R.id.taskName, R.id.taskStatus, R.id.taskScore}); 
			setListAdapter(adapter);
		}
	}
	public void showAddForm(View view) {
		Intent objIntent = new Intent(getApplicationContext(), AddTask.class);
		startActivity(objIntent);
	}
	/* Initiating Menu XML file (menu.xml) */
	@Override
	public boolean onCreateOptionsMenu(Menu menu)
	{
	    MenuInflater menuInflater = getMenuInflater();
	    menuInflater.inflate(R.menu.menu_mytasklist, menu);
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
	        //Create Intent for Kid home page
	        Intent kidIntent = new Intent(this,KidHomePage.class); 
	        startActivity(kidIntent);
	        return true;
	    	}
	   
	    if (id == R.id.menu_loginpage) {
	        //Create Intent for Product Activity
	        Intent loginIntent = new Intent(this,LoginPage.class); 
	        startActivity(loginIntent);
	        return true;
	        }
	    return super.onOptionsItemSelected(item);
	}
}	




