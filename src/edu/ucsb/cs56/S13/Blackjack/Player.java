package edu.ucsb.cs56.S13.Blackjack;
/** Player Class has the following: Hand, String name, and a boolean for busting.
    @author Brian Wan
    @author Fanny Kuang
    @version 2013.05.15
*/

public class Player{
    
    private Hand playerHand;
    private String name;
    private int money;
    private int numberOfCards;
    private boolean notBust = true;
    
    /** No-arg constructor creates new Player with a new Hand
     */
    public Player() {
	playerHand = new Hand();
	numberOfCards = 2;
	money = 5000;
	name = "";
    }
    
    /** Setter to set each person's name
     *  @param name name of Player to set name
     */
    public void setName(String name){
	this.name = name;
    }

    /** Setter to set player's number of cards in hand
     * @param num number of cards to add/deduct from player's hand
     */
    public void setNumOfCards(int num) {
	numberOfCards += num;
    }

    /** Setter to set player's balance (amount of money)
     * @param amount amount (negative/positive) to add/subtract from Player's money
     */
    public void setMoney(int amount) {
	money += amount;
    }

    /** Resets player hand so a new round of blackjack can begin
     * @param d Deck to play with
     */
    public void newHand(Deck d){
	playerHand = new Hand();
	this.drawCard(d.draw());
	this.drawCard(d.draw());
	notBust = true;
    }
    
    /** draws a Card for the player and checks if the total exceeds 21
     * @param c Card to draw
     */
    public void drawCard(Card c){
	playerHand.addCard(c);
	if(playerHand.getHandValue() > 21)
	    notBust = false;
    }
    
    /** returns players Hand object
     */
    public Hand getHand(){
	return this.playerHand;
    }
    
    /** Getter to get each person's name
     */
    public String getName(){
	return this.name;
    }

    /** Getter to get player's money on hand
     */
    public String getMoney(){
        return this.money;
    }
    
    /** formats a string that displays the value(s) of the player's hand
     */
    public String displayHandValue(){
	return name + "'s hand value: " + playerHand.displayHandValue();
    }
    
    /** returns a player's hand value with Aces as 1
     */
    public int getHandValue(){
	return playerHand.getHandValue();
    }
    
    /** returns a a players hand value with Aces as 11 or returns -1 if doing so makes value > 21
     */
    public int getSecondHandValue(){
	return playerHand.getSecondHandValue();
    }
    
    
    /** sets players hand to defined Hand
	@param h Hand to set
    */
    public void setHand(Hand h){
	playerHand = h;
	if(h.getHandValue() > 21)
	    notBust = false;
    }
    /** toString, returns ""
     */
    public String toString(){
	return "";
    }
    
    /** returns false if player went bust
     */
    public boolean isNotBust(){
	return this.notBust;
    }
    /** returns if player has blackjack
     */
    public boolean hasBlackjack(){
	return playerHand.hasBlackjack();
    }
    
    
}//end Player
