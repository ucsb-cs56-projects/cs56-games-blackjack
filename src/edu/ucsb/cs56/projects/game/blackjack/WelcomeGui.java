package edu.ucsb.cs56.projects.game.blackjack;

import javax.swing.*;
import java.awt.*;

/**
 * WelcomeGui.java
 *
 * The class for the welcome gui.
 *
 * @author Ryan Lorica
 * @author Ryan Kirkpatrick
 * @version 11/28/17
 */

public class WelcomeGui implements Gui{

    JPanel outerPanel;

    JFrame welcomeFrame;
    JPanel welcomePanel;
    JLabel welcomeLabel;

    public JButton onePlayerButton;
    public JButton twoPlayersButton;
    public JButton threePlayersButton;

    public void display(){
        welcomeFrame = new JFrame();
        welcomePanel = new JPanel(new GridLayout(4, 0, 5, 0));
        welcomeLabel = new JLabel();

        onePlayerButton = new JButton("1 player");
        twoPlayersButton = new JButton("2 players");
        threePlayersButton = new JButton("3 players");


        welcomeLabel.setText("Welcome to Blackjack");
        welcomeLabel.setHorizontalAlignment(SwingConstants.CENTER);

        welcomePanel.add(welcomeLabel);

        welcomePanel.add(onePlayerButton);
        welcomePanel.add(twoPlayersButton);
        welcomePanel.add(threePlayersButton);
        welcomePanel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

        // create the outer panel to center the widgets
        outerPanel = new JPanel();

        outerPanel.setLayout(new BoxLayout(outerPanel, BoxLayout.PAGE_AXIS));
        outerPanel.add(Box.createHorizontalGlue());
        outerPanel.add(welcomePanel);
        outerPanel.add(Box.createHorizontalGlue());

        welcomeFrame.add(welcomePanel);
        welcomeFrame.setSize(200,175);
        welcomeFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        welcomeFrame.setVisible(true);
        welcomeFrame.pack();
        welcomeFrame.setLocationRelativeTo(null);
    }

}
