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
    MasterController game = new MasterController();
    game.run();
  }
}
