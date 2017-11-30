package edu.ucsb.cs56.projects.game.blackjack;
import java.util.ArrayList;

/**
 * GuiModel.java
 *
 * The GuiModel class for the Blackjack Game.
 * Contains GuiModel
 *
 * @author Ryan Lorica
 * @author Ryan Kirkpatrick
 * @version 11/28/17
 */
 
public class GuiModel{
    private int numPlayers;
    private ArrayList<String> playerNames;
    private int betAmount;
    private int stage;
    private boolean load;

    public void setNumPlayers(int numPlayers){
        this.numPlayers = numPlayers;
    }
    public int getNumPlayers(){
        return this.numPlayers;
    }
    public void setPlayerNames(ArrayList<String> playerNames){
        this.playerNames = playerNames;
    }
    public ArrayList<String> getPlayerNames(){
        return this.playerNames;
    }
    public void setBetAmount(int betAmount){
        this.betAmount = betAmount;
    }
    public int getBetAmount(){
        return this.betAmount;
    }
    public void incrementStage(){
        this.stage++;
    }
    public int getStage(){
        return this.stage;
    }
    public void setLoad(boolean load){
        this.load = load;
    }
    public boolean getLoad(){
        return this.load;
    }
}
