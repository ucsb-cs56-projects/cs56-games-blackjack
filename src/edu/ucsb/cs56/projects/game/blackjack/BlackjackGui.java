package edu.ucsb.cs56.projects.game.blackjack;
import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.net.*;
import java.util.ArrayList;
import java.io.*;


/**BlackjackGui Class contains all widgets for Blackjack gam
e interface.
   @author Brian Wan
   @author Fanny Kuang
   @author Eric Palyan
   @author David Tsu
   @author Marco Chavez
   @author Ryan Kirkpatrick
   @author Ryan Lorica
   @version 2017.11.28
*/
public class BlackjackGui {

    /** WELCOME WINDOW,
     * # OF PLAYERS SELECTION WINDOW,
     * and BLACKJACK TABLE WINDOW **/
    boolean isFirstRound = true;


    JFrame frame;
    JFrame welcomeFrame;
    JFrame nameFrame;
    JFrame addMoneyFrame;
    JPanel addMoneyPanel;
    JPanel playerPanelS;
    JPanel playerPanelE;
    JPanel playerPanelW;
    JPanel cardPanelS;
    JPanel cardPanelE;
    JPanel cardPanelW;
    JPanel dealerPanel;
    JPanel welcomePanel;
    JPanel displayPanel;
    JPanel centerPanel;
    JPanel textPanel;
    JLabel dealerLabel;
    JLabel displayLabel;
    JLabel welcomeLabel;
    String p1Name;
    String p2Name;
    String p3Name;
    JPanel namePanel;
    Blackjack game = new Blackjack();
    boolean dealerTurn;
    int playerTurn = 1;
    Card displayCard;
    JLabel downCard;
    JButton playAgain = new JButton("Play again");

	  /**SOUND EFFECTS **/
    Sound cardEffect;
    Sound song;
    /** BET INFO **/
    int amountBet;

     int restartCount = 0;

    /** ADD MONEY FRAME **/
    int playerToAdd;
    int currentMoneyInt;
    JLabel currentMoney;
  
    /** BUTTONS **/
    JButton hit;
    JButton stay;

    /** COLOR **/
    Color feltgreen = new Color(39,119,20);
    Color maroon = new Color(204,0,0);
    Color navy = new Color(0,102,204);
    Color gray = new Color(224,224,224);
    Color currentColor = feltgreen;
    /** PLAYER NAME LABELS **/
    JLabel playerLabelS;
    JLabel playerLabelE;
    JLabel playerLabelW;
    JLabel[] playerLabelArray = new JLabel[3];
    /** PLAYER CARD VALUES LABELS **/
    JLabel cardLabelS;
    JLabel cardLabelE;
    JLabel cardLabelW;
    JLabel[] card1LabelArray = new JLabel[3];
    JLabel card2LabelS;
    JLabel card2LabelE;
    JLabel card2LabelW;
    JLabel[] card2LabelArray = new JLabel[3];

    /** PLAYER MONEY LABELS **/
    JLabel playerLabelSM;
    JLabel playerLabelEM;
    JLabel playerLabelWM;

    /** PLAYER STATISTICS LABELS **/
    JLabel playerLabelSWinLoss;
    JLabel playerLabelEWinLoss;
    JLabel playerLabelWWinLoss;
    JLabel playerLabelSMWonLost;
    JLabel playerLabelEMWonLost;
    JLabel playerLabelWMWonLost;

    /** PLAYER CARDS PANELS **/
    JPanel cardsPanelS;
    JPanel cardsPanelE;
    JPanel cardsPanelW;

    /** NUM OF PLAYER CARDS **/
    int numCardsD = 2;
    int numCardsS = 2;
    int numCardsE = 2;
    int numCardsW = 2;

    /** ABLE TO DOUBLE DOWN? **/
    JButton dd;
    boolean canPlayer1DD;
    boolean canPlayer2DD;
    boolean canPlayer3DD;
    boolean canPlayer4DD;

    /** ABLE TO SPLIT HAND? **/
    JButton split;
    boolean didPlayer1Split = false;
    boolean didPlayer2Split = false;
    boolean didPlayer3Split = false;

    /** TABLE INFORMATION **/
    JLabel totalPotLabel = new JLabel();
    int totalPot;

    /** GAME INFORMATION **/
    ArrayList<String> names;
    public int numPlayers;
    public boolean load = false;
    public boolean shift = false;
    public static boolean keepRunning=false;
    int speed = 1000;
    Timer timer;

    BlackjackGui(GuiModel gm) {
        //Get info from GuiModel
        this.game.resetStats();
        this.numPlayers = gm.getNumPlayers();
        this.load = gm.getLoad();
        setPlayerNames(gm);
        this.amountBet = gm.getBetAmount();

        // Set cardEffect sound and start song automatically
        cardEffect = new Sound("music/dealingCard.wav");
        song = new Sound("music/Casino_Ambiance_Music.wav");
        song.play();
        song.loop();

        frame = new JFrame();
        playerLabelArray[0] = playerLabelS;
        playerLabelArray[1] = playerLabelE;
        playerLabelArray[2] = playerLabelW;

        card1LabelArray[0]  = cardLabelS;
        card1LabelArray[1]  = cardLabelE;
        card1LabelArray[2]  = cardLabelW;

        card2LabelArray[0]  = card2LabelS;
        card2LabelArray[1]  = card2LabelE;
        card2LabelArray[2]  = card2LabelW;
    }

    public void setPlayerNames(GuiModel gm) {
        this.names = gm.getPlayerNames();
        p1Name = this.names.get(0);
        p2Name = this.names.get(1);
        p3Name = this.names.get(2);
    }

  /** enabled or disabled Double Down optiion
  */
  public void setDoubleDown()
  {
    if( playerTurn == 1 && canPlayer1DD )
    {
      canPlayer1DD = false;
      dd.setVisible(true);
    }

    else if( playerTurn == 2 && canPlayer2DD )
    {
      canPlayer2DD = false;
      dd.setVisible(true);
    }

    else if( playerTurn == 3 && canPlayer3DD )
    {
      canPlayer3DD = false;
      dd.setVisible(true);
    }

    else if( playerTurn == 4 && canPlayer4DD )
    {
      canPlayer4DD = false;
      dd.setVisible(true);
    }

    else
    {
      dd.setVisible(false);
    }
  }

  /** enabled or disabled split option */
  public void setSplit()
  {
    int cardOne = 0;
    int cardTwo = 1;

    switch(playerTurn)
    {
      case(1):
      // % 13  is used becuase every 13 cards there's another card with the same value exmaple: 1 % 13 and
      // 14 % 13 will both be A's
      cardOne = ( game.getPlayerS().getHand().getFirstCard().getCardNumber() ) % 13;
      cardTwo = ( game.getPlayerS().getHand().getSecondCard().getCardNumber() ) % 13;

      case(2):
	    //System.out.println("East: " + ((game.getPlayerE().getHand().getFirstCard().getCardNumber()) % 13 ) + " " + ((game.getPlayerE().getHand().getSecondCard().getCardNumber()) % 13 ));
      cardOne = ( game.getPlayerE().getHand().getFirstCard().getCardNumber() ) % 13;
      cardTwo = ( game.getPlayerE().getHand().getSecondCard().getCardNumber() ) % 13;

      case(3):
	    //System.out.println("West: " + ((game.getPlayerW().getHand().getFirstCard().getCardNumber()) % 13 ) + " " + ((game.getPlayerW().getHand().getSecondCard().getCardNumber()) % 13 ));
      cardOne = ( game.getPlayerW().getHand().getFirstCard().getCardNumber() ) % 13;
      cardTwo = ( game.getPlayerW().getHand().getSecondCard().getCardNumber() ) % 13;
    }
    split.setVisible(cardOne == cardTwo);
  }

  /** splits the hand of player
  * @param player the integer of the player
  */
  public void splitHand(int player)
  {
    Player playerObj = game.getPlayer(player);

    Hand hand1 = playerObj.getHand();
    ImageIcon hand1_card2_img = getMyImage(hand1.getSecondCard());

    Hand hand2 = playerObj.getHand2();
    JLabel hand2_card1_img = new JLabel(getMyImage(hand2.getFirstCard()));
    JLabel hand2_card2_img = new JLabel(getMyImage(hand2.getSecondCard()));

    switch(player)
    {
      case 1:
      cardsPanelS.add( hand2_card1_img, BorderLayout.EAST);
	    cardsPanelS.add( hand2_card2_img, BorderLayout.EAST);
	    card2LabelS.setIcon(hand1_card2_img);
	    cardLabelS.setText
      (
        "Hand Value: " + hand1.displayHandValue() +
        "  Second Hand Value: " + hand2.displayHandValue()
      );
	    didPlayer1Split = true;
	    break;

      case 2:
      cardsPanelE.add( hand2_card1_img, BorderLayout.EAST);
	    cardsPanelE.add( hand2_card2_img, BorderLayout.EAST);
	    card2LabelE.setIcon(hand1_card2_img);
	    cardLabelE.setText
      (
        "Hand Value: " + hand1.displayHandValue() +
			  "  Second Hand Value: " + hand2.displayHandValue()
      );
	    didPlayer2Split = true;
	    break;

      case 3:
      cardsPanelW.add( hand2_card1_img, BorderLayout.EAST);
	    cardsPanelW.add( hand2_card2_img, BorderLayout.EAST);
	    card2LabelW.setIcon(hand1_card2_img);
	    cardLabelW.setText
      (
        "Hand Value: " + hand1.displayHandValue() +
        "  Second Hand Value: " + hand2.displayHandValue()
      );
	    didPlayer3Split = true;
	    break;
    	}
      playerObj.setHasSplitTrue();
    }


  // TODO initialize these in constructor
  JButton save = new JButton("Save all stats");
  JButton exitE = new JButton("Leave Game");
  JButton addMoneyE = new JButton("Add Money?");;
  JButton changeBetE = new JButton("Change Bet Amount?");;
  JButton exitW = new JButton("Leave Game");
  JButton addMoneyW = new JButton("Add Money?");
  JButton changeBetW = new JButton("Change Bet Amount?");
  JButton exitS = new JButton("Leave Game");
	JButton addMoneyS = new JButton("Add Money?");
	JButton changeBetS = new JButton("Change Bet Amount?");
  JButton resumeGame = new JButton("Cancel");

  /** gets the winner and displays it in a label
  *  also makes the playAgain button visible
  */
  public void getWinner()
  {
    boolean DealerWon = true;

    for(int count = 0; count < numPlayers; count++)
    {
      Player playerObj = game.getPlayerX(count);
      boolean PlayerWon = game.evaluateWinner(playerObj);
	    String winOrLose = PlayerWon ? " wins" : " loses";

      if (PlayerWon)
      {
        playerObj.addWin();
        playerObj.addMoneyWon(amountBet + playerObj.getDD());
        updateMoney(amountBet, count + 1);
        DealerWon = false;
	    }
	    else
      {
        playerObj.addLoss();
        playerObj.addMoneyLost(amountBet + playerObj.getDD());
	    }

	    updateMoneyLabel(count + 1);
	    playerLabelArray[count].setText(playerObj.getName() + winOrLose);
	    card1LabelArray[count].setText("Hand Value: " + playerObj.getHand().displayBestValue());
    }

    if(DealerWon) displayLabel.setText("Dealer wins");
  }

  public void drawEndScreen()
  {
    /** create 'play again' button to display at the end of the round and removes hit and stay button **/
    save.setMaximumSize(new Dimension(170, 75));
    //save.addActionListener(new SaveListener());
    //playAgain = new JButton("Play again");
    playAgain.setMaximumSize(new Dimension(130, 75));
    //playAgain.addActionListener(new PlayAgainListener());
    hit.setVisible(false);
    stay.setVisible(false);
    shift = false;

    if (game.getPlayerS() != null)
    {
      createSouthAfterRoundButtons();
    }
    if (game.getPlayerE() != null)
    {
      playerPanelE.add(addMoneyE);
	    playerPanelE.add(changeBetE);
	    playerPanelE.add(exitE);
    }
    if (game.getPlayerW() != null)
    {
	    playerPanelW.add(addMoneyW);
	    playerPanelW.add(changeBetW);
	    playerPanelW.add(exitW);
    }
    displayPanel.add(playAgain);
    displayPanel.add(save);
  }

    /** creates buttons at round's end
     */
    public void createSouthAfterRoundButtons() {
      playerPanelS.add(addMoneyS);
      playerPanelS.add(changeBetS);
      playerPanelS.add(exitS);
    }

    /** Fetches stats when a player leaves
     * @param player int for which player
     */
    public void playerLeaves(int player) {
      game.players.get(player).resetMoney(game.players.get(player + 1).getMoney());
      game.players.get(player).setWon(game.players.get(player + 1).getMoneyWon());
      game.players.get(player).setLost(game.players.get(player + 1).getMoneyLost());
      game.players.get(player).setWins(game.players.get(player + 1).getWins());
      game.players.get(player).setLosses(game.players.get(player + 1).getLosses());
      game.players.get(player).setName(game.players.get(player + 1).getName());
    }

    JTextField amountTextField = new JTextField(20);
    /** creates frame to add money to players hand
	  @param player int number of players
    **/
    public void createAddMoneyFrame(int player) {
      addMoneyFrame = new JFrame();
      addMoneyPanel = new JPanel();
      addMoneyFrame.setSize(300,220);

	    JLabel amount = new JLabel("Enter amount of money desired: ");
      amountTextField.setText("");

      if(player == 1)
      {
        currentMoneyInt = game.getPlayerS().getMoney();
        currentMoney = new JLabel("Your current amount of Money: " + game.getPlayerS().getMoney());
        playerToAdd = 1;
      }
      else if(player == 2)
      {
        currentMoneyInt = game.getPlayerE().getMoney();
        currentMoney = new JLabel("Your current amount of Money: " + game.getPlayerE().getMoney());
        playerToAdd = 2;
      }
      else if(player == 3)
      {
        currentMoneyInt = game.getPlayerW().getMoney();
        currentMoney = new JLabel("Your current amount of Money: " + game.getPlayerW().getMoney());
        playerToAdd = 3;
      }

      addMoneyPanel.add(currentMoney);
      addMoneyPanel.add(amount);
      addMoneyPanel.add(amountTextField);
      addMoneyPanel.add(resumeGame);
      addMoneyFrame.getContentPane().add(addMoneyPanel);
      addMoneyFrame.setLocationRelativeTo(null); // center the window
      addMoneyFrame.setVisible(true);
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

    /** add money to the total pot for the hand
     * @param amount amount to add to the total pot
     */
    public void updateTotalPot(int amount) {
        totalPot = amount;
    	totalPotLabel.setText("Total pot: $" + totalPot);
    }

  /** deduct the bet amount from each players' hand at the start of each round
  */
  public void updateMoney()
  {
    switch(numPlayers)
    {
      case(3): game.getPlayerW().setMoney(-amountBet);
      case(2): game.getPlayerE().setMoney(-amountBet);
      case(1): game.getPlayerS().setMoney(-amountBet);
    }
  }

  /** [overloaded] add the total pot to the winner's total money
  * @param pot pot is the amount of money to be won
  * @param player the player that won
  */
  public void updateMoney(int pot, int player)
  {
    switch(numPlayers)
    {
      case(3): if(player == 3) game.getPlayerW().setMoney(pot*(numPlayers+1));
      case(2): if(player == 2) game.getPlayerE().setMoney(pot*(numPlayers+1));
      case(1): if(player == 1) game.getPlayerS().setMoney(pot*(numPlayers+1));
    	}
    }

    /** [overloaded] add/deduct amount from a specific player
     * @param player player to deduct from
     */
    public void updateMoney(int player) {
    	game.getPlayer(player).setMoney(-amountBet);
    	updateMoneyLabel(player);
    }

    public void updateAddMoney(int amount, int player) {
      game.getPlayer(player).setMoney(amount);
      updateMoneyLabel(player);

    }

    /** update money label for the player
     * @param player player's label to update
     */
    public void updateMoneyLabel(int player) {
    	switch(player) {
	case(1):
	    playerLabelSM.setText("Money: $" + game.getPlayerS().getMoney());
	    playerLabelSWinLoss.setText("Wins/Losses: " + game.getPlayerS().getWins() + "/" + game.getPlayerS().getLosses());
	    playerLabelSMWonLost.setText("Money Won/Lost: " + game.getPlayerS().getMoneyWon() + "/" + game.getPlayerS().getMoneyLost());
	    break;
	case(2):
	    playerLabelEM.setText("Money: $" + game.getPlayerE().getMoney());
	    playerLabelEWinLoss.setText("Wins/Losses: " + game.getPlayerE().getWins() + "/" + game.getPlayerE().getLosses());
	    playerLabelEMWonLost.setText("Money Won/Lost: " + game.getPlayerE().getMoneyWon() + "/" + game.getPlayerE().getMoneyLost());
	    break;
	case(3):
	    playerLabelWM.setText("Money: $" + game.getPlayerW().getMoney());
	    playerLabelWWinLoss.setText("Wins/Losses: " + game.getPlayerW().getWins() + "/" + game.getPlayerW().getLosses());
	    playerLabelWMWonLost.setText("Money Won/Lost: " + game.getPlayerW().getMoneyWon() + "/" + game.getPlayerW().getMoneyLost());
	    break;
	default:
	    break;
    	}
    }

    // create 3rd player's label
    public void create3rdPlayersLabel(){
    	playerPanelW = new JPanel(); playerLabelW = new JLabel(p3Name);  playerLabelArray[2] = playerLabelW;
    	playerLabelWM = new JLabel("Money: $" + game.getPlayerW().getMoney());
    	playerLabelWWinLoss = new JLabel("Wins/Losses: " + game.getPlayerW().getWins() + "/" + game.getPlayerW().getLosses());
    	playerLabelWMWonLost = new JLabel("Money Won/Lost: " + game.getPlayerW().getMoneyWon() + "/" + game.getPlayerW().getMoneyLost());
    }

    // create 2nd player's label
    public void create2ndPlayersLabel(){
    	playerPanelE = new JPanel(); playerLabelE = new JLabel(p2Name);  playerLabelArray[1] = playerLabelE;
    	playerLabelEM = new JLabel("Money: $" + game.getPlayerE().getMoney());
    	playerLabelEWinLoss = new JLabel("Wins/Losses: " + game.getPlayerE().getWins() + "/" + game.getPlayerE().getLosses());
    	playerLabelEMWonLost = new JLabel("Money Won/Lost: " + game.getPlayerE().getMoneyWon() + "/" + game.getPlayerE().getMoneyLost());
    }

    // create 1st player's label
    public void create1stPlayersLabel(){
    	playerPanelS = new JPanel(); playerLabelS = new JLabel(p1Name); playerLabelArray[0] = playerLabelS;
    	playerLabelSM = new JLabel("Money: $" + game.getPlayerS().getMoney());
    	playerLabelSWinLoss = new JLabel("Wins/Losses: " + game.getPlayerS().getWins() + "/" + game.getPlayerS().getLosses());
    	playerLabelSMWonLost = new JLabel("Money Won/Lost: " + game.getPlayerS().getMoneyWon() + "/" + game.getPlayerS().getMoneyLost());
    }

    // create dealer's Labels
    public void createDealerLabels(){
	dealerPanel = new JPanel();
	dealerLabel = new JLabel();
	dealerPanel.setOpaque(true);
	dealerPanel.setBackground(currentColor);
	dealerPanel.add(dealerLabel);
    }

    // create card displays for all players
    public void createCardDisplayForAllPlayers(){
    	displayPanel = new JPanel(); displayLabel = new JLabel();
    	displayPanel.setOpaque(true);
    	displayPanel.setBackground(currentColor);
    	cardPanelE = new JPanel(); cardPanelW = new JPanel();
    	cardPanelE.setOpaque(true);
    	cardPanelE.setBackground(currentColor);
    	textPanel = new JPanel();
    	textPanel.setOpaque(true);
    	textPanel.setBackground(currentColor);
    	centerPanel = new JPanel();
    	centerPanel.setOpaque(true);
    	centerPanel.setBackground(currentColor);
    	displayLabel.setFont(new Font(displayLabel.getName(), Font.PLAIN, 20));
    }

    // create the 'hit' and 'stay' buttons
    public void createHitAndStayButtons(){
    	hit = new JButton("hit");
    	hit.setMaximumSize(new Dimension(75,75));
    	//hit.addActionListener(new HitListener());
    	stay = new JButton("stay");
    	stay.setMaximumSize(new Dimension(75,75));
    	//stay.addActionListener(new StayListener());
    	displayPanel.setLayout(new BoxLayout(displayPanel, BoxLayout.Y_AXIS));
    	centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.X_AXIS));
    	centerPanel.add(Box.createRigidArea(new Dimension(120, 0)));
    	centerPanel.add(hit);
    	centerPanel.add(stay);
    	displayPanel.add(Box.createRigidArea(new Dimension(0, 60)));
    	displayPanel.add(displayLabel);
    	displayPanel.add(centerPanel);

      dd = new JButton("Double Down?");
    	//dd.addActionListener(new ddListener());
    	displayPanel.add(dd);
    	setDoubleDown();

	//should only appear if someone can split
    	split = new JButton("Split Hand");
    	//split.addActionListener(new SplitListener());
    	displayPanel.add(split);
    }

  /** initializes many of the widgets and sets up listeners to some of those widgets
  */
  public void go() {
    frame.getContentPane().removeAll();
    dealerTurn = false;
    playerTurn = 1;
    canPlayer1DD = true;
    canPlayer2DD = true;
    canPlayer3DD = true;
    canPlayer4DD = true;

    createDealerLabels();
	  create1stPlayersLabel();
    create2ndPlayersLabel();
    create3rdPlayersLabel();
    createCardDisplayForAllPlayers();
    createHitAndStayButtons();

    // remove the bet amount from all of the players' total money
    updateMoney();
    dealerPanel.add(new JLabel(getMyImage(game.getDealer().getHand().getFirstCard())));
    URL myURL = getClass().getResource("/images/b1fv.gif");
    ImageIcon myImage = new ImageIcon(myURL);
    downCard = new JLabel(myImage);
    dealerPanel.add(downCard);
    dealerLabel.setText(game.displayDealerCardValue());

    // display total pot
    totalPot = 0;
    updateTotalPot(amountBet*(numPlayers+1));
    displayPanel.add(totalPotLabel);

    /*********************************/
    /** Prepare the blackjack table **/
    /*********************************/

    // add 1st player's labels and starter cards to the panel
    cardsPanelS = new JPanel();
    cardsPanelS.setOpaque(true);
    cardsPanelS.setBackground(currentColor);
    cardsPanelS.setAlignmentX(Component.CENTER_ALIGNMENT);
    cardsPanelS.setLayout(new BoxLayout(cardsPanelS, BoxLayout.X_AXIS));
    cardsPanelS.setBorder(BorderFactory.createEmptyBorder(10,100,10,10)); // keep cards aligned
    cardsPanelS.add( new  JLabel(getMyImage(game.getPlayerS().getHand().getFirstCard())), BorderLayout.WEST); // add first card
    card2LabelS = new JLabel(getMyImage(game.getPlayerS().getHand().getSecondCard()));
    cardsPanelS.add(card2LabelS, BorderLayout.WEST); // add second card

    cardLabelS = new JLabel("Hand Value: " + game.getPlayerS().getHand().displayHandValue());
    card1LabelArray[0] = cardLabelS;

    playerPanelS.setLayout(new BoxLayout(playerPanelS, BoxLayout.Y_AXIS));
    playerPanelS.setOpaque(true);
    playerPanelS.setBackground(currentColor);
    playerPanelS.setAlignmentX(Component.CENTER_ALIGNMENT);
    playerPanelS.add(playerLabelS); // name of player
    playerPanelS.add(cardsPanelS); // cards in hand
    playerPanelS.add(cardLabelS); // value of cards
    playerPanelS.add(playerLabelSM); // amount of money
    playerPanelS.add(playerLabelSWinLoss);
    playerPanelS.add(playerLabelSMWonLost);

    // add 2nd player's labels and starter cards to the panel
    cardsPanelE = new JPanel();
    cardsPanelE.setOpaque(true);
    cardsPanelE.setBackground(currentColor);
    cardsPanelE.setAlignmentX(Component.CENTER_ALIGNMENT);
    cardsPanelE.setLayout(new BoxLayout(cardsPanelE, BoxLayout.X_AXIS));
    cardsPanelE.setBorder(BorderFactory.createEmptyBorder(10,100,10,10)); // keep cards aligned
    cardsPanelE.add( new  JLabel(getMyImage(game.getPlayerE().getHand().getFirstCard())), BorderLayout.EAST); // add first card
    card2LabelE = new JLabel(getMyImage(game.getPlayerE().getHand().getSecondCard()));
    cardsPanelE.add(card2LabelE, BorderLayout.WEST); // add second card

    cardLabelE = new JLabel("Hand Value: " + game.getPlayerE().getHand().displayHandValue());
    card1LabelArray[1] = cardLabelE;
    playerPanelE.setLayout(new BoxLayout(playerPanelE, BoxLayout.Y_AXIS));
    playerPanelE.setOpaque(true);
    playerPanelE.setBackground(currentColor);
    playerPanelE.setAlignmentX(Component.CENTER_ALIGNMENT);
    playerPanelE.add(playerLabelE); // name of player
    playerPanelE.add(cardsPanelE); // cards in hand
    playerPanelE.add(cardLabelE); // value of cards
    playerPanelE.add(playerLabelEM); // amount of money
    playerPanelE.add(playerLabelEWinLoss);
    playerPanelE.add(playerLabelEMWonLost);

    // add 3rd  player's labels and starter cards to the panel
    cardsPanelW = new JPanel();
    cardsPanelW.setOpaque(true);
    cardsPanelW.setBackground(currentColor);
    cardsPanelW.setAlignmentX(Component.CENTER_ALIGNMENT);
    cardsPanelW.setLayout(new BoxLayout(cardsPanelW, BoxLayout.X_AXIS));
    cardsPanelW.setBorder(BorderFactory.createEmptyBorder(10,100,10,10)); // keep cards aligned
    cardsPanelW.add( new  JLabel(getMyImage(game.getPlayerW().getHand().getFirstCard())), BorderLayout.WEST); // add first card
    card2LabelW = new JLabel(getMyImage(game.getPlayerW().getHand().getSecondCard()));
    cardsPanelW.add(card2LabelW, BorderLayout.WEST); // add second card

    cardLabelW = new JLabel("Hand Value: " + game.getPlayerW().getHand().displayHandValue());
    card1LabelArray[2] = cardLabelW;
    playerPanelW.setLayout(new BoxLayout(playerPanelW, BoxLayout.Y_AXIS));
    playerPanelW.setOpaque(true);
    playerPanelW.setBackground(currentColor);
    playerPanelW.setAlignmentX(Component.CENTER_ALIGNMENT);
    playerPanelW.add(playerLabelW); // name of player
    playerPanelW.add(cardsPanelW); // cards in hand
    playerPanelW.add(cardLabelW); // value of cards
    playerPanelW.add(playerLabelWM); // amount of money
    playerPanelW.add(playerLabelWWinLoss);
    playerPanelW.add(playerLabelWMWonLost);

    // set all the player+dealer+buttons panels into proper positions in the frame
    frame.getContentPane().add(BorderLayout.NORTH, dealerPanel);
    frame.getContentPane().add(BorderLayout.CENTER, displayPanel);
    frame.getContentPane().add(BorderLayout.SOUTH, playerPanelS);
    frame.getContentPane().add(BorderLayout.EAST, playerPanelE);
    frame.getContentPane().add(BorderLayout.WEST, playerPanelW);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    //frame.setSize(10000,10000);
    frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
    frame.pack();
    setSplit();

    // This section is for a new round of Blackjack
    if(keepRunning == true){
        if(numPlayers == 0){
            song.stop();
            frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
        } else if(numPlayers == 1){
            cardLabelS.setText(game.getPlayerS().displayHandValue());
            frame.remove(playerPanelE);
            frame.remove(playerPanelW);
            //frame.setSize(1000,1000);
            frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        } else if(numPlayers == 2){
            cardLabelS.setText(game.getPlayerS().displayHandValue());
            cardLabelE.setText(game.getPlayerE().displayHandValue());
            frame.remove(playerPanelW);
            //frame.setSize(800,600);
            frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
	    } else if(numPlayers == 3){
            cardLabelS.setText(game.getPlayerS().displayHandValue());
            cardLabelE.setText(game.getPlayerE().displayHandValue());
            cardLabelW.setText(game.getPlayerW().displayHandValue());
            //frame.setSize(1000,600);
            frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
	    }

	    displayLabel.setText("New Round, " + p1Name + "'s turn");
	    frame.setLocationRelativeTo(null);
	    frame.getContentPane().setBackground(currentColor);
	    //frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.pack();
	    frame.setVisible(true);
	}

    if(isFirstRound){
          initialize();
          isFirstRound = false;
    }
}

public void initialize()
{
  switch(numPlayers)
  {
    case 3: game.getPlayerW().setName(p3Name);
    case 2: game.getPlayerE().setName(p2Name);
    case 1: game.getPlayerS().setName(p1Name);
  }

  if (load) game.loadStats(this);

  // switch statement gives players names and makes their cards visible
  switch(numPlayers)
  {
    case 3:
    playerLabelWM.setText("Money: $" + (game.getPlayerW().getMoney()));
    playerLabelWWinLoss.setText("Wins/Losses: " + game.getPlayerW().getWins() + "/" + game.getPlayerW().getLosses());
    playerLabelWMWonLost.setText("Money Won/Lost: " + game.getPlayerW().getMoneyWon() + "/" + game.getPlayerW().getMoneyLost());
    playerLabelW.setText(game.getPlayerW().displayHandValue());

    case 2:
    playerLabelEM.setText("Money: $" + (game.getPlayerE().getMoney()));
    playerLabelEWinLoss.setText("Wins/Losses: " + game.getPlayerE().getWins() + "/" + game.getPlayerE().getLosses());
    playerLabelEMWonLost.setText("Money Won/Lost: " + game.getPlayerE().getMoneyWon() + "/" + game.getPlayerE().getMoneyLost());
    playerLabelE.setText(game.getPlayerE().displayHandValue());

    case 1:
    playerLabelSM.setText("Money: $" + game.getPlayerS().getMoney());
    playerLabelSWinLoss.setText("Wins/Losses: " + game.getPlayerS().getWins() + "/" + game.getPlayerS().getLosses());
    playerLabelSMWonLost.setText("Money Won/Lost: " + game.getPlayerS().getMoneyWon() + "/" + game.getPlayerS().getMoneyLost());
    playerLabelS.setText(game.getPlayerS().displayHandValue());
  }

  switch(numPlayers)
  {
    case 1: frame.remove(playerPanelE);
    case 2: frame.remove(playerPanelW); break;
    case 3: frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
  }

  displayLabel.setText(p1Name + "'s turn");
  updateTotalPot(amountBet*(numPlayers+1));
  frame.setLocationRelativeTo(null);
  frame.setVisible(true);
}

    /** Allows next player to take turn
     */
    public void nextPlayersTurn(){
    	playerTurn++;
    	displayLabel.setText(game.getPlayer(playerTurn).getName() + "'s turn");
    	setDoubleDown();
    	setSplit();
    }

    /** returns the image corresponding to the Card passed in
     *  @param c Card to retrieve the image of
     *  @return ImageIcon for corresponding card
    */
    public ImageIcon getMyImage(Card c){
	String cardString ="/images/" + c + ".gif";
    cardString = cardString.replaceAll(" ",""); //remove spaces

	URL myurl = getClass().getResource(cardString);
	if(myurl != null){
	    ImageIcon myImage = new ImageIcon(myurl);
	    return myImage;
	}
	else
	    return new ImageIcon();
    }

    /** updates ingame statistics
     */
    public void updateStats() {
	if (game.getPlayerS() != null) { game.getPlayerS().setWins(game.p1wins); game.getPlayerS().setLosses(game.p1losses);
	    game.getPlayerS().setWon(game.p1won); game.getPlayerS().setLost(game.p1lost);
	    game.getPlayerS().resetMoney(game.p1money);
	}

	if (game.getPlayerE() != null) { game.getPlayerE().setWins(game.p2wins); game.getPlayerE().setLosses(game.p2losses);
	    game.getPlayerE().setWon(game.p2won); game.getPlayerE().setLost(game.p2lost);
	    game.getPlayerE().resetMoney(game.p2money);
	}

	if (game.getPlayerW() != null) { game.getPlayerW().setWins(game.p3wins); game.getPlayerW().setLosses(game.p3losses);
	    game.getPlayerW().setWon(game.p3won); game.getPlayerW().setLost(game.p3lost);
	    game.getPlayerW().resetMoney(game.p3money);
	}
    }
} //end BlackjackGui
