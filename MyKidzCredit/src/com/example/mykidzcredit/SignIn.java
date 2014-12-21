/*----------------------------------------------------------------
 *  Author:        Jyotika Syal
 *  Written:       11/29/2014
 *  
 *  SignIn shows Sign in and Sign Up buttons for the app
 *
 *----------------------------------------------------------------*/

package com.example.mykidzcredit;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class SignIn extends Activity {

	   EditText  username=null;
	   EditText  password=null;
	   Button login;
	   
	   @Override
	   protected void onCreate(Bundle savedInstanceState) 
	   {
	      super.onCreate(savedInstanceState);
	      setContentView(R.layout.activity_signin);
	      username = (EditText)findViewById(R.id.editText1);
	      password = (EditText)findViewById(R.id.editText2);
	      login = (Button)findViewById(R.id.button1);
	   
	      // Set OnClick Listener on SignIn button 
	      login.setOnClickListener(new View.OnClickListener() 
	      {
	    	  public void onClick(View v) 
	    	  {
	    		  // TODO Auto-generated method stub
	    		  if(username.getText().toString().equals("admin") && 
				  password.getText().toString().equals("admin"))
	    		  {
	    			  //Create Intent for SignIn  and Start The Activity
	    			  Intent intentSignIN=new Intent(getApplicationContext(),LoginPage.class);
	    			  startActivity(intentSignIN);
	    		  }
	    		  	else
	    		  	{
	    		  		Toast.makeText(getApplicationContext(), "Wrong Credentials",
					    Toast.LENGTH_SHORT).show();
	    		  	}	
	    	    }
	   			});
	   		}
}

	   
	   
	   
	   
	   

	
	   
	   



	