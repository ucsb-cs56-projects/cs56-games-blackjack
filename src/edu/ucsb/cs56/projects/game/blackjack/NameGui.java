package edu.ucsb.cs56.projects.game.blackjack;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;

public class NameGui implements Gui{

    //Was createNewNameFrame
    public void display(GuiController controller){
        JFrame nameFrame = new JFrame();
        JPanel namePanel = new JPanel();
        int numPlayers = controller.getNumPlayers();

        nameFrame.setSize(300,220);
        /*for(int i = 1; i <= numPlayers; i++){
            namePanel.add(new JLabel("Player " + i + "'s name: "));
            namePanel.add(new JTextField(20))
        }*/
        if(numPlayers == 1)
	    {
		JLabel name1 = new JLabel("Player 1's name: ");
		JTextField player1Name = new JTextField(20);
		namePanel.add(name1);
		namePanel.add(player1Name);
	    }
	else if(numPlayers == 2)
	    {
		JLabel name1 = new JLabel("Player 1's name: ");
		JTextField player1Name = new JTextField(20);
		JLabel name2 = new JLabel("Player 2's name: ");
		JTextField player2Name = new JTextField(20);
		namePanel.add(name1);
		namePanel.add(player1Name);
		namePanel.add(name2);
		namePanel.add(player2Name);
	    }
	else if(numPlayers ==3)
	    {
		JLabel name1 = new JLabel("Player 1's name: ");
		JTextField player1Name = new JTextField(20);
		JLabel name2 = new JLabel("Player 2's name: ");
		JTextField player2Name = new JTextField(20);
		JLabel name3 = new JLabel("Player 3's name: ");
		JTextField player3Name = new JTextField(20);
		namePanel.add(name1);
		namePanel.add(player1Name);
		namePanel.add(name2);
		namePanel.add(player2Name);
		namePanel.add(name3);
		namePanel.add(player3Name);
}

        JCheckBox loadSave = new JCheckBox("Load Saved Stats");
        loadSave.addItemListener(new LoadListener());
        namePanel.add(loadSave);
        JButton beginGame = new JButton("Confirm");
        beginGame.addActionListener(e -> {nameFrame.setVisible(false);}); //Close and open bet
        namePanel.add(beginGame);
        nameFrame.getContentPane().add(namePanel);
        nameFrame.setLocationRelativeTo(null); // center the window
        nameFrame.setVisible(true);
    }


    /** listener class for load button
     *  @author ???
     *  @version 2016.11.9
     */
    public class LoadListener implements ItemListener {
    /** prepares for load
     *  @param e ItemEvent
     */
    public void itemStateChanged(ItemEvent e) {
        /*if (e.getStateChange() == ItemEvent.SELECTED) {
        load = true;
        }
        else {
        game.resetStats();
        load = false;
    }*/
    }
    }
}
