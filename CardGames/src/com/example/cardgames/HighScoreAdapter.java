package com.example.cardgames;

import java.util.ArrayList;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

public class HighScoreAdapter extends BaseAdapter{
	ArrayList<String> highscores;
	Context hContext;
	public HighScoreAdapter(Context context){
		highscores = new ArrayList<String>();
		hContext =  context;
	}
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return highscores.size();
	}

	@Override
	public Object getItem(int arg0) {
		// TODO Auto-generated method stub
		return highscores.get(arg0);
	}

	@Override
	public long getItemId(int arg0) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public View getView(int arg0, View arg1, ViewGroup arg2) {
		// TODO Auto-generated method stub
		return null;
	}

}
