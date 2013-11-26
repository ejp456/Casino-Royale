package com.example.cardgames;


import com.gamefiles.Deck;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class BlackJackActivity extends Activity {
    private GameView mGameView;
    private Button deal;
    private Button hit;
    private Button stand;
    TextView text;
    public static String PACKAGE_NAME;
    Deck currentDeck;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_black_jack);
		mGameView = (GameView) findViewById(R.id.game_view);
		
		deal = (Button)findViewById(R.id.dealButton);
		hit = (Button)findViewById(R.id.hitButton);		
		stand = (Button)findViewById(R.id.stayButton);
		
		hit.setEnabled(false);
		stand.setEnabled(false);
		
		PACKAGE_NAME = getApplicationContext().getPackageName();
		currentDeck = new Deck();
		text = (TextView)findViewById(R.id.textView1);
		deal.setOnClickListener(new View.OnClickListener() {
			
			public void onClick(View v) {
				hit.setEnabled(true);
				stand.setEnabled(true);
				mGameView.startGame(currentDeck);
				if(mGameView.getPlayer().getValue()==21){
					endRound();
				}else{
					text.setText("Player: "+mGameView.getPlayer().getValue());
					deal.setEnabled(false);
				}
				
				
			}
		});
		hit.setOnClickListener(new View.OnClickListener() {
			
			public void onClick(View v) {
				mGameView.hitPressed(currentDeck);
				text.setText("Player: "+mGameView.getPlayer().getValue());
				if(mGameView.getPlayer().getValue()>21){
					text.setText("Player busts");
					hit.setEnabled(false);
					stand.setEnabled(false);
					deal.setEnabled(true);
				}
				
			}
		});
		stand.setOnClickListener(new View.OnClickListener() {
			
			public void onClick(View v) {
				endRound();
			}
		});
		
		
	}
	public void endRound(){
		mGameView.endRound(currentDeck);
		if(mGameView.getDealer().getValue()>21){
			text.setText("Dealer busts");
		}else if(mGameView.getDealer().getValue()>mGameView.getPlayer().getValue()){
			text.setText("Dealer wins");
		}else if(mGameView.getDealer().getValue()==mGameView.getPlayer().getValue()){
			text.setText("Push");
		}else{
			text.setText("Player wins");
		}
		hit.setEnabled(false);
		stand.setEnabled(false);
		deal.setEnabled(true);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.black_jack, menu);
		return true;
	}

}
