package edu.ucsb.cs56.projects.game.blackjack;

import org.junit.Test;

//import jdk.internal.jline.internal.TestAccessible;
import jdk.jfr.Timestamp;

import static org.junit.Assert.assertEquals;

/** test class for Deck

@author Garrett Johnston
@author Brian Wan
@author Fanny Kuang
@version 2013.05.15
@see Deck

*/

public class DeckTest{
        
    /** Test cases for Deck.getDeckSize() 
	  @see Deck */

	@Test
	public void test_FullDeckCreated() {
		Deck d = new Deck();
		assertEquals(52, d.getDeckSize());
	}

    @Test
	public void test_DrawOneCard() {
		Deck d = new Deck();
		d.draw();
		assertEquals(51,d.getDeckSize());
    }

    @Test
	public void test_DrawAllCards() {
		Deck d = new Deck();
		for (int i = 1; i < 53; i++)
			d.draw();
		assertEquals(0,d.getDeckSize());
	}
}//end DeckTest
