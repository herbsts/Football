package herbsts.soccer;

/**
 * Created by Stefan Herbst on 16.03.2017.
 */

public class Player {
    private int id = -1;
    private static int idCounter = 0;
    private String name = "";
    private boolean isGoalie = false;
    private boolean isMidFielder = false;
    private boolean isDefender = false;
    private boolean isForward = false;
    private boolean isActive = false;

    public Player(int id, String name, boolean isGoalie, boolean isMidFielder, boolean isDefender, boolean isForward, boolean isActive) {
        this.id = ++idCounter;
        this.name = name;
        this.isGoalie = isGoalie;
        this.isMidFielder = isMidFielder;
        this.isDefender = isDefender;
        this.isForward = isForward;
        this.isActive = isActive;
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

    public boolean isMidFielder() {
        return isMidFielder;
    }

    public void setMidFielder(boolean midFielder) {
        isMidFielder = midFielder;
    }

    public boolean isDefender() {
        return isDefender;
    }

    public void setDefender(boolean defender) {
        isDefender = defender;
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

    @Override
    public String toString() {
        return "Player{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", isGoalie=" + isGoalie +
                ", isMidFielder=" + isMidFielder +
                ", isDefender=" + isDefender +
                ", isForward=" + isForward +
                ", isActive=" + isActive +
                '}';
    }
}