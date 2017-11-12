<<<<<<< Updated upstream
=======
package edu.ucsb.cs56.projects.game.blackjack;
import javax.swing.*;
import java.awt.*;


public class WelcomeGui implements Gui {

public void display(GuiController controller){
    JFrame welcomeFrame = new JFrame();
    JPanel welcomePanel = new JPanel(new GridLayout(4, 0, 5, 0));
    JLabel welcomeLabel = new JLabel();

    JButton onePlayer = new JButton("1 player");
    JButton twoPlayers = new JButton("2 players");
    JButton threePlayers = new JButton("3 players");
    onePlayer.addActionListener(e -> {
                                        controller.setNumPlayers(1);
                                        welcomeFrame.setVisible(false);
                                        controller.setDone();
                                });
    twoPlayers.addActionListener(e -> {
                                         controller.setNumPlayers(2);
                                         welcomeFrame.setVisible(false);
                                         controller.setDone();
                                 });
    threePlayers.addActionListener(e -> {
                                           controller.setNumPlayers(3);
                                           welcomeFrame.setVisible(false);
                                           controller.setDone();
                                   });

    welcomeLabel.setText("Welcome to Blackjack");
    welcomeLabel.setHorizontalAlignment(SwingConstants.CENTER);

    welcomePanel.add(welcomeLabel);
    welcomePanel.add(onePlayer);
    welcomePanel.add(twoPlayers);
    welcomePanel.add(threePlayers);
    welcomePanel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

    // create the outer panel to center the widgets
    JPanel outerPanel = new JPanel();
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
>>>>>>> Stashed changes
