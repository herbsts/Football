package herbsts.soccer.pkgData;

import android.support.annotation.NonNull;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TreeSet;

/**
 * Last edit by Stefan Herbst on 26.05.2017: Merged class to new Webservice
 */

public class Match implements Comparable<Match> {
    private int id = -1;
    private String firmDate = null;
    private int goalsA = 0;
    private int goalsB = 0;
    private transient TreeSet<Player> tsTeam1 = null;
    private transient TreeSet<Player> tsTeam2 = null;

    public Match(String date) {
        this.firmDate = date;
        this.tsTeam1 = new TreeSet<>();
        this.tsTeam2 = new TreeSet<>();
    }

    public int getId() {
        return id;
    }

    public String getDate() {
        return firmDate;
    }

    public void setDate(String date) {
        this.firmDate = date;
    }

    public int getGoalsTeam1() {
        return goalsA;
    }

    public void setGoalsTeam1(int goalsTeam1) {
        this.goalsA = goalsTeam1;
    }

    public int getGoalsTeam2() {
        return goalsB;
    }

    public void setGoalsTeam2(int goalsTeam2) {
        this.goalsB = goalsTeam2;
    }


    public TreeSet<Player> getTsTeam1() {
        return tsTeam1;
    }
    public void setTsTeam1(TreeSet<Player> tsTeam1) {
        this.tsTeam1 = tsTeam1;
    }
    public void addPlayerTeam1(Player p){this.tsTeam1.add(p);};
    public void removePlayerTeam1(Player p){this.tsTeam1.remove(p);};

    public TreeSet<Player> getTsTeam2() {
        return tsTeam2;
    }
    public void setTsTeam2(TreeSet<Player> tsTeam2) {
        this.tsTeam2 = tsTeam2;
    }
    public void addPlayerTeam2(Player p){this.tsTeam2.add(p);};
    public void removePlayerTeam2(Player p){this.tsTeam2.remove(p);};

    @Override
    public String toString() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("E, dd.MM.yyyy");
        //SimpleDateFormat simpleDateFormatWebservice = new SimpleDateFormat("yyyy-MM-dd");
        return simpleDateFormat.format(firmDate) + ", " + goalsA + ":" + goalsB;
    }

    @Override
    public int compareTo(@NonNull Match m) {
        return this.firmDate.compareTo(m.getDate());
    }
}