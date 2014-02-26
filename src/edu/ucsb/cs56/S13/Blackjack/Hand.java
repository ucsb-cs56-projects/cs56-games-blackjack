package edu.ucsb.cs56.S13.Blackjack;
import java.util.ArrayList;

/** Class Hand contains an ArrayList of Cards and an int
 *   representing the value of the hand
 @author Brian Wan
 @author Fanny Kuang
 @version 2013.05.15
*/

public class Hand{
    
    private ArrayList<Card> myCards;
    private boolean hasAce;
    private int handValue;

    /** Two-arg constructor
	@param c1 Card, one of two cards
	@param c2 Card, two of two cards
    */
    public Hand(Card c1, Card c2){
	myCards = new ArrayList<Card>();
	myCards.add(c1);
	myCards.add(c2);

	for(int i=0; i<myCards.size(); i++){
	    handValue += myCards.get(i).getValue();
	}
	
	if(c1.isAnAce() || c2.isAnAce())
	    hasAce=true;
	else
	    hasAce=false;
    }
    
    /** No-arg constructor
     */
    public Hand(){
	myCards = new ArrayList<Card>();
	hasAce=false;
    }
    

    /** gets the value of the hand with aces counted as 1
     */
    public int getHandValue(){
	return handValue;
    }
    
    /** gets the value of the hand with aces counted as 11 or returns -1 if the value of the hand exceeds 21 with aces counted as 11
     */
    public int getSecondHandValue(){
	if(hasAce && getHandValue()<=11)
	    return getHandValue() + 10;
	else
	    return -1;
	}
    
    /** adds a Card to the ArrayList of Cards and keeps track of whether
     *  or not it was an ace
     @param c Card, adding a card
    */
	public void addCard(Card c){
	    myCards.add(c);
	    handValue += c.getValue();

	    if(c.isAnAce())
		hasAce=true;
	}
    
    /** toString returning the hand
     */
    public String toString(){
	String myHand = "";
	for(int i=0; i<myCards.size(); i++){
	    myHand += myCards.get(i).toString();
	    if(myCards.size() - i > 1){
		myHand += ", ";
	    }
	}
	return myHand;
    }
    
    /** gets the first card in the hand
     */    
    public Card getFirstCard(){
	return myCards.get(0);
    }
    
    /** gets the second card in the hand
     */
    public Card getSecondCard(){
	return myCards.get(1);
    }
    
    /** calculates whether or not the hand has blackjack
     */
    public boolean hasBlackjack(){
	if(getSecondHandValue() == 21 && myCards.size() == 2)
	    return true;
	return false;
    }
    
    /** returns a string displaying the hand's value(s)
     */
    public String displayHandValue(){
	String display = "";
	display += getHandValue();
	if(getSecondHandValue() > 0)
	    display+= " or " + getSecondHandValue();
	return display;
    }
    
    /** returns a string displaying the best hand value
     */
    public String displayBestValue(){
	String display = "";
	if(getHandValue() > getSecondHandValue())
	    display += getHandValue();
	else
	    display += getSecondHandValue();
	return display;
    }
}//end Hand
