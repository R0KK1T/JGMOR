package edu.chalmers.projecttemplate.model.snake22model;

import edu.chalmers.projecttemplate.controller.snake22controller.GameConfiguration;

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
//        String gameSpeed = gc.getGameSpeed();
//
        // TODO Implement different score for different speeds
        //        switch (gc.getGameSpeed()) {
//            case "SLOW":
//                break;
//            case 2:
//                break;
//            case 3:
//                break;
//            case 4:
//                break;
//            default:
//                break;
//        }
    }

    public int getGameScore() {
        return gameScore;
    }

    public void setGameScore(int gameScore) {
        this.gameScore = gameScore;
    }
}
