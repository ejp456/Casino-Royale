package com.example.cardgames;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import android.os.Bundle;
import android.app.ListActivity;
import android.view.Menu;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;

public class HighScoreActivity extends ListActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_high_score);
		String[] test = {"Joelle  $2000","John  $1500","Etai  $1000"};
		ArrayList<String> highscores=new ArrayList<String>();
		
		 
		 

        ParseQuery<ParseObject> query = ParseQuery.getQuery("GameScore");
        query.orderByDescending("score");
        query.setLimit(10); //this might return only the last ten, not sure
        query.selectKeys(Arrays.asList("playerName", "score"));
        List<ParseObject> results;
        try {
            results = query.find();
            for(int i=0;i<results.size();i++){
                System.out.println(results.get(i).getInt("score")+" "+results.get(i).getString("playerName"));
                highscores.add(results.get(i).getString("playerName")+": "+results.get(i).getInt("score"));
            }
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        setListAdapter(new ArrayAdapter<String>(this,R.layout.simplelist,highscores));
	
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.high_score, menu);
		return true;
	}

}
