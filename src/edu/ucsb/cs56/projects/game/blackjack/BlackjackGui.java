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
   @version 2014.02.27
*/
public class BlackjackGui{ 
    /** WELCOME WINDOW,
     * # OF PLAYERS SELECTION WINDOW,
     * and BLACKJACK TABLE WINDOW **/
    JFrame frame;
    JFrame welcomeFrame;
    JFrame nameFrame;
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

    /** MENUBAR **/
    JMenuBar menuBar;
    JMenu menuFile;
    JMenu menuEdit;
    JMenu menuView;
    JMenu menuHelp;
    
    /** BUTTONS **/
    JButton hit;
    JButton stay;
    
    /** COLOR **/
    Color feltgreen = new Color(39,119,20);

    /** PLAYER NAME LABELS **/
    JLabel playerLabelS;
    JLabel playerLabelE;
    JLabel playerLabelW;

    /** PLAYER CARD VALUES LABELS **/
    JLabel cardLabelS;
    JLabel cardLabelE;
    JLabel cardLabelW;
    JLabel card2LabelS;
    JLabel card2LabelE;
    JLabel card2LabelW;

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
    Timer timer = new Timer(speed, new MyTimerListener());
    

    /** launches/runs the blackjack game
     * @param args array of String command line arguments 
     */
    public static void main(String[] args){
	BlackjackGui gui = new BlackjackGui();
	gui.rules();
	gui.welcome();
	gui.go();
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
	    if (game.getPlayerS().getHand().getFirstCard().getValue() == game.getPlayerS().getHand().getSecondCard().getValue()) {
	    	split.setVisible(true);
	    }
	    else split.setVisible(false);
	    break;
	case(2):
	    if (game.getPlayerE().getHand().getFirstCard().getValue() == game.getPlayerE().getHand().getSecondCard().getValue()) {
	    	split.setVisible(true);
	    }
	    else split.setVisible(false);
	    break;
	case(3):
	    if (game.getPlayerW().getHand().getFirstCard().getValue() == game.getPlayerW().getHand().getSecondCard().getValue()) {
	    	split.setVisible(true);
	    }
	    else split.setVisible(false);
	    break;
	case(4): split.setVisible(false); 
		break;
	}
    }
    
    public void splitHand(int player) {
    	switch(player) {
    		case 1: cardsPanelS.add( new  JLabel(getMyImage(game.getPlayer(player).getHand2().getFirstCard())), BorderLayout.EAST);
		cardsPanelS.add( new  JLabel(getMyImage(game.getPlayer(player).getHand2().getSecondCard())), BorderLayout.EAST);
		card2LabelS.setIcon(getMyImage(game.getPlayer(player).getHand().getSecondCard()));
		cardLabelS.setText("Hand Value: " + game.getPlayer(player).getHand().displayHandValue() + 
				   "  Second Hand Value: " + game.getPlayer(player).getHand2().displayHandValue());
		break;
		case 2: cardsPanelE.add( new  JLabel(getMyImage(game.getPlayer(player).getHand2().getFirstCard())), BorderLayout.EAST);
		cardsPanelE.add( new  JLabel(getMyImage(game.getPlayer(player).getHand2().getSecondCard())), BorderLayout.EAST);
		card2LabelE.setIcon(getMyImage(game.getPlayer(player).getHand().getSecondCard()));
		cardLabelE.setText("Hand Value: " + game.getPlayer(player).getHand().displayHandValue() + 
				   "  Second Hand Value: " + game.getPlayer(player).getHand2().displayHandValue());
		break;
		case 3: cardsPanelW.add( new  JLabel(getMyImage(game.getPlayer(player).getHand2().getFirstCard())), BorderLayout.EAST);
		cardsPanelW.add( new  JLabel(getMyImage(game.getPlayer(player).getHand2().getSecondCard())), BorderLayout.EAST);
		card2LabelW.setIcon(getMyImage(game.getPlayer(player).getHand().getSecondCard()));
		cardLabelW.setText("Hand Value: " + game.getPlayer(player).getHand().displayHandValue() + 
				   "  Second Hand Value: " + game.getPlayer(player).getHand2().displayHandValue());
		break;
    	}
    }
    
    public class SplitListener implements ActionListener {
    	public void actionPerformed(ActionEvent e) {
    		game.splitHand(playerTurn);
    		splitHand(playerTurn);
    		split.removeActionListener(this);
    	}
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
	    // player 1
	    String winOrLose1 = p1IsWinner ? " wins" : " loses";
	    if (p1IsWinner) {
		game.getPlayerS().addWin();
		game.getPlayerS().addMoneyWon(amountBet + game.getPlayerS().getDD());
	    }
	    else {
		game.getPlayerS().addLoss();
		game.getPlayerS().addMoneyLost(amountBet + game.getPlayerS().getDD());
	    }
	    updateMoneyLabel(1);
	    playerLabelS.setText(p1Name + winOrLose1);
	    cardLabelS.setText("Hand Value: " + game.getPlayerS().getHand().displayBestValue());

	    // if the players did not win then the dealer won
	    if(!p1IsWinner)
		displayLabel.setText("Dealer wins");
	    break;
	case 2:
	    // player 1
	    String winOrLose2 = p1IsWinner ? " wins" : " loses";
	    if (p1IsWinner) {
		game.getPlayerS().addWin();
		game.getPlayerS().addMoneyWon(amountBet + game.getPlayerS().getDD());
	    }
	    else {
		game.getPlayerS().addLoss();
		game.getPlayerS().addMoneyLost(amountBet + game.getPlayerS().getDD());
	    }
	    playerLabelS.setText(p1Name + winOrLose2);
	    cardLabelS.setText("Hand Value: " + game.getPlayerS().getHand().displayBestValue());
	    
	    // player 2
	    winOrLose2 = p2IsWinner ? " wins" : " loses";
	    if (p2IsWinner) {
		game.getPlayerE().addWin();
		game.getPlayerE().addMoneyWon(amountBet + game.getPlayerE().getDD());
	    }
	    else {
		game.getPlayerE().addLoss();
		game.getPlayerE().addMoneyLost(amountBet + game.getPlayerE().getDD());
	    }
	    updateMoneyLabel(1);
	    updateMoneyLabel(2);
	    playerLabelE.setText(p2Name + winOrLose2);
	    cardLabelE.setText("Hand Value: " + game.getPlayerE().getHand().displayBestValue());

	    // if the players did not win then the dealer won
	    if(!p1IsWinner && !p2IsWinner)
		displayLabel.setText("Dealer Wins");
	    break;
	case 3:
	    // player 1
	    String winOrLose3 = p1IsWinner ? " wins" : " loses";
	    if (p1IsWinner) {
		game.getPlayerS().addWin();
		game.getPlayerS().addMoneyWon(amountBet + game.getPlayerS().getDD());
	    }
	    else {
		game.getPlayerS().addLoss();
		game.getPlayerS().addMoneyLost(amountBet + game.getPlayerS().getDD());
	    }
	    playerLabelS.setText(p1Name + winOrLose3);
	    cardLabelS.setText("Hand Value: " + game.getPlayerS().getHand().displayBestValue());
	    
	    // player 2
	    winOrLose3 = p2IsWinner ? " wins" : " loses";
	    if (p2IsWinner) {
		game.getPlayerE().addWin();
		game.getPlayerE().addMoneyWon(amountBet + game.getPlayerE().getDD());
	    }
	    else {
		game.getPlayerE().addLoss();
		game.getPlayerE().addMoneyLost(amountBet + game.getPlayerE().getDD());
	    }
	    playerLabelE.setText(p2Name + winOrLose3);
	    cardLabelS.setText("Hand Value: " + game.getPlayerE().getHand().displayBestValue());
	    
	    // player 3
	    winOrLose3 = p3IsWinner ? " wins" : " loses";
	    if (p3IsWinner) {
		game.getPlayerW().addWin();
		game.getPlayerW().addMoneyWon(amountBet + game.getPlayerW().getDD());
	    }
	    else {
		game.getPlayerW().addLoss();
		game.getPlayerW().addMoneyLost(amountBet + game.getPlayerW().getDD());
	    }
	    updateMoneyLabel(1);
	    updateMoneyLabel(2);
	    updateMoneyLabel(3);
	    playerLabelW.setText(p3Name + winOrLose3);
	    cardLabelW.setText("Hand Value: " + game.getPlayerW().getHand().displayBestValue());	    

	    // if the players did not win then the dealer won
	    if(!p1IsWinner && !p2IsWinner && !p3IsWinner)
		displayLabel.setText("Dealer Wins");
	    break;
	default:
	    break;
	}

	/** UPDATE MONEY FOR THE WINNER **/
	if (p1IsWinner)
	    updateMoney(amountBet, 1);
	else if (p2IsWinner)
	    updateMoney(amountBet, 2);
	else if (p3IsWinner)
	    updateMoney(amountBet, 3);
     
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
		JButton exitS = new JButton("Leave Game");
		exitS.addActionListener(new ExitSListener());
		playerPanelS.add(exitS);
	}
	if (game.getPlayerE() != null) {
		JButton exitE = new JButton("Leave Game");
		exitE.addActionListener(new ExitEListener());
		playerPanelE.add(exitE);
	}
	if (game.getPlayerW() != null) {
		JButton exitW = new JButton("Leave Game");
		exitW.addActionListener(new ExitWListener());
		playerPanelW.add(exitW);
	}
	displayPanel.add(playAgain);
	displayPanel.add(save);
    }
    
    public class ExitSListener implements ActionListener {
    	public void actionPerformed(ActionEvent e) {
    		if (numPlayers == 1) frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
    		if (numPlayers == 3) {
    			shift = true;
    			game.players.get(0).resetMoney(game.players.get(1).getMoney());
    			game.players.get(0).setWon(game.players.get(1).getMoneyWon());
    			game.players.get(0).setLost(game.players.get(1).getMoneyLost());
    			game.players.get(0).setWins(game.players.get(1).getWins());
    			game.players.get(0).setLosses(game.players.get(1).getLosses());
    			game.players.get(0).setName(game.players.get(1).getName());
    			p1Name = p2Name;
    			game.players.get(1).resetMoney(game.players.get(2).getMoney());
    			game.players.get(1).setWon(game.players.get(2).getMoneyWon());
    			game.players.get(1).setLost(game.players.get(2).getMoneyLost());
    			game.players.get(1).setWins(game.players.get(2).getWins());
    			game.players.get(1).setLosses(game.players.get(2).getLosses());
    			game.players.get(1).setName(game.players.get(2).getName());
    			p2Name = p3Name;
    		}
    		else if (numPlayers == 2) {
    			game.players.get(0).resetMoney(game.players.get(1).getMoney());
    			game.players.get(0).setWon(game.players.get(1).getMoneyWon());
    			game.players.get(0).setLost(game.players.get(1).getMoneyLost());
    			game.players.get(0).setWins(game.players.get(1).getWins());
    			game.players.get(0).setLosses(game.players.get(1).getLosses());
    			game.players.get(0).setName(game.players.get(1).getName());
    			p1Name = p2Name;
    		}
    		numPlayers--;
    		JButton exit = (JButton)e.getSource();
    		exit.removeActionListener(this);
    	}
    }
    
    public class ExitEListener implements ActionListener {
    	public void actionPerformed(ActionEvent e) {
    		if (numPlayers == 1) {
    			frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
    		}
    		else if (numPlayers == 3) {
    			game.players.get(1).resetMoney(game.players.get(2).getMoney());
    			game.players.get(1).setWon(game.players.get(2).getMoneyWon());
    			game.players.get(1).setLost(game.players.get(2).getMoneyLost());
    			game.players.get(1).setWins(game.players.get(2).getWins());
    			game.players.get(1).setLosses(game.players.get(2).getLosses());
    			game.players.get(1).setName(game.players.get(2).getName());
    			p2Name = p3Name;
    		}
    		else if (numPlayers == 2) {
    			if (shift == true) {
    				game.players.get(0).resetMoney(game.players.get(1).getMoney());
    				game.players.get(0).setWon(game.players.get(1).getMoneyWon());
    				game.players.get(0).setLost(game.players.get(1).getMoneyLost());
    				game.players.get(0).setWins(game.players.get(1).getWins());
    				game.players.get(0).setLosses(game.players.get(1).getLosses());
    				game.players.get(0).setName(game.players.get(1).getName());
    				p1Name = p2Name;
    			}
    		}
    		numPlayers--;
    		JButton exit = (JButton)e.getSource();
    		exit.removeActionListener(this);
    	}
    }
    
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

    public class ExitListener implements ActionListener {
	public void actionPerformed(ActionEvent e) {
	    System.exit(0);
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

    /** initializes many of the widgets and sets up listeners to some of those widgets
     */
    public void go(){
	theGui=this;
	frame = new JFrame();
	dealerTurn = false;
	playerTurn = 1;
	canPlayer1DD =true;
	canPlayer2DD =true;
	canPlayer3DD =true;
    canPlayer4DD =true;

    //create menubar
    menuBar = new JMenuBar();
    menuFile = new JMenu("File");
    menuEdit = new JMenu("Edit");
    menuView = new JMenu("View");
    menuHelp = new JMenu("Help");
    JMenuItem menuSave= new JMenuItem("Save");
    menuSave.addActionListener(new SaveListener());
    JMenuItem menuExit = new JMenuItem("Exit");
    menuExit.addActionListener(new ExitListener());
    JMenuItem menuRestart = new JMenuItem("Restart");
    menuRestart.addActionListener(new PlayAgainListener());
    JMenuItem menuRules= new JMenuItem("Rules");
    menuRules.addActionListener(new RulesListener());
    JMenuItem menuColors= new JMenuItem("Colors");
    JMenuItem menuNames = new JMenuItem("Player Names");
    menuNames.addActionListener(new ChangeNamesListener());
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
    frame.setJMenuBar(menuBar);

    
		
    
    // background music
    song.play();
    song.loop();

	// remove the bet amount from all of the players' total money
	updateMoney();
	
	// create dealer's label
	dealerPanel = new JPanel(); dealerLabel = new JLabel();
	dealerPanel.setOpaque(true);
	dealerPanel.setBackground(feltgreen);



	// create 1st player's label
	playerPanelS = new JPanel(); playerLabelS = new JLabel(p1Name);
	playerLabelSM = new JLabel("Money: $" + game.getPlayerS().getMoney());
	playerLabelSWinLoss = new JLabel("Wins/Losses: " + game.getPlayerS().getWins() + "/" + game.getPlayerS().getLosses());
	playerLabelSMWonLost = new JLabel("Money Won/Lost: " + game.getPlayerS().getMoneyWon() + "/" + game.getPlayerS().getMoneyLost());

	// create 2nd player's label
	playerPanelE = new JPanel(); playerLabelE = new JLabel(p2Name);
	playerLabelEM = new JLabel("Money: $" + game.getPlayerE().getMoney());
	playerLabelEWinLoss = new JLabel("Wins/Losses: " + game.getPlayerE().getWins() + "/" + game.getPlayerE().getLosses());
	playerLabelEMWonLost = new JLabel("Money Won/Lost: " + game.getPlayerE().getMoneyWon() + "/" + game.getPlayerE().getMoneyLost());

	// create 3rd player's label
	playerPanelW = new JPanel(); playerLabelW = new JLabel(p3Name);
	playerLabelWM = new JLabel("Money: $" + game.getPlayerW().getMoney());
	playerLabelWWinLoss = new JLabel("Wins/Losses: " + game.getPlayerW().getWins() + "/" + game.getPlayerW().getLosses());
	playerLabelWMWonLost = new JLabel("Money Won/Lost: " + game.getPlayerW().getMoneyWon() + "/" + game.getPlayerW().getMoneyLost());

	// create card displays for all players
	displayPanel = new JPanel(); displayLabel = new JLabel();
	displayPanel.setOpaque(true);
	displayPanel.setBackground(feltgreen);
	cardPanelE = new JPanel(); cardPanelW = new JPanel();
	cardPanelE.setOpaque(true);
	cardPanelE.setBackground(feltgreen);
	textPanel = new JPanel();
	textPanel.setOpaque(true);
	textPanel.setBackground(feltgreen);
	centerPanel = new JPanel();
	centerPanel.setOpaque(true);
	centerPanel.setBackground(feltgreen);
	displayLabel.setFont(new Font(displayLabel.getName(), Font.PLAIN, 20));
	
 
	dealerPanel.add(dealerLabel);
	dealerPanel.add(new JLabel(getMyImage(game.getDealer().getHand().getFirstCard())));
	URL myURL = getClass().getResource("/images/b1fv.gif");
	ImageIcon myImage = new ImageIcon(myURL);
	downCard = new JLabel(myImage);
	dealerPanel.add(downCard);
	dealerLabel.setText(game.displayDealerCardValue());

	// create the 'hit' and 'stay' buttons	
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
	
	split = new JButton("Split Hand");
	split.addActionListener(new SplitListener());
	displayPanel.add(split);
	
	
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
	cardsPanelS.setBackground(feltgreen);
	cardsPanelS.setAlignmentX(Component.CENTER_ALIGNMENT);
	cardsPanelS.setLayout(new BoxLayout(cardsPanelS, BoxLayout.X_AXIS));
	cardsPanelS.setBorder(BorderFactory.createEmptyBorder(10,100,10,10)); // keep cards aligned 
	cardsPanelS.add( new  JLabel(getMyImage(game.getPlayerS().getHand().getFirstCard())), BorderLayout.WEST); // add first card
	card2LabelS = new JLabel(getMyImage(game.getPlayerS().getHand().getSecondCard()));
	cardsPanelS.add(card2LabelS, BorderLayout.WEST); // add second card

	cardLabelS = new JLabel("Hand Value: " + game.getPlayerS().getHand().displayHandValue());	
	
	playerPanelS.setLayout(new BoxLayout(playerPanelS, BoxLayout.Y_AXIS));
	playerPanelS.setOpaque(true);
	playerPanelS.setBackground(feltgreen);
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
	cardsPanelE.setBackground(feltgreen);
	cardsPanelE.setAlignmentX(Component.CENTER_ALIGNMENT);
	cardsPanelE.setLayout(new BoxLayout(cardsPanelE, BoxLayout.X_AXIS));
	cardsPanelE.setBorder(BorderFactory.createEmptyBorder(10,100,10,10)); // keep cards aligned 
	cardsPanelE.add( new  JLabel(getMyImage(game.getPlayerE().getHand().getFirstCard())), BorderLayout.EAST); // add first card
	card2LabelE = new JLabel(getMyImage(game.getPlayerE().getHand().getSecondCard()));
	cardsPanelE.add(card2LabelE, BorderLayout.WEST); // add second card

	cardLabelE = new JLabel("Hand Value: " + game.getPlayerE().getHand().displayHandValue());	
	
	playerPanelE.setLayout(new BoxLayout(playerPanelE, BoxLayout.Y_AXIS));
	playerPanelE.setOpaque(true);
	playerPanelE.setBackground(feltgreen);
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
	cardsPanelW.setBackground(feltgreen);
	cardsPanelW.setAlignmentX(Component.CENTER_ALIGNMENT);
	cardsPanelW.setLayout(new BoxLayout(cardsPanelW, BoxLayout.X_AXIS));
	cardsPanelW.setBorder(BorderFactory.createEmptyBorder(10,100,10,10)); // keep cards aligned 
	cardsPanelW.add( new  JLabel(getMyImage(game.getPlayerW().getHand().getFirstCard())), BorderLayout.WEST); // add first card
	card2LabelW = new JLabel(getMyImage(game.getPlayerW().getHand().getSecondCard()));
	cardsPanelW.add(card2LabelW, BorderLayout.WEST); // add second card

	cardLabelW = new JLabel("Hand Value: " + game.getPlayerW().getHand().displayHandValue());	
	
	playerPanelW.setLayout(new BoxLayout(playerPanelW, BoxLayout.Y_AXIS));
	playerPanelW.setOpaque(true);
	playerPanelW.setBackground(feltgreen);
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
	frame.setSize(800,600);
	 frame.pack();

	setSplit();
	
	// This section is for a new round of Blackjack
	if(keepRunning == true){
	    if(numPlayers == 0) {
	    	song.stop();
	    	frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
	    }
	    if(numPlayers == 1){
		cardLabelS.setText(game.getPlayerS().displayHandValue());
		frame.remove(playerPanelE);
		frame.remove(playerPanelW);
		frame.setSize(600,600);
	    }
	    else if(numPlayers == 2){
		cardLabelS.setText(game.getPlayerS().displayHandValue());
		cardLabelE.setText(game.getPlayerE().displayHandValue());
		frame.remove(playerPanelW);
		frame.setSize(800,600);
	    }
	    else if(numPlayers == 3){
		cardLabelS.setText(game.getPlayerS().displayHandValue());
		cardLabelE.setText(game.getPlayerE().displayHandValue());
		cardLabelW.setText(game.getPlayerW().displayHandValue());
		frame.setSize(1000,600);
	    }
	    displayLabel.setText("New Round, " + p1Name + "'s turn");
	    frame.setLocationRelativeTo(null);
	    frame.getContentPane().setBackground(feltgreen);
	    frame.setVisible(true);
	}
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
    public void rules() {
	rulesFrame = new JFrame();
        rulesPanel = new JPanel(new GridLayout(2, 0, 5, 0));
        rulesLabel = new JLabel("<html><br>Blackjack win and loss conditions.. <br>Loss Conditions: <br>-Having a hand value of over 21 (BUST!) <br>-Having a hand value less than the dealer's hand value <br><br>Win Conditions: <br>-Get blackjack (hand value of 21) <br>-Have 5 cards without busting (5 card charlie! you still still lose if the dealer has blackjack) <br>-Have a hand value greater than the dealer's <br><br>Other Features:<br>-Double downing draws one card then moves onto the next player (deducts bet amount, chance to win double)<br>-Every player starts with $5,000<br><br>");

        rulesButton = new JButton("Play!");
        rulesButton.addActionListener(new CloseRulesListener());
        
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

    

    /** initializes the welcome widgets
     */
    public void welcome(){
	totalPotLabel = new JLabel();

	welcomeFrame = new JFrame();
	welcomePanel = new JPanel(new GridLayout(4, 0, 5, 0));
	welcomeLabel = new JLabel();
	
	JButton onePlayer = new JButton("1 player");
	JButton twoPlayers = new JButton("2 players");
	JButton threePlayers = new JButton("3 players");
	onePlayer.addActionListener(new WelcomeListener1());
	twoPlayers.addActionListener(new WelcomeListener2());
	threePlayers.addActionListener(new WelcomeListener3());
	
	welcomeLabel.setText("Welcome to Blackjack");
	welcomeLabel.setHorizontalAlignment(SwingConstants.CENTER);

	welcomePanel.add(welcomeLabel);
	welcomePanel.add(onePlayer);
	welcomePanel.add(twoPlayers);
	welcomePanel.add(threePlayers);
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
	    setDoubleDown();

	    if(!dealerTurn){
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
		    break;
		case 2:
		    playerLabelE.setText(p2Name + isBust);
		    cardLabelE.setText(game.getPlayerE().displayHandValue());
		    cardsPanelE.add(new JLabel(getMyImage(newCard)));
		    displayLabel.setText(p2Name + " hit!");
		    break;
		case 3:
		    playerLabelW.setText(p3Name + isBust);
		    cardLabelW.setText(game.getPlayerW().displayHandValue());
		    cardsPanelW.add(new JLabel(getMyImage(newCard)));
		    displayLabel.setText(p3Name + " hit!");
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

    
    /** Opens rules without Play button **/
    public class RulesListener implements ActionListener{
	public void actionPerformed(ActionEvent e)
	{
	    rulesFrame.setVisible(true);
	    rulesButton.setVisible(false);
	}

    }
    

    /** listender class for confirm name button 
     */
    public class ConfirmName implements ActionListener{
	/** initializes some of the JLabels for a set bet menu and brings up the main JFrame
	    @param event ActionEvent, set bet
	*/
	public void actionPerformed(ActionEvent event){	
	    // disable the previous window ('enter player name(s)')
	    nameFrame.setVisible(false);
	    
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
	    betText.addActionListener(new BetTextListener());

	    // create bet amount buttons and assign ActionListeners
	    betAmount1 = new JButton("$25");
	    betAmount2 = new JButton("$50");
	    betAmount3 = new JButton("$100");
	    betAmount4 = new JButton("$250");
	    betAmount5 = new JButton("$500");
	    betAmount1.addActionListener(new BetAmountListener1());
	    betAmount2.addActionListener(new BetAmountListener2());
	    betAmount3.addActionListener(new BetAmountListener3());
	    betAmount4.addActionListener(new BetAmountListener4());
	    betAmount5.addActionListener(new BetAmountListener5());

	    // create button to confirm selected bet amount
            JButton betButton = new JButton("Confirm Bet");
            betButton.addActionListener(new BeginGameListener());

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
	    betFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    betFrame.pack();
	    betFrame.setLocationRelativeTo(null); // center window
	    betFrame.setVisible(true);
	    
	}
    }


    /**listener class for bet text field allowing for custom user bets
     */

    public class BetTextListener implements ActionListener
    {
	/** changes bet text
            @param event ActionEvent, set bet
	*/
	public void actionPerformed(ActionEvent event){
	    betText.selectAll();
	    amountBet = Integer.parseInt(betText.getText());
	    betAmount.setText("$" + amountBet);
}
    }

    /** listener class for setting the bet to $25
     */
    public class BetAmountListener1 implements ActionListener{
        /** changes bet text
            @param event ActionEvent, set bet
	*/
        public void actionPerformed(ActionEvent event){
	    amountBet += 25;
	    betAmount.setText("$" + amountBet);

	}
    }

    /** listender class for setting the bet to $50
     */
    public class BetAmountListener2 implements ActionListener{
        /** changes bet text
            @param event ActionEvent, set bet
	*/
        public void actionPerformed(ActionEvent event){
            amountBet += 50;
            betAmount.setText("$" + amountBet);

        }
    }

    /** listender class for setting the bet to $100
     */
    public class BetAmountListener3 implements ActionListener{
        /** changes bet text
            @param event ActionEvent, set bet
	*/
        public void actionPerformed(ActionEvent event){
            amountBet += 100;
            betAmount.setText("$" + amountBet);

        }
    }

    /** listender class for setting the bet to $250
     */
    public class BetAmountListener4 implements ActionListener{
        /** changes bet text
            @param event ActionEvent, set bet
	*/
        public void actionPerformed(ActionEvent event){
            amountBet += 250;
            betAmount.setText("$" + amountBet);

        }
    }
    
    public class BetAmountListener5 implements ActionListener{
        /** changes bet text
            @param event ActionEvent, set bet
	*/
        public void actionPerformed(ActionEvent event){
            amountBet += 500;
            betAmount.setText("$" + amountBet);

        }
    }


    /** listener class for closing the rules window
     */
    public class CloseRulesListener implements ActionListener {
	/** closes the rules window
            @param event ActionEvent, play button (close rules)
	*/
	public void actionPerformed(ActionEvent event) {
	    rulesFrame.setVisible(false);
	    welcomeFrame.setVisible(true);
	}
    }

    /** listener class for player names change in menu bar **/
    public class ChangeNamesListener implements ActionListener{
	public void actionPerformed(ActionEvent e){
	    createNewNameFrame(numPlayers);
	}
       
    }

    /** creates name frame that sets player names
	@param num number of players
    **/
    public void createNewNameFrame(int num){
	nameFrame = new JFrame();
	namePanel = new JPanel();
	nameFrame.setSize(300,200);
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
	loadSave.addItemListener(new LoadListener());
	namePanel.add(loadSave);
	beginGame = new JButton("Confirm");
	beginGame.addActionListener(new ConfirmName());
	namePanel.add(beginGame);
	nameFrame.getContentPane().add(namePanel);
	nameFrame.setLocationRelativeTo(null); // center the window 
	nameFrame.setVisible(true);
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
	    createNewNameFrame(numPlayers);
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
	    createNewNameFrame(numPlayers);
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
            createNewNameFrame(numPlayers);
	}
    }
       

    public class LoadListener implements ItemListener {
    	public void itemStateChanged(ItemEvent e) {
    		if (e.getStateChange() == ItemEvent.SELECTED) {
    			load = true;
    		}
    		else {
    			game.resetStats();
    			load = false;
    		}
    	}
    }
    
    public class SaveListener implements ActionListener {
    	public void actionPerformed(ActionEvent event) {
    		game.saveStats(theGui);
    	}
    }
    
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
    
    /** listener class for beginGame button after entering player names
     */
    public class BeginGameListener implements ActionListener{
	/** adds names to players and starts game
	    @param event ActionEvent, begins the game
	*/
	public void actionPerformed(ActionEvent event){
	    betFrame.setVisible(false);
	    
	    switch(numPlayers) {
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
	    updateStats();
	    updateMoney();
	    
	    // switch statement gives players names and makes their cards visible
	    switch(numPlayers) {
	    case 1:
		playerLabelSM.setText("Money: $" + (game.getPlayerS().getMoney()));
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
		frame.setSize(1000,600);
	    }
	    displayLabel.setText(p1Name + "'s turn");

	    updateTotalPot(amountBet*(numPlayers+1));

	    frame.setLocationRelativeTo(null); // center window
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
	    song.stop();
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



