package herbsts.soccer;

import java.util.ArrayList;
import java.util.TreeSet;

/**
 * Created by Lorenz on 18.03.2017.
 */

public class Database {
    private static Database database = null;
    private TreeSet<Player> tsPlayer = null;
    private TreeSet<Match> tsMatches = null;

    //Muss private sein, weil es darf ja nur von newInstance() aufgerufen werden, wegen Singleton
    private Database()
    {
        this.tsPlayer = new TreeSet<Player>();
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

        if (this.tsPlayer.contains(player) == false)
        {
            this.tsPlayer.add(player);
            helpReturn = 1;
        }

        return helpReturn;
    }

    public int removePlayer(Player player) throws Exception
    {
        int helpReturn = 0;

        if (this.tsPlayer.contains(player) == true)
        {
            this.tsPlayer.remove(player);
            helpReturn = 1;
        }

        return helpReturn;
    }

    public int addMatch(Match match) throws Exception
    {
        int helpReturn = 0;

        if (this.tsMatches.contains(match) == false)
        {
            this.tsMatches.add(match);
            helpReturn = 1;
        }

        return helpReturn;
    }

    public int removeMatch(Match match) throws Exception
    {
        int helpReturn = 0;

        if (this.tsMatches.contains(match) == true)
        {
            this.tsMatches.remove(match);
            helpReturn = 1;
        }

        return helpReturn;
    }
}
