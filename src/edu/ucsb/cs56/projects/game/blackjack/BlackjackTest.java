package edu.ucsb.cs56.projects.game.blackjack;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import javax.swing.*;
import java.awt.*;

/** test class for Blackjack

@author Garrett Johnston
@author Brian Wan
@author Fanny Kuang
@version 2013.05.15
@see Blackjack

*/

public class BlackjackTest{

	/** Test case for Blackjack.getPlayer(i).isNotBust()
	    @see Blackjack
	*/
    @Test
		public void test_PlayerNotBust1() {
		Card c1 = new Card(1, "Spades");
		Card c2 = new Card(10, "Clubs");
		Card c3 = new Card(13, "Clubs");
		Card c4 = new Card(5, "Diamonds");
		Card c5 = new Card(10, "Spades");
		Card c6 = new Card(9, "Hearts");
		Card c7 = new Card(2, "Spades");
		Card c8 = new Card(6, "Diamonds");
		Hand dh = new Hand(c1, c2);
		Hand h1 = new Hand(c3, c4);
		Hand h2 = new Hand(c5, c6);
		Hand h3 = new Hand(c7, c8);
		Blackjack b = new Blackjack(dh, h1, h2, h3);
		assertEquals(true,b.getPlayer(1).isNotBust());
    }

    /** Test case for Blackjack.getPlayer(i).isNotBust()
	    @see Blackjack
	*/
    @Test
		public void test_PlayerNotBust2() {
		Card c1 = new Card(1, "Spades");
		Card c2 = new Card(10, "Clubs");
		Card c3 = new Card(13, "Clubs");
		Card c4 = new Card(5, "Diamonds");
		Card c5 = new Card(10, "Spades");
		Card c6 = new Card(9, "Hearts");
		Card c7 = new Card(2, "Spades");
		Card c8 = new Card(6, "Diamonds");
		Hand dh = new Hand(c1, c2);
		Hand h1 = new Hand(c3, c4);
		Hand h2 = new Hand(c5, c6);
		Hand h3 = new Hand(c7, c8);
		Blackjack b = new Blackjack(dh, h1, h2, h3);
		Card newCard = new Card(3, "Hearts");
		b.getPlayer(2).drawCard(newCard);
		assertEquals(false,b.getPlayer(2).isNotBust());
    }

    /** Test case for Blackjack.getPlayer(i).isNotBust()
	    @see Blackjack
	*/
    @Test
		public void test_PlayerNotBust3() {
		Card c1 = new Card(1, "Spades");
		Card c2 = new Card(10, "Clubs");
		Card c3 = new Card(13, "Clubs");
		Card c4 = new Card(5, "Diamonds");
		Card c5 = new Card(10, "Spades");
		Card c6 = new Card(9, "Hearts");
		Card c7 = new Card(1, "Hearts");
		Card c8 = new Card(12, "Diamonds");
		Hand dh = new Hand(c1, c2);
		Hand h1 = new Hand(c3, c4);
		Hand h2 = new Hand(c5, c6);
		Hand h3 = new Hand(c7, c8);
		Blackjack b = new Blackjack(dh, h1, h2, h3);
		assertEquals(true,b.getPlayer(3).isNotBust());
    }


    /** Test case for Blackjack.dealerNotBust()
	    @see Blackjack
	*/
    @Test
		public void test_dealerNotBust1() {
		Card c1 = new Card(1, "Spades");
		Card c2 = new Card(10, "Clubs");
		Card c3 = new Card(13, "Clubs");
		Card c4 = new Card(5, "Diamonds");
		Card c5 = new Card(10, "Spades");
		Card c6 = new Card(9, "Hearts");
		Card c7 = new Card(1, "Hearts");
		Card c8 = new Card(12, "Diamonds");
		Hand dh = new Hand(c1, c2);
		Hand h1 = new Hand(c3, c4);
		Hand h2 = new Hand(c5, c6);
		Hand h3 = new Hand(c7, c8);
		Blackjack b = new Blackjack(dh, h1, h2, h3);
		assertEquals(true,b.dealerNotBust());
    }

    /** Test case for Blackjack.dealerNotBust()
	    @see Blackjack
	*/
    @Test
		public void test_dealerNotBust2() {
		Card c1 = new Card(1, "Spades");
		Card c2 = new Card(10, "Clubs");
		Card c3 = new Card(13, "Clubs");
		Card c4 = new Card(5, "Diamonds");
		Card c5 = new Card(10, "Spades");
		Card c6 = new Card(9, "Hearts");
		Card c7 = new Card(1, "Hearts");
		Card c8 = new Card(12, "Diamonds");
		Hand dh = new Hand(c1, c2);
		Hand h1 = new Hand(c3, c4);
		Hand h2 = new Hand(c5, c6);
		Hand h3 = new Hand(c7, c8);
		Blackjack b = new Blackjack(dh, h1, h2, h3);
		Card newCard = new Card(11, "Clubs");
		b.getDealer().drawCard(newCard);
		assertEquals(true,b.dealerNotBust());
    }


	/** Test case for Blackjack.dealerHandValue()
            @see Blackjack
    */
    @Test
		public void test_DealerHandValue1() {
		Card c1 = new Card(5, "Spades");
		Card c2 = new Card(10, "Clubs");
		Card c3 = new Card(13, "Clubs");
		Card c4 = new Card(5, "Diamonds");
		Card c5 = new Card(10, "Spades");
		Card c6 = new Card(9, "Hearts");
		Card c7 = new Card(1, "Hearts");
		Card c8 = new Card(12, "Diamonds");
		Hand dh = new Hand(c1, c2);
		Hand h1 = new Hand(c3, c4);
		Hand h2 = new Hand(c5, c6);
		Hand h3 = new Hand(c7, c8);
		Blackjack b = new Blackjack(dh, h1, h2, h3);
		assertEquals(15,b.dealerHandValue());
    }

    /** Test case for Blackjack.dealerHandValue()
            @see Blackjack
    */
    @Test
		public void test_DealerHandValue2() {
		Card c1 = new Card(5, "Spades");
		Card c2 = new Card(6, "Clubs");
		Card c3 = new Card(13, "Clubs");
		Card c4 = new Card(5, "Diamonds");
		Card c5 = new Card(10, "Spades");
		Card c6 = new Card(9, "Hearts");
		Card c7 = new Card(1, "Hearts");
		Card c8 = new Card(12, "Diamonds");
		Hand dh = new Hand(c1, c2);
		Hand h1 = new Hand(c3, c4);
		Hand h2 = new Hand(c5, c6);
		Hand h3 = new Hand(c7, c8);
		Blackjack b = new Blackjack(dh, h1, h2, h3);
		assertEquals(11,b.dealerHandValue());
    }

	/** Test case for Blackjack.dealerSecondValue()
            @see Blackjack
    */
    @Test
		public void test_DealerSecondValue1() {
		Card c1 = new Card(5, "Spades");
		Card c2 = new Card(10, "Clubs");
		Card c3 = new Card(13, "Clubs");
		Card c4 = new Card(5, "Diamonds");
		Card c5 = new Card(10, "Spades");
		Card c6 = new Card(9, "Hearts");
		Card c7 = new Card(1, "Hearts");
		Card c8 = new Card(12, "Diamonds");
		Hand dh = new Hand(c1, c2);
		Hand h1 = new Hand(c3, c4);
		Hand h2 = new Hand(c5, c6);
		Hand h3 = new Hand(c7, c8);
		Blackjack b = new Blackjack(dh, h1, h2, h3);
		assertEquals(-1,b.dealerSecondValue());
    }

    /** Test case for Blackjack.dealerSecondValue()
            @see Blackjack
    */
    @Test
		public void test_DealerSecondValue2() {
		Card c1 = new Card(1, "Spades");
		Card c2 = new Card(10, "Clubs");
		Card c3 = new Card(13, "Clubs");
		Card c4 = new Card(5, "Diamonds");
		Card c5 = new Card(10, "Spades");
		Card c6 = new Card(9, "Hearts");
		Card c7 = new Card(1, "Hearts");
		Card c8 = new Card(12, "Diamonds");
		Hand dh = new Hand(c1, c2);
		Hand h1 = new Hand(c3, c4);
		Hand h2 = new Hand(c5, c6);
		Hand h3 = new Hand(c7, c8);
		Blackjack b = new Blackjack(dh, h1, h2, h3);
		assertEquals(21,b.dealerSecondValue());
    }

    /** Test case for Blackjack.dealerSecondValue()
            @see Blackjack
    */
    @Test
		public void test_DealerSecondValue3() {
		Card c1 = new Card(5, "Spades");
		Card c2 = new Card(10, "Clubs");
		Card c3 = new Card(13, "Clubs");
		Card c4 = new Card(5, "Diamonds");
		Card c5 = new Card(10, "Spades");
		Card c6 = new Card(9, "Hearts");
		Card c7 = new Card(1, "Hearts");
		Card c8 = new Card(12, "Diamonds");
		Hand dh = new Hand(c1, c2);
		Hand h1 = new Hand(c3, c4);
		Hand h2 = new Hand(c5, c6);
		Hand h3 = new Hand(c7, c8);
		Blackjack b = new Blackjack(dh, h1, h2, h3);
		b.getDealer().drawCard(new Card(7, "Spades"));
		assertEquals(-1,b.dealerSecondValue());
    }


    /** Test case for Blackjack.evaluateWinner()
            @see Blackjack
    */
    @Test
		public void test_EvaluateWinner1() {
		Card c1 = new Card(5, "Spades");
		Card c2 = new Card(10, "Clubs");
		Card c3 = new Card(9, "Clubs");
		Card c4 = new Card(5, "Diamonds");
		Card c5 = new Card(10, "Spades");
		Card c6 = new Card(9, "Hearts");
		Card c7 = new Card(1, "Hearts");
		Card c8 = new Card(12, "Diamonds");
		Hand dh = new Hand(c1, c2);
		Hand h1 = new Hand(c3, c4);
		Hand h2 = new Hand(c5, c6);
		Hand h3 = new Hand(c7, c8);
		Blackjack b = new Blackjack(dh, h1, h2, h3);
		assertEquals(false,b.evaluateWinner(b.getPlayer(1)));
    }

    /** Test case for Blackjack.evaluateWinner()
            @see Blackjack
    */
    @Test
		public void test_EvaluateWinner2() {
		Card c1 = new Card(5, "Spades");
		Card c2 = new Card(10, "Clubs");
		Card c3 = new Card(9, "Clubs");
		Card c4 = new Card(5, "Diamonds");
		Card c5 = new Card(10, "Spades");
		Card c6 = new Card(9, "Hearts");
		Card c7 = new Card(1, "Hearts");
		Card c8 = new Card(12, "Diamonds");
		Hand dh = new Hand(c1, c2);
		Hand h1 = new Hand(c3, c4);
		Hand h2 = new Hand(c5, c6);
		Hand h3 = new Hand(c7, c8);
		Blackjack b = new Blackjack(dh, h1, h2, h3);
		assertEquals(true,b.evaluateWinner(b.getPlayer(2)));
    }


	/** Test case for Blackjack.evaluateWinner()
            @see Blackjack
    */
    @Test
		public void test_EvaluateWinner3() {
		Card c1 = new Card(5, "Spades");
		Card c2 = new Card(10, "Clubs");
		Card c3 = new Card(9, "Clubs");
		Card c4 = new Card(5, "Diamonds");
		Card c5 = new Card(10, "Spades");
		Card c6 = new Card(9, "Hearts");
		Card c7 = new Card(1, "Hearts");
		Card c8 = new Card(12, "Diamonds");
		Hand dh = new Hand(c1, c2);
		Hand h1 = new Hand(c3, c4);
		Hand h2 = new Hand(c5, c6);
		Hand h3 = new Hand(c7, c8);
		Blackjack b = new Blackjack(dh, h1, h2, h3);
		b.getDealer().drawCard(new Card(11, "Spades"));
		assertEquals(true,b.evaluateWinner(b.getPlayer(3)));
    }


    /** Test case for Blackjack.dealerHasBlackjack()
            @see Blackjack
    */
    @Test
		public void test_DealerHasBlackjack1() {
		Card c1 = new Card(5, "Spades");
		Card c2 = new Card(10, "Clubs");
		Card c3 = new Card(9, "Clubs");
		Card c4 = new Card(5, "Diamonds");
		Card c5 = new Card(10, "Spades");
		Card c6 = new Card(9, "Hearts");
		Card c7 = new Card(1, "Hearts");
		Card c8 = new Card(12, "Diamonds");
		Hand dh = new Hand(c1, c2);
		Hand h1 = new Hand(c3, c4);
		Hand h2 = new Hand(c5, c6);
		Hand h3 = new Hand(c7, c8);
		Blackjack b = new Blackjack(dh, h1, h2, h3);
		b.getDealer().drawCard(new Card(6, "Hearts"));
		assertEquals(false,b.dealerHasBlackjack());
    }

    /** Test case for Blackjack.dealerHasBlackjack()
            @see Blackjack
    */
    @Test
		public void test_DealerHasBlackjack2() {
		Card c1 = new Card(1, "Spades");
		Card c2 = new Card(10, "Clubs");
		Card c3 = new Card(9, "Clubs");
		Card c4 = new Card(5, "Diamonds");
		Card c5 = new Card(10, "Spades");
		Card c6 = new Card(9, "Hearts");
		Card c7 = new Card(1, "Hearts");
		Card c8 = new Card(12, "Diamonds");
		Hand dh = new Hand(c1, c2);
		Hand h1 = new Hand(c3, c4);
		Hand h2 = new Hand(c5, c6);
		Hand h3 = new Hand(c7, c8);
		Blackjack b = new Blackjack(dh, h1, h2, h3);
		assertEquals(true,b.dealerHasBlackjack());
    }


	/** Test case for Blackjack.playerHasBlackjack(Player p)
            @see Blackjack
    */
    @Test
		public void test_PlayerHasBlackjack1() {
		Card c1 = new Card(1, "Spades");
		Card c2 = new Card(10, "Clubs");
		Card c3 = new Card(9, "Clubs");
		Card c4 = new Card(5, "Diamonds");
		Card c5 = new Card(10, "Spades");
		Card c6 = new Card(9, "Hearts");
		Card c7 = new Card(1, "Hearts");
		Card c8 = new Card(12, "Diamonds");
		Hand dh = new Hand(c1, c2);
		Hand h1 = new Hand(c3, c4);
		Hand h2 = new Hand(c5, c6);
		Hand h3 = new Hand(c7, c8);
		Blackjack b = new Blackjack(dh, h1, h2, h3);
		assertEquals(false,b.playerHasBlackjack(b.getPlayer(1)));
    }

    /** Test case for Blackjack.playerHasBlackjack(Player p)
            @see Blackjack
    */
    @Test
		public void test_PlayerHasBlackjack2() {
		Card c1 = new Card(1, "Spades");
		Card c2 = new Card(10, "Clubs");
		Card c3 = new Card(9, "Clubs");
		Card c4 = new Card(5, "Diamonds");
		Card c5 = new Card(10, "Spades");
		Card c6 = new Card(9, "Hearts");
		Card c7 = new Card(1, "Hearts");
		Card c8 = new Card(12, "Diamonds");
		Hand dh = new Hand(c1, c2);
		Hand h1 = new Hand(c3, c4);
		Hand h2 = new Hand(c5, c6);
		Hand h3 = new Hand(c7, c8);
		Blackjack b = new Blackjack(dh, h1, h2, h3);
		assertEquals(true,b.playerHasBlackjack(b.getPlayer(3)));
    }

    /** Test case for Blackjack.playerHasBlackjack(Player p)
            @see Blackjack
    */
    @Test
		public void test_PlayerHasBlackjack3() {
		Card c1 = new Card(1, "Spades");
		Card c2 = new Card(10, "Clubs");
		Card c3 = new Card(9, "Clubs");
		Card c4 = new Card(5, "Diamonds");
		Card c5 = new Card(10, "Spades");
		Card c6 = new Card(9, "Hearts");
		Card c7 = new Card(1, "Hearts");
		Card c8 = new Card(12, "Diamonds");
		Hand dh = new Hand(c1, c2);
		Hand h1 = new Hand(c3, c4);
		Hand h2 = new Hand(c5, c6);
		Hand h3 = new Hand(c7, c8);
		Blackjack b = new Blackjack(dh, h1, h2, h3);
		b.getPlayer(2).drawCard(new Card(2, "Hearts"));
		assertEquals(false,b.playerHasBlackjack(b.getPlayer(2)));
    }

    /** Test case for Blackjack.dealerShouldStay()
            @see Blackjack
    */
    @Test
		public void test_DealerShouldStay1() {
		Card c1 = new Card(2, "Spades");
		Card c2 = new Card(10, "Clubs");
		Card c3 = new Card(9, "Clubs");
		Card c4 = new Card(5, "Diamonds");
		Card c5 = new Card(10, "Spades");
		Card c6 = new Card(9, "Hearts");
		Card c7 = new Card(1, "Hearts");
		Card c8 = new Card(12, "Diamonds");
		Hand dh = new Hand(c1, c2);
		Hand h1 = new Hand(c3, c4);
		Hand h2 = new Hand(c5, c6);
		Hand h3 = new Hand(c7, c8);
		Blackjack b = new Blackjack(dh, h1, h2, h3);
		assertEquals(false,b.dealerShouldStay());
    }

    /** Test case for Blackjack.dealerShouldStay()
            @see Blackjack
    */
    @Test
		public void test_DealerShouldStay2() {
		Card c1 = new Card(7, "Spades");
		Card c2 = new Card(10, "Clubs");
		Card c3 = new Card(9, "Clubs");
		Card c4 = new Card(5, "Diamonds");
		Card c5 = new Card(10, "Spades");
		Card c6 = new Card(9, "Hearts");
		Card c7 = new Card(1, "Hearts");
		Card c8 = new Card(12, "Diamonds");
		Hand dh = new Hand(c1, c2);
		Hand h1 = new Hand(c3, c4);
		Hand h2 = new Hand(c5, c6);
		Hand h3 = new Hand(c7, c8);
		Blackjack b = new Blackjack(dh, h1, h2, h3);
		assertEquals(true,b.dealerShouldStay());
    }

    /** Test case for Blackjack.dealerShouldStay()
            @see Blackjack
    */
    @Test
		public void test_DealerShouldStay3() {
		Card c1 = new Card(1, "Spades");
		Card c2 = new Card(10, "Clubs");
		Card c3 = new Card(9, "Clubs");
		Card c4 = new Card(5, "Diamonds");
		Card c5 = new Card(10, "Spades");
		Card c6 = new Card(9, "Hearts");
		Card c7 = new Card(1, "Hearts");
		Card c8 = new Card(12, "Diamonds");
		Hand dh = new Hand(c1, c2);
		Hand h1 = new Hand(c3, c4);
		Hand h2 = new Hand(c5, c6);
		Hand h3 = new Hand(c7, c8);
		Blackjack b = new Blackjack(dh, h1, h2, h3);
		b.getDealer().drawCard(new Card(6, "Clubs"));
		assertEquals(true,b.dealerShouldStay());
    }


    /** Test case for Blackjack.getDealerCard()
            @see Blackjack
    */
    @Test
		public void test_GetDealerCard1() {
		Card c1 = new Card(13, "Spades");
		Card c2 = new Card(10, "Clubs");
		Card c3 = new Card(9, "Clubs");
		Card c4 = new Card(5, "Diamonds");
		Card c5 = new Card(10, "Spades");
		Card c6 = new Card(9, "Hearts");
		Card c7 = new Card(1, "Hearts");
		Card c8 = new Card(12, "Diamonds");
		Hand dh = new Hand(c1, c2);
		Hand h1 = new Hand(c3, c4);
		Hand h2 = new Hand(c5, c6);
		Hand h3 = new Hand(c7, c8);
		Blackjack b = new Blackjack(dh, h1, h2, h3);
		assertEquals(c1,b.getDealerCard());
    }

    /** Test case for Blackjack.getDealerCard()
            @see Blackjack
    */
    @Test
		public void test_GetDealerCard2() {
		Card c1 = new Card(1, "Spades");
		Card c2 = new Card(10, "Clubs");
		Card c3 = new Card(9, "Clubs");
		Card c4 = new Card(5, "Diamonds");
		Card c5 = new Card(10, "Spades");
		Card c6 = new Card(9, "Hearts");
		Card c7 = new Card(1, "Hearts");
		Card c8 = new Card(12, "Diamonds");
		Hand dh = new Hand(c1, c2);
		Hand h1 = new Hand(c3, c4);
		Hand h2 = new Hand(c5, c6);
		Hand h3 = new Hand(c7, c8);
		Blackjack b = new Blackjack(dh, h1, h2, h3);
		assertEquals(c1,b.getDealerCard());
    }

    /** Test case for Blackjack.displayDealerCardValue()
            @see Blackjack
    */
    @Test
		public void test_DisplayDealerCardValue() {
		Card c1 = new Card(3, "Spades");
		Card c2 = new Card(10, "Clubs");
		Card c3 = new Card(9, "Clubs");
		Card c4 = new Card(5, "Diamonds");
		Card c5 = new Card(10, "Spades");
		Card c6 = new Card(9, "Hearts");
		Card c7 = new Card(1, "Hearts");
		Card c8 = new Card(12, "Diamonds");
		Hand dh = new Hand(c1, c2);
		Hand h1 = new Hand(c3, c4);
		Hand h2 = new Hand(c5, c6);
		Hand h3 = new Hand(c7, c8);
		Blackjack b = new Blackjack(dh, h1, h2, h3);
		assertEquals("Dealer's hand value: 3",b.displayDealerCardValue());
    }

    /** Test case for BlackjackGui.getMyImage()
            @see BlackjackGui
            @author Ryan Kirkpatrick
            @version 11/28/17
    */
    @Test
    public void test_getMyImage() {

    }

}//end BlackjackTest
