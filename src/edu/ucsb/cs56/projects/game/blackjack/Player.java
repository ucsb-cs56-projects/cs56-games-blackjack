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
    private boolean notBust = true;
    private boolean notBust2 = true;
    
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
    public void setNumberOfCards(int num) {
	numberOfCards += num;
    }
    
    public void setNumberOfCards2(int num) {
    	numberOfCards2 += num;
    }

    /** Reset number of cards the player has
     */
    public void resetNumberOfCards() {
	numberOfCards = 2;
    }

    /** Setter to set player's balance (amount of money)
     * @param amount amount (negative/positive) to add/subtract from Player's money
     */
    public void setMoney(int amount) {
	money += amount;
    }

    public void resetMoney(int amount) {
    	if (amount==0) money = 5000;
    	else money = amount;
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
    
    public void drawCard2(Card c){
	playerHand2.addCard(c);
	if(playerHand2.getHandValue() > 21)
	    notBust2 = false;
    }

    /** returns number of cards in player's hand
     */
    public int getNumberOfCards() {
	return numberOfCards;
    }

    /** returns players Hand object
     */
    public Hand getHand(){
	return this.playerHand;
    }
    
    public Hand getHand2() {
    	return this.playerHand2;
    }
    
    /** Getter to get each person's name
     */
    public String getName(){
	return this.name;
    }

    /** Getter to get player's money on hand
     */
    public int getMoney(){
        return this.money;
    }
    
    /** Getter to get player's money won
     */
    public int getMoneyWon(){
        return this.moneyWon;
    }
    
    /** Getter to get player's money lost
     */
    public int getMoneyLost(){
        return this.moneyLost;
    }
    
    /** Getter to get player's wins
     */
    public int getWins(){
        return this.wins;
    }
    
    /** Getter to get player's losses
     */
    public int getLosses(){
        return this.losses;
    }
    
    public int getDD() {
    	return this.DD;
    }
    
    public void setDD(int dd) {
    	this.DD = dd;
    }
    
    public void setWins(int wins) {
    	this.wins = wins;
    }
    
    public void setLosses(int losses) {
    	this.losses = losses;
    }
    
    public void setWon(int won) {
    	this.moneyWon = won;
    }
    
    public void setLost(int lost) {
    	this.moneyLost = lost;
    }
    
    /** Adds money won to player's instance variable
     */
    public void addMoneyWon(int amount) {
    	this.moneyWon += amount;
    }
    
    /** Adds money lost to player's instance variable
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
