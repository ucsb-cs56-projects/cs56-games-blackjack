package edu.ucsb.cs56.projects.game.blackjack;
import javax.swing.*;
import java.awt.*;

public class NameGui implements Gui{
    JFrame nameFrame;
    JPanel namePanel;
    JLabel name1;
    JTextField player1Name;
    JLabel name2;
    JTextField player2Name;
    JLabel name3;
    JTextField player3Name;

    JCheckBox loadSave;
    JButton beginGame;

    int numPlayers;

    NameGui(int numPlayers){
        this.numPlayers = numPlayers;
    }

    //Was createNewNameFrame
    public void display(){
        nameFrame = new JFrame();
        namePanel = new JPanel();

        nameFrame.setSize(300,220);
        name1 = new JLabel("Player 1's name: ");
        player1Name = new JTextField(20);
        name2 = new JLabel("Player 2's name: ");
        player2Name = new JTextField(20);
        name3 = new JLabel("Player 3's name: ");
        player3Name = new JTextField(20);
        /*for(int i = 1; i <= numPlayers; i++){
            namePanel.add(new JLabel("Player " + i + "'s name: "));
            namePanel.add(new JTextField(20))
        }*/
        if(numPlayers == 1){
            namePanel.add(name1);
            namePanel.add(player1Name);
	    }
        else if(numPlayers == 2){
            namePanel.add(name1);
            namePanel.add(player1Name);
            namePanel.add(name2);
            namePanel.add(player2Name);
        }
        else if(numPlayers ==3) {
            namePanel.add(name1);
            namePanel.add(player1Name);
            namePanel.add(name2);
            namePanel.add(player2Name);
            namePanel.add(name3);
            namePanel.add(player3Name);
        }

        loadSave = new JCheckBox("Load Saved Stats");
        namePanel.add(loadSave);
        beginGame = new JButton("Confirm");
        namePanel.add(beginGame);
        nameFrame.getContentPane().add(namePanel);
        nameFrame.setLocationRelativeTo(null); // center the window
        nameFrame.setVisible(true);
    }

}
