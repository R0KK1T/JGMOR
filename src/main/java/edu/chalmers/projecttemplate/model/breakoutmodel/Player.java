package edu.chalmers.projecttemplate.model.breakoutmodel;

import java.io.IOException;

/*
 * Breakout model for the player. The class contains the player's name and score.
 */
public class Player implements Comparable<Player> {
    private String firstName;
    private String lastName;
    private int myScore;
    private BestScore bestScore;
    /**
     * Constructs a player with specified first and last name
     *
     * @param firstName the player's first name
     * @param lastName the player's last name
     */
    public Player(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.myScore = 0;
        bestScore = BestScore.getInstance();
    }
    public void setFirstName(String name) {
        firstName = name;
    }
    public void setLastName(String name) {
        lastName = name;
    }
    public String getFirstName() {
        return firstName;
    }
    public String getLastName() {
        return lastName;
    }
    public void setMyScore(int i) {
        myScore = myScore + i;
    }
    public int getMyScore() {
        return myScore;
    }
    /*
     * The class implements comparable interface for the purpose
     * of arranging players in a list based on their scores
     */
    @Override
    public int compareTo(Player o) {
        if (myScore > o.myScore)
            return 1;
        else if (myScore < o.myScore)
            return -1;
        return 0;
    }
    /*
     * Saving player's score after he/she won or lost the game
     */
    public void saveMyScore()  {
        bestScore.saveScore(this);
    }
}
