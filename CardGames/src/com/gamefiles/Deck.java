package com.gamefiles;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Random;

import com.gamefiles.Card.FaceValue;
import com.gamefiles.Card.Suit;

public class Deck {
	// Creates a list of cards
    protected ArrayList<Card> cards = new ArrayList<Card>();

    // Returns the card at the given position
    public Card getCard(int position){return cards.get(position);}
    /// <summary>
    /// One complete deck with every face value and suit
    /// </summary>
    public Deck()
    {
        for(final Suit suit : Card.Suit.values())
        {
            for(FaceValue faceVal : Card.FaceValue.values())
            {
                cards.add(new Card(suit, faceVal, true));
            }
        }
    }

    /// <summary>
    /// Draws one card and removes it from the deck
    /// </summary>
    /// <returns></returns>
    public Card Draw()
    {
    	if(cards.size()==0){
    		 for(final Suit suit : Card.Suit.values())
    	        {
    	            for(FaceValue faceVal : Card.FaceValue.values())
    	            {
    	                cards.add(new Card(suit, faceVal, true));
    	            }
    	        }
    		 Collections.shuffle(cards);
    	}
    		Card card = cards.get(0);
            cards.remove(0);
    
        
        return card;
    }

    /// <summary>
    /// Shuffles the cards in the deck
    /// </summary>
    public void Shuffle()
    {
    	Collections.shuffle(cards);
        
    }

    /// <summary>
    /// Swaps the placement of two cards
    /// </summary>
    /// <param name="index1"></param>
    /// <param name="index2"></param>
   
}
