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
      gui.stage++;
    }
  }
}
