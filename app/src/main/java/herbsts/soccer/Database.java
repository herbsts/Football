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
        ArrayList<Player> listPlayer = new ArrayList<>(tsPlayer);
        return listPlayer;
    }

    //Braucht man für die ProfileActivity um den Player zu bekommen
    public TreeSet<Player> getTsPlayer() {
        return this.tsPlayer;
    }

    public int addPlayer(Player player) throws Exception
    {
        int helpReturn = 0;

        if (!this.tsPlayer.contains(player))
        {
            this.tsPlayer.add(player);
            helpReturn = 1;
        }

        return helpReturn;
    }

    public int removePlayer(Player player) throws Exception
    {
        int helpReturn = 0;

        if (this.tsPlayer.contains(player))
        {
            this.tsPlayer.remove(player);
            helpReturn = 1;
        }

        return helpReturn;
    }

    public ArrayList<Match> getArrayListMatches() {
        ArrayList<Match> listMatches = new ArrayList<>(tsMatches);
        return listMatches;
    }

    //Braucht man für die ProfileActivity um den Player zu bekommen
    public TreeSet<Match> getTsMatches() {
        return this.tsMatches;
    }

    public int addMatch(Match match) throws Exception
    {
        int helpReturn = 0;

        if (!this.tsMatches.contains(match))
        {
            this.tsMatches.add(match);
            helpReturn = 1;
        }

        return helpReturn;
    }

    public int removeMatch(Match match) throws Exception
    {
        int helpReturn = 0;

        if (this.tsMatches.contains(match))
        {
            this.tsMatches.remove(match);
            helpReturn = 1;
        }

        return helpReturn;
    }
}
