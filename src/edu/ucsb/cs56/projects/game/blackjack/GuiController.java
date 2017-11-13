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
        //RulesGui rulesGui = new RulesGui();
        //rulesGui.display();
        GuiController controller = new GuiController();
        WelcomeGui welcomeGui = new WelcomeGui();
        welcomeGui.display();

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

    public void DisplayRules(){
        RulesGui rulesGui = new RulesGui();
        rulesGui.display();
    }
    public void DisplayWelcome(){

    }

}
