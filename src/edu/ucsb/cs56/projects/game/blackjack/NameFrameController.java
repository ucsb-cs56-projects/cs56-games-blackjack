package edu.ucsb.cs56.projects.game.blackjack;

import java.awt.event.*;
import java.awt.*;

/**
 * NameFrameController.java
 *
 * The Controller class for the NameFrame.
 *
 * @author Ryan Lorica
 * @author Ryan Kirkpatrick
 * @version 11/7/17
 */

public class NameFrameController
{
  BlackjackGui gui;

  public NameFrameController(BlackjackGui g)
  {
    gui = g;
  }

  public void run()
  {
    gui.createNewNameFrame(gui.numPlayers);
    attachActionListeners();
  }

  public void attachActionListeners()
  {
    gui.loadSave.addItemListener(new LoadListener());
    gui.beginGame.addActionListener(new ConfirmName());
  }

  /** listener class for load button
   *  @author ???
   *  @version 2016.11.9
   */
  public class LoadListener implements ItemListener
  {
    /** prepares for load
    *  @param e ItemEvent
    */
    public void itemStateChanged(ItemEvent e)
    {
      if (e.getStateChange() == ItemEvent.SELECTED)
      {
        gui.load = true;
      }
      else
      {
        gui.game.resetStats();
        gui.load = false;
      }
    }
  }

  /** listender class for confirm name button
   *  @author ???
   *  @version 2016.11.9
   */
  public class ConfirmName implements ActionListener
  {
    /** initializes some of the JLabels for a set bet menu and brings up the main JFrame
    @param event ActionEvent, set bet
    */
    public void actionPerformed(ActionEvent event)
    {
      // disable the previous window ('enter player name(s)')
      gui.nameFrame.setVisible(false);
      gui.stage++;
    }
  }

}
