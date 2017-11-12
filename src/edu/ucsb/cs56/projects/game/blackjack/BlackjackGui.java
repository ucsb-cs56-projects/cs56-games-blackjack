package edu.ucsb.cs56.projects.game.blackjack;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.net.*;
import java.util.ArrayList;
import java.io.*;


/**BlackjackGui Class contains all widgets for Blackjack game interface.
   @author Brian Wan
   @author Fanny Kuang
   @author Eric Palyan
   @author David Tsu
   @author Marco Chavez
   @version 2016.11.9
*/
public class BlackjackGui
{

    public int stage;

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
    JButton playAgain = new JButton("Play again");;
    JButton beginGame;
	/** CARD SOUND EFFECT **/
	    Sound cardEffect = new Sound("music/dealingCard.wav");


    /** BET WINDOW **/
    JFrame betFrame;
    JPanel betPanel;
    JLabel betLabel;
    JTextField betText;
    JButton betButton;
    JButton betAmount1;
    JButton betAmount2;
    JButton betAmount3;
    JButton betAmount4;
    JButton betAmount5;
    JLabel betAmount;
    int amountBet;
    JCheckBox loadSave;

    /** ADD MONEY FRAME **/
    int currentMoneyInt;
    JLabel currentMoney;

    /** MENUBAR **/
    JMenuBar menuBar;
    JMenu menuFile;
    JMenu menuEdit;
    JMenu menuView;
    JMenu menuHelp;
    JMenu menuMusic;

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

  BlackjackGui()
  {
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

    stage = 0;
  }


    /** enabled or disabled Double Down optiion
     */
    public void setDoubleDown() {
    	switch(playerTurn) {
	case(1):
	    if (canPlayer1DD) {
		canPlayer1DD = false;
		dd.setVisible(true);
	    }
	    else
		dd.setVisible(false);
	    break;
	case(2):
	    if (canPlayer2DD) {
		canPlayer2DD = false;
		dd.setVisible(true);
	    }
	    else
		dd.setVisible(false);
	    break;
	case(3):
	    if (canPlayer3DD) {
		canPlayer3DD = false;
		dd.setVisible(true);
	    }
	    else
		dd.setVisible(false);
	    break;
	case(4):
	    if (canPlayer4DD) {
		canPlayer4DD = false;
		dd.setVisible(true);
	    }
	    else
		dd.setVisible(false);
	    break;
    	}
    }

    /** enabled or disabled split option */
    public void setSplit() {
	switch(playerTurn) {
	case(1):
	   // % 13  is used becuase every 13 cards there's another card with the same value exmaple: 1 % 13 and
	   // 14 % 13 will both be A's
	    if (((game.getPlayerS().getHand().getFirstCard().getCardNumber()) % 13 )== ((game.getPlayerS().getHand().getSecondCard().getCardNumber()) % 13 )) {
		split.setVisible(true);
	    }
	    else split.setVisible(false);
	    break;
	case(2):
	    //System.out.println("East: " + ((game.getPlayerE().getHand().getFirstCard().getCardNumber()) % 13 ) + " " + ((game.getPlayerE().getHand().getSecondCard().getCardNumber()) % 13 ));
	    if (((game.getPlayerE().getHand().getFirstCard().getCardNumber()) % 13) == ((game.getPlayerE().getHand().getSecondCard().getCardNumber()) % 13)) {
		split.setVisible(true);
	    }
	    else split.setVisible(false);
	    break;
	case(3):
	    //System.out.println("West: " + ((game.getPlayerW().getHand().getFirstCard().getCardNumber()) % 13 ) + " " + ((game.getPlayerW().getHand().getSecondCard().getCardNumber()) % 13 ));
	    if (((game.getPlayerW().getHand().getFirstCard().getCardNumber()) % 13) == ((game.getPlayerW().getHand().getSecondCard().getCardNumber()) % 13 )) {
		split.setVisible(true);
	    }
	    else split.setVisible(false);
	    break;
	case(4): split.setVisible(false);
	    break;
	}
    }

    /** splits the hand of player
     * @param player the integer of the player
     */
    public void splitHand(int player) {
    	switch(player) {
	case 1: cardsPanelS.add( new  JLabel(getMyImage(game.getPlayer(player).getHand2().getFirstCard())), BorderLayout.EAST);
	    cardsPanelS.add( new  JLabel(getMyImage(game.getPlayer(player).getHand2().getSecondCard())), BorderLayout.EAST);
	    card2LabelS.setIcon(getMyImage(game.getPlayer(player).getHand().getSecondCard()));
	    cardLabelS.setText("Hand Value: " + game.getPlayer(player).getHand().displayHandValue() +
			       "  Second Hand Value: " + game.getPlayer(player).getHand2().displayHandValue());
	    game.getPlayer(player).setHasSplitTrue();
	    didPlayer1Split = true;
	    break;
	case 2: cardsPanelE.add( new  JLabel(getMyImage(game.getPlayer(player).getHand2().getFirstCard())), BorderLayout.EAST);
	    cardsPanelE.add( new  JLabel(getMyImage(game.getPlayer(player).getHand2().getSecondCard())), BorderLayout.EAST);
	    card2LabelE.setIcon(getMyImage(game.getPlayer(player).getHand().getSecondCard()));
	    cardLabelE.setText("Hand Value: " + game.getPlayer(player).getHand().displayHandValue() +
			       "  Second Hand Value: " + game.getPlayer(player).getHand2().displayHandValue());
	    game.getPlayer(player).setHasSplitTrue();
	    didPlayer2Split = true;
	    break;
	case 3: cardsPanelW.add( new  JLabel(getMyImage(game.getPlayer(player).getHand2().getFirstCard())), BorderLayout.EAST);
	    cardsPanelW.add( new  JLabel(getMyImage(game.getPlayer(player).getHand2().getSecondCard())), BorderLayout.EAST);
	    card2LabelW.setIcon(getMyImage(game.getPlayer(player).getHand().getSecondCard()));
	    cardLabelW.setText("Hand Value: " + game.getPlayer(player).getHand().displayHandValue() +
			       "  Second Hand Value: " + game.getPlayer(player).getHand2().displayHandValue());
	    game.getPlayer(player).setHasSplitTrue();
	    didPlayer3Split = true;
	    break;
    	}
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
  JButton resumeGame = new JButton("Confirm");

    /** gets the winner and displays it in a label
     *  also makes the playAgain button visible
     */
    public void getWinner(){

	boolean DealerWon = true;

	for(int count = 0; count < numPlayers; count++){
	    boolean PlayerWon = game.evaluateWinner(game.getPlayerX(count)) == 'P';
	    String winOrLose = PlayerWon ? " wins" : " loses";
	    if (PlayerWon) {
		game.getPlayerX(count).addWin();
		game.getPlayerX(count).addMoneyWon(amountBet + game.getPlayerX(count).getDD());
	    }
	    else {
		game.getPlayerX(count).addLoss();
		game.getPlayerX(count).addMoneyLost(amountBet + game.getPlayerX(count).getDD());
	    }
	    updateMoneyLabel(count + 1);
	    playerLabelArray[count].setText(game.getPlayerX(count).getName() + winOrLose);
	    card1LabelArray[count].setText("Hand Value: " + game.getPlayerX(count).getHand().displayBestValue());
	    if (PlayerWon)
		updateMoney(amountBet, count + 1);
	    if(PlayerWon)
		DealerWon = false;

	}
	if(DealerWon)
	    displayLabel.setText("Dealer wins");
	/** create 'play again' button to display at the end of the round and removes hit and stay button **/

	save.setMaximumSize(new Dimension(170, 75));
	//save.addActionListener(new SaveListener());
  //playAgain = new JButton("Play again");
  playAgain.setMaximumSize(new Dimension(130, 75));
	//playAgain.addActionListener(new PlayAgainListener());
	hit.setVisible(false);
	stay.setVisible(false);
	shift = false;
	if (game.getPlayerS() != null) {
		createSouthAfterRoundButtons();

	}
	if (game.getPlayerE() != null) {


	    playerPanelE.add(addMoneyE);
	    playerPanelE.add(changeBetE);
	    playerPanelE.add(exitE);
	}
	if (game.getPlayerW() != null) {
	    playerPanelW.add(addMoneyW);
	    playerPanelW.add(changeBetW);
	    playerPanelW.add(exitW);
	}
	displayPanel.add(playAgain);
	displayPanel.add(save);
    }

    /** creates buttons at round's end
     */
    public void createSouthAfterRoundButtons(){
	playerPanelS.add(addMoneyS);
	playerPanelS.add(changeBetS);
	playerPanelS.add(exitS);
    }
    /** Fetches stats when a player leaves
     * @param player int for which player
     */
    public void playerLeaves(int player){
	game.players.get(player).resetMoney(game.players.get(player + 1).getMoney());
	game.players.get(player).setWon(game.players.get(player + 1).getMoneyWon());
	game.players.get(player).setLost(game.players.get(player + 1).getMoneyLost());
	game.players.get(player).setWins(game.players.get(player + 1).getWins());
	game.players.get(player).setLosses(game.players.get(player + 1).getLosses());
	game.players.get(player).setName(game.players.get(player + 1).getName());
    }

    JTextField amountTextField = new JTextField(20);
    /** creates name frame that sets player names
	@param player int number of players
    **/
    public void createAddMoneyFrame(int player){
	addMoneyFrame = new JFrame();
	addMoneyPanel = new JPanel();
	addMoneyFrame.setSize(300,220);

	JLabel amount = new JLabel("Enter amount of money desired: ");
	//amountTextField = new JTextField(20);
	//amountTextField.addActionListener(new AmountTextFieldListener());

	if(player == 1)
	    {
		currentMoneyInt = game.getPlayerS().getMoney();
		currentMoney = new JLabel("Your current amount of Money: " + game.getPlayerS().getMoney() );


	    }
	else if(player == 2)
	    {
		currentMoneyInt = game.getPlayerE().getMoney();
		currentMoney = new JLabel("Your current amount of Money: " + game.getPlayerE().getMoney());


	    }
	else if(player == 3)
	    {
		currentMoneyInt = game.getPlayerW().getMoney();
		currentMoney = new JLabel("Your current amount of Money: " + game.getPlayerW().getMoney());


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

    /** add money to the total pot
     * @param amount amount to add to the total pot
     */
    public void updateTotalPot(int amount) {
    	totalPot += amount;
    	totalPotLabel.setText("Total pot: $" + totalPot);
    }

    /** deduct the bet amount from each players' hand at the start of each round
     */
    public void updateMoney() {
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
     * @param player the player that won
     */
    public void updateMoney(int pot, int player) {
    	switch(numPlayers) {
	case(1):
	    if(player == 1)
		game.getPlayerS().setMoney(pot*2);
	    break;
	case(2):
	    if(player == 1) game.getPlayerS().setMoney(pot*3);
	    if(player == 2) game.getPlayerE().setMoney(pot*3);
	    break;
	case(3):
	    if(player == 1) game.getPlayerS().setMoney(pot*4);
	    if(player == 2) game.getPlayerE().setMoney(pot*4);
	    if(player == 3) game.getPlayerW().setMoney(pot*4);
	    break;
	default:
	    break;
    	}
    }

    /** [overloaded] add/deduct amount from a specific player
     * @param player player to deduct from
     */
    public void updateMoney(int player) {
    	game.getPlayer(player).setMoney(-amountBet);
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

    /** sound class, plays background music from menu bar
     *  @author John Lau
     *  @version 2016.2.18
     */
    public class Sound {
    	public Clip clip;
    	public Sound(String fileName) {
	    // specify the sound to play
	    // (assuming the sound can be played by the audio system)
	    // from a wave File
	    try {
		File file = new File(fileName);
		if (file.exists()) {
		    AudioInputStream sound = AudioSystem.getAudioInputStream(file);
		    // load the sound into memory (a Clip)
		    clip = AudioSystem.getClip();
		    clip.open(sound);
		}
		else {
		    throw new RuntimeException("Sound: file not found: " + fileName);
		}
	    }
	    catch (MalformedURLException e) {
		e.printStackTrace();
		throw new RuntimeException("Sound: Malformed URL: " + e);
	    }
	    catch (UnsupportedAudioFileException e) {
		e.printStackTrace();
		throw new RuntimeException("Sound: Unsupported Audio File: " + e);
	    }
	    catch (IOException e) {
		e.printStackTrace();
		throw new RuntimeException("Sound: Input/Output Error: " + e);
	    }
	    catch (LineUnavailableException e) {
		e.printStackTrace();
		throw new RuntimeException("Sound: Line Unavailable Exception Error: " + e);
	    }
	    // play, stop, loop the sound clip
    	}
    public void play(){
	    clip.setFramePosition(0);  // Must always rewind!
	    clip.start();
	}
	public void loop(){
	    clip.loop(Clip.LOOP_CONTINUOUSLY);
	}
	public void stop(){
	    clip.stop();
	}
    }
    // song object for background music
    Sound song = new Sound("music/Casino_Ambiance_Music.wav");


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

  public JMenuItem menuSave;
  public JMenuItem menuExit;
  public JMenuItem menuRestart;
  public JMenuItem menuRules;
  public JMenuItem menuNames;
  public JMenuItem songPause;
  public JMenuItem songPlay;
  public JMenuItem colorNavy;
  public JMenuItem colorFeltGreen;
  public JMenuItem colorMaroon;
  public JMenuItem colorGray;

    //create menubar
    public void createMenuBar(){
    	menuBar = new JMenuBar();
    	menuFile = new JMenu("File");
    	menuEdit = new JMenu("Edit");
    	menuView = new JMenu("View");
    	menuHelp = new JMenu("Help");
    	menuMusic = new JMenu("Music");

    	menuSave= new JMenuItem("Save");
    	//menuSave.addActionListener(new SaveListener());
    	menuExit = new JMenuItem("Exit");
    	//menuExit.addActionListener(new ExitListener());
    	menuRestart = new JMenuItem("Restart");
    	//menuRestart.addActionListener(new PlayAgainListener());
      menuRules= new JMenuItem("Rules");
    	//menuRules.addActionListener(new RulesListener());
    	JMenu menuColors= new JMenu("Colors");
    	colorNavy = new JMenuItem("Navy");
    	colorMaroon = new JMenuItem("Maroon");
    	colorGray = new JMenuItem("Gray");
	    colorFeltGreen = new JMenuItem("Felt Green");
    	menuColors.add(colorNavy);
    	menuColors.add(colorGray);
    	menuColors.add(colorMaroon);
        menuColors.add(colorFeltGreen);
    	//colorNavy.addActionListener(new NavyActionListener());
    	//colorGray.addActionListener(new GrayActionListener());
    	//colorMaroon.addActionListener(new MaroonActionListener());
	    //colorFeltGreen.addActionListener(new FeltGreenActionListener());
    	menuNames = new JMenuItem("Player Names");
    	//menuNames.addActionListener(new ChangeNamesListener());
    	songPause = new JMenuItem("Pause");
    	menuMusic.add(songPause);
    	//songPause.addActionListener(new PauseMusicListener());
    	songPlay = new JMenuItem("Play");
    	menuMusic.add(songPlay);
    	//songPlay.addActionListener(new PlayMusicListener());

    	menuFile.add(menuSave);
    	menuFile.add(menuExit);
    	menuFile.add(menuRestart);
    	menuBar.add(menuFile);
    	menuEdit.add(menuNames);
    	menuBar.add(menuEdit);
    	menuView.add(menuColors);
    	menuBar.add(menuView);
    	menuHelp.add(menuRules);
    	menuBar.add(menuHelp);
    	menuBar.add(menuMusic);
    	frame.setJMenuBar(menuBar);


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
  public void go()
  {
    theGui=this;
    frame.getContentPane().removeAll();
    dealerTurn = false;
    playerTurn = 1;
    canPlayer1DD =true;
    canPlayer2DD =true;
    canPlayer3DD =true;
    canPlayer4DD =true;

    createMenuBar();
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
    if(keepRunning == true)
    {
      if(numPlayers == 0)
      {
        song.stop();
        frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
      }
	    if(numPlayers == 1)
      {
        cardLabelS.setText(game.getPlayerS().displayHandValue());
        frame.remove(playerPanelE);
        frame.remove(playerPanelW);
        //frame.setSize(1000,1000);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
      }
	    else if(numPlayers == 2)
      {
        cardLabelS.setText(game.getPlayerS().displayHandValue());
        cardLabelE.setText(game.getPlayerE().displayHandValue());
        frame.remove(playerPanelW);
        //frame.setSize(800,600);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
	    }
	    else if(numPlayers == 3)
      {
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

    if(isFirstRound)
    {
      // start background music automatically
    	song.play();
    	song.loop();
      initialize();
      isFirstRound = false;
    }
  }

public void initialize()
{
  switch(numPlayers)
  {
    case 1:
    game.getPlayerS().setName(p1Name);
    break;
    case 2:
    game.getPlayerS().setName(p1Name);
    game.getPlayerE().setName(p2Name);
    break;
    case 3:
    game.getPlayerS().setName(p1Name);
    game.getPlayerE().setName(p2Name);
    game.getPlayerW().setName(p3Name);
    break;
  }

  if (load) game.loadStats(theGui);

  // switch statement gives players names and makes their cards visible
  switch(numPlayers)
  {
    case 1:
    playerLabelSM.setText("Money: $" + game.getPlayerS().getMoney());
    playerLabelSWinLoss.setText("Wins/Losses: " + game.getPlayerS().getWins() + "/" + game.getPlayerS().getLosses());
    playerLabelSMWonLost.setText("Money Won/Lost: " + game.getPlayerS().getMoneyWon() + "/" + game.getPlayerS().getMoneyLost());

    p1Name = new String(game.getPlayerS().getName());
    playerLabelS.setText(game.getPlayerS().displayHandValue());
    playerLabelS.setText(p1Name);
    frame.remove(playerPanelW);
    frame.remove(playerPanelE);
    break;
    case 2:
    playerLabelSM.setText("Money: $" + (game.getPlayerS().getMoney()));
    playerLabelSWinLoss.setText("Wins/Losses: " + game.getPlayerS().getWins() + "/" + game.getPlayerS().getLosses());
    playerLabelSMWonLost.setText("Money Won/Lost: " + game.getPlayerS().getMoneyWon() + "/" + game.getPlayerS().getMoneyLost());
    playerLabelEM.setText("Money: $" + (game.getPlayerE().getMoney()));
    playerLabelEWinLoss.setText("Wins/Losses: " + game.getPlayerE().getWins() + "/" + game.getPlayerE().getLosses());
    playerLabelEMWonLost.setText("Money Won/Lost: " + game.getPlayerE().getMoneyWon() + "/" + game.getPlayerE().getMoneyLost());

    p1Name = new String(game.getPlayerS().getName());
    p2Name = new String(game.getPlayerE().getName());
    playerLabelS.setText(game.getPlayerS().displayHandValue());
    playerLabelE.setText(game.getPlayerE().displayHandValue());
    frame.remove(playerPanelW);
    break;
    case 3:
    playerLabelSM.setText("Money: $" + (game.getPlayerS().getMoney()));
    playerLabelSWinLoss.setText("Wins/Losses: " + game.getPlayerS().getWins() + "/" + game.getPlayerS().getLosses());
    playerLabelSMWonLost.setText("Money Won/Lost: " + game.getPlayerS().getMoneyWon() + "/" + game.getPlayerS().getMoneyLost());
    playerLabelEM.setText("Money: $" + (game.getPlayerE().getMoney()));
    playerLabelEWinLoss.setText("Wins/Losses: " + game.getPlayerE().getWins() + "/" + game.getPlayerE().getLosses());
    playerLabelEMWonLost.setText("Money Won/Lost: " + game.getPlayerE().getMoneyWon() + "/" + game.getPlayerE().getMoneyLost());
    playerLabelWM.setText("Money: $" + (game.getPlayerW().getMoney()));
    playerLabelWWinLoss.setText("Wins/Losses: " + game.getPlayerW().getWins() + "/" + game.getPlayerW().getLosses());
    playerLabelWMWonLost.setText("Money Won/Lost: " + game.getPlayerW().getMoneyWon() + "/" + game.getPlayerW().getMoneyLost());

    p1Name = new String(game.getPlayerS().getName());
    p2Name = new String(game.getPlayerE().getName());
    p3Name = new String(game.getPlayerW().getName());
    playerLabelS.setText(game.getPlayerS().displayHandValue());
    playerLabelE.setText(game.getPlayerE().displayHandValue());
    playerLabelW.setText(game.getPlayerW().displayHandValue());
    //frame.setSize(1000,600);
    frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
  }
  displayLabel.setText(p1Name + "'s turn");

  updateTotalPot(amountBet*(numPlayers+1));

  frame.setLocationRelativeTo(null); // center window
  //frame.setUndecorated(true);
  //frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
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

  /** creates window for betting
  */
  public void createBetWindow(boolean exit_on_close)
  {
    // create the frame and panels, as well as set the layout
    betFrame = new JFrame();
    betPanel = new JPanel(new GridLayout(7,0,5,0));
    betLabel = new JLabel();

    // set the main text and center it
    betLabel.setText("<html>How much would you<br>like to bet?</html>");
    betLabel.setHorizontalAlignment(JLabel.CENTER);

    // by default have the $25 bet selected
    betAmount = new JLabel("$25");
    betAmount.setHorizontalAlignment(JLabel.CENTER); // center the label
    amountBet = 25;

    betText = new JTextField("Or enter your desired bet amount here");
    //betText.addActionListener(new BetTextListener());

    // create bet amount buttons and assign ActionListeners
    betAmount1 = new JButton("$25");
    betAmount2 = new JButton("$50");
    betAmount3 = new JButton("$100");
    betAmount4 = new JButton("$250");
    betAmount5 = new JButton("$500");
    //betAmount1.addActionListener(new BetAmountListener1());
    //betAmount2.addActionListener(new BetAmountListener2());
    //betAmount3.addActionListener(new BetAmountListener3());
    //betAmount4.addActionListener(new BetAmountListener4());
    //betAmount5.addActionListener(new BetAmountListener5());

    // create button to confirm selected bet amount
    betButton = new JButton("Confirm Bet");
    //betButton.addActionListener(new BeginGameListener());
    // add widgets to panel
    betPanel.add(betLabel);
    betPanel.add(betAmount);
    betPanel.add(betAmount1);
    betPanel.add(betAmount2);
    betPanel.add(betAmount3);
    betPanel.add(betAmount4);
    betPanel.add(betAmount5);
    betPanel.add(betText);
    betPanel.add(betButton);
    betText.selectAll();
    // create the outer panel to center the widgets
    JPanel outerPanel = new JPanel();
    outerPanel.setLayout(new BoxLayout(outerPanel, BoxLayout.PAGE_AXIS));
    outerPanel.add(Box.createHorizontalGlue());
    outerPanel.add(betPanel);
    outerPanel.add(Box.createHorizontalGlue());
    // add the panel to the frame and set frame attributes
    betFrame.add(outerPanel);
    // Since this is the only frame before the game starts, if this window
    // is closed the application should terminate
    betFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    // If the betting frame is brought up at the end of a round and closed using the 'x' button the game
    // should not be terminated.
    if(!exit_on_close)
    betFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    betFrame.pack();
    betFrame.setLocationRelativeTo(null); // center window
    betFrame.setVisible(true);
    // Set PartOfWelcomeingWindow to false. This is the last frame before game starts.
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
