package edu.ucsb.cs56.S13.Blackjack;
import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.net.*;
import java.util.ArrayList;


/**BlackjackGui Class contains all widgets for Blackjack game interface.
@author Brian Wan
@author Fanny Kuang
@version 2013.05.15
 */
public class BlackjackGui{
    JFrame frame;
    JFrame welcomeFrame;
    JFrame nameFrame;
    JPanel playerPanelS;
    JPanel playerPanelE;
    JPanel playerPanelW;
    JPanel cardPanelE;
    JPanel cardPanelW;
    JPanel dealerPanel;
    JPanel welcomePanel;
    JPanel displayPanel;
    JPanel centerPanel;
    JPanel textPanel;
    JLabel playerLabelS;
    JLabel playerLabelE;
    JLabel playerLabelW;
    JLabel dealerLabel;
    JLabel displayLabel;
    JLabel welcomeLabel;
    JTextField player1Name;
    JTextField player2Name;
    JTextField player3Name;
    String p1Name;
    String p2Name;
    String p3Name;
    JPanel namePanel;
    Blackjack game = new Blackjack();
    boolean dealerTurn;
    int playerTurn = 1;
    BlackjackGui theGui;
    Card displayCard;
    JLabel downCard;
    JButton playAgain;
    JButton beginGame;

    JFrame betFrame;
    JPanel betPanel;
    JLabel betLabel;
    JButton betButton;
    JButton betAmount1;
    JButton betAmount2;
    JButton betAmount3;
    JButton betAmount4;
    JLabel betAmount;
    int amountBet;

    JLabel playerLabelSM;
    JLabel playerLabelWM;
    JLabel playerLabelEM;

    ArrayList<String> names;
    public static int numPlayers;
    public static boolean keepRunning=false;
    int speed = 1000;
    Timer timer = new Timer(speed, new MyTimerListener());
    
    public static void main(String[] args){
	BlackjackGui gui = new BlackjackGui();
	gui.welcome();
	gui.go();
    }
    
    /** gets the winner and displays it in a label
     *  also makes the playAgain button visible
     */
    public void getWinner(){
	//sets true or false whether each player won or lost
	boolean p1IsWinner = game.evaluateWinner(game.getPlayerS()) == 'P';
	boolean p2IsWinner = game.evaluateWinner(game.getPlayerE()) == 'P';
	boolean p3IsWinner = game.evaluateWinner(game.getPlayerW()) == 'P';
	/* depending on the number of players, set the players' text to say if they won or
	   lost and what their ultimate hand value was
	*/
	switch(numPlayers){
	case 1:
	    String winOrLose1 = p1IsWinner ? " wins" : " loses";
	    playerLabelS.setText(p1Name + winOrLose1 + ", hand value = " + game.getPlayerS().getHand().displayBestValue());
	    if(!p1IsWinner)
		displayLabel.setText("Dealer wins");
	    break;
	case 2:
	    String winOrLose2 = p1IsWinner ? " wins" : " loses";
	    playerLabelS.setText(p1Name + winOrLose2+ ", hand value = " + game.getPlayerS().getHand().displayBestValue());
	    winOrLose2 = p2IsWinner ? " wins" : " loses";
	    playerLabelE.setText(p2Name + winOrLose2+ ", hand value = " + game.getPlayerE().getHand().displayBestValue());
	    if(!p1IsWinner && !p2IsWinner)
		displayLabel.setText("Dealer Wins");
	    break;
	case 3:
	    String winOrLose3 = p1IsWinner ? " wins" : " loses";
	    playerLabelS.setText(p1Name + winOrLose3+ ", hand value = " + game.getPlayerS().getHand().displayBestValue());
	    winOrLose3 = p2IsWinner ? " wins" : " loses";
	    playerLabelE.setText(p2Name + winOrLose3+ ", hand value = " + game.getPlayerE().getHand().displayBestValue());
	    winOrLose3 = p3IsWinner ? " wins" : " loses";
	    playerLabelW.setText(p3Name + winOrLose3+ ", hand value = " + game.getPlayerW().getHand().displayBestValue());
	    if(!p1IsWinner && !p2IsWinner && !p3IsWinner)
		displayLabel.setText("Dealer Wins");
	    break;
	default:
	    break;
	}
	playAgain = new JButton("Play again");
	playAgain.setMaximumSize(new Dimension(130, 75));
	playAgain.addActionListener(new PlayAgainListener());
	displayPanel.add(playAgain);
    }
    
    /** beings the delay timer and shows the dealer's card that was face down
     */
    public void startDealerTurn(){
	timer.setInitialDelay(speed);
	dealerTurn = true;
	dealerLabel.setText(game.getDealer().displayHandValue());
	dealerPanel.remove(downCard);
	dealerPanel.add(new JLabel(getMyImage(game.getDealer().getHand().getSecondCard())));
	timer.start();
    }

    /** deduct the bet amount from each players' hand at the start of each round
     */
    private void updateMoney() {
	switch(numPlayers) {
	case(1):
	    game.getPlayerS().setMoney(-amountBet);
	    break;
	case(2):
	    game.getPlayerS().setMoney(-amountBet);
	    game.getPlayerE().setMoney(-amountBet);
	    break;
	case(3):
	    game.getPlayerS().setMoney(-amountBet);
	    game.getPlayerE().setMoney(-amountBet);
	    game.getPlayerW().setMoney(-amountBet);
	    break;
	default:
	    break;
	}
    }
    
    /** [overloaded] add the total pot to the winner's total money
     * @param pot pot is the amount of money to be won
     */
    private void updateMoney(int pot) {
	switch(numPlayers) {
	case(1):
	    game.getPlaterS().setMoney(pot*2);
	    break;
	case(2):
	    game.getPlayerS().setMoney(pot*3);
	    game.getPlayerE().setMoney(pot*3);
	    break;
	case(3):
	    game.getPlayerS().setMoney(pot*4);
	    game.getPlayerE().setMoney(pot*4);
	    game.getPlayerW().setMoney(pot*4);
	    break;
	default:
	    break;
	}
    }
    
    /** initializes many of the widgets and sets up listeners to some of those widgets
     */
    public void go(){
	theGui=this;
	frame = new JFrame();
	dealerTurn = false;
	playerTurn = 1;

	updateMoney();
	
	dealerPanel = new JPanel(); dealerLabel = new JLabel();

	playerPanelS = new JPanel(); playerLabelS = new JLabel();
	playerLabelSM = new JLabel("Money: $" + game.getPlayerS().getMoney() + ", ");

	playerPanelE = new JPanel(); playerLabelE = new JLabel();
	playerLabelEM = new JLabel("Money: $" + game.getPlayerE().getMoney() + ", ");

	playerPanelW = new JPanel(); playerLabelW = new JLabel();
	playerLabelWM = new JLabel("Money: $" + game.getPlayerW().getMoney() + ", ");

	displayPanel = new JPanel(); displayLabel = new JLabel();
	cardPanelE = new JPanel(); cardPanelW = new JPanel();
	textPanel = new JPanel();
	centerPanel = new JPanel();
	displayLabel.setFont(new Font(displayLabel.getName(), Font.PLAIN, 20));
	
	dealerPanel.add(dealerLabel);
	dealerPanel.add(new JLabel(getMyImage(game.getDealer().getHand().getFirstCard())));
	URL myURL = getClass().getResource("/images/b1fv.gif");
	ImageIcon myImage =	new ImageIcon(myURL);
	downCard = new JLabel(myImage);
	dealerPanel.add(downCard);
	dealerLabel.setText(game.displayDealerCardValue());
	
	JButton hit = new JButton("hit");
	hit.setMaximumSize(new Dimension(75,75));
	hit.addActionListener(new HitListener());
	JButton stay = new JButton("stay");
	stay.setMaximumSize(new Dimension(75,75));
	stay.addActionListener(new StayListener());
	displayPanel.setLayout(new BoxLayout(displayPanel, BoxLayout.Y_AXIS));
	centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.X_AXIS));
	centerPanel.add(Box.createRigidArea(new Dimension(120, 0)));
	centerPanel.add(hit);
	centerPanel.add(stay);
	displayPanel.add(Box.createRigidArea(new Dimension(0, 60)));
	displayPanel.add(displayLabel);
	displayPanel.add(centerPanel);
	
	playerPanelS.add(playerLabelSM);
	playerPanelS.add(playerLabelS);
	playerPanelS.add(new JLabel(getMyImage(game.getPlayerS().getHand().getFirstCard())));
	playerPanelS.add(new JLabel(getMyImage(game.getPlayerS().getHand().getSecondCard())));
	
	playerPanelE.setLayout(new BoxLayout(playerPanelE, BoxLayout.Y_AXIS));
	playerPanelE.add(playerLabelEM);
	playerPanelE.add(playerLabelE);
	cardPanelE.add(new JLabel(getMyImage(game.getPlayerE().getHand().getFirstCard())));
	cardPanelE.add(new JLabel(getMyImage(game.getPlayerE().getHand().getSecondCard())));
	playerPanelE.add(cardPanelE);
		
	playerPanelW.setLayout(new BoxLayout(playerPanelW, BoxLayout.Y_AXIS));
	
	playerPanelW.add(playerLabelWM);
	playerPanelW.add(playerLabelW);
	cardPanelW.add(new JLabel(getMyImage(game.getPlayerW().getHand().getFirstCard())));
	cardPanelW.add(new JLabel(getMyImage(game.getPlayerW().getHand().getSecondCard())));
	playerPanelW.add(cardPanelW);
	
	frame.getContentPane().add(BorderLayout.NORTH, dealerPanel);
	frame.getContentPane().add(BorderLayout.CENTER, displayPanel);
	frame.getContentPane().add(BorderLayout.SOUTH, playerPanelS);
	frame.getContentPane().add(BorderLayout.EAST, playerPanelE);
	frame.getContentPane().add(BorderLayout.WEST, playerPanelW);
	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	frame.setSize(800,600);
	
	
	// This section is for a new round of Blackjack
	if(keepRunning == true){
	    if(numPlayers == 1){
		playerLabelS.setText(game.getPlayerS().displayHandValue());
		frame.remove(playerPanelE);
		frame.remove(playerPanelW);
		frame.setSize(600,600);
	    }
	    else if(numPlayers == 2){
		playerLabelS.setText(game.getPlayerS().displayHandValue());
		playerLabelE.setText(game.getPlayerE().displayHandValue());
		frame.remove(playerPanelW);
		frame.setSize(800,600);
	    }
	    else if(numPlayers == 3){
		playerLabelS.setText(game.getPlayerS().displayHandValue());
		playerLabelE.setText(game.getPlayerE().displayHandValue());
		playerLabelW.setText(game.getPlayerW().displayHandValue());
		frame.setSize(1000,600);
	    }
	    displayLabel.setText("New Round, " + p1Name + "'s turn");
	    frame.setVisible(true);
	}
    }
    
    /** Allows next player to take turn
     */
    public void nextPlayersTurn(){
	playerTurn++;
	displayLabel.setText(game.getPlayer(playerTurn).getName() + "'s turn");
    }
    
    
    /** initializes the welcome widgets
     */
    public void welcome(){
	welcomeFrame = new JFrame();
	welcomePanel = new JPanel();
	welcomeLabel = new JLabel();
	welcomePanel.setLayout(new BoxLayout(welcomePanel, BoxLayout.Y_AXIS));
	
	JButton onePlayer = new JButton("1 player");
	JButton twoPlayers = new JButton("2 players");
	JButton threePlayers = new JButton("3 players");
	onePlayer.addActionListener(new WelcomeListener1());
	twoPlayers.addActionListener(new WelcomeListener2());
	threePlayers.addActionListener(new WelcomeListener3());
	
	welcomeLabel.setText("Welcome to Blackjack");
	welcomePanel.add(welcomeLabel);
	welcomePanel.add(onePlayer);
	welcomePanel.add(twoPlayers);
	welcomePanel.add(threePlayers);
	welcomeFrame.add(welcomePanel);
	welcomeFrame.setSize(200,175);
	welcomeFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	welcomeFrame.setVisible(true);
    }
    
    
    /** returns the image corresponding to the Card passed in
	@param c Card to retrieve the image of
    */
    public ImageIcon getMyImage(Card c){
	String cardString ="";
	switch(c.getCardNumber()){
	case(1): cardString = "/images/c1.gif"; break;
	case(2): cardString = "/images/c2.gif"; break;
	case(3): cardString = "/images/c3.gif"; break;
	case(4): cardString = "/images/c4.gif"; break;
	case(5): cardString = "/images/c5.gif"; break;
	case(6): cardString = "/images/c6.gif"; break;
	case(7): cardString = "/images/c7.gif"; break;
	case(8): cardString = "/images/c8.gif"; break;
	case(9): cardString = "/images/c9.gif"; break;
	case(10): cardString = "/images/c10.gif"; break;
	case(11): cardString = "/images/cj.gif"; break;
	case(12): cardString = "/images/cq.gif"; break;
	case(13): cardString = "/images/ck.gif"; break;
	case(14): cardString = "/images/d1.gif"; break;
	case(15): cardString = "/images/d2.gif"; break;
	case(16): cardString = "/images/d3.gif"; break;
	case(17): cardString = "/images/d4.gif"; break;
	case(18): cardString = "/images/d5.gif"; break;
	case(19): cardString = "/images/d6.gif"; break;
	case(20): cardString = "/images/d7.gif"; break;
	case(21): cardString = "/images/d8.gif"; break;
	case(22): cardString = "/images/d9.gif"; break;
	case(23): cardString = "/images/d10.gif"; break;
	case(24): cardString = "/images/dj.gif"; break;
	case(25): cardString = "/images/dq.gif"; break;
	case(26): cardString = "/images/dk.gif"; break;
	case(27): cardString = "/images/h1.gif"; break;
	case(28): cardString = "/images/h2.gif"; break;
	case(29): cardString = "/images/h3.gif"; break;
	case(30): cardString = "/images/h4.gif"; break;
	case(31): cardString = "/images/h5.gif"; break;
	case(32): cardString = "/images/h6.gif"; break;
	case(33): cardString = "/images/h7.gif"; break;
	case(34): cardString = "/images/h8.gif"; break;
	case(35): cardString = "/images/h9.gif"; break;
	case(36): cardString = "/images/h10.gif"; break;
	case(37): cardString = "/images/hj.gif"; break;
	case(38): cardString = "/images/hq.gif"; break;
	case(39): cardString = "/images/hk.gif"; break;
	case(40): cardString = "/images/s1.gif"; break;
	case(41): cardString = "/images/s2.gif"; break;
	case(42): cardString = "/images/s3.gif"; break;
	case(43): cardString = "/images/s4.gif"; break;
	case(44): cardString = "/images/s5.gif"; break;
	case(45): cardString = "/images/s6.gif"; break;
	case(46): cardString = "/images/s7.gif"; break;
	case(47): cardString = "/images/s8.gif"; break;
	case(48): cardString = "/images/s9.gif"; break;
	case(49): cardString = "/images/s10.gif"; break;
	case(50): cardString = "/images/sj.gif"; break;
	case(51): cardString = "/images/sq.gif"; break;
	case(52): cardString = "/images/sk.gif"; break;
	}
	URL myurl = getClass().getResource(cardString);
	if(myurl != null){
	    ImageIcon myImage = new ImageIcon(myurl);
	    return myImage;
	}
	else
	    return new ImageIcon();
    }
    
    
    /** listener class for the hit button
     */
    public class HitListener implements ActionListener{
	/** does nothing if it is not the player's turn
	 *   otherwise makes the player hit and checks if the player went bust.
	 *   If player busts, it goes to next player's turn or the dealer's turn.
	 @param event ActionEvent, player's action
	*/
	public void actionPerformed(ActionEvent event){
	    if(!dealerTurn){
		//adds a new card to the players hand
		Card newCard = game.playerHit(game.getPlayer(playerTurn));
		//makes a string for whether or not the player busts,
		//to later append to their label
		String isBust = !game.getPlayer(playerTurn).isNotBust() ? " You went bust" : "";
		switch(playerTurn){
		case 1:
		    playerLabelS.setText(game.getPlayerS().displayHandValue() + isBust);
		    playerPanelS.add(new JLabel(getMyImage(newCard)));
		    break;
		case 2:
		    playerLabelE.setText(game.getPlayerE().displayHandValue()+ isBust);
		    cardPanelE.add(new JLabel(getMyImage(newCard)));
		    break;
		case 3:
		    playerLabelW.setText(game.getPlayerW().displayHandValue()+ isBust);
		    cardPanelW.add(new JLabel(getMyImage(newCard)));
		    break;
		default:
		    break;
		}
		//if player busts, either continue on to next player's turn or dealer's turn
		if(!game.getPlayer(playerTurn).isNotBust()){
		    if(playerTurn < numPlayers)
			theGui.nextPlayersTurn();
		    else
			theGui.startDealerTurn();
		}
	    }
	}
    }
    
    /** listener class for the stay button
     */
    public class StayListener implements ActionListener{
	
	/** does nothing if it is not the player's turn
	 *  otherwise makes the player stay and starts the dealer's turn
	 @param event ActionEvent, Player stays
	*/
	public void actionPerformed(ActionEvent event){
	    if(!dealerTurn){
		if(playerTurn < numPlayers){
		    playerTurn++;
		    displayLabel.setText(game.getPlayer(playerTurn).getName() + "'s turn");
		}
		else{
		    displayLabel.setText("Dealer's Turn");
		    theGui.startDealerTurn();
		}
	    }
	}
    }

    /** listender class for confirm name button 
     */
    public class ConfirmName implements ActionListener{
	/** initializes some of the JLabels for a set bet menu and brings up the main JFrame
	    @param event ActionEvent, set bet
	*/
	public void actionPerformed(ActionEvent event){
	    nameFrame.setVisible(false);

	    betFrame = new JFrame();
	    betPanel = new JPanel();
	    betLabel = new JLabel();
	    betPanel.setLayout(new BoxLayout(betPanel, BoxLayout.Y_AXIS));
      
	    betLabel.setText("How much would you like to bet?");
	    betLabel.setHorizontalAlignment(JLabel.CENTER);
	    betLabel.setVerticalAlignment(JLabel.CENTER);

	    betAmount = new JLabel("$25");
	    amountBet = 25;

	    betAmount1 = new JButton("$25");
	    betAmount2 = new JButton("$50");
	    betAmount3 = new JButton("$100");
	    betAmount4 = new JButton("$250");
	    betAmount1.addActionListener(new BetAmountListener1());
	    betAmount2.addActionListener(new BetAmountListener2());
	    betAmount3.addActionListener(new BetAmountListener3());
	    betAmount4.addActionListener(new BetAmountListener4());

            JButton betButton = new JButton("Confirm Bet");
            betButton.addActionListener(new BeginGameListener());

	    betPanel.add(betLabel);
	    betPanel.add(betAmount);
	    betPanel.add(betAmount1);
	    betPanel.add(betAmount2);
	    betPanel.add(betAmount3);
	    betPanel.add(betAmount4);
	    betPanel.add(betButton);

	    betFrame.getContentPane().add(betPanel);
	    //betFrame.add(betPanel);
	    betFrame.setSize(200,225);
	    betFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    betFrame.setVisible(true);
	    
	}
    }
    
    /** listender class for setting the bet to $25
     */
    public class BetAmountListener1 implements ActionListener{
        /** changes bet text
            @param event ActionEvent, set bet
	*/
        public void actionPerformed(ActionEvent event){
	    betAmount.setText("$25");
	    amountBet = 25;
	}
    }

    /** listender class for setting the bet to $50
     */
    public class BetAmountListener2 implements ActionListener{
        /** changes bet text
            @param event ActionEvent, set bet
	*/
        public void actionPerformed(ActionEvent event){
            betAmount.setText("$50");
            amountBet = 50;
        }
    }

    /** listender class for setting the bet to $100
     */
    public class BetAmountListener3 implements ActionListener{
        /** changes bet text
            @param event ActionEvent, set bet
	*/
        public void actionPerformed(ActionEvent event){
            betAmount.setText("$100");
            amountBet = 100;
        }
    }

    /** listender class for setting the bet to $250
     */
    public class BetAmountListener4 implements ActionListener{
        /** changes bet text
            @param event ActionEvent, set bet
	*/
        public void actionPerformed(ActionEvent event){
            betAmount.setText("$250");
            amountBet = 250;
        }
    }


    
    /** listener class for 1 player button
     */
    public class WelcomeListener1 implements ActionListener{
	/** initializes the window for set bet amount and prepares for a 1 player game
	    @param event ActionEvent, welcome 1 player
	*/
	public void actionPerformed(ActionEvent event){
	    numPlayers = 1;
	    welcomeFrame.setVisible(false);
	    nameFrame = new JFrame();
	    namePanel = new JPanel();
	    nameFrame.setSize(300,200);
	    JLabel name1 = new JLabel("Player 1's name: ");
	    player1Name = new JTextField(20);
	    namePanel.add(name1);
	    namePanel.add(player1Name);
	    beginGame = new JButton("Confirm");
	    beginGame.addActionListener(new ConfirmName());
	    namePanel.add(beginGame);
	    nameFrame.getContentPane().add(namePanel);
	    nameFrame.setVisible(true);
	}
    }
    
    /** listener class for 2 player button
     */
    public class WelcomeListener2 implements ActionListener{
	/** initializes  the window for set bet amount and prepares for a 2 player game
	    @param event ActionEvent, welcome 2 player
	*/
	public void actionPerformed(ActionEvent event){
	    numPlayers = 2;
	    welcomeFrame.setVisible(false);
	    nameFrame = new JFrame();
	    namePanel = new JPanel();
	    nameFrame.setSize(360,150);
	    JLabel name1 = new JLabel("Player 1's name: ");
	    player1Name = new JTextField(20);
	    JLabel name2 = new JLabel("Player 2's name: ");
	    player2Name = new JTextField(20);
	    namePanel.add(name1);
	    namePanel.add(player1Name);
	    namePanel.add(name2);
	    namePanel.add(player2Name);
	    beginGame = new JButton("Confirm");
	    beginGame.addActionListener(new ConfirmName());
	    namePanel.add(beginGame);
	    nameFrame.getContentPane().add(namePanel);
	    nameFrame.setVisible(true);
	    
	}
    }
    
    /** listener class for 3 player button
     */
    public class WelcomeListener3 implements ActionListener{
	/** initializes the window for set bet amount and prepares for a 3 player game
	    @param event ActionEvent, welcome 3 player
	*/
	public void actionPerformed(ActionEvent event){
	    numPlayers = 3;
	    welcomeFrame.setVisible(false);
	    nameFrame = new JFrame();
	    namePanel = new JPanel();
	    nameFrame.setSize(360,200);
	    JLabel name1 = new JLabel("Player 1's name: ");
	    player1Name = new JTextField(20);
	    JLabel name2 = new JLabel("Player 2's name: ");
	    player2Name = new JTextField(20);
	    JLabel name3 = new JLabel("Player 3's name: ");
	    player3Name = new JTextField(20);
	    namePanel.add(name1);
	    namePanel.add(player1Name);
	    namePanel.add(name2);
	    namePanel.add(player2Name);
	    namePanel.add(name3);
	    namePanel.add(player3Name);
	    beginGame = new JButton("Confirm");
	    beginGame.addActionListener(new ConfirmName());
	    namePanel.add(beginGame);
	    nameFrame.getContentPane().add(namePanel);
	    nameFrame.setVisible(true);
	}
    }
    
    /** listener class for beginGame button after entering player names
     */
    public class BeginGameListener implements ActionListener{
	/** adds names to players and starts game
	    @param event ActionEvent, begins the game
	*/
	public void actionPerformed(ActionEvent event){
	    betFrame.setVisible(false);
	    
	    updateMoney();
	    
	    // switch statement gives players names and makes their cards visible
	    switch(numPlayers) {
	    case 1:
		game.getPlayerS().setName(player1Name.getText());
		playerLabelSM.setText("Money: $" + (game.getPlayerS().getMoney()));

		p1Name = new String(game.getPlayerS().getName());
		playerLabelS.setText(game.getPlayerS().displayHandValue());
		frame.remove(playerPanelW);
		frame.remove(playerPanelE);
		break;
	    case 2:
		game.getPlayerS().setName(player1Name.getText());
		game.getPlayerE().setName(player2Name.getText());
		playerLabelSM.setText("Money: $" + (game.getPlayerS().getMoney()));
		playerLabelEM.setText("Money: $" + (game.getPlayerE().getMoney()));

		p1Name = new String(game.getPlayerS().getName());
		p2Name = new String(game.getPlayerE().getName());
		playerLabelS.setText(game.getPlayerS().displayHandValue());
		playerLabelE.setText(game.getPlayerE().displayHandValue());
		frame.remove(playerPanelW);
		break;
	    case 3:
		game.getPlayerS().setName(player1Name.getText());
		game.getPlayerE().setName(player2Name.getText());
		game.getPlayerW().setName(player3Name.getText());
		playerLabelSM.setText("Money: $" + (game.getPlayerS().getMoney()));
		playerLabelEM.setText("Money: $" + (game.getPlayerE().getMoney()));
		playerLabelWM.setText("Money: $" + (game.getPlayerW().getMoney()));

		p1Name = new String(game.getPlayerS().getName());
		p2Name = new String(game.getPlayerE().getName());
		p3Name = new String(game.getPlayerW().getName());
		playerLabelS.setText(game.getPlayerS().displayHandValue());
		playerLabelE.setText(game.getPlayerE().displayHandValue());
		playerLabelW.setText(game.getPlayerW().displayHandValue());
		frame.setSize(1000,600);
	    }
	    displayLabel.setText(p1Name + "'s turn");
	    frame.setVisible(true);
	}
    }
    
    /** listener class for playAgain button
     */
    public class PlayAgainListener implements ActionListener{
	
	/** starts a new game
	    @param event ActionEvent, play again
	*/
	public void actionPerformed(ActionEvent event){
	    game.newRound();
	    frame.dispose();
	    welcomeFrame.dispose();
	    nameFrame.dispose();
	    keepRunning = true;
	    theGui.go();
	}
    }
    
    /** listener class for timer
     */
    class MyTimerListener implements ActionListener{
	
	/**logic for dealer's turn, calls getWinner() at the end
	   @param event ActionEvent, timer
	*/
	public void actionPerformed(ActionEvent event){
	    if(numPlayers == 1 && game.getPlayerS().isNotBust() == false){
		timer.stop();
		dealerLabel.setText("Dealer wins");
		theGui.getWinner();
		return;
	    }
	    else if(numPlayers == 2 && game.getPlayerS().isNotBust() == false 
		    && game.getPlayerE().isNotBust() == false){
		timer.stop();
		dealerLabel.setText("Dealer wins");
		theGui.getWinner();
		return;
	    }
	    else if(numPlayers == 3 && game.getPlayerS().isNotBust() == false 
		    && game.getPlayerE().isNotBust() == false 
		    && game.getPlayerW().isNotBust() == false){
		timer.stop();
		dealerLabel.setText("Dealer wins");
		theGui.getWinner();
		return;
	    }
	    if(game.dealerHasBlackjack()){
		timer.stop();
		dealerLabel.setText("Dealer has Blackjack");
		theGui.getWinner();
		return;
	    }
	    dealerLabel.setText(game.getDealer().displayHandValue());
	    
	    if(game.dealerShouldStay()){
		timer.stop();
		displayLabel.setText("Did you win?");
		theGui.getWinner();
		return;
	    }
	    else{
		displayCard = game.dealerHit();
		dealerPanel.add(new JLabel(getMyImage(displayCard)));
	    }
	    if(game.dealerNotBust()){
		timer.restart();
		dealerLabel.setText(game.getDealer().displayHandValue());
	    }
	    else{
		dealerLabel.setText(game.getDealer().displayHandValue());
		dealerLabel.setText(dealerLabel.getText() + " Dealer went bust");
		timer.stop();
		theGui.getWinner();
		return;
	    }
	}
    }
} //end BlackjackGui



