/*----------------------------------------------------------------
 *  Author:        Jyotika Syal
 *  Written:       10/24/2014
 *  
 *  MainActivity 
 *
 *----------------------------------------------------------------*/
package com.example.mykidzcredit;

import java.util.List;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import android.app.Activity;


public class MainActivity extends Activity

{	
	Button btnsignin;
	Button btnsignup;
	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
	     super.onCreate(savedInstanceState);
	     setContentView(R.layout.activity_main);
	// Get The Reference Of Buttons
    btnsignin=(Button)findViewById(R.id.signinbutton);
    btnsignup=(Button)findViewById(R.id.signupbutton);
		
   // Set OnClick Listener on SignUp button 
   btnsignin.setOnClickListener(new View.OnClickListener() {
	public void onClick(View v) {
		// TODO Auto-generated method stub
		
		/// Create Intent for SignUpActivity  and Start The Activity
		Intent intentSignIN=new Intent(getApplicationContext(),SignIn.class);
		startActivity(intentSignIN);
		}
	});

   
   btnsignup.setOnClickListener(new View.OnClickListener() {
		public void onClick(View v) {
			Toast.makeText(getApplicationContext(), "Implementation in next version!",
				      Toast.LENGTH_SHORT).show();
			}
		});
}
}
