package herbsts.soccer;

import android.support.annotation.NonNull;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TreeSet;

import herbsts.soccer.pkgData.Player;
import herbsts.soccer.pkgData.Statistic;

/**
 * Created by Lorenz on 18.03.2017.
 */

public class matchbackup implements Comparable<matchbackup> {
    private int id = -1;
    private static int idCounter = 0;
    private Date date = null;
    private int goalsMadeTeam1 = 0;
    private int goalsMadeTeam2 = 0;
    //private TreeSet<Player> tsTeamUnassigned = null;
    private TreeSet<Player> tsTeam1 = null;
    private TreeSet<Player> tsTeam2 = null;
    private TreeSet<Statistic> tsStatistics = null;

    public matchbackup(Date date, int goalsMadeTeam1, int goalsMadeTeam2, TreeSet<Player> tsTeam1, TreeSet<Player> tsTeam2, TreeSet<Statistic> tsStatistics) {
        this.id = ++idCounter;
        this.date = date;
        this.goalsMadeTeam1 = goalsMadeTeam1;
        this.goalsMadeTeam2 = goalsMadeTeam2;
        this.tsTeam1 = tsTeam1;
        this.tsTeam2 = tsTeam2;
        this.tsStatistics = tsStatistics;
    }

    //!!!!!ACHTUNG: Nicht für ein "normales" Match verwenden, sondern nur für ceiling() vom TreeSet in StatisticMatchActivity
    public matchbackup(Date date)
    {
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getGoalsMadeTeam1() {
        return goalsMadeTeam1;
    }

    public void setGoalsMadeTeam1(int goalsMadeTeam1) {
        this.goalsMadeTeam1 = goalsMadeTeam1;
    }

    public int getGoalsMadeTeam2() {
        return goalsMadeTeam2;
    }

    public void setGoalsMadeTeam2(int goalsMadeTeam2) {
        this.goalsMadeTeam2 = goalsMadeTeam2;
    }

    /*public TreeSet<Player> getTsTeamUnassigned() {
        return tsTeamUnassigned;
    }

    public void setTsTeamUnassigned(TreeSet<Player> tsTeamUnassigned) {
        this.tsTeamUnassigned = tsTeamUnassigned;
    }*/

    public TreeSet<Player> getTsTeam1() {
        return tsTeam1;
    }

    public void setTsTeam1(TreeSet<Player> tsTeam1) {
        this.tsTeam1 = tsTeam1;
    }

    public TreeSet<Player> getTsTeam2() {
        return tsTeam2;
    }

    public void setTsTeam2(TreeSet<Player> tsTeam2) {
        this.tsTeam2 = tsTeam2;
    }

    public TreeSet<Statistic> getTsStatistics() {
        return tsStatistics;
    }

    public void setTsStatistics(TreeSet<Statistic> tsStatistics) {
        this.tsStatistics = tsStatistics;
    }

    @Override
    public String toString() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("E, dd.MM.yyyy");
        return simpleDateFormat.format(date) + ", " + goalsMadeTeam1 + ":" + goalsMadeTeam2;
    }

    @Override
    public int compareTo(@NonNull matchbackup m) {
        return this.date.compareTo(m.getDate());
    }
}
