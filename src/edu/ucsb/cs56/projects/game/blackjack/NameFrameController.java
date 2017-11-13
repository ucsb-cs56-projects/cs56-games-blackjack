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

public class NameFrameController{
  BlackjackGui gui;
  NameGui nameGui;

  public NameFrameController(BlackjackGui gui){
    this.gui = gui;
  }

  public void run(){
      nameGui = new NameGui(gui.numPlayers);
      nameGui.display();
      attachActionListeners();
  }

  public void attachActionListeners(){
    nameGui.loadSave.addItemListener(new LoadListener());
    nameGui.beginGame.addActionListener(new ConfirmName());
  }

  /** listener class for load button
   *  @author ???
   *  @version 2016.11.9
   */
  public class LoadListener implements ItemListener{
    /** prepares for load
    *  @param e ItemEvent
    */
    public void itemStateChanged(ItemEvent e){
      if (e.getStateChange() == ItemEvent.SELECTED){
        gui.load = true;
      }
      else{
        gui.game.resetStats();
        gui.load = false;
      }
    }
  }

  /** listender class for confirm name button
   *  @author ???
   *  @version 2016.11.9
   */
  public class ConfirmName implements ActionListener{
    /** initializes some of the JLabels for a set bet menu and brings up the main JFrame
    @param event ActionEvent, set bet
    */
    public void actionPerformed(ActionEvent event){
      // disable the previous window ('enter player name(s)')
      nameGui.nameFrame.setVisible(false);
      gui.p1Name = nameGui.player1Name.getText();
      gui.p2Name = nameGui.player2Name.getText();
      gui.p3Name = nameGui.player3Name.getText();
      gui.stage++;
    }
  }

}
