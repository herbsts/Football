package herbsts.soccer;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.TreeSet;
import java.util.concurrent.ExecutionException;

import herbsts.soccer.pkgData.Match;
import herbsts.soccer.pkgData.Player;

public class MatchActivity extends AppCompatActivity implements View.OnClickListener{
    /*
    non-gui-attributes
     */
    private Database db = null;
    private TreeSet<Player> tsTeam1 = null;
    private TreeSet<Player> tsTeam2 = null;
    private TreeSet<Player> tsAllPlayers = null;        // !!! Damit man die Daten der Player (Password brauchen wir) gespeichert hat, weil man dann bei der Teamzuweisung das Passwort bräuchte um den Player vom Webservice zu holen und in der List-Anzeige ja nur der Name steht.

    /*
    gui-attributes
     */
    private DatePicker dpMatch = null;
    private Button btnAdd = null;
    private Button btnTeam1 = null;
    private Button btnTeam2 = null;
    private Button btnUnassign = null;
    private TableLayout tblPlayer = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        try
        {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_match);
            this.tsTeam1 = new TreeSet<>();
            this.tsTeam2 = new TreeSet<>();
            this.tsAllPlayers = new TreeSet<>();
            this.db = Database.newInstance();
            this.getAllViews();
            this.registrateEventhandlers();
            this.makePlayerRows();
        }
        catch (Exception e)
        {
            Toast toast = Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_LONG);
            toast.show();
        }
    }

    private void getAllViews() throws Exception
    {
        this.dpMatch = (DatePicker) findViewById(R.id.dpMatch);
        this.btnAdd = (Button) findViewById(R.id.btnAdd);
        this.btnTeam1 = (Button) findViewById(R.id.btnTeam1);
        this.btnTeam2 = (Button) findViewById(R.id.btnTeam2);
        this.btnUnassign = (Button) findViewById(R.id.btnUnassign);
        this.tblPlayer = (TableLayout) findViewById(R.id.tblPlayer);
    }

    private void registrateEventhandlers() throws Exception
    {
        this.btnAdd.setOnClickListener(this);
        this.btnTeam1.setOnClickListener(this);
        this.btnTeam2.setOnClickListener(this);
        this.btnUnassign.setOnClickListener(this);
    }

    public void onClick(View view) {
        try
        {
            if (view == this.btnTeam1)
            {
                // !!! Table-Lenght speichern, weil er sonst nachher in der Schleife die Table ja immer um 1 Row verringert, wenn ein Spieler einem Team hinzugefügt wird
                // und man somit nicht mehr durch alle Spieler, sondern nur die Hälfte durchgehen würde
                int tblLength = 0, counterSelectedPlayer = 0;
                tblLength = this.tblPlayer.getChildCount();

                //Alle Zeilen der Table durchgehen
                for(int i = 0; i < tblLength; i++) {
                    View viewRow = this.tblPlayer.getChildAt(counterSelectedPlayer);        //!!!! man kann hier nicht getChildAt(i) schreiben, weil der counter ja nicht jedes Mal weitergeht, weil beim Zeilen löschen ja der counter gleich bleiben muss

                    Player player = this.getPlayerOfTable(viewRow);
                    counterSelectedPlayer++;

                    //Nicht vergessen auf null abzufragen, weil theoretisch auch null von getPlayerOfTable() zurückkommen könnte.
                    //z.b. bei allen Player, wo die Checkbox nicht angehackelt ist (die also nicht in dieses Team kommen
                    if (player != null)
                    {
                        this.tsTeam1.add(player);
                        counterSelectedPlayer--;
                    }
                }
            }
            else
                if (view == this.btnTeam2)
                {
                    int tblLength = this.tblPlayer.getChildCount(), counterSelectedPlayer = 0;

                    //Alle Zeilen der Table durchgehen
                    for(int i = 0; i < tblLength; i++) {
                        View viewRow = this.tblPlayer.getChildAt(counterSelectedPlayer);

                        Player player = this.getPlayerOfTable(viewRow);
                        counterSelectedPlayer++;

                        //Nicht vergessen auf null abzufragen, weil theoretisch auch null von getPlayerOfTable() zurückkommen könnte
                        if (player != null)
                        {
                            this.tsTeam2.add(player);
                            counterSelectedPlayer--;
                        }
                    }
                }
                else
                    if (view == this.btnUnassign)
                    {
                        //Zuerst alle alten Zeilen aus der Table löschen, weil vielleicht noch ein paar Player drinnen sind und diese sonst doppelt sind
                        //Alle Zeilen der Table durchgehen
                        for(int i = 0; i < this.tblPlayer.getChildCount(); i++) {
                            View viewRow = this.tblPlayer.getChildAt(i);

                            if (viewRow instanceof TableRow) {
                                TableRow row = (TableRow) viewRow;

                                //Row aus Table entfernen!!!
                                this.tblPlayer.removeView(row);
                            }
                        }

                        //Die Player neu hinzufügen in die Table
                        this.makePlayerRows();
                    }
                    else
                        if (view == this.btnAdd)
                        {
                            boolean matchAdded = false;
                            SimpleDateFormat simpleDateFormatWebservice = new SimpleDateFormat("yyyy-MM-dd");        //E, dd.MM.yyyy (better readabilty, but incompatible with webservice)
                            //SimpleDateFormat simpleDateFormat = new SimpleDateFormat("E, dd.MM.yyyy");
                            Calendar c = Calendar.getInstance();

                            c.set(this.dpMatch.getYear(), this.dpMatch.getMonth(), this.dpMatch.getDayOfMonth());
                            Date date = c.getTime();
                            String dateNewString = simpleDateFormatWebservice.format(date);
                            //date = simpleDateFormatWebservice.parse(dateNewString);

                            Match match = new Match(dateNewString);
                            matchAdded = this.db.addMatchWebservice(match);      // !!! Webservice

                            if (matchAdded == false) {
                                throw new Exception("Match creation failed.");
                            }

                            //Damit die Activity geschlossen wird, und der User wieder auf seinem Startbildschirm ist
                            this.finish();
                        }
        }
        catch (Exception e)
        {
            Toast toast = Toast.makeText(getApplicationContext(), "Match not saved. Error: " + e.getMessage(), Toast.LENGTH_LONG);
            toast.show();
        }
    }

    //Make the rows with all Players in the table
    private void makePlayerRows() throws Exception
    {
        /*
        Webservice aufrufen
         */
        ArrayList<Player> arrListPlayer = this.db.getAllPlayersWebservice();
        this.tsAllPlayers = new TreeSet<>(arrListPlayer);

        //DOM-Baum-ähnliches "anhängen" der Elemente in die Gui
        for (Player player : arrListPlayer)
        {
            //Nur jene werden angezeigt, die zur Zeit auch spielbereit sind
            //if (player.isActive())
            //{
                TableRow tr = new TableRow(this);
                TableRow.LayoutParams tblRowLp = new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.WRAP_CONTENT);
                tr.setLayoutParams(tblRowLp);

                TextView textView = new TextView(this);
                textView.setBackgroundColor(Color.WHITE);
                textView.setText(player.getName());

                CheckBox checkBox = new CheckBox(this);
                checkBox.setBackgroundColor(Color.WHITE);
                checkBox.setChecked(false);

                tr.addView(textView);           //"anhängen" in Row
                tr.addView(checkBox);

                //row in table "anhängen"
                this.tblPlayer.addView(tr, new TableLayout.LayoutParams(TableLayout.LayoutParams.MATCH_PARENT, TableLayout.LayoutParams.WRAP_CONTENT));
            //}
        }
    }

    private Player getPlayerOfTable(View viewRow) throws Exception
    {
        Player player = null;

        //Schauen, ob es row ist
        if (viewRow instanceof TableRow) {
            TableRow row = (TableRow) viewRow;

            View viewCheckBox = row.getChildAt(1);

            //Schauen, ob es checkbox ist und ob sie true ist
            if (viewCheckBox instanceof CheckBox)
            {
                CheckBox checkBox = (CheckBox) viewCheckBox;

                //!!! Nur wenn CheckBox angehackelt ist, den Spieler zum Team hinzufügen
                if (checkBox.isChecked() == true)
                {
                    View viewTextView = row.getChildAt(0);

                    if (viewTextView instanceof TextView)
                    {
                        TextView textViewPlayer = (TextView) viewTextView;

                        //Player hier in der Match-Activity ins Team hinzufügen und dann später wenn "Add" geklickt wird, das Team von hier ins Match kopieren
                        player = this.tsAllPlayers.ceiling(new Player(textViewPlayer.getText().toString()));

                        //Row aus Table entfernen!!!
                        this.tblPlayer.removeView(row);
                    }
                }
            }
        }

        return player;
    }
}
