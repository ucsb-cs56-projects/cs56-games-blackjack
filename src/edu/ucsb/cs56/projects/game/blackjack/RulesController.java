package edu.ucsb.cs56.projects.game.blackjack;

import java.awt.event.*;
import java.awt.*;

/**
 * RulesController.java
 *
 * The Controller class for the Rules screen.
 *
 * @author Ryan Lorica
 * @author Ryan Kirkpatrick
 * @version 11/7/17
 */

public class RulesController
{
  public BlackjackGui gui;

  public RulesController(BlackjackGui g)
  {
    gui = g;
  }

  public void run()
  {
    gui.rules();
    attachActionListeners();
  }

  public void attachActionListeners()
  {
    gui.rulesButton.addActionListener(new CloseRulesListener());
  }

  /** listener class for closing the rules window
   *  @author ???
   *  @version 2016.11.9
   */
  public class CloseRulesListener implements ActionListener
  {
    /** closes the rules window
    @param event ActionEvent, play button (close rules)
    */
    public void actionPerformed(ActionEvent event)
    {
      gui.rulesFrame.setVisible(false);
      gui.welcomeFrame.setVisible(true);
    }
  }
}
