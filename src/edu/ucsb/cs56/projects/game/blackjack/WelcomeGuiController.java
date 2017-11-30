package edu.ucsb.cs56.projects.game.blackjack;

import java.awt.event.*;
import java.awt.*;

/**
 * WelcomeGuiController.java
 *
 * The Controller class for the Welcome screen.
 *
 * @author Ryan Lorica
 * @author Ryan Kirkpatrick
 * @version 11/28/17
 */

public class WelcomeGuiController
{
  GuiModel gm;
  WelcomeGui welcomeGui;

  public WelcomeGuiController(GuiModel gm){
    this.gm = gm;
  }

  public void run(){
    welcomeGui = new WelcomeGui();
    welcomeGui.display();
    attachActionListeners();
  }

  public void attachActionListeners(){
      welcomeGui.onePlayerButton.addActionListener(e -> gm.setNumPlayers(1));
      welcomeGui.onePlayerButton.addActionListener(new closeAndIncrementStage());
      welcomeGui.twoPlayersButton.addActionListener(e -> gm.setNumPlayers(2));
      welcomeGui.twoPlayersButton.addActionListener(new closeAndIncrementStage());
      welcomeGui.threePlayersButton.addActionListener(e -> gm.setNumPlayers(3));
      welcomeGui.threePlayersButton.addActionListener(new closeAndIncrementStage());
  }

  public class closeAndIncrementStage implements ActionListener{
      public void actionPerformed(ActionEvent event){
          welcomeGui.welcomeFrame.setVisible(false);
          gm.incrementStage();
      }
  }
}
