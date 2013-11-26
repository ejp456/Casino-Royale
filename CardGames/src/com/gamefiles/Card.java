package com.gamefiles;

public class Card {
	 public enum Suit
	    {
	        Diamonds, Spades, Clubs, Hearts
	    }

	    /// <summary>
	    /// Card face values
	    /// </summary>
	 public enum FaceValue
	    {
	        Two(2), Three(3), Four(4), Five(5), Six(6), Seven(7), Eight(8),
	        Nine(9), Ten(10), Jack(11), Queen(12), King(13), Ace(14);
	        
	        private int value;
	        private FaceValue(int value) {
	                this.value = value;
	        }
	        public int getValue(){
	        	return value;
	        }


	    }
	    // Objects for card information
        private final Suit suit;
        private final FaceValue faceVal;
        private boolean isCardUp;

        public Suit getSuit(){
        	return suit;
        }
        public FaceValue getFaceVal(){
        	return faceVal;
        }
        public void isCardUp(boolean toggle){
        	this.isCardUp = toggle;
        }
        public boolean isCardUp(){
        	return isCardUp;
        }
    

        /// <summary>
        /// Constructor for a new card.  Assign the card a suit, face value, and if the card is facing up or down
        /// </summary>
        /// <param name="suit"></param>
        /// <param name="faceVal"></param>
        /// <param name="isCardUp"></param>
        public Card(Suit suit, FaceValue faceVal, Boolean isCardUp)
        {
            this.suit = suit;
            this.faceVal = faceVal;
            this.isCardUp = isCardUp;
        }

        /// <summary>
        /// Return the card as a string (i.e. "The Ace of Spades")
        /// </summary>
        /// <returns></returns>
        @Override
        public String toString()
        {
            return "The" + faceVal.toString() + "of" + suit.toString();
        }
}
