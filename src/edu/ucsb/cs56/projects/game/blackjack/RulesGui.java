package edu.ucsb.cs56.projects.game.blackjack;
import javax.swing.*;
import java.awt.*;

public class RulesGui implements Gui {
    String rulesParagraph =
    "<html><br>Blackjack win and loss conditions.."
    + "<br>Loss Conditions:"
    + "<br>-Having a hand value of over 21 (BUST!)"
    + "<br>-Having a hand value less than the dealer's hand value"
    + "<br><br>Win Conditions:"
    + "<br>-Get blackjack (hand value of 21)"
    + "<br>-Have 5 cards without busting (5 card charlie! you still still lose if the dealer has blackjack)"
    + "<br>-Have a hand value greater than the dealer's"
    + "<br><br>Other Features:"
    + "<br>-Double downing draws one card then moves onto the next player (deducts bet amount, chance to win double)"
    + "<br>-Every player starts with $5,000<br><br>";

    JPanel outerPanel;

    JFrame rulesFrame;
    JPanel rulesPanel;
    JLabel rulesLabel;

    JButton rulesButton;

    /** initializes the rules window
     */
    public void display() {
            rulesFrame = new JFrame();
            rulesPanel = new JPanel(new GridLayout(2, 0, 5, 0));
            rulesLabel = new JLabel(rulesParagraph);

            //Button to close the window and go to the welcome window
            rulesButton = new JButton("Play!");

            rulesLabel.setHorizontalAlignment(SwingConstants.CENTER);

            rulesPanel.add(rulesLabel);
            rulesPanel.add(rulesButton);
            rulesPanel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

            // create the outer panel to center the widgets
            outerPanel = new JPanel();
            outerPanel.setLayout(new BoxLayout(outerPanel, BoxLayout.PAGE_AXIS));
            outerPanel.add(Box.createHorizontalGlue());
            outerPanel.add(rulesPanel);
            outerPanel.add(Box.createHorizontalGlue());

            rulesFrame.add(rulesPanel);
            rulesFrame.setSize(300,400);
            rulesFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            rulesFrame.pack();
            rulesFrame.setLocationRelativeTo(null); // center the window
            rulesFrame.setVisible(true);
    }

}
