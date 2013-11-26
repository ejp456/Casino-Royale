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
		Parse.initialize(this, "rcoJm1GGgUI9wgN19GDGBk8qgbsHD3buV3MiquBp", "lUYmJQ1pQS3MrOMTrmycEKS8jRCg0Vzd45HYxLJz"); 
		User user = new User("e456","IamHere","skater85234@hotmail.com");
		context = this;
		//user.signUp();
	
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	public void login(View v){
		ParseUser.logInInBackground("e456", "IamHere", new LogInCallback() {
			  @SuppressLint("ShowToast")
			public void done(ParseUser user, ParseException e) {
			    if (user != null) {
			      // Hooray! The user is logged in.
			    //	Toast.makeText(context, "Login Successful", Toast.LENGTH_LONG).show();
			    
			    	Intent i = new Intent(MainActivity.this, BlackJackActivity.class);
				       startActivity(i);
			    } else {
			      // Signup failed. Look at the ParseException to see what happened.
			    	//Toast.makeText(context, e.getLocalizedMessage(), Toast.LENGTH_LONG);
			    }
			  }
			});
		
	}
	public void createUser(View v){
		Intent i = new Intent(MainActivity.this, HighScoreActivity.class);
	       startActivity(i);
	}

}
