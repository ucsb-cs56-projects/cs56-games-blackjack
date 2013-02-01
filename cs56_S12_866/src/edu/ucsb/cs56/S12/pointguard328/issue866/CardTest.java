package edu.ucsb.cs56.S12.pointguard328.issue866;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

/** test class for Card

@author Garrett Johnston
@version 2012.05.16
@see Card

*/

public class CardTest{
        
	/** Test case for Card.getValue()
	    @see Card
	*/
    
    @Test
	public void test_GetValue1() {
	Card c = new Card(12, "Diamonds");
	assertEquals(10,c.getValue());
    }

	/** Test case for Card.getValue()
	    @see Card
	*/
    
    @Test
	public void test_GetValue3() {
	Card c = new Card(13, "Clubs");
	assertEquals(10,c.getValue());
    }

	/** Test case for Card.getValue()
	    @see Card
	*/
    
    @Test
	public void test_GetValue2() {
	Card c = new Card(4, "Spades");
	assertEquals(4,c.getValue());
    }

    	/** Test case for Card.toString()
	    @see Card
	*/
    
    @Test
	public void test_ToString1() {
	Card c = new Card(9, "Clubs");
	assertEquals("9 of Clubs",c.toString());
    }

	/** Test case for Card.toString()
	    @see Card
	*/
    
    @Test
	public void test_ToString2() {
	Card c = new Card(1, "Hearts");
	assertEquals("Ace of Hearts",c.toString());
    }
	/** Test case for Card.toString()
	    @see Card
	*/
    
    @Test
	public void test_ToString3() {
	Card c = new Card(11, "Hearts");
	assertEquals("Jack of Hearts",c.toString());
    }

	/** Test case for Card.toString()
	    @see Card
	*/
    
    @Test
	public void test_ToString4() {
	Card c = new Card(12, "Clubs");
	assertEquals("Queen of Clubs",c.toString());
    }

    /** Test case for Card.isAnAce()
	    @see Card
	*/
    
    @Test
	public void test_IsAnAce1() {
	Card c = new Card(11, "Hearts");
	assertEquals(false,c.isAnAce());
    }
	/** Test case for Card.isAnAce()
	    @see Card
	*/
    
    @Test
	public void test_IsAnAce2() {
	Card c = new Card(1, "Diamonds");
	assertEquals(true,c.isAnAce());
    }


}
