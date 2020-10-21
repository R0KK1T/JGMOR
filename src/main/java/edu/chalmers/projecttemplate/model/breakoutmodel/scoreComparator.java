package edu.chalmers.projecttemplate.model.breakoutmodel;

import java.util.Comparator;

public class scoreComparator implements Comparator<Player> {
    @Override
    public int compare(Player o1, Player o2) {
        int score1 = o1.getMyScore();
        int score2 = o2.getMyScore();
        return o1.compareTo(o2);
    }

    @Override
    public Comparator<Player> reversed() {
        return null;
    }
}
