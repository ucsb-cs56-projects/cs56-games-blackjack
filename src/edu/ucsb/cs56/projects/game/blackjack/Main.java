package edu.ucsb.cs56.projects.game.blackjack;

/**
 * Main.java
 *
 * The Main class for the Blackjack Game.
 * Contains main
 *
 * @author Ryan Lorica
 * @author Ryan Kirkpatrick
 * @version 11/7/17
 */

public class Main
{

  /** Launches and runs the Blackjack game
   * @param args Array of String command line arguments
   */
  public static void main(String[] args)
  {
    BlackjackGui gui = new BlackjackGui();

    try{

      RulesController rc = new RulesController(gui);
      rc.run();
      while(gui.stage == 0) { Thread.sleep(10); }

      WelcomeController wc = new WelcomeController(gui);
      wc.run();
      while(gui.stage == 1) { Thread.sleep(10); }

      NameFrameController nfc = new NameFrameController(gui);
      nfc.run();
      while(gui.stage == 2) { Thread.sleep(10); }

      BetWindowController bwc = new BetWindowController(gui);
      bwc.run();
      while(gui.stage == 3) { Thread.sleep(10); }

    }
    catch(InterruptedException ex)
    {
      Thread.currentThread().interrupt();
    }

    TableController tc = new TableController(gui);
    tc.run();
  }
}
