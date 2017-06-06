package herbsts.soccer.pkgData;

import android.support.annotation.NonNull;

/**
 * Last edit by Stefan Herbst on 26.05.2017: Merged class to new Webservice
 */

public class Statistic implements Comparable<Statistic> {
    private int id = -1;
    private int matchId = 0;
    private int goalsShot = -1;
    private int goalsPenalty = -1;
    private int goalsHead = -1;
    private int goalsHeadSnow = -1;
    private int goalsOwn = -1;
    private int nutmegs = -1;

    public Statistic(int matchId, int goalsShot, int goalsPenalty, int goalsHead, int goalsHeadSnow, int goalsOwn, int nutmegs) {
        this.matchId = matchId;
        this.goalsShot = goalsShot;
        this.goalsPenalty = goalsPenalty;
        this.goalsHead = goalsHead;
        this.goalsHeadSnow = goalsHeadSnow;
        this.goalsOwn = goalsOwn;
        this.nutmegs = nutmegs;
    }

    public Statistic(int matchId)
    {
        this.matchId = matchId;
    }

    public int getMatchId() {
        return matchId;
    }

    public void setMatch(int matchId) {
        this.matchId = matchId;
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
        return "Statistic{" + "matchId=" + matchId +
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
        String idStr = ""+matchId;
        String idStrComp = ""+s.matchId;
        return idStr.compareTo(idStrComp);
    }
}