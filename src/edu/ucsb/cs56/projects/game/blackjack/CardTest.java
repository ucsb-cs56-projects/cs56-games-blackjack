package edu.ucsb.cs56.projects.game.blackjack;

import org.junit.Test;
import static org.junit.Assert.*;

/** test class for Card

@author Garrett Johnston
@author Brian Wan
@author Fanny Kuang
@version 2013.05.15
@see Card

*/

public class CardTest{

	/** Test case for Card.getValue()
	    @see Card
	*/

    @Test
	public void test_getDiamondCardValues() {
		Card c1 = new Card(1, "Diamonds");
		Card c2 = new Card(2, "Diamonds");
		Card c3 = new Card(3, "Diamonds");
		Card c4 = new Card(4, "Diamonds");
		Card c5 = new Card(5, "Diamonds");
		Card c6 = new Card(6, "Diamonds");
		Card c7 = new Card(7, "Diamonds");
		Card c8 = new Card(8, "Diamonds");
		Card c9 = new Card(9, "Diamonds");
		Card c10 = new Card(10, "Diamonds");
		Card c11 = new Card(11, "Diamonds");
		Card c12 = new Card(12, "Diamonds");
		Card c13 = new Card(13, "Diamonds");
		assertEquals(1,c1.getValue());
		assertEquals(2,c2.getValue());
		assertEquals(3,c3.getValue());
		assertEquals(4,c4.getValue());
		assertEquals(5,c5.getValue());
		assertEquals(6,c6.getValue());
		assertEquals(7,c7.getValue());
		assertEquals(8,c8.getValue());
		assertEquals(9,c9.getValue());
		assertEquals(10,c10.getValue());
		assertEquals(10,c11.getValue());
		assertEquals(10,c12.getValue());
		assertEquals(10,c13.getValue());
	}

	@Test
	public void test_getSpadesCardValues() {
		Card c1 = new Card(1, "Spades");
		Card c2 = new Card(2, "Spades");
		Card c3 = new Card(3, "Spades");
		Card c4 = new Card(4, "Spades");
		Card c5 = new Card(5, "Spades");
		Card c6 = new Card(6, "Spades");
		Card c7 = new Card(7, "Spades");
		Card c8 = new Card(8, "Spades");
		Card c9 = new Card(9, "Spades");
		Card c10 = new Card(10, "Spades");
		Card c11 = new Card(11, "Spades");
		Card c12 = new Card(12, "Spades");
		Card c13 = new Card(13, "Spades");
		assertEquals(1,c1.getValue());
		assertEquals(2,c2.getValue());
		assertEquals(3,c3.getValue());
		assertEquals(4,c4.getValue());
		assertEquals(5,c5.getValue());
		assertEquals(6,c6.getValue());
		assertEquals(7,c7.getValue());
		assertEquals(8,c8.getValue());
		assertEquals(9,c9.getValue());
		assertEquals(10,c10.getValue());
		assertEquals(10,c11.getValue());
		assertEquals(10,c12.getValue());
		assertEquals(10,c13.getValue());
	}

	@Test
	public void test_getHeartsCardValues() {
		Card c1 = new Card(1, "Hearts");
		Card c2 = new Card(2, "Hearts");
		Card c3 = new Card(3, "Hearts");
		Card c4 = new Card(4, "Hearts");
		Card c5 = new Card(5, "Hearts");
		Card c6 = new Card(6, "Hearts");
		Card c7 = new Card(7, "Hearts");
		Card c8 = new Card(8, "Hearts");
		Card c9 = new Card(9, "Hearts");
		Card c10 = new Card(10, "Hearts");
		Card c11 = new Card(11, "Hearts");
		Card c12 = new Card(12, "Hearts");
		Card c13 = new Card(13, "Hearts");
		assertEquals(1,c1.getValue());
		assertEquals(2,c2.getValue());
		assertEquals(3,c3.getValue());
		assertEquals(4,c4.getValue());
		assertEquals(5,c5.getValue());
		assertEquals(6,c6.getValue());
		assertEquals(7,c7.getValue());
		assertEquals(8,c8.getValue());
		assertEquals(9,c9.getValue());
		assertEquals(10,c10.getValue());
		assertEquals(10,c11.getValue());
		assertEquals(10,c12.getValue());
		assertEquals(10,c13.getValue());
	}

	@Test
	public void test_getClubsCardValues() {
		Card c1 = new Card(1, "Clubs");
		Card c2 = new Card(2, "Clubs");
		Card c3 = new Card(3, "Clubs");
		Card c4 = new Card(4, "Clubs");
		Card c5 = new Card(5, "Clubs");
		Card c6 = new Card(6, "Clubs");
		Card c7 = new Card(7, "Clubs");
		Card c8 = new Card(8, "Clubs");
		Card c9 = new Card(9, "Clubs");
		Card c10 = new Card(10, "Clubs");
		Card c11 = new Card(11, "Clubs");
		Card c12 = new Card(12, "Clubs");
		Card c13 = new Card(13, "Clubs");
		assertEquals(1,c1.getValue());
		assertEquals(2,c2.getValue());
		assertEquals(3,c3.getValue());
		assertEquals(4,c4.getValue());
		assertEquals(5,c5.getValue());
		assertEquals(6,c6.getValue());
		assertEquals(7,c7.getValue());
		assertEquals(8,c8.getValue());
		assertEquals(9,c9.getValue());
		assertEquals(10,c10.getValue());
		assertEquals(10,c11.getValue());
		assertEquals(10,c12.getValue());
		assertEquals(10,c13.getValue());
	}

    /** Test case for Card.toString()
	    @see Card
	*/

	@Test
	public void test_DiamondsToString() {
		Card c1 = new Card(1, "Diamonds");
		Card c2 = new Card(2, "Diamonds");
		Card c3 = new Card(3, "Diamonds");
		Card c4 = new Card(4, "Diamonds");
		Card c5 = new Card(5, "Diamonds");
		Card c6 = new Card(6, "Diamonds");
		Card c7 = new Card(7, "Diamonds");
		Card c8 = new Card(8, "Diamonds");
		Card c9 = new Card(9, "Diamonds");
		Card c10 = new Card(10, "Diamonds");
		Card c11 = new Card(11, "Diamonds");
		Card c12 = new Card(12, "Diamonds");
		Card c13 = new Card(13, "Diamonds");
		assertEquals("Ace of Diamonds",c1.toString());
		assertEquals("2 of Diamonds",c2.toString());
		assertEquals("3 of Diamonds",c3.toString());
		assertEquals("4 of Diamonds",c4.toString());
		assertEquals("5 of Diamonds",c5.toString());
		assertEquals("6 of Diamonds",c6.toString());
		assertEquals("7 of Diamonds",c7.toString());
		assertEquals("8 of Diamonds",c8.toString());
		assertEquals("9 of Diamonds",c9.toString());
		assertEquals("10 of Diamonds",c10.toString());
		assertEquals("Jack of Diamonds",c11.toString());
		assertEquals("Queen of Diamonds",c12.toString());
		assertEquals("King of Diamonds",c13.toString());
    }

    @Test
	public void test_SpadesToString() {
		Card c1 = new Card(1, "Spades");
		Card c2 = new Card(2, "Spades");
		Card c3 = new Card(3, "Spades");
		Card c4 = new Card(4, "Spades");
		Card c5 = new Card(5, "Spades");
		Card c6 = new Card(6, "Spades");
		Card c7 = new Card(7, "Spades");
		Card c8 = new Card(8, "Spades");
		Card c9 = new Card(9, "Spades");
		Card c10 = new Card(10, "Spades");
		Card c11 = new Card(11, "Spades");
		Card c12 = new Card(12, "Spades");
		Card c13 = new Card(13, "Spades");
		assertEquals("Ace of Spades",c1.toString());
		assertEquals("2 of Spades",c2.toString());
		assertEquals("3 of Spades",c3.toString());
		assertEquals("4 of Spades",c4.toString());
		assertEquals("5 of Spades",c5.toString());
		assertEquals("6 of Spades",c6.toString());
		assertEquals("7 of Spades",c7.toString());
		assertEquals("8 of Spades",c8.toString());
		assertEquals("9 of Spades",c9.toString());
		assertEquals("10 of Spades",c10.toString());
		assertEquals("Jack of Spades",c11.toString());
		assertEquals("Queen of Spades",c12.toString());
		assertEquals("King of Spades",c13.toString());
	}
	
    @Test
	public void test_HeartsToString() {
		Card c1 = new Card(1, "Hearts");
		Card c2 = new Card(2, "Hearts");
		Card c3 = new Card(3, "Hearts");
		Card c4 = new Card(4, "Hearts");
		Card c5 = new Card(5, "Hearts");
		Card c6 = new Card(6, "Hearts");
		Card c7 = new Card(7, "Hearts");
		Card c8 = new Card(8, "Hearts");
		Card c9 = new Card(9, "Hearts");
		Card c10 = new Card(10, "Hearts");
		Card c11 = new Card(11, "Hearts");
		Card c12 = new Card(12, "Hearts");
		Card c13 = new Card(13, "Hearts");
		assertEquals("Ace of Hearts",c1.toString());
		assertEquals("2 of Hearts",c2.toString());
		assertEquals("3 of Hearts",c3.toString());
		assertEquals("4 of Hearts",c4.toString());
		assertEquals("5 of Hearts",c5.toString());
		assertEquals("6 of Hearts",c6.toString());
		assertEquals("7 of Hearts",c7.toString());
		assertEquals("8 of Hearts",c8.toString());
		assertEquals("9 of Hearts",c9.toString());
		assertEquals("10 of Hearts",c10.toString());
		assertEquals("Jack of Hearts",c11.toString());
		assertEquals("Queen of Hearts",c12.toString());
		assertEquals("King of Hearts",c13.toString());
    }

    @Test
	public void test_ClubsToString() {
		Card c1 = new Card(1, "Clubs");
		Card c2 = new Card(2, "Clubs");
		Card c3 = new Card(3, "Clubs");
		Card c4 = new Card(4, "Clubs");
		Card c5 = new Card(5, "Clubs");
		Card c6 = new Card(6, "Clubs");
		Card c7 = new Card(7, "Clubs");
		Card c8 = new Card(8, "Clubs");
		Card c9 = new Card(9, "Clubs");
		Card c10 = new Card(10, "Clubs");
		Card c11 = new Card(11, "Clubs");
		Card c12 = new Card(12, "Clubs");
		Card c13 = new Card(13, "Clubs");
		assertEquals("Ace of Clubs",c1.toString());
		assertEquals("2 of Clubs",c2.toString());
		assertEquals("3 of Clubs",c3.toString());
		assertEquals("4 of Clubs",c4.toString());
		assertEquals("5 of Clubs",c5.toString());
		assertEquals("6 of Clubs",c6.toString());
		assertEquals("7 of Clubs",c7.toString());
		assertEquals("8 of Clubs",c8.toString());
		assertEquals("9 of Clubs",c9.toString());
		assertEquals("10 of Clubs",c10.toString());
		assertEquals("Jack of Clubs",c11.toString());
		assertEquals("Queen of Clubs",c12.toString());
		assertEquals("King of Clubs",c13.toString());
    }

    /** Test case for Card.isAnAce()
	    @see Card
	*/

    @Test
	public void test_IsNotAnAce() {
		Card c1 = new Card(10, "Diamonds");
		Card c2 = new Card(11, "Spades");
		Card c3 = new Card(12, "Hearts");
		Card c4 = new Card(13, "Clubs");
		assertFalse(c1.isAnAce());
		assertFalse(c2.isAnAce());
		assertFalse(c3.isAnAce());
		assertFalse(c4.isAnAce());
    }

    @Test
	public void test_IsAnAce() {
		Card c1 = new Card(1, "Diamonds");
		Card c2 = new Card(1, "Spades");
		Card c3 = new Card(1, "Hearts");
		Card c4 = new Card(1, "Clubs");
		assertTrue(c1.isAnAce());
		assertTrue(c2.isAnAce());
		assertTrue(c3.isAnAce());
		assertTrue(c4.isAnAce());
    }

} //end CardTest
