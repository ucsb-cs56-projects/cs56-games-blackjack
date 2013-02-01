package edu.ucsb.cs56.S12.pointguard328.issue866;
import java.util.*;

/**Blackjack Class contains a deck, a list of players, a dealer "Player", the next Card being 
 * turned up,  as well as methods for hitting and staying
 */
public class Blackjack{
	private Deck d;
	private ArrayList<Player> players;
	private Player dealer;
	private Card displayCard;
	
	/** constructor
	 */
	public Blackjack(){
		d = new Deck();
		dealer = new Player();
		dealer.drawCard(d.draw());
		dealer.drawCard(d.draw());
		dealer.setName("Dealer");
	
		players = new ArrayList<Player>();
		for(int i=0; i < 3; i++){
			Player newPlayer = new Player();
			newPlayer.drawCard(d.draw());
			newPlayer.drawCard(d.draw());
			players.add(newPlayer);
		}

		displayCard = null;
	}

	/** 4 arg constructor for testing purposes
	@param dh Dealer's hand
	@param p1 Player 1's hand
	@param p2 Player 2's hand
	@param p3 Player 3's hand
	 */
	public Blackjack(Hand dh, Hand p1, Hand p2, Hand p3){
		d = new Deck();
		dealer = new Player();
		dealer.setHand(dh);
		dealer.setName("Dealer");
	
		players = new ArrayList<Player>();
		for(int i=0; i < 3;){
			Player newPlayer = new Player();
			newPlayer.setName("Player " + ++i);
			players.add(newPlayer);
		}
		players.get(0).setHand(p1);
		players.get(1).setHand(p2);
		players.get(2).setHand(p3);

		displayCard = null;
	}
	/** initializes a new round by dealing new hands to every player
	 */
	public void newRound(){
		d = new Deck();
		players.get(0).newHand(d);
		players.get(1).newHand(d);
		players.get(2).newHand(d);
		dealer.newHand(d);
	}

	/** returns false if the dealer went bust
	 */
	public boolean dealerNotBust(){
		return dealer.isNotBust();
	}
	
	/** returns reference to dealer Player
	 */
	public Player getDealer(){
		return dealer;
	}
	
	/** returns reference to Player within players ArrayList according to index
	@param index integer of player (i.e. 1, 2, or 3)
	 */
	public Player getPlayer(int index){
		return players.get(index-1);
	}
	
	/** returns reference to Player in South
	 */
	public Player getPlayerS(){
		if(players.size() > 0)
			return players.get(0);
		return null;
	}
	
	/** returns reference to Player in East
	 */
	public Player getPlayerE(){
		if(players.size() > 1)
			return players.get(1);
		return null;
	}
	
	/** returns reference to Player in West
	 */
	public Player getPlayerW(){
		if(players.size() > 2)
			return players.get(2);
		return null;
	}

	/** returns the value of the dealer's hand with aces valued at 1
	 */
	public int dealerHandValue(){
		return dealer.getHandValue();
	}

	/** returns the value of the dealer's hand with aces valued at 11 or returns -1 if the value 
	 *  of the dealer's hand with aces valued at 11 makes the value greater than 21
	 */
	public int dealerSecondValue(){
		return dealer.getSecondHandValue();
	}

	/** returns a reference to the Card that the dealer is showing
	 */
	public Card getDealerCard(){
		return dealer.getHand().getFirstCard();
	}

	/** makes the dealer hit and returns a reference to the card he drew so it can be displayed
	 */
	public Card dealerHit(){
		displayCard = d.draw();
		dealer.drawCard(displayCard);
		return displayCard;
	}

	/** returns a reference to the dealer's hand
	 */
	public Hand getDealerHand(){
		return dealer.getHand();
	}

	/** makes the player passed as a parameter hit and returns a reference to the card drawn so 		it can be displayed
	@param player 
	 */
	public Card playerHit(Player player){
		displayCard = d.draw();
		player.drawCard(displayCard);
		return displayCard;
	}

	/** decides whether or not the dealer should stay i.e. dealer has 17 or 
	higher
	 */
	public boolean dealerShouldStay(){
		if(dealerHandValue() >= 17 || dealerSecondValue() >= 17)
			return true;
		return false;	
	}

	/** returns true if dealer has blackjack
	 */
	public boolean dealerHasBlackjack(){
		return dealer.hasBlackjack();
	}

	/** returns true if player passed to method has blackjack
	@param player to check for blackjack
	 */
	public boolean playerHasBlackjack(Player player){
		return player.hasBlackjack();
	}

	/** formats a String to return the value of just the dealer's first card during the player's turn
	 */
	public String displayDealerCardValue(){
		return "Dealer's hand value: " + dealer.getHand().getFirstCard().getValue();
	}

	/** evaluates the winner of the game
	@param player to compare if won against dealer
	 */
	public char evaluateWinner(Player player){
		if(!player.isNotBust())
			return 'D';
		else if(!dealerNotBust())
			return 'P';
		else if(dealer.hasBlackjack())
			return 'D';
		else if(player.hasBlackjack())
			return 'P';
		else if(dealer.getSecondHandValue() >= 
				player.getSecondHandValue() && 
				dealer.getSecondHandValue() >= 
				player.getHandValue())
			return 'D';
		else if(dealer.getHandValue() >= player.getSecondHandValue() && dealer.getHandValue() >= player.getHandValue())
			return 'D';
		else
			return 'P';
	}
}//class blackjack
