/*----------------------------------------------------------------
 *  Author:        Jyotika Syal
 *  Written:       11/14/2014
 *  
 *  LoginPage allows the user to access the app as parent or kid
 *
 *----------------------------------------------------------------*/

package com.example.mykidzcredit;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class LoginPage extends Activity {
		Button parentbuttonp;
		Button childbuttonc;
		
		@Override
		protected void onCreate(Bundle savedInstanceState) 
		{
		     super.onCreate(savedInstanceState);
		     setContentView(R.layout.activity_loginpage);
		     // Get The Reference Of Buttons
		     parentbuttonp=(Button)findViewById(R.id.parentbutton);
		     childbuttonc=(Button)findViewById(R.id.childbutton);
			
		     // Set OnClick Listener on parent Login button 
		     parentbuttonp.setOnClickListener(new View.OnClickListener() {
		     public void onClick(View v) {
			// TODO Auto-generated method stub
			
			// Create Intent for SignUpActivity  and Start The Activity
			Intent intentparent=new Intent(getApplicationContext(),ParentHomePage.class);
			startActivity(intentparent);
			}
		});

		  // Set OnClick Listener on Child Login button 
		  childbuttonc.setOnClickListener(new View.OnClickListener() {
			  public void onClick(View v) {
				  // TODO Auto-generated method stub
			
				  // Create Intent for SignUpActivity  and Start The Activity
				  Intent intentchild=new Intent(getApplicationContext(),KidHomePage.class);
				  startActivity(intentchild);
			  	}
		  	});
}
}
