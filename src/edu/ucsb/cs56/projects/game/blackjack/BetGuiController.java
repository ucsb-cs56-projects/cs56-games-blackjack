package edu.ucsb.cs56.projects.game.blackjack;

import java.awt.event.*;
import java.awt.*;
import javax.swing.*;

/**
 * BetGuiController.java
 *
 * The Controller class for the BetWindow.
 *
 * @author Ryan Lorica
 * @author Ryan Kirkpatrick
 * @version 11/28/17
 */

public class BetGuiController{
  GuiModel gm;
  BetGui betGui;

  public BetGuiController(GuiModel gm){
    this.gm = gm;
  }

  public void run(){
      betGui = new BetGui();
      betGui.display();
      attachActionListeners();
  }

  public void attachActionListeners(){
    betGui.betText.addActionListener(new BetTextListener());

    betGui.betAmount1.addActionListener(new BetAmountListener1());
    betGui.betAmount2.addActionListener(new BetAmountListener2());
    betGui.betAmount3.addActionListener(new BetAmountListener3());
    betGui.betAmount4.addActionListener(new BetAmountListener4());
    betGui.betAmount5.addActionListener(new BetAmountListener5());

    betGui.betButton.addActionListener(new BeginGameListener());
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
      betGui.betText.selectAll();
      betGui.amountBet = Integer.parseInt(betGui.betText.getText());
      betGui.betAmountLabel.setText("$" + betGui.amountBet);
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
      betGui.amountBet += 25;
      betGui.betAmountLabel.setText("$" + betGui.amountBet);
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
      betGui.amountBet += 50;
      betGui.betAmountLabel.setText("$" + betGui.amountBet);
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
      betGui.amountBet += 100;
      betGui.betAmountLabel.setText("$" + betGui.amountBet);
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
      betGui.amountBet += 250;
      betGui.betAmountLabel.setText("$" + betGui.amountBet);
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
      betGui.amountBet += 500;
      betGui.betAmountLabel.setText("$" + betGui.amountBet);
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
    public void actionPerformed(ActionEvent event){
      betGui.betFrame.setVisible(false);
      gm.setBetAmount(betGui.amountBet);
      gm.incrementStage();
    }
  }
}
