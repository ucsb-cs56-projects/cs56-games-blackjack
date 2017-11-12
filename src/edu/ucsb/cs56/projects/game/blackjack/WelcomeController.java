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
  WelcomeGui welcomeGui;

  public WelcomeController(BlackjackGui gui)
  {
    this.gui = gui;
  }

  public void run(){
    welcomeGui = new WelcomeGui();
    welcomeGui.display();
    attachActionListeners();
  }

  public void attachActionListeners()
  {
      welcomeGui.onePlayerButton.addActionListener(e -> gui.numPlayers = 1);
      welcomeGui.onePlayerButton.addActionListener(new closeAndIncrementStage());
      welcomeGui.twoPlayersButton.addActionListener(e -> gui.numPlayers = 2);
      welcomeGui.twoPlayersButton.addActionListener(new closeAndIncrementStage());
      welcomeGui.threePlayersButton.addActionListener(e -> gui.numPlayers = 3);
      welcomeGui.threePlayersButton.addActionListener(new closeAndIncrementStage());
  }

  public class closeAndIncrementStage implements ActionListener{

      public void actionPerformed(ActionEvent event)
      {
          welcomeGui.welcomeFrame.setVisible(false);
          gui.stage++;
      }
  }
  
}
