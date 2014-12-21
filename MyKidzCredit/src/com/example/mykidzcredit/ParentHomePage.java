/*----------------------------------------------------------------
 *  Author:        Jyotika Syal
 *  Written:       10/14/2014
 *  
 *  ParentHomePage allows the parent to access kid information 
 *
 *----------------------------------------------------------------*/
package com.example.mykidzcredit;

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
import android.widget.Toast;


public class ParentHomePage extends ListActivity 
	{
	Intent intent;
	TextView kidId;
	DBController controller = new DBController(this);
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		getActionBar().setHomeButtonEnabled(true);
		setContentView(R.layout.activity_parenthomepage);
		ArrayList<HashMap<String, String>> kidList =  controller.getAllKids();
		if(kidList.size()!=0) {
			ListView lv = getListView();
			lv.setOnItemClickListener(new OnItemClickListener() {
				  @Override 
				  public void onItemClick(AdapterView<?> parent, View view,int position, long id) {
					  kidId = (TextView) view.findViewById(R.id.kidId);
					  String valKidId = kidId.getText().toString();					  
					  Intent  objIndent = new Intent(getApplicationContext(),EditKid.class);
					  objIndent.putExtra("kidId", valKidId); 
					  startActivity(objIndent); 
				  }
			}); 
			ListAdapter adapter = new SimpleAdapter( ParentHomePage.this,kidList, R.layout.activity_viewkidentry, new String[] { "kidId","kidName"}, new int[] {R.id.kidId, R.id.kidName}); 
			setListAdapter(adapter);
		}
	}
	public void showAddForm(View view) {
		Intent objIntent = new Intent(getApplicationContext(), AddKid.class);
		startActivity(objIntent);
	}
	
	
	/* Initiating Menu XML file (menu.xml) */
    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu1, menu);
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
	


