<<<<<<< HEAD
=======
package edu.ucsb.cs56.projects.game.blackjack;
>>>>>>> GuiSeparation
import java.util.ArrayList;
//Rules, welcome, playernames, bet, game
public class GuiController{
    public int numPlayers;
    public ArrayList<String> playerNames;
    public int betAmount;
<<<<<<< HEAD
=======
    public boolean done;
>>>>>>> GuiSeparation

    public GuiController(){
    }
    public static void main(String args[]){
        //RulesGui rulesGui = new RulesGui();
        //rulesGui.display();
        GuiController controller = new GuiController();
        WelcomeGui welcomeGui = new WelcomeGui();
<<<<<<< HEAD
        welcomeGui.display(controller);
=======
        welcomeGui.display();

>>>>>>> GuiSeparation
/*
        NamesGui namesGui = new namesGui(this);
        namesGui.display();

        BetGui betGui = new betGui();
        betGui.display();

        GameGui gameGui = new gameGui();
        gameGui.display();*/
    }

    public void setNumPlayers(int numPlayers){
        this.numPlayers = numPlayers;
        System.out.println(numPlayers + "");
    }
    public int getNumPlayers(){
        return this.numPlayers;
    }

<<<<<<< HEAD
=======
    public void DisplayRules(){
        RulesGui rulesGui = new RulesGui();
        rulesGui.display();
    }
    public void DisplayWelcome(){

    }
>>>>>>> GuiSeparation

}
