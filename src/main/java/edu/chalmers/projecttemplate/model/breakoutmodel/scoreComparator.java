package edu.chalmers.projecttemplate.model.breakoutmodel;

import java.util.Comparator;
/*
 * scoreComparator implements interface Comparator for the purpose
 * to order the objects of Player class.
 */
public class scoreComparator implements Comparator<Player> {
    @Override
    public int compare(Player o1, Player o2) {
        int score1 = o1.getMyScore();
        int score2 = o2.getMyScore();
        return o1.compareTo(o2);
    }
}
