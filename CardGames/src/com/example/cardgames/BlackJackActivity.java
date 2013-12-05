package com.example.cardgames;


import com.gamefiles.Deck;
import com.parse.ParseUser;

import android.os.Bundle;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class BlackJackActivity extends Activity {
	final int WIN = 1;
	final int LOSE = 2;
	final int PUSH = 3;
    private GameView mGameView;
    private Button deal;
    private Button hit;
    private Button stand;
    private Button bet;
    int backgroundColor=R.color.sendDarkColor;
    ParseUser user;
    int currentBet=5;
    private TextView userInfo;
    Context context;
    TextView text;
    public static String PACKAGE_NAME;
    Deck currentDeck;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_black_jack);
		
		
		
		
		userInfo = (TextView)findViewById(R.id.userInfo);
		context=this;
		user = ParseUser.getCurrentUser();
		user.getUsername();
		
		userInfo.setText(user.getUsername()+"    "+"$"+BankAccount.getInstanceOf().getBalanace());
		
		mGameView = (GameView) findViewById(R.id.game_view);
		
		deal = (Button)findViewById(R.id.dealButton);
		hit = (Button)findViewById(R.id.hitButton);		
		stand = (Button)findViewById(R.id.stayButton);
		bet = (Button)findViewById(R.id.increaseBet);
		
		hit.setEnabled(false);
		stand.setEnabled(false);
		
		PACKAGE_NAME = getApplicationContext().getPackageName();
		currentDeck = new Deck();
		text = (TextView)findViewById(R.id.textView1);
		deal.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				if(currentBet<=BankAccount.getInstanceOf().getBalanace()){
					hit.setEnabled(true);
					stand.setEnabled(true);
					bet.setEnabled(false);
					mGameView.startGame(currentDeck);
					if(mGameView.getPlayer().getValue()==21){
						endRound();
					}else{
						text.setText("Player: "+mGameView.getPlayer().getValue());
						deal.setEnabled(false);
					}
				}else{
					Toast.makeText(context, "Insufficient funds,decrease bet", Toast.LENGTH_LONG).show();
				}
							
			}
		});
		hit.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				mGameView.hitPressed(currentDeck);
				text.setText("Player: "+mGameView.getPlayer().getValue());
				if(mGameView.getPlayer().getValue()>21){
					text.setText("Player busts");
					hit.setEnabled(false);
					stand.setEnabled(false);
					deal.setEnabled(true);
					bet.setEnabled(true);
					decreaseMoney();
				}
				
			}
		});
		stand.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				endRound();
			}
		});
		
		
	}
	public void showBetDialog(View v){
		final Dialog dialog = new Dialog(context);
		dialog.setContentView(R.layout.bet_screen);
		dialog.setTitle("Determine bet");
		final EditText eText = (EditText)dialog.findViewById(R.id.editText1);
		eText.setText(""+currentBet);
		Button setButton = (Button)dialog.findViewById(R.id.setButton);
		setButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				TextView text = (TextView)dialog.findViewById(R.id.textView1);
				
				int money = Integer.parseInt(eText.getText().toString());
				if(money<=BankAccount.getInstanceOf().getBalanace()){
					currentBet = money;
					dialog.dismiss();
				}else{
					text.setText("Insufficient funds");
				}
				
				
			}
		});
		dialog.show();
	}
	public void endRound(){
		mGameView.endRound(currentDeck);
		if(mGameView.getDealer().getValue()>21){
			text.setText("Dealer busts");
			mGameView.roundResults(WIN,currentBet);
			increaseMoney();
		}else if(mGameView.getDealer().getValue()>mGameView.getPlayer().getValue()){
			text.setText("Dealer wins");
			mGameView.roundResults(LOSE,currentBet);
			decreaseMoney();
		}else if(mGameView.getDealer().getValue()==mGameView.getPlayer().getValue()){
			mGameView.roundResults(PUSH,currentBet);
			text.setText("Push");
		}else{
			mGameView.roundResults(WIN,currentBet);
			text.setText("Player wins");
			increaseMoney();
		}
		hit.setEnabled(false);
		stand.setEnabled(false);
		deal.setEnabled(true);
		bet.setEnabled(true);
	}


	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.black_jack, menu);
		return true;
	}
	public void increaseMoney(){
		BankAccount.getInstanceOf().deposit(currentBet);
		userInfo.setText(user.getUsername()+"    "+"$"+BankAccount.getInstanceOf().getBalanace());
	}
	public void decreaseMoney(){
		BankAccount.getInstanceOf().withdraw(currentBet);
		userInfo.setText(user.getUsername()+"    "+"$"+BankAccount.getInstanceOf().getBalanace());
		if(BankAccount.getInstanceOf().getBalanace()==0){
			endGameDialog();
		}
	}
	public void endGameDialog(){
		final Dialog dialog = new Dialog(context);
		dialog.setContentView(R.layout.game_over);
		dialog.setTitle("Game Over");
		Button newGame = (Button)dialog.findViewById(R.id.newGame);
		Button quit = (Button)dialog.findViewById(R.id.Quit);
		newGame.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				BankAccount.getInstanceOf().deposit(1000);
				userInfo.setText(user.getUsername()+"    "+"$"+BankAccount.getInstanceOf().getBalanace());
				
				dialog.dismiss();
			}
		});
		quit.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent i = new Intent(BlackJackActivity.this, MainActivity.class);
			       startActivity(i);
				dialog.dismiss();
			}
		});
		dialog.show();
	}
	
}
