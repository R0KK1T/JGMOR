package edu.chalmers.projecttemplate.model.breakoutmodel;

import java.util.PriorityQueue;

public class Score {
    private PriorityQueue<Player> bestPlayers;
    public Score() {
        bestPlayers = new PriorityQueue<>();
    }
    public void setBestPlayers(Player player) {
        bestPlayers.add(player);
    }
    public PriorityQueue<Player> getBestPlayers() {
        return bestPlayers;
    }

}
