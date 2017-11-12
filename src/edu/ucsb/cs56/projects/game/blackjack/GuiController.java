<<<<<<< Updated upstream
=======
package edu.ucsb.cs56.projects.game.blackjack;
import java.util.ArrayList;
//Rules, welcome, playernames, bet, game
public class GuiController{
    public int numPlayers;
    public ArrayList<String> playerNames;
    public int betAmount;

    public boolean done;

    public GuiController(){

    }
    public static void main(String args[]){
        GuiController controller = new GuiController();

        RulesGui rulesGui = new RulesGui();
        rulesGui.display(controller);

        WelcomeGui welcomeGui = new WelcomeGui();
        welcomeGui.display(controller);

        NameGui nameGui = new NameGui();
        nameGui.display(controller);
/*
        createBetWindow(true);
        BetGui betGui = new betGui();
        betGui.display();

        GameGui gameGui = new gameGui();
        gameGui.display();*/
    }

    public void setNumPlayers(int numPlayers){
        this.numPlayers = numPlayers;
    }
    public int getNumPlayers(){
        return this.numPlayers;
    }
    public void setDone(){
        this.done = true;
    }

}
>>>>>>> Stashed changes
