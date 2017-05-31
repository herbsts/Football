package herbsts.soccer;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.TreeSet;

import herbsts.soccer.pkgController.PlayerController;
import herbsts.soccer.pkgData.Match;
import herbsts.soccer.pkgData.Player;

/**
 * Created by Lorenz Fritz
 * Written by Stefan Herbst and Lorenz Fritz
 * Last edit by Stefan Herbst on 31.05.2017: Finished authPlayer()
 */

public class Database {
    private static Database database = null;
    private TreeSet<Player> tsPlayer = null;
    private TreeSet<Match> tsMatches = null;
    private Gson gson = new Gson();

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

    public Match getSpecifyMatch(Match match) throws Exception
    {
        Match returnMatch = null;

        if (this.tsMatches.contains(match))
        {
            returnMatch = this.tsMatches.ceiling(match);
        }

        return returnMatch;
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

/**************************************************************************************************/

    /**
     *Webservice Methods
     */
    public boolean authPlayer(Player player) throws Exception {
        PlayerController controller = new PlayerController();
        Player playerFromWebservice = null;
        Object[] params = new Object[3];        //Object, weil man player als Object übergeben muss und nicht als String, weil man das PlayerObject erst in der AsynchTask-Klasse (=ControllerPlayer) mit gson.toJson() zu einem String machen darf. Sonst geht es nicht

        params[0] = "POST";
        params[1] = "player/auth";
        params[2] = player;

        controller.execute(params);
        playerFromWebservice = gson.fromJson(controller.get(), Player.class);

        return (playerFromWebservice.getId() != -1);
    }
}