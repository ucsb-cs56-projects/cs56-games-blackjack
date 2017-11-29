package edu.ucsb.cs56.projects.game.blackjack;
import javax.swing.*;

/**
 * MenuBar.java
 *
 * The class for the menubar that is contained within the blackjack gui
 *
 * @author Ryan Lorica
 * @author Ryan Kirkpatrick
 * @version 11/7/17
 */

public class MenuBar{
    /** MENUBAR **/
    public JMenuBar menuBar;
    public JMenu menuFile;
    public JMenu menuEdit;
    public JMenu menuView;
    public JMenu menuColors;
    public JMenu menuMusic;
    public JMenu menuHelp;

    public JMenuItem menuSave;
    public JMenuItem menuExit;
    public JMenuItem menuRestart;
    public JMenuItem menuRules;
    public JMenuItem menuNames;
    public JMenuItem songPause;
    public JMenuItem songPlay;
    public JMenuItem colorNavy;
    public JMenuItem colorFeltGreen;
    public JMenuItem colorMaroon;
    public JMenuItem colorGray;

      //create menubar
    public void display(){
        //Menu Options
      	menuBar = new JMenuBar();
      	menuFile = new JMenu("File");
      	menuEdit = new JMenu("Edit");
      	menuView = new JMenu("View");
        menuColors= new JMenu("Colors");
      	menuMusic = new JMenu("Music");
      	menuHelp = new JMenu("Help");

      	menuSave= new JMenuItem("Save");
      	menuExit = new JMenuItem("Exit");
      	menuRestart = new JMenuItem("Restart");
        menuRules= new JMenuItem("Rules");
      	menuNames = new JMenuItem("Player Names");

      	colorNavy = new JMenuItem("Navy");
      	colorMaroon = new JMenuItem("Maroon");
      	colorGray = new JMenuItem("Gray");
  	    colorFeltGreen = new JMenuItem("Felt Green");
      	menuColors.add(colorNavy);
      	menuColors.add(colorGray);
      	menuColors.add(colorMaroon);
        menuColors.add(colorFeltGreen);

      	songPause = new JMenuItem("Pause");
      	menuMusic.add(songPause);
      	songPlay = new JMenuItem("Play");
      	menuMusic.add(songPlay);

      	menuFile.add(menuSave);
      	menuFile.add(menuExit);
      	menuFile.add(menuRestart);
      	menuBar.add(menuFile);
      	menuEdit.add(menuNames);
      	menuBar.add(menuEdit);
      	menuView.add(menuColors);
      	menuBar.add(menuView);
      	menuHelp.add(menuRules);
      	menuBar.add(menuHelp);
      	menuBar.add(menuMusic);
      }

}
