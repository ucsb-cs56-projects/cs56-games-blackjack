package edu.ucsb.cs56.projects.game.blackjack;
/** Player Class has the following: Hand, String name, and a boolean for busting.
    @author Brian Wan
    @author Fanny Kuang
    @author Eric Palyan
    @version 2014.02.27
*/

public class Player{
    
    private Hand playerHand, playerHand2;
    private String name;
    private int money, moneyWon, moneyLost, wins, losses, DD;
    private int numberOfCards = 2; 
    private int numberOfCards2 = 2;
    private boolean hasSplit = false;
    private boolean notBust = true;
    private boolean notBust2 = true;
    
    /** No-arg constructor creates new Player with a new Hand
     */
    public Player() {
	playerHand = new Hand();
	playerHand2 = new Hand();
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
     * @param num int number of cards to add/deduct from player's hand
     */
    public void setNumberOfCards(int num) {
	numberOfCards += num;
    }

    /** Setter to set player's number of cards in hand2
     *  @param num int number of cards to add/deduct from player's hand2
     */
    public void setNumberOfCards2(int num) {
    	numberOfCards2 += num;
    }

    /** Reset number of cards the player has
     */
    public void resetNumberOfCards() {
	numberOfCards = 2;
    }

    /** Setter to set player's balance (amount of money)
     * @param amount int amount (negative/positive) to add/subtract from Player's money
     */
    public void setMoney(int amount) {
	money += amount;
    }

    /** Setter to reset player's balance
     *  @param amount int amount
     */
    public void resetMoney(int amount) {
    	if (amount==0) money = 5000;
    	else money = amount;
    }

    public void resetMoney2(int amount) {
    	money = amount;
    }


    /** Resets player hand so a new round of blackjack can begin
     * @param d Deck to play with
     */
    public void newHand(Deck d){
	playerHand = new Hand();
	playerHand2 = new Hand();
	this.drawCard(d.draw());
	this.drawCard(d.draw());
	notBust = true;
	hasSplit = false;
    }
    
    /** draws a Card for the player and checks if the total exceeds 21
     * @param c Card to draw
     */
    public void drawCard(Card c){
	playerHand.addCard(c);
	if(playerHand.getHandValue() > 21)
	    notBust = false;
    }

    /** draws a card for player, checks if total in hand2 exceeds 21
     *  @param c Card to draw
     */
    public void drawCard2(Card c){
	playerHand2.addCard(c);
	if(playerHand2.getHandValue() > 21)
	    notBust2 = false;
    }

    /** returns number of cards in player's hand
     *  @return int numberOfCards in hand1
     */
    public int getNumberOfCards() {
	return numberOfCards;
    }

    /** returns number of cards in player's hand2
     *  @return int numberOfCards in hand2
     */
    public int getNumberOfCards2() {
	return numberOfCards2;
    }

    /** returns players Hand object
     *  @return Hand
     */
    public Hand getHand(){
	return this.playerHand;
    }

    /** returns player's Hand2 object
     *  @return Hand
     */
    public Hand getHand2() {
    	return this.playerHand2;
    }
    
    /** Getter to get each person's name
     *  @return String
     */
    public String getName(){
	return this.name;
    }

    /** Getter to get player's money on hand
     *  @return int money
     */
    public int getMoney(){
        return this.money;
    }
    
    /** Getter to get player's money won
     *  @return int moneyWon
     */
    public int getMoneyWon(){
        return this.moneyWon;
    }
    
    /** Getter to get player's money lost
     *  @return int moneyLost
     */
    public int getMoneyLost(){
        return this.moneyLost;
    }
    
    /** Getter to get player's wins
     *  @return int win
     */
    public int getWins(){
        return this.wins;
    }
    
    /** Getter to get player's losses
     *  @return int losses
     */
    public int getLosses(){
        return this.losses;
    }

    /** Getter to return player's doubleDown
     *  @return int dd
     */
    public int getDD() {
    	return this.DD;
    }

    /** Getter to return if player has split
     *  @return boolean hassplit
     */
    public boolean getSplit() {
	return this.hasSplit; 
    }

    /** sets dd
     *  @param dd int
     */
    public void setDD(int dd) {
    	this.DD = dd;
    }

    /** sets wins
     *  @param wins int
     */
    public void setWins(int wins) {
    	this.wins = wins;
    }

    /** sets losses
     *  @param losses int
     */
    public void setLosses(int losses) {
    	this.losses = losses;
    }

    /** sets money won
     *  @param won int
     */
    public void setWon(int won) {
    	this.moneyWon = won;
    }

    /** sets money lost
     *  @param lost int
     */
    public void setLost(int lost) {
    	this.moneyLost = lost;
    }

    /** sets hasSplit to true
     */
    public void setHasSplitTrue(){
	this.hasSplit = true;
    }
	
    /** Adds money won to player's instance variable
     *  @param amount int
     */
    public void addMoneyWon(int amount) {
    	this.moneyWon += amount;
    }
    
    /** Adds money lost to player's instance variable
     *  @param amount int
     */
    public void addMoneyLost(int amount) {
    	this.moneyLost += amount;
    }
    
    /** Adds a win to player's instance variable
     */
    public void addWin() {
    	this.wins++;
    }
    
    /** Adds a loss to player's instance variable
     */
    public void addLoss() {
    	this.losses++;
    }
    
    /** formats a string that displays the value(s) of the player's hand
     *  @return String name's hand value
     */
    public String displayHandValue(){
	return name + "'s hand value: " + playerHand.displayHandValue();
    }
    
    /** returns a player's hand value with Aces as 1
     *  @return int hand value
     */
    public int getHandValue(){
	return playerHand.getHandValue();
    }
    
    /** returns a a players hand value with Aces as 11 or returns -1 if doing so makes value greater than 21
     *  @return int second hand value
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

    /** sets player's hand2 to defined hand
     *  @param h Hand
     */
    public void setHand2(Hand h){
	playerHand2 = h;
	if(h.getSecondHandValue() > 21)
	    notBust2 = false;
    }
    
    /** toString, returns ""
     *  @return String empty
     */
    public String toString(){
	return "";
    }
    
    /** returns false if player went bust. checks both hands if split.
     *  @return boolean if busted in both hands (for split)
     */
    public boolean isNotBust(){
	if(!hasSplit)
	    return this.notBust;
	return this.notBust && this.notBust2;
    }
    
    /** returns if player has blackjack. checks both hands if split.
     *  @return boolean if blackjack in either hand
     */
    public boolean hasBlackjack(){
	return playerHand.hasBlackjack() || playerHand2.hasBlackjack();
    }
    
    
}//end Player
