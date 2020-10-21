package edu.chalmers.projecttemplate.model.breakoutmodel;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.PriorityQueue;

public class BestScore {
    private PriorityQueue<Player> bestPlayers;
    private PrintWriter filout;
    public BestScore() {
        bestPlayers = new PriorityQueue<>();
    }
    public void setBestPlayers(Player player) {
        bestPlayers.add(player);
    }
    public PriorityQueue<Player> getBestPlayers() {
        return bestPlayers;
    }
    public void saveScore(Player player) throws IOException {
        filout = new PrintWriter(new FileWriter("src/main/resources/breakoutresources/files/bestPlayer.txt", true));
        filout.write(player.getFirstName()+" "+player.getLastName()+ " "+player.getMyScore()+"\n");
        filout.close();
    }

    private void scoreOrderedByPoint() {

    }
}
