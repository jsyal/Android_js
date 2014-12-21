/*----------------------------------------------------------------
 *  Author:        Jyotika Syal
 *  Written:       12/05/2014
 *  
 *  UpdateTask allows the kid to send a task for approval
 *
 *----------------------------------------------------------------*/
package com.example.mykidzcredit;

import java.util.HashMap;
import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
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
import android.widget.TextView;
import android.widget.Toast;

public class UpdateTask extends Activity {
Button assignbutton;
RadioButton rg1,rg2;
String status;
String title;
String text;
TextView txttitle;
TextView txttext;


DBController controller = new DBController(this);

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_updatetask);
		 
		 Button assignbutton = (Button) findViewById(R.id.buttonsend);
		 
		 RadioButton rg1 = (RadioButton) findViewById(R.id.radio01);
		 RadioButton rg2 = (RadioButton) findViewById(R.id.radio02);
		 
	        
	       //OnClick Listener for Incomplete radio button for task 
	        findViewById(R.id.radio02).setOnClickListener( new View.OnClickListener() 
	        	{
	        	@Override
	        	public void onClick(View v) {
	        	Button assignbutton=(Button)findViewById(R.id.buttonsend);
	        	assignbutton.setEnabled(false);
	        	status="Incomplete";
	        	}
	        	});
	        
	      //OnClick Listener for Complete radio button for task 
	        findViewById(R.id.radio01).setOnClickListener( new View.OnClickListener() 
	        	{
	        	@Override
	        	public void onClick(View v) {
	        	Button assignbutton=(Button)findViewById(R.id.buttonsend);
	        	assignbutton.setEnabled(true);
	        	status="Complete";
	        	}
	        	});
	        
	      //OnClick Listener for Send for approval button for task 
	        findViewById(R.id.buttonsend).setOnClickListener( new View.OnClickListener() {
	        	
	        	@Override
	        	public void onClick(View v) {
	        	    Button assignbutton=(Button)findViewById(R.id.buttonsend);
	        	    
	        	    HashMap<String, String> queryValues =  new  HashMap<String, String>();
	                Intent objIntent = getIntent();
	        		String taskId = objIntent.getStringExtra("taskId");
	        		queryValues.put("taskId", taskId);
	        		queryValues.put("taskStatus",status);
	        		controller.updateTask(queryValues);
	        		Notification();
	        		Toast.makeText(getApplicationContext(), "Notification sent to parent!",
	  				      Toast.LENGTH_SHORT).show();
	        		Intent objIntent2=new Intent(getApplicationContext(),KidHomePage.class);		
	        		startActivity(objIntent2);
	        	}
	        	});
	}
	        
	public void Notification() {
		// Open EditKid Class on Notification Click
		Intent intent = new Intent(this, ParentHomePage.class);
		// Set Notification Title
		String strtitle = getString(R.string.notificationtitle);
		// Set Notification Text
		String strtext = getString(R.string.notificationtext);
		// Send data to ParentHomePage Class
		intent.putExtra("title", strtitle);
		intent.putExtra("text", strtext);
		// Open ParentHomePAge.java Activity
		Intent resultIntent = new Intent(this, ParentHomePage.class);
		//resultIntent.putExtra(CommonConstants.EXTRA_MESSAGE, msg);
		resultIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | 
		        Intent.FLAG_ACTIVITY_CLEAR_TASK);
		     
		// Because clicking the notification launches a new ("special") activity, 
		// there's no need to create an artificial back stack.
		PendingIntent resultPendingIntent =
		         PendingIntent.getActivity(
		         this,
		         0,
		         resultIntent,
		         PendingIntent.FLAG_UPDATE_CURRENT
		);
		
		
		//Create Notification using NotificationCompat.Builder 
		NotificationCompat.Builder builder = new NotificationCompat.Builder(this)
				// Set Icon
				.setSmallIcon(R.drawable.kiddies2)
				// Set Ticker Message
				.setTicker(getString(R.string.notificationticker))
				// Set Title
				.setContentTitle(getString(R.string.notificationtitle))
				// Set Text
				.setContentText(getString(R.string.notificationtext))
				// Add an Action Button below Notification
				.addAction(R.drawable.ic_launcher, "Action Button", resultPendingIntent)
				// Set PendingIntent into Notification
				.setContentIntent(resultPendingIntent)
				// Dismiss Notification
				.setAutoCancel(true);
 
		// Create Notification Manager
		NotificationManager notificationmanager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
		// Build Notification with Notification Manager
		notificationmanager.notify(0, builder.build());
 
	}
 
	/* Initiating Menu XML file (menu.xml) */
    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.update_task, menu);
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
    	
    	if (id == R.id.menu_homepage) {
            //Create Intent for HomePage Activity
            Intent kidIntent = new Intent(this,KidHomePage.class); 
            //Start KidHomePage Activity
            startActivity(kidIntent);
            return true;
            } 
  
    		//When Kid Info action item is clicked
        	if (id == R.id.menu_loginpage) {
            //Create Intent for Login Activity
            Intent loginIntent = new Intent(this,LoginPage.class); 
            //Start Login Activity
            startActivity(loginIntent);
            return true;
            }
      return super.onOptionsItemSelected(item);
    }
}	
	



	
	
	          
	        
	
	        
	            
	