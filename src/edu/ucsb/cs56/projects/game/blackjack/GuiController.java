package edu.ucsb.cs56.projects.game.blackjack;

/**
 * GuiController.java
 *
 * The GuiController class for the Blackjack Game.
 * Contains GuiController
 *
 * @author Ryan Lorica
 * @author Ryan Kirkpatrick
 * @version 11/28/17
 */

public class GuiController{
    public GuiModel gm;

    GuiController(){
        gm = new GuiModel();
    }

  /** Launches and runs the Blackjack game
   */
  public void run(){
    try{

      RulesGuiController rc = new RulesGuiController(gm);
      rc.run();
      while(gm.getStage() == 0) { Thread.sleep(10); }

      WelcomeGuiController wc = new WelcomeGuiController(gm);
      wc.run();
      while(gm.getStage() == 1) { Thread.sleep(10); }

      NameGuiController nfc = new NameGuiController(gm);
      nfc.run();
      while(gm.getStage() == 2) { Thread.sleep(10); }

      BetGuiController bwc = new BetGuiController(gm);
      bwc.run();
      while(gm.getStage() == 3) { Thread.sleep(10); }

    }
    catch(InterruptedException ex)
    {
      Thread.currentThread().interrupt();
    }

    BlackjackGuiController bgc = new BlackjackGuiController(gm);
    bgc.run();
  }
}
