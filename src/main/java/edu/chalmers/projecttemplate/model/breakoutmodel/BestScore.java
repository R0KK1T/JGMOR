package edu.chalmers.projecttemplate.model.breakoutmodel;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class BestScore {
    private List<Player> bestPlayers;
    private PrintWriter filout;
    private Scanner filin;
    public BestScore() {
        bestPlayers = new ArrayList<>();
    }

    private void saveScore(List<Player> players) throws IOException {
        filout = new PrintWriter(new FileWriter("src/main/resources/breakoutresources/files/bestPlayer.txt", false));
        for (int i=0; i<players.size(); i++)
            filout.write(players.get(i).getFirstName()+" "+players.get(i).getLastName()+ " "+players.get(i).getMyScore()+"\n");
        filout.close();
    }

    public void readAndSaveScore(Player o) throws IOException {
        filin = new Scanner(new File("src/main/resources/breakoutresources/files/bestPlayer.txt"));
        System.out.println(" l1");
        bestPlayers.clear();
        while (filin.hasNext()) {
            String s = filin.nextLine();
            String[] str = s.split(" ");
            //Spara string från filen som player i rätt ordning i listan bestPlayer.
            Player player = new Player(str[0], str[1]);
            player.setMyScore(Integer.parseInt(str[2]));
            bestPlayers.add(player);
        }
        filin.close();
        bestPlayers.add(o);
        bestPlayers.sort(new scoreComparator());
        saveScore(bestPlayers);
    }
    public List<Player> getBestPlayers() {
        return bestPlayers;
    }
}
