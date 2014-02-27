package edu.ucsb.cs56.S13.Blackjack;
import java.util.ArrayList;

/** class Deck
 *  contains an ArrayList of Cards
 @author Brian Wan
 @author Fanny Kuang
 @author Eric Palyan
 @version 2014.02.27
 */
public class Deck{
    
    private ArrayList<Card> cards;
    /** No-arg constructor creates 52 cards then shuffles them
     */
    public Deck(){
	cards = new ArrayList<Card>();
	int [] values = new int [13];
	String [] suits = new String[4];
	for(int i=1; i<=13; i++){
	    values[i-1] = i;
	}
	suits[0] = "Spades";
	suits[1] = "Hearts";
	suits[2] = "Diamonds";
	suits[3] = "Clubs";
	for(int i=0; i<13; i++){
	    for(int j=0; j<4; j++){
		cards.add(new Card(values[i], suits[j]));
	    }
	}
	shuffle();
    }
    /** helper function to shuffle the cards
     */
    private void shuffle(){
	ArrayList<Card> tempCards = new ArrayList<Card>();
	int rand;
	while(cards.size() > 0){
	    rand = (int)(Math.random() * cards.size());
	    tempCards.add(cards.get(rand));
	    cards.remove(rand);
	}
	cards = tempCards;
    }
    /** removes the top card and returns a reference to it
     */
    public Card draw(){
	Card temp = cards.get(0);
	cards.remove(0);
	return temp;
    }
    
    
    /** get size of the deck, for testing purposes
     */
    public int getDeckSize(){
	return cards.size();
    }
}//end Deck
