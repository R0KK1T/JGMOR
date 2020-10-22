package edu.chalmers.projecttemplate.model.breakoutmodel;

import java.io.*;
import java.util.*;
/*
 * Class BestScore is responsible to keep a record of the best scores.
 */
public class BestScore {
    private List<Player> bestPlayers;
    private PrintWriter filout;
    private Scanner filin;
    //constructor
    public BestScore() {
        bestPlayers = new ArrayList<>();
    }
    /*
     * This method is used for saving information about the player into the 'semi-database file' bestPlayer.txt
     */
    private void saveScore(List<Player> players) throws IOException {
        filout = new PrintWriter(new FileWriter("src/main/resources/breakoutresources/files/bestPlayer.txt", false));
        for (int i=0; i<players.size(); i++)
            filout.write(players.get(i).getFirstName()+" "+players.get(i).getLastName()+ " "+players.get(i).getMyScore()+"\n");
        filout.close();
    }
    /*
     * This method loads all information from the file and creates a list with all recent score in ascending order.
     */
    public void loadScore() throws FileNotFoundException {
        filin = new Scanner(new File("src/main/resources/breakoutresources/files/bestPlayer.txt"));
        bestPlayers.clear();
        while (filin.hasNext()) {
            String s = filin.nextLine();
            String[] str = s.split(" ");
            Player player = new Player(str[0], str[1]);
            player.setMyScore(Integer.parseInt(str[2]));
            bestPlayers.add(player);
        }
        filin.close();
        bestPlayers.sort(new scoreComparator());
    }
    /*
     * This method do practically the same function has the loadScore-method.
     * The difference is that it gets the list of all players and scores, and then adds the new score (the new player) and finally calls
     * the method saveScore for saving the new list into the file bestPlayer.txt
     */
    public void readAndSaveScore(Player o) throws IOException {
        filin = new Scanner(new File("src/main/resources/breakoutresources/files/bestPlayer.txt"));
        bestPlayers.clear();
        while (filin.hasNext()) {
            String s = filin.nextLine();
            String[] str = s.split(" ");
            Player player = new Player(str[0], str[1]);
            player.setMyScore(Integer.parseInt(str[2]));
            bestPlayers.add(player);
        }
        filin.close();
        bestPlayers.add(o);
        bestPlayers.sort(new scoreComparator());
        saveScore(bestPlayers);
    }
    /*
     * This method returns a list of all players and scores in descending order.
     */
    public List<Player> getBestPlayers() {
        Collections.sort(bestPlayers, Collections.reverseOrder());
        return bestPlayers;
    }
    /*
     * This method clean the list to avoid redundancy
     */
    public void cleanFile() throws IOException {
        filout = new PrintWriter(new FileWriter("src/main/resources/breakoutresources/files/bestPlayer.txt", false));
        filout.close();
    }
}
