package edu.chalmers.projecttemplate.model.breakoutmodel;

import java.io.IOException;

/*
 * Breakout model for player. The class contains the player's name and score.
 */
public class Player implements Comparable<Player> {
    private String firstName;
    private String lastName;
    private int myScore;
    public Player(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.myScore = 0;
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

    @Override
    public int compareTo(Player o) {
        if (myScore > o.myScore)
            return 1;
        else if (myScore < o.myScore)
            return -1;
        return 0;
    }
}
