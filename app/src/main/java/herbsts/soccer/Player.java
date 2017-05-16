package herbsts.soccer;

import android.support.annotation.NonNull;

import java.util.TreeSet;

/**
 * Created by Stefan Herbst on 16.03.2017.
 */

public class Player implements Comparable<Player> {
    private int id = -1;
    private static int idCounter = 0;
    private String name = "";
    private boolean isGoalie = false;
    private boolean isDefender = false;
    private boolean isMidFielder = false;
    private boolean isForward = false;
    private boolean isActive = false;
    private TreeSet<Statistic> tsStatistic = null;

    public Player(String name, boolean isGoalie, boolean isMidFielder, boolean isDefender, boolean isForward, boolean isActive) {
        this.id = ++idCounter;
        this.name = name;
        this.isGoalie = isGoalie;
        this.isMidFielder = isMidFielder;
        this.isDefender = isDefender;
        this.isForward = isForward;
        this.isActive = isActive;
        this.tsStatistic = new TreeSet<>();
    }

    //!!!!!ACHTUNG: Nicht für einen "normalen" Spieler verwenden, sondern nur für ceiling() vom TreeSet in ProfileActivity
    public Player(String name)
    {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

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

    public TreeSet<Statistic> getTsStatistic() {
        return tsStatistic;
    }

    @Override
    public String toString() {
        return this.name;
    }

    @Override
    public int compareTo(@NonNull Player p) {
        return this.name.compareTo(p.getName());
    }

    public void updateProfile(boolean _isGoalie, boolean _isDefender,boolean _isMidFielder, boolean _isForward, boolean _isActive) throws Exception
    {
        this.setGoalie(_isGoalie);
        this.setDefender(_isDefender);
        this.setMidFielder(_isMidFielder);
        this.setForward(_isForward);
        this.setActive(_isActive);
    }

    public Position getSelectedPosition()
    {
        Position pos = null;

        if (isGoalie())
        {
            pos = Position.GOALIE;
        }
        else
            if (isDefender())
            {
                pos = Position.DEFENDER;
            }
            else
                if (isMidFielder())
                {
                    pos = Position.MIDFIELDER;
                }
                else
                    if (isForward())
                    {
                        pos = Position.FORWARD;
                    }

        return pos;
    }

    public void addStatistic(Statistic statistic) {
        this.tsStatistic.add(statistic);
    }
}