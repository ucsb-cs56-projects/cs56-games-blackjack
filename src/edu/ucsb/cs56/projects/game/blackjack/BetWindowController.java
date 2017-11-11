package edu.ucsb.cs56.projects.game.blackjack;

import java.awt.event.*;
import java.awt.*;
import javax.swing.*;

/**
 * BetWindowController.java
 *
 * The Controller class for the BetWindow.
 *
 * @author Ryan Lorica
 * @author Ryan Kirkpatrick
 * @version 11/7/17
 */

public class BetWindowController
{
  BlackjackGui gui;

  public BetWindowController(BlackjackGui g)
  {
    gui = g;
  }

  public void run()
  {
    gui.createBetWindow(true);
    attachActionListeners();
  }

  public void attachActionListeners()
  {
    gui.betText.addActionListener(new BetTextListener());

    gui.betAmount1.addActionListener(new BetAmountListener1());
    gui.betAmount2.addActionListener(new BetAmountListener2());
    gui.betAmount3.addActionListener(new BetAmountListener3());
    gui.betAmount4.addActionListener(new BetAmountListener4());
    gui.betAmount5.addActionListener(new BetAmountListener5());

    gui.betButton.addActionListener(new BeginGameListener());
  }

  /** listener class for bet text field allowing for custom user bets
   *  @author ???
   *  @version 2016.11.9
   */
  public class BetTextListener implements ActionListener
  {
    /** changes bet text
    @param event ActionEvent, set bet
    */
    public void actionPerformed(ActionEvent event)
    {
      gui.betText.selectAll();
      gui.amountBet = Integer.parseInt(gui.betText.getText());
      gui.betAmount.setText("$" + gui.amountBet);
    }
  }

  /** listener class for setting the bet to $25
   *  @author ???
   *  @version 2016.11.9
   */
  public class BetAmountListener1 implements ActionListener
  {
    /** changes bet text
    @param event ActionEvent, set bet
    */
    public void actionPerformed(ActionEvent event)
    {
      gui.amountBet += 25;
      gui.betAmount.setText("$" + gui.amountBet);
    }
  }

  /** listender class for setting the bet to $50
   *  @author ???
   *  @version 2016.11.9
   */
  public class BetAmountListener2 implements ActionListener
  {
    /** changes bet text
    @param event ActionEvent, set bet
    */
    public void actionPerformed(ActionEvent event)
    {
      gui.amountBet += 50;
      gui.betAmount.setText("$" + gui.amountBet);
    }
  }

  /** listender class for setting the bet to $100
   *  @author ???
   *  @version 2016.11.9
   */
  public class BetAmountListener3 implements ActionListener
  {
    /** changes bet text
    @param event ActionEvent, set bet
    */
    public void actionPerformed(ActionEvent event)
    {
      gui.amountBet += 100;
      gui.betAmount.setText("$" + gui.amountBet);
    }
  }

  /** listender class for setting the bet to $250
   *  @author ???
   *  @version 2016.11.9
   */
  public class BetAmountListener4 implements ActionListener
  {
    /** changes bet text
    @param event ActionEvent, set bet
    */
    public void actionPerformed(ActionEvent event)
    {
      gui.amountBet += 250;
      gui.betAmount.setText("$" + gui.amountBet);
    }
  }

  /** listener class for setting the bet to $500
   *  @author ???
   *  @version 2016.11.9
   */
  public class BetAmountListener5 implements ActionListener
  {
    /** changes bet text
    @param event ActionEvent, set bet
    */
    public void actionPerformed(ActionEvent event)
    {
      gui.amountBet += 500;
      gui.betAmount.setText("$" + gui.amountBet);
    }
  }

  /** listener class for beginGame button after entering player names
   *  @author ???
   *  @version 2016.11.9
   */
  public class BeginGameListener implements ActionListener
  {
    /** adds names to players and starts game
    @param event ActionEvent, begins the game
    */
    public void actionPerformed(ActionEvent event)
    {
      gui.betFrame.setVisible(false);
      gui.go();
      
      switch(gui.numPlayers)
      {
        case 1:
        gui.game.getPlayerS().setName(gui.player1Name.getText());
        break;
        case 2:
        gui.game.getPlayerS().setName(gui.player1Name.getText());
        gui.game.getPlayerE().setName(gui.player2Name.getText());
        break;
        case 3:
        gui.game.getPlayerS().setName(gui.player1Name.getText());
        gui.game.getPlayerE().setName(gui.player2Name.getText());
        gui.game.getPlayerW().setName(gui.player3Name.getText());
        break;
      }

      if (gui.load) gui.game.loadStats(gui.theGui);

      // switch statement gives players names and makes their cards visible
      switch(gui.numPlayers)
      {
        case 1:
        gui.playerLabelSM.setText("Money: $" + gui.game.getPlayerS().getMoney());
        gui.playerLabelSWinLoss.setText("Wins/Losses: " + gui.game.getPlayerS().getWins() + "/" + gui.game.getPlayerS().getLosses());
        gui.playerLabelSMWonLost.setText("Money Won/Lost: " + gui.game.getPlayerS().getMoneyWon() + "/" + gui.game.getPlayerS().getMoneyLost());

        gui.p1Name = new String(gui.game.getPlayerS().getName());
        gui.playerLabelS.setText(gui.game.getPlayerS().displayHandValue());
        gui.playerLabelS.setText(gui.p1Name);
        gui.frame.remove(gui.playerPanelW);
        gui.frame.remove(gui.playerPanelE);
        break;
        case 2:
        gui.playerLabelSM.setText("Money: $" + (gui.game.getPlayerS().getMoney()));
        gui.playerLabelSWinLoss.setText("Wins/Losses: " + gui.game.getPlayerS().getWins() + "/" + gui.game.getPlayerS().getLosses());
        gui.playerLabelSMWonLost.setText("Money Won/Lost: " + gui.game.getPlayerS().getMoneyWon() + "/" + gui.game.getPlayerS().getMoneyLost());
        gui.playerLabelEM.setText("Money: $" + (gui.game.getPlayerE().getMoney()));
        gui.playerLabelEWinLoss.setText("Wins/Losses: " + gui.game.getPlayerE().getWins() + "/" + gui.game.getPlayerE().getLosses());
        gui.playerLabelEMWonLost.setText("Money Won/Lost: " + gui.game.getPlayerE().getMoneyWon() + "/" + gui.game.getPlayerE().getMoneyLost());

        gui.p1Name = new String(gui.game.getPlayerS().getName());
        gui.p2Name = new String(gui.game.getPlayerE().getName());
        gui.playerLabelS.setText(gui.game.getPlayerS().displayHandValue());
        gui.playerLabelE.setText(gui.game.getPlayerE().displayHandValue());
        gui.frame.remove(gui.playerPanelW);
        break;
        case 3:
        gui.playerLabelSM.setText("Money: $" + (gui.game.getPlayerS().getMoney()));
        gui.playerLabelSWinLoss.setText("Wins/Losses: " + gui.game.getPlayerS().getWins() + "/" + gui.game.getPlayerS().getLosses());
        gui.playerLabelSMWonLost.setText("Money Won/Lost: " + gui.game.getPlayerS().getMoneyWon() + "/" + gui.game.getPlayerS().getMoneyLost());
        gui.playerLabelEM.setText("Money: $" + (gui.game.getPlayerE().getMoney()));
        gui.playerLabelEWinLoss.setText("Wins/Losses: " + gui.game.getPlayerE().getWins() + "/" + gui.game.getPlayerE().getLosses());
        gui.playerLabelEMWonLost.setText("Money Won/Lost: " + gui.game.getPlayerE().getMoneyWon() + "/" + gui.game.getPlayerE().getMoneyLost());
        gui.playerLabelWM.setText("Money: $" + (gui.game.getPlayerW().getMoney()));
        gui.playerLabelWWinLoss.setText("Wins/Losses: " + gui.game.getPlayerW().getWins() + "/" + gui.game.getPlayerW().getLosses());
        gui.playerLabelWMWonLost.setText("Money Won/Lost: " + gui.game.getPlayerW().getMoneyWon() + "/" + gui.game.getPlayerW().getMoneyLost());

        gui.p1Name = new String(gui.game.getPlayerS().getName());
        gui.p2Name = new String(gui.game.getPlayerE().getName());
        gui.p3Name = new String(gui.game.getPlayerW().getName());
        gui.playerLabelS.setText(gui.game.getPlayerS().displayHandValue());
        gui.playerLabelE.setText(gui.game.getPlayerE().displayHandValue());
        gui.playerLabelW.setText(gui.game.getPlayerW().displayHandValue());
        //frame.setSize(1000,600);
        gui.frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
      }
      gui.displayLabel.setText(gui.p1Name + "'s turn");

      gui.updateTotalPot(gui.amountBet*(gui.numPlayers+1));

      gui.frame.setLocationRelativeTo(null); // center window
      //frame.setUndecorated(true);
      //frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
      gui.frame.setVisible(true);
      gui.stage++;
    }
  }
}
