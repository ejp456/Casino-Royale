package com.example.cardgames;


import com.gamefiles.BlackJackHand;
import com.gamefiles.Card;
import com.gamefiles.Card.FaceValue;
import com.gamefiles.Deck;
import com.gamefiles.Player;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;

public class GameView extends View {
	Bitmap card;
	boolean startgame = false;
	boolean hit = false;
	Canvas canvas;
	int count = 1;
	
	Player player;
	Player dealer;
	//getResources().getIdentifier("resname", "restype", com.example.whatever);//
	public GameView(Context context, AttributeSet attrs) {
		super(context, attrs);	
		setDrawingCacheEnabled(true);
		
	}
	public Player getPlayer(){
		return player;
	}
	public Player getDealer(){
		return dealer;
	}
	public void startGame(Deck currentDeck){
		player = new Player();
		dealer = new Player();
		currentDeck.Shuffle();
		for(int i=0;i<4;i++){
			Card c = currentDeck.Draw();
			if(i%2==0 || i==0){
				player.getHand().getCards().add(c);				
			}else{
				if(i==3){
					c.isCardUp(false);
				}
				dealer.getHand().getCards().add(c);			
			}
			
		}
		startgame = true;
		invalidate();
	}
	public void DealerPlay(Deck currentDeck)
    {
		Card c = currentDeck.Draw();
        // Dealer's card that was facing down is turned up when they start playing
        dealer.getHand().getCards().get(1).isCardUp(true);

        // Check to see if dealer has a hand that is less than 17
        // If so, dealer should keep hitting until their hand is greater or equal to 17
        if (dealer.getValue()<17)
        {
            dealer.getHand().getCards().add(c);
            DealerPlay(currentDeck);
        }
    }
	public void endRound(Deck currentDeck){
		DealerPlay(currentDeck);
		invalidate();
	}
	public void hitPressed(Deck currentDeck){
		Card c = currentDeck.Draw();
		player.hand.getCards().add(c);
		invalidate();
	}
	@Override
	  protected void onDraw(Canvas canvas){
		 float playercards = 0;
		 float dealercards = 0;
		 super.onDraw(canvas);
		 Paint mBmpPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
		 Bitmap b = BitmapFactory.decodeResource(getResources(), R.drawable.cl1);
		 if(startgame){
			 for(int i=0;i<dealer.getHand().getCards().size();i++){
				 Bitmap dealerCard = BitmapFactory.decodeResource(getResources(), getResources().getIdentifier(loadCard(dealer.getHand().getCards().get(i)), "drawable", BlackJackActivity.PACKAGE_NAME));
				 if(!dealer.getHand().getCards().get(i).isCardUp()){
					 canvas.drawBitmap(dealerCard.createScaledBitmap(dealerCard, b.getWidth(), b.getHeight(), true), b.getWidth()/2,0,mBmpPaint);
				 }else{
					 canvas.drawBitmap(dealerCard, dealercards, 0, mBmpPaint); 
				 }
				 dealercards+=dealerCard.getWidth()/2;
			     
			 }
			 for(int i=0;i<player.getHand().getCards().size();i++){
				 Bitmap playerCard = BitmapFactory.decodeResource(getResources(), getResources().getIdentifier(loadCard(player.getHand().getCards().get(i)), "drawable", BlackJackActivity.PACKAGE_NAME)); 
				
			     canvas.drawBitmap(playerCard, playercards, canvas.getHeight()-b.getHeight(), mBmpPaint);
			     playercards += playerCard.getWidth()/2;
			 }
				 
			
		    
		     
		 
		     
		 }
		 

	}
	private String loadCard(Card c)
    {
            StringBuilder image = new StringBuilder();

            switch (c.getSuit())
            {
                case Diamonds:
                    image.append("di");
                    break;
                case Hearts:
                    image.append("he");
                    break;
                case Spades:
                    image.append("sp");
                    break;
                case Clubs:
                    image.append("cl");
                    break;
            }

            switch (c.getFaceVal())
            {
                case Ace:
                    image.append("1");
                    break;
                case King:
                    image.append("k");
                    break;
                case Queen:
                    image.append("q");
                    break;
                case Jack:
                    image.append("j");
                    break;
                case Ten:
                    image.append("10");
                    break;
                case Nine:
                    image.append("9");
                    break;
                case Eight:
                    image.append("8");
                    break;
                case Seven:
                    image.append("7");
                    break;
                case Six:
                    image.append("6");
                    break;
                case Five:
                    image.append("5");
                    break;
                case Four:
                    image.append("4");
                    break;
                case Three:
                    image.append("3");
                    break;
                case Two:
                    image.append("2");
                    break;
            }
            //check to see if the card should be faced down or up;
            if (!c.isCardUp()){
            	return "cover";
            }else{
            	 return image.toString();
            }
          
         
        }

        
    
	
}
