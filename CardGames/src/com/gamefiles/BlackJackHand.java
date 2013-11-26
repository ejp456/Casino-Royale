package com.gamefiles;

import com.gamefiles.Card.FaceValue;
	 public class BlackJackHand extends Hand
	    {
	        /// <summary>
	        /// This method compares two BlackJack hands
	        /// </summary>
	        /// <param name="otherHand"></param>
	        /// <returns></returns>
	        public int compareFaceValue(Object otherHand)
	        {
	            BlackJackHand aHand = (BlackJackHand)otherHand;
	            if (aHand != null)
	            {
	                return compareTo(getSumOfHand(),aHand.getSumOfHand());
	            }
	            else
	            {
	                throw new IllegalArgumentException("Argument is not a Hand");
	            }
	        }
	        public int compareTo(int one, int two){
	        	if(one==two){
	        		return 0;
	        	}else if(one>two){
	        		return 1;
	        	}else{
	        		return -1;
	        	}
	        }

	        /// <summary>
	        /// Gets the total value of a hand from BlackJack values
	        /// </summary>
	        /// <returns>int</returns>
	        public int getSumOfHand()
	        {
	            int val = 0;
	            int numAces = 0;

	            for(Card c : cards)
	            {
	                if (c.getFaceVal() == FaceValue.Ace)
	                {
	                    numAces++;
	                    val += 11;
	                }
	                else if (c.getFaceVal() == FaceValue.Jack || c.getFaceVal() == FaceValue.Queen || c.getFaceVal() == FaceValue.King)
	                {
	                    val += 10;
	                }
	                else
	                {
	                    val += c.getFaceVal().getValue();
	                }
	            }

	            while (val > 21 && numAces > 0)
	            {
	                val -= 10;
	                numAces--;
	            }

	            return val;
	        }
}

