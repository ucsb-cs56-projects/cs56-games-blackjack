package edu.ucsb.cs56.S13.Blackjack;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

/** test class for Deck

@author Garrett Johnston
@version 2012.05.16
@see Deck

*/

public class DeckTest{
        
    	/** Test case for Deck.getDeckSize()
	    @see Deck
	*/

    @Test
	public void test_Draw() {
	Deck d = new Deck();
	d.draw();
	assertEquals(51,d.getDeckSize());
    }

	/** Test case for Deck.getDeckSize()
	    @see Deck
	*/
    
    @Test
	public void test_Draw2() {
	Deck d = new Deck();
	d.draw();
	d.draw();
	d.draw();
	d.draw();
	d.draw();
	assertEquals(47,d.getDeckSize());
    }


}//end DeckTest
