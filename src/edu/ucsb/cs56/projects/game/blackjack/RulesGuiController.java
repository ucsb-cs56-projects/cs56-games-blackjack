package edu.ucsb.cs56.projects.game.blackjack;

import java.awt.event.*;
import java.awt.*;

/**
 * RulesGuiController.java
 *
 * The Controller class for the Rules screen.
 *
 * @author Ryan Lorica
 * @author Ryan Kirkpatrick
 * @version 11/28/17
 */

public class RulesGuiController{
    GuiModel gm;
    RulesGui rulesGui;

    public RulesGuiController(GuiModel gm){
        this.gm = gm;
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
                gm.incrementStage();
        }
    }
}
