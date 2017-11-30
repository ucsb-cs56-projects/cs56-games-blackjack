package edu.ucsb.cs56.projects.game.blackjack;

import java.awt.event.*;
import java.awt.*;

/**
 * MenuBarController.java
 *
 * The Controller class for the menubar.
 *
 * @author Ryan Lorica
 * @author Ryan Kirkpatrick
 * @version 11/28/17
 */

public class MenuBarController{
  GuiModel gm;
  BlackjackGuiController bgc;
  BlackjackGui gui;
  MenuBar menuBar;

  public MenuBarController(BlackjackGuiController bgc){
    this.bgc = bgc;
    this.gm = bgc.gm;
    this.gui = bgc.gui;
  }

  public void run(){
    menuBar = new MenuBar();
    menuBar.display();
    gui.frame.setJMenuBar(menuBar.menuBar);
    attachActionListeners();
  }

  public void attachActionListeners(){

      menuBar.menuSave.addActionListener(new SaveListener());
      menuBar.menuExit.addActionListener(new ExitListener());
      menuBar.menuRestart.addActionListener(new PlayAgainListener());
      menuBar.menuRules.addActionListener(new RulesListener());
      menuBar.menuNames.addActionListener(new ChangeNamesListener());

      menuBar.colorNavy.addActionListener(new NavyActionListener());
      menuBar.colorGray.addActionListener(new GrayActionListener());
      menuBar.colorMaroon.addActionListener(new MaroonActionListener());
      menuBar.colorFeltGreen.addActionListener(new FeltGreenActionListener());

      menuBar.songPause.addActionListener(new PauseMusicListener());
      menuBar.songPlay.addActionListener(new PlayMusicListener());
  }

  /** listener class for save button
   *  @author ???
   *  @version 2016.11.9
   */
  public class SaveListener implements ActionListener{
    /** prepares for save
    *  @param event ActionEvent
    */
    public void actionPerformed(ActionEvent event){
      gui.game.saveStats(gui);
    }
  }


  /** ExitListener, listens for general game exit
   *  @author David Tsu
   *  @author Marco Chavez
   *  @version 2016.11.9
   */
  public class ExitListener implements ActionListener
  {
    public void actionPerformed(ActionEvent e)
    {
      System.exit(0);
    }
  }

  /** listener class for playAgain button
   *  @author ???
   *  @version 2016.11.9
   */
  public class PlayAgainListener implements ActionListener{
    /** starts a new game
    @param event ActionEvent, play again
    */
    public void actionPerformed(ActionEvent event){
      bgc.restart();
    }
  }

    /** Opens rules without Play button
     *  @author ???
     *  @version 2016.11.9
     */
    public class RulesListener implements ActionListener{
        public void actionPerformed(ActionEvent e){
            RulesGuiController rc = new RulesGuiController(gm);
            rc.run();
            rc.rulesGui.rulesButton.setVisible(false);
      }
    }

    // TODO Fix this listener
    /** listener class for player names change in menu bar
     *  @author ???
     *  @version 2016.11.9
     */
     public class ChangeNamesListener implements ActionListener{
         public void actionPerformed(ActionEvent e){
             NameGuiController nc = new NameGuiController(gm);
             nc.run();
         }
     }
     /** listener class for Navy color change
      *  @author ???
      *  @version 2016.11.9
      */
     public class NavyActionListener implements ActionListener
     {
       public void actionPerformed(ActionEvent e)
       {
         changeColor(gui.navy);
       }
     }

     /** listener class for Maroon color change
      *  @author ???
      *  @version 2016.11.9
      */
     public class MaroonActionListener implements ActionListener
     {
       public void actionPerformed(ActionEvent e)
       {
         changeColor(gui.maroon);
       }
     }

     /** listener class for Gray color change
      *  @author ???
      *  @version 2016.11.9
      */
     public class GrayActionListener implements ActionListener
     {
       public void actionPerformed(ActionEvent e)
       {
         changeColor(gui.gray);
       }
     }

     /** listener class for FeltGreen color change
      *  @author Marco Chavez
      *  @version 2016.11.9
      */
     public class FeltGreenActionListener implements ActionListener
     {
       public void actionPerformed(ActionEvent e)
       {
         changeColor(gui.feltgreen);
       }
     }

     public void changeColor(Color color)
     {
       gui.currentColor = color;
       gui.dealerPanel.setBackground(color);
       gui.displayPanel.setBackground(color);
       gui.cardsPanelE.setBackground(color);
       gui.cardsPanelS.setBackground(color);
       gui.cardsPanelW.setBackground(color);
       gui.centerPanel.setBackground(color);
       gui.textPanel.setBackground(color);
       gui.playerPanelS.setBackground(color);
       gui.playerPanelE.setBackground(color);
       gui.playerPanelW.setBackground(color);
     }

       /** listener class for pause button
        *  @author John Lau
        *  @version 2016.2.18
        */
       public class PauseMusicListener implements ActionListener
       {
         public void actionPerformed(ActionEvent e)
         {
           gui.song.stop();
         }
       }

       /** listener class for play button
        *  @author John Lau
        *  @version 2016.2.18
        */
       public class PlayMusicListener implements ActionListener
       {
         public void actionPerformed(ActionEvent e)
         {
           gui.song.play();
         }
       }


}
