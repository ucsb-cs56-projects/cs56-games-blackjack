package edu.ucsb.cs56.projects.game.blackjack;

import java.awt.event.*;
import java.awt.*;
import javax.swing.*;
import javax.sound.sampled.*;

/**
 * BlackjackGuiController.java
 *
 * The Controller class for the Blackjack Table.
 *
 * @author Ryan Lorica
 * @author Ryan Kirkpatrick
 * @version 11/28/17
 */

public class BlackjackGuiController
{
  public BlackjackGui gui;
  public GuiModel gm;
  public MenuBarController mbc;

  public BlackjackGuiController(GuiModel gm){
    this.gui = new BlackjackGui(gm);
    this.gm = gm;
  }

  public void run(){
    attachMenuBar();
    gui.go();
    attachActionListeners();
  }
  public void restart(){
      gui.didPlayer1Split = false;
      gui.didPlayer2Split = false;
      gui.didPlayer3Split = false;
      gui.game.newRound();
      gui.keepRunning = true;
      run();
  }

  public void attachMenuBar(){
      mbc = new MenuBarController(this);
      mbc.run();
  }

  public void attachActionListeners()
  {
    gui.timer = new Timer(gui.speed, new MyTimerListener());
    gui.playAgain.addActionListener(new PlayAgainListener());
    gui.save.addActionListener(new SaveListener());
    gui.amountTextField.addActionListener(new AmountTextFieldListener());
    gui.hit.addActionListener(new HitListener());
    gui.stay.addActionListener(new StayListener());
    gui.exitE.addActionListener(new ExitEListener());
    gui.changeBetE.addActionListener(new ChangeBetListener());
    gui.addMoneyE.addActionListener(new AddMoneyEListener());
    gui.exitW.addActionListener(new ExitWListener());
    gui.changeBetW.addActionListener(new ChangeBetListener());
    gui.addMoneyW.addActionListener(new AddMoneyWListener());
    gui.exitS.addActionListener(new ExitSListener());
  	gui.changeBetS.addActionListener(new ChangeBetListener());
  	gui.addMoneyS.addActionListener(new AddMoneySListener());
    gui.resumeGame.addActionListener(new ConfirmAddMoney());
    gui.dd.addActionListener(new ddListener());
    gui.split.addActionListener(new SplitListener());
  }

  /** split listener
   *  @author David Tsu
   *  @author Marco Chavez
   *  @version 2016.11.9
   */
  public class SplitListener implements ActionListener
  {
    public void actionPerformed(ActionEvent e)
    {
      gui.game.splitHand(gui.playerTurn);
      gui.splitHand(gui.playerTurn);
      gui.split.setVisible(false);
    }
  }

  /** listener class for the double down button
   *  @author ???
   *  @version 2016.11.9
   */
  public class ddListener implements ActionListener
  {
    /** does nothing if it is not the player's turn
    *  otherwise makes the player draw one card and then goes to the next player's turn
    @param event ActionEvent, Player doubles down
    */
    public void actionPerformed(ActionEvent event)
    {
      if(!gui.dealerTurn)
      {
        //update pot and deduct player's money on hand
        gui.updateTotalPot(gui.amountBet);
        gui.updateMoney(gui.playerTurn);

        //adds a new card to the players hand
        Card newCard = gui.game.playerHit(gui.game.getPlayer(gui.playerTurn));
        //makes a string for whether or not the player busts,
        //to later append to their label
        String isBust = !gui.game.getPlayer(gui.playerTurn).isNotBust() ? " went bust!" : "";
        switch(gui.playerTurn)
        {
          case 1:
          gui.playerLabelS.setText(gui.p1Name + isBust);
          gui.cardLabelS.setText(gui.game.getPlayerS().displayHandValue());
          gui.cardsPanelS.add(new JLabel(gui.getMyImage(newCard)));
          gui.displayLabel.setText(gui.p1Name + " hit!");
          gui.game.getPlayerS().setDD(gui.amountBet);
          break;
          case 2:
          gui.playerLabelE.setText(gui.p2Name + isBust);
          gui.cardLabelE.setText(gui.game.getPlayerE().displayHandValue());
          gui.cardsPanelE.add(new JLabel(gui.getMyImage(newCard)));
          gui.displayLabel.setText(gui.p2Name + " hit!");
          gui.game.getPlayerE().setDD(gui.amountBet);
          break;
          case 3:
          gui.playerLabelW.setText(gui.p3Name + isBust);
          gui.cardLabelW.setText(gui.game.getPlayerW().displayHandValue());
          gui.cardsPanelW.add(new JLabel(gui.getMyImage(newCard)));
          gui.displayLabel.setText(gui.p3Name + " hit!");
          gui.game.getPlayerW().setDD(gui.amountBet);
          break;
          default:
          break;
        }
        //if player busts, either continue on to next player's turn or dealer's turn
        if(!gui.game.getPlayer(gui.playerTurn).isNotBust())
        {
          if(gui.playerTurn < gui.numPlayers)
          gui.nextPlayersTurn();
          else
          gui.startDealerTurn();
        }
        // if not bust
        else if(gui.playerTurn < gui.numPlayers)
        {
          gui.playerTurn++;
          gui.displayLabel.setText(gui.game.getPlayer(gui.playerTurn).getName() + "'s turn");
        }
        // if dealer's turn
        else
        {
          gui.displayLabel.setText("Dealer's Turn");
          gui.startDealerTurn();
        }
      }
    }
  }


  /** AddMoneyWListener
   *  @author David Tsu
   *  @author Marco Chavez
   *  @version 2016.11.9
   */
  public class AddMoneyWListener implements ActionListener
  {
    public void actionPerformed(ActionEvent e)
    {
      gui.createAddMoneyFrame(3);
    }
  }

  /** AddMoneySListener
   *  @author David Tsu
   *  @author Marco Chavez
   *  @version 2016.11.9
   */
  public class AddMoneySListener implements ActionListener
  {
    public void actionPerformed(ActionEvent e)
    {
      gui.createAddMoneyFrame(1);
    }
  }

  /** AddMoneyEListener
   *  @author David Tsu
   *  @author Marco Chavez
   *  @version 2016.11.9
   */
  public class AddMoneyEListener implements ActionListener
  {
    public void actionPerformed(ActionEvent e)
    {
      gui.createAddMoneyFrame(2);
    }
  }

  /** listener class for changebet
   *  @author David Tsu
   *  @author Marco Chavez
   *  @version 2016.11.9
   */
  public class ChangeBetListener implements ActionListener{
    public void actionPerformed(ActionEvent e){
      BetGuiController bc = new BetGuiController(gm);
      bc.run();
    }
  }

  /** listener class for the hit button
   */
  public class HitListener implements ActionListener
  {
    /** does nothing if it is not the player's turn
    *   otherwise makes the player hit and checks if the player went bust.
    *   If player busts, it goes to next player's turn or the dealer's turn.
    @param event ActionEvent, player's action
    */
    public void actionPerformed(ActionEvent event){

      gui.setDoubleDown();

      if(!gui.dealerTurn)
      {
        //adds a new card to the players hand
        Card newCard = gui.game.playerHit(gui.game.getPlayer(gui.playerTurn));
        //makes a string for whether or not the player busts,
        //to later append to their label
        String isBust = !gui.game.getPlayer(gui.playerTurn).isNotBust() ? " went bust!" : "";
        gui.split.setVisible(false);
        switch(gui.playerTurn)
        {
          case 1:
          gui.playerLabelS.setText(gui.game.getPlayerX(0).getName() + isBust);
          gui.cardLabelS.setText(gui.game.getPlayerS().displayHandValue());
          gui.cardsPanelS.add(new JLabel(gui.getMyImage(newCard)));
          gui.displayLabel.setText(gui.game.getPlayerX(0).getName() + " hit!");
          gui.cardEffect.play();
          break;
          case 2:
          gui.playerLabelE.setText(gui.game.getPlayerX(1).getName() + isBust);
          gui.cardLabelE.setText(gui.game.getPlayerE().displayHandValue());
          gui.cardsPanelE.add(new JLabel(gui.getMyImage(newCard)));
          gui.displayLabel.setText(gui.game.getPlayerX(1).getName() + " hit!");
          gui.cardEffect.play();
          break;
          case 3:
          gui.playerLabelW.setText(gui.game.getPlayerX(2).getName() + isBust);
          gui.cardLabelW.setText(gui.game.getPlayerW().displayHandValue());
          gui.cardsPanelW.add(new JLabel(gui.getMyImage(newCard)));
          gui.displayLabel.setText(gui.game.getPlayerX(2).getName() + " hit!");
          gui.cardEffect.play();
          break;
          default:
          break;
        }
        //if player busts, either continue on to next player's turn or dealer's turn
        if(!gui.game.getPlayer(gui.playerTurn).isNotBust())
        {
          if(gui.playerTurn < gui.numPlayers)
          gui.nextPlayersTurn();
          else
          gui.startDealerTurn();
        }
      }
    }
  }

  /** listener class for confirm addMoney button
   *  @author David Tsu
   *  @author Marco Chavez
   *  @version 2016.11.9
   */
  public class ConfirmAddMoney implements ActionListener
  {
    /** initializes some of the JLabels for a set bet menu and brings up the main JFrame
    @param event ActionEvent, set bet
    */
    public void actionPerformed(ActionEvent event)
    {
      // disable the previous window ('enter player name(s)')
      gui.addMoneyFrame.setVisible(false);
    }
  }

  /** listener class for the stay button
   *  @author ???
   *  @version 2016.11.9
   */
  public class StayListener implements ActionListener
  {
    /** does nothing if it is not the player's turn
    *  otherwise makes the player stay and starts the dealer's turn
    @param event ActionEvent, Player stays
    */
    public void actionPerformed(ActionEvent event)
    {
      if(!gui.dealerTurn)
      {
        if(gui.playerTurn < gui.numPlayers)
        {
          gui.playerTurn++;
          gui.displayLabel.setText(gui.game.getPlayer(gui.playerTurn).getName() + "'s turn");
          gui.setSplit();
        }
        else
        {
          gui.displayLabel.setText("Dealer's Turn");
          gui.startDealerTurn();
        }
      }
      gui.setDoubleDown();
    }
  }

  /** AmountTextFieldListener
   *  @author David Tsu
   *  @author Marco Chavez
   *  @version 2016.11.9
   */
  public class AmountTextFieldListener implements ActionListener
  {
    public void actionPerformed(ActionEvent e)
    {
      int moneyToAdd = Integer.parseInt(gui.amountTextField.getText());
      int currentMoneyToDisplay = gui.currentMoneyInt + moneyToAdd;

      gui.currentMoney.setText("Your current amount of Money:" + currentMoneyToDisplay);
    }
  }

  /** ExitSListener, listens for bottom player's exit
   *  @author David Tsu
   *  @author Marco Chavez
   *  @version 2016.11.9
   */
  public class ExitSListener implements ActionListener
  {
    public void actionPerformed(ActionEvent e)
    {
      if (gui.numPlayers == 1) gui.frame.dispatchEvent(new WindowEvent(gui.frame, WindowEvent.WINDOW_CLOSING));
      if (gui.numPlayers == 3)
      {
        gui.shift = true;
        gui.playerLeaves(0);
        gui.p1Name = gui.p2Name;
        gui.playerLeaves(1);
        gui.p2Name = gui.p3Name;
      }
      else if (gui.numPlayers == 2)
      {
        gui.playerLeaves(0);
        gui.p1Name = gui.p2Name;
      }
      gui.numPlayers--;
      JButton exit = (JButton)e.getSource();
      exit.removeActionListener(this);
    }
  }

  /** ExitEListener, listens for right player's exit
   *  @author David Tsu
   *  @author Marco Chavez
   *  @version 2016.11.9
   */
  public class ExitEListener implements ActionListener
  {
    public void actionPerformed(ActionEvent e)
    {
      if (gui.numPlayers == 1)
      {
        gui.frame.dispatchEvent(new WindowEvent(gui.frame, WindowEvent.WINDOW_CLOSING));
      }
      else if (gui.numPlayers == 3)
      {
        gui.playerLeaves(1);
        gui.p2Name = gui.p3Name;
      }
      else if (gui.numPlayers == 2)
      {
        if (gui.shift == true)
        {
          gui.playerLeaves(0);
          gui.p1Name = gui.p2Name;
        }
      }
      gui.numPlayers--;
      JButton exit = (JButton)e.getSource();
      exit.removeActionListener(this);
    }
  }

  /** ExitWListener, listens for left player's exit
   *  @author David Tsu
   *  @author Marco Chavez
   *  @version 2016.11.9
   */
  public class ExitWListener implements ActionListener
  {
    public void actionPerformed(ActionEvent e)
    {
      if (gui.numPlayers == 1)
      {
        gui.frame.dispatchEvent(new WindowEvent(gui.frame, WindowEvent.WINDOW_CLOSING));
      }
      gui.numPlayers--;
      JButton exit = (JButton)e.getSource();
      exit.removeActionListener(this);
    }
  }

  /** listener class for save button
   *  @author ???
   *  @version 2016.11.9
   */
  public class SaveListener implements ActionListener
  {
    /** prepares for save
    *  @param event ActionEvent
    */
    public void actionPerformed(ActionEvent event)
    {
      gui.game.saveStats(gui);
    }
  }


  /** listener class for playAgain button
   *  @author ???
   *  @version 2016.11.9
   */
  public class PlayAgainListener implements ActionListener
  {
    /** starts a new game
    @param event ActionEvent, play again
    */
    public void actionPerformed(ActionEvent event)
    {
      restart();
    }
  }

  /** listener class for timer
   *  @author ???
   *  @author David Tsu
   *  @author Marco Chavez
   *  @version 2016.11.9
   */
  class MyTimerListener implements ActionListener
  {

    /**logic for dealer's turn, calls getWinner() at the end
    @param event ActionEvent, timer
    */
    String winners = new String("");

    public void actionPerformed(ActionEvent event)
    {
      if(gui.numPlayers == 1 && gui.game.getPlayerS().isNotBust() == false)
      {
        gui.timer.stop();
        gui.dealerLabel.setText("Dealer wins");
        gui.getWinner();
        gui.drawEndScreen();
        return;
      }
      else if (gui.numPlayers == 2
        && gui.game.getPlayerS().isNotBust() == false
        && gui.game.getPlayerE().isNotBust() == false)
      {
        gui.timer.stop();
        gui.dealerLabel.setText("Dealer wins");
        gui.getWinner();
        gui.drawEndScreen();
        return;
      }
      else if(gui.numPlayers == 3
        && gui.game.getPlayerS().isNotBust() == false
        && gui.game.getPlayerE().isNotBust() == false
        && gui.game.getPlayerW().isNotBust() == false)
      {
        gui.timer.stop();
        gui.dealerLabel.setText("Dealer wins");
        gui.getWinner();
        gui.drawEndScreen();
        return;
      }
      if(gui.game.dealerHasBlackjack())
      {
        gui.timer.stop();
        gui.dealerLabel.setText("Dealer has Blackjack");
        gui.getWinner();
        gui.drawEndScreen();
        return;
      }
      gui.dealerLabel.setText(gui.game.getDealer().displayHandValue());

      if(gui.game.dealerShouldStay())
      {
        gui.timer.stop();
        if(gui.numPlayers == 1)
        {
          boolean PlayerWon = gui.game.evaluateWinner(gui.game.getPlayerS());
          if(PlayerWon) winners = gui.game.getPlayerS().getName() + " won";
        }
        if(gui.numPlayers == 2)
        {
          boolean Player1Won = gui.game.evaluateWinner(gui.game.getPlayerS());
          boolean Player2Won = gui.game.evaluateWinner(gui.game.getPlayerE());
          if(Player1Won && Player2Won) winners = gui.game.getPlayerS().getName() + " and " + gui.game.getPlayerE().getName() + " won";
          if(Player1Won && !Player2Won) winners = gui.game.getPlayerS().getName() + " won";
          if(!Player1Won && Player2Won) winners = gui.game.getPlayerE().getName() + " won";
        }
        if(gui.numPlayers == 3)
        {
          boolean Player1Won = gui.game.evaluateWinner(gui.game.getPlayerS());
          boolean Player2Won = gui.game.evaluateWinner(gui.game.getPlayerE());
          boolean Player3Won = gui.game.evaluateWinner(gui.game.getPlayerW());
          //all 3 win
          if(Player1Won && Player2Won && Player3Won) winners = gui.game.getPlayerS().getName() + ", " + gui.game.getPlayerE().getName() + ", and " + gui.game.getPlayerW().getName() + " won";
          //p1, p2 win
          if(Player1Won && Player2Won && !Player3Won) winners = gui.game.getPlayerS().getName() + " and " + gui.game.getPlayerE().getName() + " won";
          //p1, p3 win
          if(Player1Won && !Player2Won && Player3Won) winners = gui.game.getPlayerS().getName() + " and " + gui.game.getPlayerW().getName() + " won";
          //p2, p3 win
          if(!Player1Won && Player2Won && Player3Won) winners = gui.game.getPlayerE().getName() + " and " + gui.game.getPlayerW().getName() + " won";
          //p1 win
          if(Player1Won && !Player2Won && !Player3Won) winners = gui.game.getPlayerS().getName() + " won";
          //p2 win
          if(!Player1Won && Player2Won && !Player3Won) winners = gui.game.getPlayerE().getName() + " won";
          //p3 win
          if(!Player1Won && !Player2Won && Player3Won) winners = gui.game.getPlayerW().getName() + " won";
        }
        gui.displayLabel.setText(winners);
        gui.getWinner();
        gui.drawEndScreen();
        return;
      }
      else
      {
        gui.displayCard = gui.game.dealerHit();
        gui.cardEffect.play();
        gui.dealerPanel.add(new JLabel(gui.getMyImage(gui.displayCard)));
      }
      if(gui.game.dealerNotBust())
      {
        gui.timer.restart();
        gui.dealerLabel.setText(gui.game.getDealer().displayHandValue());
      }
      else
      {
        gui.dealerLabel.setText(gui.game.getDealer().displayHandValue());
        gui.dealerLabel.setText(gui.dealerLabel.getText() + " Dealer went bust");
        if(gui.numPlayers == 1)
        {
          boolean PlayerWon = gui.game.evaluateWinner(gui.game.getPlayerS());
          if(PlayerWon) winners = gui.game.getPlayerS().getName() + " won";
        }
        if(gui.numPlayers == 2)
        {
          boolean Player1Won = gui.game.evaluateWinner(gui.game.getPlayerS());
          boolean Player2Won = gui.game.evaluateWinner(gui.game.getPlayerE());
          if(Player1Won && Player2Won) winners = gui.game.getPlayerS().getName() + " and " + gui.game.getPlayerE().getName() + " won";
          if(Player1Won && !Player2Won) winners = gui.game.getPlayerS().getName() + " won";
          if(!Player1Won && Player2Won) winners = gui.game.getPlayerE().getName() + " won";
        }
        if(gui.numPlayers == 3)
        {
          boolean Player1Won = gui.game.evaluateWinner(gui.game.getPlayerS());
          boolean Player2Won = gui.game.evaluateWinner(gui.game.getPlayerE());
          boolean Player3Won = gui.game.evaluateWinner(gui.game.getPlayerW());
          //all 3 win
          if(Player1Won && Player2Won && Player3Won) winners = gui.game.getPlayerS().getName() + ", " + gui.game.getPlayerE().getName() + ", and " + gui.game.getPlayerW().getName() + " won";
          //p1, p2 win
          if(Player1Won && Player2Won && !Player3Won) winners = gui.game.getPlayerS().getName() + " and " + gui.game.getPlayerE().getName() + " won";
          //p1, p3 win
          if(Player1Won && !Player2Won && Player3Won) winners = gui.game.getPlayerS().getName() + " and " + gui.game.getPlayerW().getName() + " won";
          //p2, p3 win
          if(!Player1Won && Player2Won && Player3Won) winners = gui.game.getPlayerE().getName() + " and " + gui.game.getPlayerW().getName() + " won";
          //p1 win
          if(Player1Won && !Player2Won && !Player3Won) winners = gui.game.getPlayerS().getName() + " won";
          //p2 win
          if(!Player1Won && Player2Won && !Player3Won) winners = gui.game.getPlayerE().getName() + " won";
          //p3 win
          if(!Player1Won && !Player2Won && Player3Won) winners = gui.game.getPlayerW().getName() + " won";
        }
        gui.displayLabel.setText(winners);
        gui.timer.stop();
        gui.getWinner();
        gui.drawEndScreen();
        return;
      }
    }
  }
}
