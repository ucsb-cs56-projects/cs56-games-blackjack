package edu.ucsb.cs56.S13.Blackjack;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

/** test class for Hand

@author Thomas Pilkington
@version 2012.02.28
@see Hand

*/

public class HandTest{
        
	/** Test case for Hand.hasBlackjack()
	    @see Hand
	*/
    
    @Test
	public void test_HasBlackjack() {
	Card c1 = new Card(12, "Diamonds");
	Card c2 = new Card(1, "Clubs");
	Hand h = new Hand(c1, c2);
	assertEquals(true,h.hasBlackjack());
    }

    /** Test case for Hand.hasBlackjack()
            @see Hand
    */

    @Test
        public void test_HasBlackjack2() {
        Card c1 = new Card(12, "Diamonds");
        Card c2 = new Card(1, "Clubs");
        Hand h = new Hand(c2, c1);
        assertEquals(true,h.hasBlackjack());
    }


    /** Test case for Hand.hasBlackjack()
            @see Hand
    */

    @Test
        public void test_HasBlackjack3() {
        Card c1 = new Card(9, "Diamonds");
        Card c2 = new Card(1, "Clubs");
        Hand h = new Hand(c1, c2);
        assertEquals(false,h.hasBlackjack());
    }

    /** Test case for Hand.hasBlackjack()
            @see Hand
    */

    @Test
        public void test_HasBlackjack4() {
        Card c1 = new Card(12, "Diamonds");
        Card c2 = new Card(4, "Clubs");
	Card c3 = new Card(7, "Clubs");
        Hand h = new Hand(c1, c2);
	h.addCard(c3);
        assertEquals(false,h.hasBlackjack());
    }


	/** Test case for Hand.getHandValue()
	    @see Hand
	*/
    
    @Test
	public void test_GetHandValue() {
	Card c1 = new Card(13, "Spades");
        Card c2 = new Card(4, "Clubs");
	Hand h = new Hand(c1, c2);
	assertEquals(14,h.getHandValue());
    }

    /** Test case for Hand.getHandValue()
            @see Hand
    */

    @Test
        public void test_GetHandValue2() {
        Card c1 = new Card(13, "Spades");
        Card c2 = new Card(4, "Clubs");
        Hand h = new Hand();
	h.addCard(c1);
	h.addCard(c2);
        assertEquals(14,h.getHandValue());
    }

    /** Test case for Hand.getHandValue()
            @see Hand
    */

    @Test
        public void test_GetHandValue3() {
        Card c1 = new Card(12, "Spades");
        Card c2 = new Card(11, "Clubs");
        Hand h = new Hand(c1, c2);
        assertEquals(20,h.getHandValue());
    }

    /** Test case for Hand.getHandValue()
            @see Hand
    */

    @Test
        public void test_GetHandValue4() {
        Card c1 = new Card(1, "Spades");
        Card c2 = new Card(1, "Clubs");
        Hand h = new Hand(c1, c2);
        assertEquals(2,h.getHandValue());
    }

    	/** Test case for Hand.toString()
	    @see Hand
	*/
    
    @Test
	public void test_ToString1() {
        Card c1 = new Card(1, "Spades");
        Card c2 = new Card(1, "Clubs");
        Hand h = new Hand(c1, c2);
	assertEquals("Ace of Spades, Ace of Clubs",h.toString());
    }

	/** Test case for Hand.toString()
	    @see Hand
	*/
    
    @Test
	public void test_ToString2() {
        Card c1 = new Card(7, "Spades");
        Card c2 = new Card(8, "Spades");
        Hand h = new Hand(c1, c2);
	assertEquals("7 of Spades, 8 of Spades",h.toString());
    }
	/** Test case for Hand.toString()
	    @see Hand
	*/
    
    @Test
	public void test_ToString3() {
        Card c1 = new Card(1, "Spades");
        Card c2 = new Card(1, "Clubs");
        Hand h = new Hand();
	h.addCard(c1);
	h.addCard(c2);
	assertEquals("Ace of Spades, Ace of Clubs",h.toString());
    }

	/** Test case for Hand.toString()
	    @see Hand
	*/
    
    @Test
	public void test_ToString4() {
	Card c1 = new Card(13, "Diamonds");
        Card c2 = new Card(12, "Hearts");
        Hand h = new Hand(c1, c2);
	assertEquals("King of Diamonds, Queen of Hearts",h.toString());
    }

    	/** Test case for Hand.getFirstCard()
	    @see Hand
	*/
    
    @Test
	public void test_GetFirstCard1() {
        Card c1 = new Card(1, "Spades");
        Card c2 = new Card(1, "Clubs");
        Hand h = new Hand(c1, c2);
	assertEquals("Ace of Spades",h.getFirstCard().toString());
    }
	/** Test case for Hand.getFirstCard()
	    @see Hand
	*/
    
    @Test
	public void test_GetFirstCard2() {
	Card c1 = new Card(10, "Clubs");
        Card c2 = new Card(11, "Hearts");
        Hand h = new Hand(c1, c2);
	assertEquals("10 of Clubs", h.getFirstCard().toString());
    }


    /** Test case for Hand.getSecondHandValue()
            @see Hand
    */

    @Test
        public void test_GetSecondHandValue() {
        Card c1 = new Card(1, "Spades");
        Card c2 = new Card(1, "Clubs");
        Hand h = new Hand(c1, c2);
        assertEquals(12,h.getSecondHandValue());
    }

    /** Test case for Hand.getSecondHandValue()
            @see Hand
    */

    @Test
        public void test_GetSecondHandValue2() {
        Card c1 = new Card(11, "Diamonds");
        Card c2 = new Card(1, "Hearts");
        Hand h = new Hand(c1, c2);
        assertEquals(21,h.getSecondHandValue());
    }
    /** Test case for Hand.getSecondHandValue()
            @see Hand
    */

    @Test
        public void test_GetSecondHandValue3() {
        Card c1 = new Card(7, "Spades");
        Card c2 = new Card(1, "Clubs");
        Hand h = new Hand(c1, c2);
        assertEquals(18,h.getSecondHandValue());
    }
    /** Test case for Hand.getSecondHandValue()
            @see Hand
    */

    @Test
        public void test_GetSecondHandValue4() {
        Card c1 = new Card(1, "Spades");
        Card c2 = new Card(2, "Clubs");
	Card c3 = new Card(3, "Diamonds");
        Hand h = new Hand(c1, c2);
	h.addCard(c3);
        assertEquals(16,h.getSecondHandValue());
    }
    /** Test case for Hand.getSecondHandValue()
            @see Hand
    */

    @Test
        public void test_GetSecondHandValue5() {
        Card c1 = new Card(8, "Spades");
        Card c2 = new Card(7, "Clubs");
        Hand h = new Hand(c1, c2);
        assertEquals(-1,h.getSecondHandValue());
    }

    /** Test case for Hand.getSecondHandValue()
            @see Hand
    */

    @Test
        public void test_GetSecondHandValue6() {
        Card c1 = new Card(5, "Spades");
        Card c2 = new Card(2, "Clubs");
        Card c3 = new Card(3, "Diamonds");
        Hand h = new Hand(c1, c2);
        h.addCard(c3);
        assertEquals(-1,h.getSecondHandValue());
    }
    /** Test case for Hand.getSecondHandValue()
            @see Hand
    */

    @Test
        public void test_GetSecondHandValue7() {
        Card c1 = new Card(10, "Spades");
        Card c2 = new Card(8, "Clubs");
        Card c3 = new Card(1, "Diamonds");
        Hand h = new Hand(c1, c2);
        h.addCard(c3);
        assertEquals(-1,h.getSecondHandValue());
    }

}//end HandTest
