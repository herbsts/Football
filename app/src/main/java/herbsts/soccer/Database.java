package herbsts.soccer;

import java.util.ArrayList;
import java.util.TreeSet;


public class Database {
    private static Database database = null;
    private TreeSet<Player> tsPlayer = null;
    private TreeSet<Match> tsMatches = null;

    //Muss private sein, weil es darf ja nur von newInstance() aufgerufen werden, wegen Singleton
    private Database()
    {
        this.tsPlayer = new TreeSet<>();
    }

    public static Database newInstance()
    {
        if (database == null)
        {
            database = new Database();
        }

        return database;
    }

    //ArrayList returnen wegen ComboBox
    public ArrayList<Player> getArrayListPlayer() {
        return new ArrayList<>(tsPlayer);
    }

    //Braucht man für die ProfileActivity um den Player zu bekommen
    public TreeSet<Player> getTsPlayer() {
        return this.tsPlayer;
    }

    public void addPlayer(Player player) throws Exception
    {
        this.tsPlayer.add(player);
    }

    public void removePlayer(Player player) throws Exception
    {
        this.tsPlayer.remove(player);
    }

    public ArrayList<Match> getArrayListMatches() {
        return new ArrayList<>(tsMatches);
    }

    //Braucht man für die ProfileActivity um den Player zu bekommen
    public TreeSet<Match> getTsMatches() {
        return this.tsMatches;
    }

    public void addMatch(Match match) throws Exception
    {
        this.tsMatches.add(match);
    }

    public void removeMatch(Match match) throws Exception
    {
        this.tsMatches.remove(match);
    }
}
