package edu.chalmers.projecttemplate.model.snake22model;

public class Score {
    private int gameScore;
    private GameConfiguration gc;

    public Score() {
    }

    public Score(int gameScore, GameConfiguration gc) {
        this.gameScore = gameScore;
        this.gc = gc;
    }


    public void increaseScore() {

        gameScore+=10;
    }

    public int getGameScore() {
        return gameScore;
    }

    public void setGameScore(int gameScore) {
        this.gameScore = gameScore;
    }
}
