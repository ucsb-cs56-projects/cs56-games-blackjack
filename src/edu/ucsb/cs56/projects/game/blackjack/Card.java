package edu.ucsb.cs56.projects.game.blackjack;

/** Card Class has the following variables: suit, value, name,
 *  isAce (check if Ace), and cardNumber (set to face if greater than 10) 
 @author Brian Wan
 @author Fanny Kuang
 @author Eric Palyan
 @version 2014.02.27
 */
public class Card{
    private String name;
    private String suit;

    private int value;
    private int cardNumber;
   
    private boolean isAce;


    /** 
	@param value int, value of card, between 1 and 13, if greater than 10 it is set to 10
	@param suit String, suit of card, either Hearts, Clubs, Spades, or Diamonds
    */
    public Card(int value,String suit){
	this.suit=suit;
	this.value=value;
	cardNumber = value;

	/** determine card number **/
	if(suit.equals("Diamonds"))
	    cardNumber += 13;
	
	else if(suit.equals("Hearts"))
	    cardNumber += 26;
	
	else if(suit.equals("Spades"))
	    cardNumber += 39;

	/** determine card name, value and whether or not it is an ace. 
	/** by default isAce is set to false, so it will remain false
	/** unless value == 1 */
	if(value == 1){
	    name="Ace";
	    isAce = true;
	}
	
	else if(value >=2 && value <=10){
	    name=Integer.toString(value);
	}

	else if(value == 11){
	    name="Jack";
	    this.value=10;
	}

	else if(value == 12){
	    name="Queen";
	    this.value=10;
	}

	else if(value == 13){
	    name="King";
	    this.value=10;
	}
    }
    
    /** returns the value of the card
     *  @return int
     */
    public int getValue(){
	return value;
    }
    
    /** returns toString 
     *  @return String
     */   
    public String toString(){
	return name + " of " + suit;
    }
    
    /** returns whether or not the card is an ace
     *  @return boolean
     */
    public boolean isAnAce(){
	return isAce;
    }
    
    /** returns the true Card Number (e.g. King = 13, Ace = 1, 4 = 4)
     *  @return int
     */
    public int getCardNumber(){
	return cardNumber;
    }
}//end Card
