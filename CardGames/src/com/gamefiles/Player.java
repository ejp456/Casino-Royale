package com.gamefiles;

public class Player {
	public BlackJackHand hand;
	
	public Player(){
		hand = new BlackJackHand();
	}
	public BlackJackHand getHand(){
		return hand;
	}
	public int getValue(){
		return hand.getSumOfHand();
	}

}
