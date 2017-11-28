package edu.ucsb.cs56.projects.game.blackjack;

import java.awt.event.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * NameGuiController.java
 *
 * The Controller class for the NameFrame.
 *
 * @author Ryan Lorica
 * @author Ryan Kirkpatrick
 * @version 11/7/17
 */

public class NameGuiController {
GuiModel gm;
NameGui nameGui;

    public NameGuiController(GuiModel gm){
        this.gm = gm;
    }

    public void run(){
        nameGui = new NameGui(gm.getNumPlayers());
        nameGui.display();
        attachActionListeners();
    }

    public void attachActionListeners(){
        nameGui.loadSave.addItemListener(new LoadListener());
        nameGui.beginGame.addActionListener(new ConfirmName());
    }

    /** listener class for load button
    *  @author ???
    *  @version 2016.11.9
    */
    public class LoadListener implements ItemListener {
        /** prepares for load
        *  @param e ItemEvent
        */
        public void itemStateChanged(ItemEvent e){
            boolean load = false;
            if (e.getStateChange() == ItemEvent.SELECTED) {
                load = true;
            }
            gm.setLoad(load);
        }
    }

    /** listender class for confirm name button
     *  @author ???
     *  @version 2016.11.9
     */
    public class ConfirmName implements ActionListener {
        /** initializes some of the JLabels for a set bet menu and brings up the main JFrame
           @param event ActionEvent, set bet
        */
        public void actionPerformed(ActionEvent event){
            // disable the previous window ('enter player name(s)')
            ArrayList<String> names = new ArrayList<String>();
            String p1Name = nameGui.player1Name.getText();
            String p2Name = nameGui.player2Name.getText();
            String p3Name = nameGui.player3Name.getText();

            names.add(p1Name);
            names.add(p2Name);
            names.add(p3Name);

            gm.setPlayerNames(names);
            nameGui.nameFrame.setVisible(false);
            gm.incrementStage();
        }
    }

}
