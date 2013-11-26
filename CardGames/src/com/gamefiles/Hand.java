package com.gamefiles;

import java.util.ArrayList;

import com.gamefiles.Card.FaceValue;

public class Hand {
	        // Creates a list of cards
	        protected ArrayList<Card> cards = new ArrayList<Card>();
	        public int numberOfCards(){
	        	return cards.size();
	        }
	        public ArrayList<Card> getCards(){
	        	return cards;
	        }

	        /// <summary>
	        /// Checks to see if the hand contains a card of a certain face value
	        /// </summary>
	        /// <param name="item"></param>
	        /// <returns></returns>
	        public boolean containsCard(FaceValue item)
	        {
	            for(Card c : cards)
	            {
	                if (c.getFaceVal() == item)
	                {
	                    return true;
	                }
	            }
	            return false;
	        }
	    }
	    /// <summary>
	    /// This class is game-specific.  Creates a BlackJack hand that inherits from class hand
	    /// </summary>
	   
