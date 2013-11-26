package com.example.cardgames;

import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

public class User {
	private ParseUser pUser;
	
	public User(String userName, String password, String email){
		pUser = new ParseUser();
		pUser.setUsername(userName);
		pUser.setPassword(password);
		pUser.setEmail(email);
	}
	public void signUp(){
		pUser.signUpInBackground(new SignUpCallback() {
			  public void done(ParseException e) {
				    if (e == null) {
				      // Hooray! Let them use the app now.
				    } else {
				    	e.printStackTrace();
				      // Sign up didn't succeed. Look at the ParseException
				      // to figure out what went wrong
				    }
				  }
				});
	}

}
