package edu.ucsb.cs56.projects.game.blackjack;

/**
 * MasterController.java
 *
 * The MasterController class for the Blackjack Game.
 *
 * @author Ryan Lorica
 * @author Ryan Kirkpatrick
 * @version 11/7/17
 */

public class MasterController
{
  //Model model;
  BlackjackGui gui;

  public MasterController()
  {
    //this.model = new Model();
    this.gui = new BlackjackGui();
  }

  public void run()
  {
    this.gui.rules();
    this.gui.welcome();
    this.gui.go();
  }
}
