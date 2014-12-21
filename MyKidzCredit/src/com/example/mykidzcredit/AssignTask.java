/*----------------------------------------------------------------
 *  Author:        Jyotika Syal
 *  Written:       11/29/2014
 *  
 *  AssignTask allows to mark the task as complete and assign a score to a task
 *
 *----------------------------------------------------------------*/


package com.example.mykidzcredit;

import java.util.HashMap;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;

public class AssignTask extends Activity {
Button assignbutton;
EditText editscore;
RadioButton rg1,rg2;
String status;
DBController controller = new DBController(this);

	@Override
	protected void onCreate(Bundle savedInstanceState) 
		{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_assigntask);
		Button assignbutton = (Button) findViewById(R.id.buttonassign);
		editscore=(EditText) findViewById(R.id.taskScore);
		RadioButton rg1 = (RadioButton) findViewById(R.id.radio01);
		RadioButton rg2 = (RadioButton) findViewById(R.id.radio02);
		
		//Initially set the assign button as disables
		assignbutton.setEnabled(false);
	      
		//OnClickListener for radio button (Incomplete)
	    findViewById(R.id.radio02).setOnClickListener( new View.OnClickListener() 
	    		{
	        	@Override
	        	public void onClick(View v) 
	        		{
	        		Button assignbutton=(Button)findViewById(R.id.buttonassign);
	        	    assignbutton.setEnabled(false);
	        	    status="Incomplete";
	        		}
	        	});
	        
	   //OnClickListener for radio button (Complete)
	     findViewById(R.id.radio01).setOnClickListener( new View.OnClickListener() 
	     		{
	    	 	@Override
	        	public void onClick(View v) 
	    	 		{
	        	    Button assignbutton=(Button)findViewById(R.id.buttonassign);
	        	    assignbutton.setEnabled(true);
	        	    status="Complete";
	    	 		}
	        	});
	        
	   //OnClickListener for assigning score
	        findViewById(R.id.buttonassign).setOnClickListener( new View.OnClickListener() 
	        	{
	        	@Override
	        	public void onClick(View v) 
	        		{
	        	    Button assignbutton=(Button)findViewById(R.id.buttonassign);
	        	    //Pass values to TaskList after score assignment
	        	    HashMap<String, String> queryValues =  new  HashMap<String, String>();
	                Intent objIntent = getIntent();
	        		String taskId = objIntent.getStringExtra("taskId");
	        		queryValues.put("taskId", taskId);
	        		queryValues.put("taskStatus",status);
	        		queryValues.put("taskScore",editscore.getText().toString());
	        		controller.updateTask(queryValues);
	        		Intent objIntent2=new Intent(getApplicationContext(),TaskList.class);		
	        		Intent objIntent3 = getIntent();
					String kidId = objIntent3.getStringExtra("kidId");
					objIntent2.putExtra("kidId", kidId);
	        		startActivity(objIntent2);
	        		}
	        	});
}
	
	/* Initiating Menu XML file (menu.xml) */
    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_assigntask, menu);
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
            //Create Intent for Product Activity
            Intent productIntent = new Intent(this,ParentHomePage.class); 
            //Start Product Activity
            startActivity(productIntent);
            return true;
            
        }
        
        if (id == R.id.menu_loginpage) {
            //Create Intent for Product Activity
            Intent productIntent = new Intent(this,LoginPage.class); 
            //Start Product Activity
            startActivity(productIntent);
            return true;
            
        }
        return super.onOptionsItemSelected(item);
    }
}	
	



	
	

	        
	            
	