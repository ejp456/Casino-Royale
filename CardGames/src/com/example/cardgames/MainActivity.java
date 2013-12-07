package com.example.cardgames;



import com.parse.LogInCallback; 
import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

import android.os.Bundle;
import android.widget.Button;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity {
	Button signup;
	Button loginbutton;
	EditText username;
	EditText password;
	String usernametxt;
    String passwordtxt;
	Context context;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		username = (EditText) findViewById(R.id.username);
		password = (EditText) findViewById(R.id.password);
		System.out.println(username.getText().toString());
		//the arguments here are used to identify this app with the Parse service
		Parse.initialize(this, "Insert Parse Key Here", "Insert Parse Key here"); 
		
		
		//context = this;
		//user.signUp(); //method can be used to create a username and password on Parse
		loginbutton = (Button) findViewById(R.id.stayButton);
		signup = (Button) findViewById(R.id.signup);
		
        // Login Button Click Listener
        loginbutton.setOnClickListener(new OnClickListener() {
 
            public void onClick(View v) {
                // Retrieve the text entered from the EditText
                usernametxt = username.getText().toString();
                passwordtxt = password.getText().toString();
                // Force user to fill up the form
                if (usernametxt.equals("") && passwordtxt.equals("")) {
                    Toast.makeText(getApplicationContext(),
                            "Please complete the login form",
                            Toast.LENGTH_LONG).show();
                } 
                else
                {
                // Send data to Parse.com for verification
                ParseUser.logInInBackground(usernametxt, passwordtxt,
                        new LogInCallback() {
                            public void done(ParseUser user, ParseException e) {
                                if (user != null) {
                                    // If user exist and authenticated, send user to Welcome.class
                                	Intent i = new Intent(MainActivity.this, BlackJackActivity.class);
             				       startActivity(i);
                                    
                                    Toast.makeText(getApplicationContext(),
                                            "Successfully Logged in",
                                            Toast.LENGTH_LONG).show();
                                    finish();
                                } else {
                                    Toast.makeText(
                                            getApplicationContext(),
                                            "No such user exist, please signup",
                                            Toast.LENGTH_LONG).show();
                                }
                            }
                        });
                }
            }
        });		
		// Sign up Button Click Listener
	    signup.setOnClickListener(new OnClickListener() {
        public void onClick(View v) {
            // Retrieve the text entered from the EditText
            usernametxt = username.getText().toString();
            passwordtxt = password.getText().toString();

            // Force user to fill up the form
            if (usernametxt.equals("") && passwordtxt.equals("")) {
                Toast.makeText(getApplicationContext(),
                        "Please complete the sign up form",
                        Toast.LENGTH_LONG).show();
            } 
            else 
            {
                // Save new user data into Parse.com Data Storage
                ParseUser user = new ParseUser();
                user.setUsername(usernametxt);
                user.setPassword(passwordtxt);
                user.signUpInBackground(new SignUpCallback() {
                    public void done(ParseException e) {
                        if (e == null) {
                            // Show a simple Toast message upon successful registration
                            Toast.makeText(getApplicationContext(),
                                    "Successfully Signed up, please log in.",
                                    Toast.LENGTH_LONG).show();
                        } else {
                            Toast.makeText(getApplicationContext(),
                                    "Username is already registered", Toast.LENGTH_LONG)
                                    .show();
                        }
                    }
                });
            }
           }
	    });
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	/*public void login(View v){
		ParseUser.logInInBackground(username.getText().toString(), password.getText().toString(), new LogInCallback() {
			  @Override
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
			    	Toast.makeText(context, e.getMessage(), Toast.LENGTH_LONG).show();;
			    }
			  }
			});
	}*/
	public void createUser(View v){
		Intent i = new Intent(MainActivity.this, HighScoreActivity.class);
	       startActivity(i);
	}
	
}

 
