package edu.chalmers.projecttemplate.model.breakoutmodel;

import java.io.*;
import java.util.*;
/*
 * Class BestScore is responsible to keep a record of the best scores.
 * The method saves the best scores only when the game is in progress.
 * When you close the game completely then the entire list is deleted.
 *
 * The class is a singleton class for the purpose of having only one object
 * (one instance of the class) at a time.
 */
public class BestScore {
    private static BestScore bestScore;
    private List<Player> bestPlayers;
    //constructor
    public static BestScore getInstance() {
        if (bestScore == null) {
            bestScore = new BestScore();
            bestScore.bestPlayers = new ArrayList<>();
        }
        return bestScore;
    }
    /*
     * This method is used for saving information about the player into the list
     */
    public void saveScore(Player player) {
        bestScore.bestPlayers.add(player);
    }
    /*
     * This method returns a list of all players and scores in descending order.
     */
    public List<Player> getBestPlayers() {
        Collections.sort(bestScore.bestPlayers, Collections.reverseOrder());
        return bestScore.bestPlayers;
    }
}