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
    JButton playAgain;
    JButton beginGame;
	/** CARD SOUND EFFECT **/
	    Sound cardEffect = new Sound("music/dealingCard.wav");

    /** RULES WINDOW **/
    JFrame rulesFrame;
    JPanel rulesPanel;
    JLabel rulesLabel;
    JButton rulesButton;

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
    JLabel totalPotLabel;
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
    private void setDoubleDown() {
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
    private void setSplit() {
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

    /** split listener
     *  @author David Tsu
     *  @author Marco Chavez
     *  @version 2016.11.9
     */
    public class SplitListener implements ActionListener {
    	public void actionPerformed(ActionEvent e) {
	    game.splitHand(playerTurn);
	    splitHand(playerTurn);
	    split.setVisible(false);

    	}
    }

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
	JButton save = new JButton("Save all stats");
	save.setMaximumSize(new Dimension(170, 75));
	save.addActionListener(new SaveListener());
	playAgain = new JButton("Play again");
	playAgain.setMaximumSize(new Dimension(130, 75));
	playAgain.addActionListener(new PlayAgainListener());
	hit.setVisible(false);
	stay.setVisible(false);
	shift = false;
	if (game.getPlayerS() != null) {
		createSouthAfterRoundButtons();

	}
	if (game.getPlayerE() != null) {
	    JButton exitE = new JButton("Leave Game");
	    JButton addMoneyE = new JButton("Add Money?");
	    JButton changeBetE = new JButton("Change Bet Amount?");
	    exitE.addActionListener(new ExitEListener());
	    changeBetE.addActionListener(new ChangeBetListener());
	    addMoneyE.addActionListener(new AddMoneyEListener());
	    playerPanelE.add(addMoneyE);
	    playerPanelE.add(changeBetE);
	    playerPanelE.add(exitE);
	}
	if (game.getPlayerW() != null) {
	    JButton exitW = new JButton("Leave Game");
	    JButton addMoneyW = new JButton("Add Money?");
	    JButton changeBetW = new JButton("Change Bet Amount?");
	    exitW.addActionListener(new ExitWListener());
	    changeBetW.addActionListener(new ChangeBetListener());
	    addMoneyW.addActionListener(new AddMoneyWListener());
	    playerPanelW.add(addMoneyW);
	    playerPanelW.add(changeBetW);
	    playerPanelW.add(exitW);
	}
	displayPanel.add(playAgain);
	displayPanel.add(save);
    }

    /** creates buttons at round's end
     */
    private void createSouthAfterRoundButtons(){
	JButton exitS = new JButton("Leave Game");
	JButton addMoneyS = new JButton("Add Money?");
	JButton changeBetS = new JButton("Change Bet Amount?");
	exitS.addActionListener(new ExitSListener());
	changeBetS.addActionListener(new ChangeBetListener());
	addMoneyS.addActionListener(new AddMoneySListener());
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

    /** ExitSListener, listens for bottom player's exit
     *  @author David Tsu
     *  @author Marco Chavez
     *  @version 2016.11.9
     */
    public class ExitSListener implements ActionListener {
	public void actionPerformed(ActionEvent e) {
	    if (numPlayers == 1) frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
	    if (numPlayers == 3) {
		shift = true;
		playerLeaves(0);
		p1Name = p2Name;
		playerLeaves(1);
		p2Name = p3Name;
	    }
	    else if (numPlayers == 2) {
		playerLeaves(0);
		p1Name = p2Name;
	    }
	    numPlayers--;
	    JButton exit = (JButton)e.getSource();
	    exit.removeActionListener(this);
	}
    }

    /** ExitEListener, listens for right player's exit
     *  @author David Tsu
     *  @author Marco Chavez
     *  @version 2016.11.9
     */
    public class ExitEListener implements ActionListener {
	public void actionPerformed(ActionEvent e) {
	    if (numPlayers == 1) {
		frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
	    }
	    else if (numPlayers == 3) {
		playerLeaves(1);
		p2Name = p3Name;
	    }
	    else if (numPlayers == 2) {
		if (shift == true) {
		    playerLeaves(0);
		    p1Name = p2Name;
		}
	    }
	    numPlayers--;
	    JButton exit = (JButton)e.getSource();
	    exit.removeActionListener(this);
	}
    }

    /** ExitWListener, listens for left player's exit
     *  @author David Tsu
     *  @author Marco Chavez
     *  @version 2016.11.9
     */
    public class ExitWListener implements ActionListener {
	public void actionPerformed(ActionEvent e) {
	    if (numPlayers == 1) {
		frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
	    }
	    numPlayers--;
	    JButton exit = (JButton)e.getSource();
	    exit.removeActionListener(this);
	}
    }

    /** ExitListener, listens for general game exit
     *  @author David Tsu
     *  @author Marco Chavez
     *  @version 2016.11.9
     */
    public class ExitListener implements ActionListener {
	public void actionPerformed(ActionEvent e) {
	    System.exit(0);
	}
    }

    JTextField amountTextField;
    /** creates name frame that sets player names
	@param player int number of players
    **/
    public void createAddMoneyFrame(int player){
	addMoneyFrame = new JFrame();
	addMoneyPanel = new JPanel();
	addMoneyFrame.setSize(300,220);

	JLabel amount = new JLabel("Enter amount of money desired: ");
	amountTextField = new JTextField(20);
	amountTextField.addActionListener(new AmountTextFieldListener());

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

	JButton resumeGame = new JButton("Confirm");
	resumeGame.addActionListener(new ConfirmAddMoney());
	addMoneyPanel.add(resumeGame);
	addMoneyFrame.getContentPane().add(addMoneyPanel);
	addMoneyFrame.setLocationRelativeTo(null); // center the window
	addMoneyFrame.setVisible(true);
    }

    /** AddMoneySListener
     *  @author David Tsu
     *  @author Marco Chavez
     *  @version 2016.11.9
     */
    public class AddMoneySListener implements ActionListener{
	public void actionPerformed(ActionEvent e){
	    createAddMoneyFrame(1);
	}
    }
    /** AddMoneyEListener
     *  @author David Tsu
     *  @author Marco Chavez
     *  @version 2016.11.9
     */
    public class AddMoneyEListener implements ActionListener{
	public void actionPerformed(ActionEvent e){
	    createAddMoneyFrame(2);

	}
    }

    /** AddMoneyWListener
     *  @author David Tsu
     *  @author Marco Chavez
     *  @version 2016.11.9
     */
    public class AddMoneyWListener implements ActionListener{
	public void actionPerformed(ActionEvent e){
	    createAddMoneyFrame(3);

	}
    }

    /** AmountTextFieldListener
     *  @author David Tsu
     *  @author Marco Chavez
     *  @version 2016.11.9
     */
    public class AmountTextFieldListener implements ActionListener{
	public void actionPerformed(ActionEvent e){
	    int moneyToAdd = Integer.parseInt(amountTextField.getText());
	    int currentMoneyToDisplay = currentMoneyInt + moneyToAdd;

	    currentMoney.setText("Your current amount of Money:" + currentMoneyToDisplay);
	}
    }

    /** listener class for confirm addMoney button
     *  @author David Tsu
     *  @author Marco Chavez
     *  @version 2016.11.9
     */
    public class ConfirmAddMoney implements ActionListener{
	/** initializes some of the JLabels for a set bet menu and brings up the main JFrame
	    @param event ActionEvent, set bet
	*/
	public void actionPerformed(ActionEvent event){
	    // disable the previous window ('enter player name(s)')
	    addMoneyFrame.setVisible(false);
	}
    }

    /** listener class for changebet
     *  @author David Tsu
     *  @author Marco Chavez
     *  @version 2016.11.9
     */
    public class ChangeBetListener implements ActionListener{
	public void actionPerformed(ActionEvent e){
	    createBetWindow(false);
	}
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
    private void updateMoney(int pot, int player) {
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
    private void updateMoney(int player) {
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
    	private Clip clip;
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

    /** listener class for pause button
     *  @author John Lau
     *  @version 2016.2.18
     */
    public class PauseMusicListener implements ActionListener{
	public void actionPerformed(ActionEvent e){
	    song.stop();
	}
    }

    /** listener class for play button
     *  @author John Lau
     *  @version 2016.2.18
     */
    public class PlayMusicListener implements ActionListener{
	public void actionPerformed(ActionEvent e){
	    song.play();
	}
    }

    /** listener class for Navy color change
     *  @author ???
     *  @version 2016.11.9
     */
    public class NavyActionListener implements ActionListener{
	public void actionPerformed(ActionEvent e){
	    currentColor = navy;
	    dealerPanel.setBackground(navy);
	    displayPanel.setBackground(navy);
	    cardsPanelE.setBackground(navy);
	    cardsPanelS.setBackground(navy);
	    cardsPanelW.setBackground(navy);
	    centerPanel.setBackground(navy);
	    textPanel.setBackground(navy);
	    playerPanelS.setBackground(navy);
	    playerPanelE.setBackground(navy);
	    playerPanelW.setBackground(navy);
	}
    }

    /** listener class for Maroon color change
     *  @author ???
     *  @version 2016.11.9
     */
    public class MaroonActionListener implements ActionListener{
	public void actionPerformed(ActionEvent e){
	    currentColor = maroon;
	    dealerPanel.setBackground(maroon);
	    displayPanel.setBackground(maroon);
	    cardsPanelE.setBackground(maroon);
	    cardsPanelS.setBackground(maroon);
	    cardsPanelW.setBackground(maroon);
	    centerPanel.setBackground(maroon);
	    textPanel.setBackground(maroon);
	    playerPanelS.setBackground(maroon);
	    playerPanelE.setBackground(maroon);
	    playerPanelW.setBackground(maroon);
	}
    }

    /** listener class for Gray color change
     *  @author ???
     *  @version 2016.11.9
     */
    public class GrayActionListener implements ActionListener{
	public void actionPerformed(ActionEvent e){
	    currentColor = gray;
	    dealerPanel.setBackground(gray);
	    displayPanel.setBackground(gray);
	    cardsPanelE.setBackground(gray);
	    cardsPanelS.setBackground(gray);
	    cardsPanelW.setBackground(gray);
	    centerPanel.setBackground(gray);
	    textPanel.setBackground(gray);
	    playerPanelS.setBackground(gray);
	    playerPanelE.setBackground(gray);
	    playerPanelW.setBackground(gray);
	}
    }

    /** listener class for FeltGreen color change
     *  @author Marco Chavez
     *  @version 2016.11.9
     */
    public class FeltGreenActionListener implements ActionListener{
	public void actionPerformed(ActionEvent e){
	    currentColor = feltgreen;
	    dealerPanel.setBackground(feltgreen);
	    displayPanel.setBackground(feltgreen);
	    cardsPanelE.setBackground(feltgreen);
	    cardsPanelS.setBackground(feltgreen);
	    cardsPanelW.setBackground(feltgreen);
	    centerPanel.setBackground(feltgreen);
	    textPanel.setBackground(feltgreen);
	    playerPanelS.setBackground(feltgreen);
	    playerPanelE.setBackground(feltgreen);
	    playerPanelW.setBackground(feltgreen);
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

    //create menubar
    public void createMenuBar(){
    	menuBar = new JMenuBar();
    	menuFile = new JMenu("File");
    	menuEdit = new JMenu("Edit");
    	menuView = new JMenu("View");
    	menuHelp = new JMenu("Help");
    	menuMusic = new JMenu("Music");

    	JMenuItem menuSave= new JMenuItem("Save");
    	menuSave.addActionListener(new SaveListener());
    	JMenuItem menuExit = new JMenuItem("Exit");
    	menuExit.addActionListener(new ExitListener());
    	JMenuItem menuRestart = new JMenuItem("Restart");
    	menuRestart.addActionListener(new PlayAgainListener());
    	JMenuItem menuRules= new JMenuItem("Rules");
    	menuRules.addActionListener(new RulesListener());
    	JMenu menuColors= new JMenu("Colors");
    	JMenuItem colorNavy = new JMenuItem("Navy");
    	JMenuItem colorMaroon = new JMenuItem("Maroon");
    	JMenuItem colorGray = new JMenuItem("Gray");
	JMenuItem colorFeltGreen = new JMenuItem("Felt Green");
    	menuColors.add(colorNavy);
    	menuColors.add(colorGray);
    	menuColors.add(colorMaroon);
	menuColors.add(colorFeltGreen);
    	colorNavy.addActionListener(new NavyActionListener());
    	colorGray.addActionListener(new GrayActionListener());
    	colorMaroon.addActionListener(new MaroonActionListener());
	colorFeltGreen.addActionListener(new FeltGreenActionListener());
    	JMenuItem menuNames = new JMenuItem("Player Names");
    	menuNames.addActionListener(new ChangeNamesListener());
    	JMenuItem songPause = new JMenuItem("Pause");
    	menuMusic.add(songPause);
    	songPause.addActionListener(new PauseMusicListener());
    	JMenuItem songPlay = new JMenuItem("Play");
    	menuMusic.add(songPlay);
    	songPlay.addActionListener(new PlayMusicListener());

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
    	hit.addActionListener(new HitListener());
    	stay = new JButton("stay");
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

    	dd = new JButton("Double Down?");
    	dd.addActionListener(new ddListener());
    	displayPanel.add(dd);
    	setDoubleDown();

	//should only appear if someone can split
    	split = new JButton("Split Hand");
    	split.addActionListener(new SplitListener());
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
    game.getPlayerS().setName(player1Name.getText());
    break;
    case 2:
    game.getPlayerS().setName(player1Name.getText());
    game.getPlayerE().setName(player2Name.getText());
    break;
    case 3:
    game.getPlayerS().setName(player1Name.getText());
    game.getPlayerE().setName(player2Name.getText());
    game.getPlayerW().setName(player3Name.getText());
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

    /** initializes the rules window
     */
    public void rules()
    {
    	rulesFrame = new JFrame();
    	rulesPanel = new JPanel(new GridLayout(2, 0, 5, 0));
    	rulesLabel = new JLabel
        ("<html><br>Blackjack win and loss conditions.. <br>Loss Conditions:"
        +" <br>-Having a hand value of over 21 (BUST!) <br>-Having a hand value"
        +" less than the dealer's hand value <br><br>Win Conditions: <br>-Get"
        +" blackjack (hand value of 21) <br>-Have 5 cards without busting (5"
        +" card charlie! you still still lose if the dealer has blackjack)"
        +" <br>-Have a hand value greater than the dealer's <br><br>Other"
        +" Features:<br>-Double downing draws one card then moves onto the"
        +" next player (deducts bet amount, chance to win double)<br>-Every"
        +" player starts with $5,000<br><br>");

    	rulesButton = new JButton("Play!");
    	//rulesButton.addActionListener(new CloseRulesListener());

    	rulesLabel.setHorizontalAlignment(SwingConstants.CENTER);

    	rulesPanel.add(rulesLabel);
    	rulesPanel.add(rulesButton);
    	rulesPanel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

      // create the outer panel to center the widgets
    	JPanel outerPanel = new JPanel();
    	outerPanel.setLayout(new BoxLayout(outerPanel, BoxLayout.PAGE_AXIS));
    	outerPanel.add(Box.createHorizontalGlue());
    	outerPanel.add(rulesPanel);
    	outerPanel.add(Box.createHorizontalGlue());

    	rulesFrame.add(rulesPanel);
    	rulesFrame.setSize(300,400);
    	rulesFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	rulesFrame.pack();
      rulesFrame.setLocationRelativeTo(null); // center the window
      rulesFrame.setVisible(true);
    }


  // NECESSARY FOR WELCOME
  public JButton onePlayerButton;
  public JButton twoPlayerButton;
  public JButton threePlayerButton;

  /** initializes the welcome widgets
  */
  public void welcome()
  {
    totalPotLabel = new JLabel();

    welcomeFrame = new JFrame();
    welcomePanel = new JPanel(new GridLayout(4, 0, 5, 0));
    welcomeLabel = new JLabel();

    onePlayerButton = new JButton("1 player");
    twoPlayerButton = new JButton("2 players");
    threePlayerButton = new JButton("3 players");

    welcomeLabel.setText("Welcome to Blackjack");
    welcomeLabel.setHorizontalAlignment(SwingConstants.CENTER);

    welcomePanel.add(welcomeLabel);

    welcomePanel.add(onePlayerButton);
    welcomePanel.add(twoPlayerButton);
    welcomePanel.add(threePlayerButton);

    welcomePanel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

    // create the outer panel to center the widgets
    JPanel outerPanel = new JPanel();
    outerPanel.setLayout(new BoxLayout(outerPanel, BoxLayout.PAGE_AXIS));
    outerPanel.add(Box.createHorizontalGlue());
    outerPanel.add(welcomePanel);
    outerPanel.add(Box.createHorizontalGlue());

    welcomeFrame.add(welcomePanel);
    welcomeFrame.setSize(200,175);
    welcomeFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    welcomeFrame.pack();
    welcomeFrame.setLocationRelativeTo(null); // center the window

    welcomeFrame.setVisible(true);
  }


    /** returns the image corresponding to the Card passed in
     *  @param c Card to retrieve the image of
     *  @return ImageIcon for corresponding card
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
	    setDoubleDown();

	    if(!dealerTurn){
		//adds a new card to the players hand
		Card newCard = game.playerHit(game.getPlayer(playerTurn));
		//makes a string for whether or not the player busts,
		//to later append to their label
		String isBust = !game.getPlayer(playerTurn).isNotBust() ? " went bust!" : "";
		split.setVisible(false);
		switch(playerTurn){
		case 1:
		    playerLabelS.setText(game.getPlayerX(0).getName() + isBust);
		    cardLabelS.setText(game.getPlayerS().displayHandValue());
		    cardsPanelS.add(new JLabel(getMyImage(newCard)));
		    displayLabel.setText(game.getPlayerX(0).getName() + " hit!");
			cardEffect.play();
		    break;
		case 2:
		    playerLabelE.setText(game.getPlayerX(1).getName() + isBust);
		    cardLabelE.setText(game.getPlayerE().displayHandValue());
		    cardsPanelE.add(new JLabel(getMyImage(newCard)));
		    displayLabel.setText(game.getPlayerX(1).getName() + " hit!");
			cardEffect.play();
		    break;
		case 3:
		    playerLabelW.setText(game.getPlayerX(2).getName() + isBust);
		    cardLabelW.setText(game.getPlayerW().displayHandValue());
		    cardsPanelW.add(new JLabel(getMyImage(newCard)));
		    displayLabel.setText(game.getPlayerX(2).getName() + " hit!");
			cardEffect.play();
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
     *  @author ???
     *  @version 2016.11.9
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
		    setSplit();
		}
		else{
		    displayLabel.setText("Dealer's Turn");
		    theGui.startDealerTurn();
		}
	    }
	    setDoubleDown();
	}
    }


    /** listener class for the double down button
     *  @author ???
     *  @version 2016.11.9
     */
    public class ddListener implements ActionListener{

        /** does nothing if it is not the player's turn
         *  otherwise makes the player draw one card and then goes to the next player's turn
         @param event ActionEvent, Player doubles down
	*/
	public void actionPerformed(ActionEvent event){

	    if(!dealerTurn){
		//update pot and deduct player's money on hand
		updateTotalPot(amountBet);
		updateMoney(playerTurn);

		//adds a new card to the players hand
		Card newCard = game.playerHit(game.getPlayer(playerTurn));
                //makes a string for whether or not the player busts,
                //to later append to their label
		String isBust = !game.getPlayer(playerTurn).isNotBust() ? " went bust!" : "";
		switch(playerTurn){
		case 1:
		    playerLabelS.setText(p1Name + isBust);
		    cardLabelS.setText(game.getPlayerS().displayHandValue());
		    cardsPanelS.add(new JLabel(getMyImage(newCard)));
		    displayLabel.setText(p1Name + " hit!");
		    game.getPlayerS().setDD(amountBet);
		    break;
		case 2:
		    playerLabelE.setText(p2Name + isBust);
		    cardLabelE.setText(game.getPlayerE().displayHandValue());
		    cardsPanelE.add(new JLabel(getMyImage(newCard)));
		    displayLabel.setText(p2Name + " hit!");
		    game.getPlayerE().setDD(amountBet);
		    break;
		case 3:
		    playerLabelW.setText(p3Name + isBust);
		    cardLabelW.setText(game.getPlayerW().displayHandValue());
		    cardsPanelW.add(new JLabel(getMyImage(newCard)));
		    displayLabel.setText(p3Name + " hit!");
		    game.getPlayerW().setDD(amountBet);
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

		// if not bust
		else if(playerTurn < numPlayers){
		    playerTurn++;
		    displayLabel.setText(game.getPlayer(playerTurn).getName() + "'s turn");
		}
		// if dealer's turn
		else{
		    displayLabel.setText("Dealer's Turn");
		    theGui.startDealerTurn();
		}
	    }

	}
    }


    /** Opens rules without Play button
     *  @author ???
     *  @version 2016.11.9
     */
    public class RulesListener implements ActionListener{
     	public void actionPerformed(ActionEvent e)
     	{
			rulesFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		    rulesFrame.setVisible(true);
		    rulesButton.setVisible(false);
     	}

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

    /** listener class for player names change in menu bar
     *  @author ???
     *  @version 2016.11.9
     */
    public class ChangeNamesListener implements ActionListener{
	public void actionPerformed(ActionEvent e){
	    createNewNameFrame(numPlayers);
	}

    }

  /** creates name frame that sets player names
	@param num number of players
  **/
  public void createNewNameFrame(int num)
  {
	  nameFrame = new JFrame();
    namePanel = new JPanel();
    nameFrame.setSize(300,220);
    if(num == 1)
	  {
      JLabel name1 = new JLabel("Player 1's name: ");
      player1Name = new JTextField(20);
      namePanel.add(name1);
      namePanel.add(player1Name);
    }
    else if(num == 2)
    {
      JLabel name1 = new JLabel("Player 1's name: ");
      player1Name = new JTextField(20);
		  JLabel name2 = new JLabel("Player 2's name: ");
      player2Name = new JTextField(20);
      namePanel.add(name1);
      namePanel.add(player1Name);
      namePanel.add(name2);
      namePanel.add(player2Name);
	  }
	  else if(num ==3)
    {
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
	  }

	  loadSave = new JCheckBox("Load Saved Stats");
	  //loadSave.addItemListener(new LoadListener());
	  namePanel.add(loadSave);
    beginGame = new JButton("Confirm");
	  //beginGame.addActionListener(new ConfirmName());
	  namePanel.add(beginGame);
	  nameFrame.getContentPane().add(namePanel);
	  nameFrame.setLocationRelativeTo(null); // center the window
	  nameFrame.setVisible(true);
  }

    /** listener class for save button
     *  @author ???
     *  @version 2016.11.9
     */
    public class SaveListener implements ActionListener {
	/** prepares for save
	 *  @param event ActionEvent
	 */
	public void actionPerformed(ActionEvent event) {
	    game.saveStats(theGui);
	}
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


    /** listener class for playAgain button
     *  @author ???
     *  @version 2016.11.9
     */
    public class PlayAgainListener implements ActionListener{

	/** starts a new game
	    @param event ActionEvent, play again
	*/
	public void actionPerformed(ActionEvent event){
	    didPlayer1Split = false;
	    didPlayer2Split = false;
	    didPlayer3Split = false;
	    game.newRound();
	    song.stop();
	    welcomeFrame.dispose();
	    nameFrame.dispose();
	    keepRunning = true;
	    go();
	}
    }
} //end BlackjackGui
