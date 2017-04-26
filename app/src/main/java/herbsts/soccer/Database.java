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
        this.tsMatches = new TreeSet<>();
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

    //***new add***
    public boolean addPlayer(Player player) throws Exception
    {
        return this.tsPlayer.add(player);
    }

    /*public boolean removePlayer(Player player) throws Exception
    {
        return this.tsPlayer.remove(player);
    }*/

    //Braucht man bei ProfileActivity, um den Spinner mit der jeweiligen Position des Players setzen. Man liefert zwar einen Player als Parameter
    //aber dieser ist nur ein Phantom mit dem gleichen Namen und anhand der ceiling-Methode (vergleicht ja mit der compareTo() ) bekommt man
    //den Player mit den richtigen Werten
    public Player getSpecifyPlayer(Player player) throws Exception
    {
        Player returnPlayer = null;

        if (this.tsPlayer.contains(player))
        {
            returnPlayer = this.tsPlayer.ceiling(player);
        }

        return returnPlayer;
    }

    public ArrayList<Match> getArrayListMatches() {
        return new ArrayList<>(tsMatches);
    }

    //Braucht man für die ProfileActivity um den Player zu bekommen
    public TreeSet<Match> getTsMatches() {
        return this.tsMatches;
    }

    public boolean addMatch(Match match) throws Exception
    {
        return this.tsMatches.add(match);
    }

    public boolean removeMatch(Match match) throws Exception
    {
        return this.tsMatches.remove(match);
    }
}