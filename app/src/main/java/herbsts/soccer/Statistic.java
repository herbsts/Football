package herbsts.soccer;

import android.support.annotation.NonNull;

/**
 * Created by Stefan Herbst on 16.03.2017.
 */

public class Statistic implements Comparable<Statistic> {
    private int id = -1;
    private static int idCounter = 0;
    private Match match = null;
    private int goalsShot = -1;
    private int goalsPenalty = -1;
    private int goalsHead = -1;
    private int goalsHeadSnow = -1;
    private int goalsOwn = -1;
    private int nutmegs = -1;

    public Statistic(Match match, int goalsShot, int goalsPenalty, int goalsHead, int goalsHeadSnow, int goalsOwn, int nutmegs) {
        this.id = ++idCounter;
        this.match = match;
        this.goalsShot = goalsShot;
        this.goalsPenalty = goalsPenalty;
        this.goalsHead = goalsHead;
        this.goalsHeadSnow = goalsHeadSnow;
        this.goalsOwn = goalsOwn;
        this.nutmegs = nutmegs;
    }

    public Statistic(Match match)
    {
        this.match = match;
    }

    public Match getMatch() {
        return match;
    }

    public void setMatch(Match match) {
        this.match = match;
    }

    public int getGoalsShot() {
        return goalsShot;
    }

    public void setGoalsShot(int goalsShot) {
        this.goalsShot = goalsShot;
    }

    public int getGoalsPenalty() {
        return goalsPenalty;
    }

    public void setGoalsPenalty(int goalsPenalty) {
        this.goalsPenalty = goalsPenalty;
    }

    public int getGoalsHead() {
        return goalsHead;
    }

    public void setGoalsHead(int goalsHead) {
        this.goalsHead = goalsHead;
    }

    public int getGoalsHeadSnow() {
        return goalsHeadSnow;
    }

    public void setGoalsHeadSnow(int goalsHeadSnow) {
        this.goalsHeadSnow = goalsHeadSnow;
    }

    public int getGoalsOwn() {
        return goalsOwn;
    }

    public void setGoalsOwn(int goalsOwn) {
        this.goalsOwn = goalsOwn;
    }

    public int getNutmegs() {
        return nutmegs;
    }

    public void setNutmegs(int nutmegs) {
        this.nutmegs = nutmegs;
    }

    @Override
    public String toString() {
        return "Statistic{" +
                "match=" + match +
                ", goalsShot=" + goalsShot +
                ", goalsPenalty=" + goalsPenalty +
                ", goalsHead=" + goalsHead +
                ", goalsHeadSnow=" + goalsHeadSnow +
                ", goalsOwn=" + goalsOwn +
                ", nutmegs=" + nutmegs +
                '}';
    }

    @Override
    public int compareTo(@NonNull Statistic s) {
        return this.match.compareTo(s.getMatch());
    }
}
