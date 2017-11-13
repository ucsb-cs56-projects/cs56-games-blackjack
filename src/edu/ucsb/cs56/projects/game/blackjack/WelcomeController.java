package edu.ucsb.cs56.projects.game.blackjack;

import java.awt.event.*;
import java.awt.*;

/**
 * WelcomeController.java
 *
 * The Controller class for the Welcome screen.
 *
 * @author Ryan Lorica
 * @author Ryan Kirkpatrick
 * @version 11/7/17
 */

public class WelcomeController
{
  BlackjackGui gui;

  public WelcomeController(BlackjackGui g)
  {
    gui = g;
  }

  public void run()
  {
    gui.welcome();
    attachActionListeners();
  }

  public void attachActionListeners()
  {
    gui.onePlayerButton.addActionListener(new WelcomeListener1());
    gui.twoPlayerButton.addActionListener(new WelcomeListener2());
    gui.threePlayerButton.addActionListener(new WelcomeListener3());
  }

  /** listener class for 1 player button
   *  @author ???
   *  @version 2016.11.9
   */
  public class WelcomeListener1 implements ActionListener
  {
    /** initializes the window for set bet amount and prepares for a 1 player game
    @param event ActionEvent, welcome 1 player
    */
    public void actionPerformed(ActionEvent event)
    {
      gui.numPlayers = 1;
      gui.welcomeFrame.setVisible(false);
      gui.stage++;
    }
  }

  /** listener class for 2 player button
   *  @author ???
   *  @version 2016.11.9
   */
  public class WelcomeListener2 implements ActionListener
  {
    /** initializes  the window for set bet amount and prepares for a 2 player game
    @param event ActionEvent, welcome 2 player
    */
    public void actionPerformed(ActionEvent event)
    {
      gui.numPlayers = 2;
      gui.welcomeFrame.setVisible(false);
      gui.stage++;
    }
  }

  /** listener class for 3 player button
   *  @author ???
   *  @version 2016.11.9
   */
  public class WelcomeListener3 implements ActionListener
  {
    /** initializes the window for set bet amount and prepares for a 3 player game
    @param event ActionEvent, welcome 3 player
    */
    public void actionPerformed(ActionEvent event)
    {
      gui.numPlayers = 3;
      gui.welcomeFrame.setVisible(false);
      gui.stage++;
    }
  }
}
