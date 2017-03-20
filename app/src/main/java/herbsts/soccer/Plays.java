package herbsts.soccer;

/**
 * Created by Stefan Herbst on 16.03.2017.
 */

public class Plays {
    private int id = -1;
    private static int idCounter = 0;
    private int idPlayer = -1;
    private int goalsShot = -1;
    private int goalsPenalty = -1;
    private int goalsHead = -1;
    private int goalsHeadSnow = -1;
    private int goalsOwn = -1;
    private int nutmegs = -1;

    public Plays(int idPlayer, int goalsShot, int goalsPenalty, int goalsHead, int goalsHeadSnow, int goalsOwn, int nutmegs) {
        this.id = ++idCounter;
        this.idPlayer = idPlayer;
        this.goalsShot = goalsShot;
        this.goalsPenalty = goalsPenalty;
        this.goalsHead = goalsHead;
        this.goalsHeadSnow = goalsHeadSnow;
        this.goalsOwn = goalsOwn;
        this.nutmegs = nutmegs;
    }

    public int getIdPlayer() {
        return idPlayer;
    }

    public void setIdPlayer(int idPlayer) {
        this.idPlayer = idPlayer;
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
        return "Plays{" +
                "idPlayer=" + idPlayer +
                ", goalsShot=" + goalsShot +
                ", goalsPenalty=" + goalsPenalty +
                ", goalsHead=" + goalsHead +
                ", goalsHeadSnow=" + goalsHeadSnow +
                ", goalsOwn=" + goalsOwn +
                ", nutmegs=" + nutmegs +
                '}';
    }
}
