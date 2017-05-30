package herbsts.soccer.pkgData;

import android.support.annotation.NonNull;

import java.util.HashMap;
import java.util.TreeSet;

/**
 * Last edit by Stefan Herbst on 23.05.2017: Merged class to new Webservice
 */

public class Player implements Comparable<Player> {
    private int id = -1;
    private String name = null;
    private String password = null;
    private transient boolean isGoalie = false;
    private transient boolean isDefender = false;
    private transient boolean isMidFielder = false;
    private transient boolean isForward = false;
    private boolean isActive = true;
    private boolean isAdmin = false;
    private int wins = -1;
    private int losses = -1;
    private int tieds = -1;
    private int goalDifference = -1;

    private TreeSet<Match> tsMatches = null;
    private HashMap<Integer, Statistic> hmStatistics = null;

    public Player(String name, String password, boolean isGoalie, boolean isMidFielder, boolean isDefender, boolean isForward, boolean isAdmin) {
        this.name = name;
        this.password = password;
        this.isGoalie = isGoalie;
        this.isMidFielder = isMidFielder;
        this.isDefender = isDefender;
        this.isForward = isForward;
        this.isActive = true;
        this.isAdmin = isAdmin;
        this.wins = 0;
        this.losses = 0;
        this.tieds = 0;
        this.goalDifference = 0;
        this.tsMatches = new TreeSet<>();
        this.hmStatistics = new HashMap<>();
    }

    //!!!!!ACHTUNG: Nicht für einen "normalen" Spieler verwenden, sondern nur für ceiling() vom TreeSet in ProfileActivity
    public Player(String name)
    {
        this.name = name;
    }
    public Player(String name, String password){this.name = name; this.password = password;}

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getPassword(){return password;}
    public void setPassword(String password){this.password = password;}

    public boolean isGoalie() {
        return isGoalie;
    }
    public void setGoalie(boolean goalie) {
        isGoalie = goalie;
    }

    public boolean isDefender() {
        return isDefender;
    }
    public void setDefender(boolean defender) {
        isDefender = defender;
    }

    public boolean isMidFielder() {
        return isMidFielder;
    }
    public void setMidFielder(boolean midFielder) {
        isMidFielder = midFielder;
    }

    public boolean isForward() {
        return isForward;
    }
    public void setForward(boolean forward) {
        isForward = forward;
    }

    public boolean isActive() {
        return isActive;
    }
    public void setActive(boolean active) {
        isActive = active;
    }

    public boolean isAdmin() {
        return isActive;
    }
    public void setAdmin(boolean active) {
        isActive = active;
    }

    public int getWins() {
        return wins;
    }
    public void setWins(int wins) {
        this.wins = wins;
    }

    public int getTieds() {
        return tieds;
    }
    public void setTieds(int tieds) {
        this.tieds = tieds;
    }

    public int getLosses() {
        return losses;
    }
    public void setLosses(int losses) {
        this.losses = losses;
    }

    public int getGoalDifference() {
        return goalDifference;
    }
    public void setGoalDifference(int goalDifference) {
        this.goalDifference = goalDifference;
    }

    public TreeSet<Match> getMatches(){return this.tsMatches;}
    public void addMatch(Match match){this.tsMatches.add(match);}

    public Statistic getStatistic(int matchID){return this.hmStatistics.get(matchID);}
    public HashMap<Integer, Statistic> getStatistics(){return this.hmStatistics;}
    public void addStatistic(int matchID, Statistic statistic) {this.hmStatistics.put(matchID, statistic);}

    @Override
    public String toString() {
        return this.name;
    }

    @Override
    public int compareTo(@NonNull Player p) {
        return this.name.compareTo(p.getName());
    }

}