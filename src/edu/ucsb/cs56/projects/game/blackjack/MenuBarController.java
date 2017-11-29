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
         gui.currentColor = gui.navy;
         gui.dealerPanel.setBackground(gui.navy);
         gui.displayPanel.setBackground(gui.navy);
         gui.cardsPanelE.setBackground(gui.navy);
         gui.cardsPanelS.setBackground(gui.navy);
         gui.cardsPanelW.setBackground(gui.navy);
         gui.centerPanel.setBackground(gui.navy);
         gui.textPanel.setBackground(gui.navy);
         gui.playerPanelS.setBackground(gui.navy);
         gui.playerPanelE.setBackground(gui.navy);
         gui.playerPanelW.setBackground(gui.navy);
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
         gui.currentColor = gui.maroon;
         gui.dealerPanel.setBackground(gui.maroon);
         gui.displayPanel.setBackground(gui.maroon);
         gui.cardsPanelE.setBackground(gui.maroon);
         gui.cardsPanelS.setBackground(gui.maroon);
         gui.cardsPanelW.setBackground(gui.maroon);
         gui.centerPanel.setBackground(gui.maroon);
         gui.textPanel.setBackground(gui.maroon);
         gui.playerPanelS.setBackground(gui.maroon);
         gui.playerPanelE.setBackground(gui.maroon);
         gui.playerPanelW.setBackground(gui.maroon);
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
         gui.currentColor = gui.gray;
         gui.dealerPanel.setBackground(gui.gray);
         gui.displayPanel.setBackground(gui.gray);
         gui.cardsPanelE.setBackground(gui.gray);
         gui.cardsPanelS.setBackground(gui.gray);
         gui.cardsPanelW.setBackground(gui.gray);
         gui.centerPanel.setBackground(gui.gray);
         gui.textPanel.setBackground(gui.gray);
         gui.playerPanelS.setBackground(gui.gray);
         gui.playerPanelE.setBackground(gui.gray);
         gui.playerPanelW.setBackground(gui.gray);
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
         gui.currentColor = gui.feltgreen;
         gui.dealerPanel.setBackground(gui.feltgreen);
         gui.displayPanel.setBackground(gui.feltgreen);
         gui.cardsPanelE.setBackground(gui.feltgreen);
         gui.cardsPanelS.setBackground(gui.feltgreen);
         gui.cardsPanelW.setBackground(gui.feltgreen);
         gui.centerPanel.setBackground(gui.feltgreen);
         gui.textPanel.setBackground(gui.feltgreen);
         gui.playerPanelS.setBackground(gui.feltgreen);
         gui.playerPanelE.setBackground(gui.feltgreen);
         gui.playerPanelW.setBackground(gui.feltgreen);
       }
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
