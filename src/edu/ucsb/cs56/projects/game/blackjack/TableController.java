package edu.ucsb.cs56.projects.game.blackjack;

import java.awt.event.*;
import java.awt.*;
import javax.swing.*;
import javax.sound.sampled.*;

/**
 * TableController.java
 *
 * The Controller class for the Blackjack Table.
 *
 * @author Ryan Lorica
 * @author Ryan Kirkpatrick
 * @version 11/7/17
 */

public class TableController
{
  public BlackjackGui gui;

  public TableController(BlackjackGui g)
  {
    gui = g;
  }

  public void run()
  {
    attachActionListeners();
  }

  public void attachActionListeners()
  {
    gui.timer = new Timer(gui.speed, new MyTimerListener());
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
        return;
      }
      else if (gui.numPlayers == 2
        && gui.game.getPlayerS().isNotBust() == false
        && gui.game.getPlayerE().isNotBust() == false)
      {
        gui.timer.stop();
        gui.dealerLabel.setText("Dealer wins");
        gui.getWinner();
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
        return;
      }
      if(gui.game.dealerHasBlackjack())
      {
        gui.timer.stop();
        gui.dealerLabel.setText("Dealer has Blackjack");
        gui.getWinner();
        return;
      }
      gui.dealerLabel.setText(gui.game.getDealer().displayHandValue());

      if(gui.game.dealerShouldStay())
      {
        gui.timer.stop();
        if(gui.numPlayers == 1)
        {
          boolean PlayerWon = gui.game.evaluateWinner(gui.game.getPlayerS()) == 'P';
          if(PlayerWon) winners = gui.game.getPlayerS().getName() + " won";
        }
        if(gui.numPlayers == 2)
        {
          boolean Player1Won = gui.game.evaluateWinner(gui.game.getPlayerS()) == 'P';
          boolean Player2Won = gui.game.evaluateWinner(gui.game.getPlayerE()) == 'P';
          if(Player1Won && Player2Won) winners = gui.game.getPlayerS().getName() + " and " + gui.game.getPlayerE().getName() + " won";
          if(Player1Won && !Player2Won) winners = gui.game.getPlayerS().getName() + " won";
          if(!Player1Won && Player2Won) winners = gui.game.getPlayerE().getName() + " won";
        }
        if(gui.numPlayers == 3)
        {
          boolean Player1Won = gui.game.evaluateWinner(gui.game.getPlayerS()) == 'P';
          boolean Player2Won = gui.game.evaluateWinner(gui.game.getPlayerE()) == 'P';
          boolean Player3Won = gui.game.evaluateWinner(gui.game.getPlayerW()) == 'P';
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
          boolean PlayerWon = gui.game.evaluateWinner(gui.game.getPlayerS()) == 'P';
          if(PlayerWon) winners = gui.game.getPlayerS().getName() + " won";
        }
        if(gui.numPlayers == 2)
        {
          boolean Player1Won = gui.game.evaluateWinner(gui.game.getPlayerS()) == 'P';
          boolean Player2Won = gui.game.evaluateWinner(gui.game.getPlayerE()) == 'P';
          if(Player1Won && Player2Won) winners = gui.game.getPlayerS().getName() + " and " + gui.game.getPlayerE().getName() + " won";
          if(Player1Won && !Player2Won) winners = gui.game.getPlayerS().getName() + " won";
          if(!Player1Won && Player2Won) winners = gui.game.getPlayerE().getName() + " won";
        }
        if(gui.numPlayers == 3)
        {
          boolean Player1Won = gui.game.evaluateWinner(gui.game.getPlayerS()) == 'P';
          boolean Player2Won = gui.game.evaluateWinner(gui.game.getPlayerE()) == 'P';
          boolean Player3Won = gui.game.evaluateWinner(gui.game.getPlayerW()) == 'P';
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
        return;
      }
    }
  }
}
