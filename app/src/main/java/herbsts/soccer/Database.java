package herbsts.soccer;

import java.util.TreeSet;

/**
 * Created by Lorenz on 18.03.2017.
 */

public class Database {
    private static Database database = null;
    private TreeSet<Player> tsPlayer = null;

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

    public void addPlayer(Player player)
    {

    }
}
