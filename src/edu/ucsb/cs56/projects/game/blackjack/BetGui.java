package edu.ucsb.cs56.projects.game.blackjack;
import javax.swing.*;
import java.awt.*;

public class BetGui implements Gui{
    JPanel outerPanel;

    JFrame betFrame;
    JPanel betPanel;
    JLabel betTitleLabel;

    JLabel betAmountLabel;
    int amountBet;

    JTextField betText;
    JButton betButton;

    JButton betAmount1;
    JButton betAmount2;
    JButton betAmount3;
    JButton betAmount4;
    JButton betAmount5;


    /** creates window for betting
     */
    public void display() {

        // create the frame and panels, as well as set the layout
        betFrame = new JFrame();
        betPanel = new JPanel(new GridLayout(7,0,5,0));
        betTitleLabel = new JLabel();

        // set the main text and center it
        betTitleLabel.setText("<html>How much would you<br>like to bet?</html>");
        betTitleLabel.setHorizontalAlignment(JLabel.CENTER);

        // by default have the $25 bet selected
        betAmountLabel = new JLabel("$25");
        betAmountLabel.setHorizontalAlignment(JLabel.CENTER); // center the label
        amountBet = 25;

        betText = new JTextField("Or enter your desired bet amount here");

        // create bet amount buttons and assign ActionListeners
        betAmount1 = new JButton("$25");
        betAmount2 = new JButton("$50");
        betAmount3 = new JButton("$100");
        betAmount4 = new JButton("$250");
        betAmount5 = new JButton("$500");

        // create button to confirm selected bet amount
        betButton = new JButton("Confirm Bet");
        //betButton.addActionListener(new BeginGameListener());
        // add widgets to panel
        betPanel.add(betTitleLabel);
        betPanel.add(betAmountLabel);
        betPanel.add(betAmount1);
        betPanel.add(betAmount2);
        betPanel.add(betAmount3);
        betPanel.add(betAmount4);
        betPanel.add(betAmount5);
        betPanel.add(betText);
        betPanel.add(betButton);
        betText.selectAll();

        // create the outer panel to center the widgets
        outerPanel = new JPanel();
        outerPanel.setLayout(new BoxLayout(outerPanel, BoxLayout.PAGE_AXIS));
        outerPanel.add(Box.createHorizontalGlue());
        outerPanel.add(betPanel);
        outerPanel.add(Box.createHorizontalGlue());
        // add the panel to the frame and set frame attributes
        betFrame.add(outerPanel);
        /*
        // Since this is the only frame before the game starts, if this window
        // is closed the application should terminate
        betFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // If the betting frame is brought up at the end of a round and closed using the 'x' button the game
        // should not be terminated.
        if(!exit_on_close)
        */
        betFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        betFrame.pack();
        betFrame.setLocationRelativeTo(null); // center window
        betFrame.setVisible(true);
        }




}
