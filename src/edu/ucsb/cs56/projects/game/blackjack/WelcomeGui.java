<<<<<<< HEAD
=======
package edu.ucsb.cs56.projects.game.blackjack;

>>>>>>> GuiSeparation
import javax.swing.*;
import java.awt.*;


public class WelcomeGui implements Gui{
<<<<<<< HEAD

    public void display(GuiController controller){
        JFrame welcomeFrame = new JFrame();
        JPanel welcomePanel = new JPanel(new GridLayout(4, 0, 5, 0));
        JLabel welcomeLabel = new JLabel();

        JButton onePlayer = new JButton("1 player");
        JButton twoPlayers = new JButton("2 players");
        JButton threePlayers = new JButton("3 players");
        onePlayer.addActionListener(e -> {
            controller.setNumPlayers(1);
            close(welcomeFrame);
        });
        twoPlayers.addActionListener(e -> {
            controller.setNumPlayers(2);
            close(welcomeFrame);
        });
        threePlayers.addActionListener(e -> {
            controller.setNumPlayers(3);
            close(welcomeFrame);
        });
=======
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
>>>>>>> GuiSeparation

        welcomeLabel.setText("Welcome to Blackjack");
        welcomeLabel.setHorizontalAlignment(SwingConstants.CENTER);

        welcomePanel.add(welcomeLabel);
<<<<<<< HEAD
        welcomePanel.add(onePlayer);
        welcomePanel.add(twoPlayers);
        welcomePanel.add(threePlayers);
        welcomePanel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

        // create the outer panel to center the widgets
        JPanel outerPanel = new JPanel();
=======
        welcomePanel.add(onePlayerButton);
        welcomePanel.add(twoPlayersButton);
        welcomePanel.add(threePlayersButton);
        welcomePanel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

        // create the outer panel to center the widgets
        outerPanel = new JPanel();
>>>>>>> GuiSeparation
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

<<<<<<< HEAD
    private void close(JFrame frame){
        frame.setVisible(false);
        frame.dispose();
    }
=======
>>>>>>> GuiSeparation
}
