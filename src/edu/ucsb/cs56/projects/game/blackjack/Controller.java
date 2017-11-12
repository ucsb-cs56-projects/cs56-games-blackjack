<<<<<<< Updated upstream
=======
package edu.ucsb.cs56.projects.game.blackjack;

/**
 * Controller.java
 *
 * The Controller class for the Blackjack Game.
 * Contains main
 *
 * @author Ryan Lorica
 * @author Ryan Kirkpatrick
 * @version 11/7/17
 */

public class Controller
{

  /** Launches and runs the Blackjack game
   * @param args Array of String command line arguments
   */
  public static void main(String[] args)
  {
      BlackjackGui gui = new BlackjackGui();
      GuiController.main(args);
      gui.go();
      /*
      System.out.println( gui.controller.getNumPlayers() + "");
      gui.createNewNameFrame(gui.controller.getNumPlayers());
      gui.go();
      */
  }
}
>>>>>>> Stashed changes
