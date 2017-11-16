package edu.ucsb.cs56.projects.game.blackjack;

import java.awt.event.*;
import java.awt.*;

/**
 * RulesController.java
 *
 * The Controller class for the Rules screen.
 *
 * @author Ryan Lorica
 * @author Ryan Kirkpatrick
 * @version 11/7/17
 */

public class RulesController{
    BlackjackGui gui;
    RulesGui rulesGui;

    public RulesController(BlackjackGui gui){
        this.gui = gui;
    }

    public void run(){
        rulesGui = new RulesGui();
        rulesGui.display();
        attachActionListeners();
    }

    public void attachActionListeners(){
        rulesGui.rulesButton.addActionListener(new CloseRulesListener());
    }

    /** listener class for closing the rules window
     *  @author ???
     *  @version 2016.11.9
     */
    public class CloseRulesListener implements ActionListener{
    /** closes the rules window
       @param event ActionEvent, play button (close rules)
     */
        public void actionPerformed(ActionEvent event){
                rulesGui.rulesFrame.setVisible(false);
                gui.stage++;
        }
    }
}
