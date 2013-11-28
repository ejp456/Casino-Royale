package com.example.cardgames;



import com.parse.LogInCallback;
import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseUser;

import android.os.Bundle;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity {
	EditText username;
	EditText password;
	Context context;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		username = (EditText) findViewById(R.id.username);
		password = (EditText) findViewById(R.id.password);
		System.out.println(username.getText().toString());
		//the arguments here are used to identify this app with the Parse service
		Parse.initialize(this, "rcoJm1GGgUI9wgN19GDGBk8qgbsHD3buV3MiquBp", "lUYmJQ1pQS3MrOMTrmycEKS8jRCg0Vzd45HYxLJz"); 
		
		User user = new User("e456","IamHere","skater85234@hotmail.com");
		context = this;
		//user.signUp(); method can be used to create a username and password on Parse
	
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	public void login(View v){
		ParseUser.logInInBackground(username.getText().toString(), password.getText().toString(), new LogInCallback() {
			  @SuppressLint("ShowToast")
			public void done(ParseUser user, ParseException e) {
			    if (user != null) {
			      // Hooray! The user is logged in.
			    	Toast.makeText(context, "Login Successful", Toast.LENGTH_LONG).show();
			    
			    	Intent i = new Intent(MainActivity.this, BlackJackActivity.class);
				       startActivity(i);
			    } else {
			      // Signup failed. Look at the ParseException to see what happened.
			    	e.printStackTrace();
			    	Log.d("reading: ", e.getMessage());
			    	Toast.makeText(context, (CharSequence) e.getMessage(), Toast.LENGTH_LONG).show();;
			    }
			  }
			});
		
	}
	public void createUser(View v){
		Intent i = new Intent(MainActivity.this, HighScoreActivity.class);
	       startActivity(i);
	}

}
