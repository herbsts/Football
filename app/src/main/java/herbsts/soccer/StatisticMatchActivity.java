package herbsts.soccer;

import android.graphics.Color;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.EventListener;


public class StatisticMatchActivity extends AppCompatActivity implements View.OnClickListener {
    /*
    non-gui-attributes
     */
    private Database db = null;
    private Match selectedMatch = null;
    TextWatcher textWatcher = null;

    /*
    gui-attributes
     */
    private TextView txtResultTeam1 = null;
    private TextView txtResultTeam2 = null;
    private TableLayout tblTeam1 = null;
    private TableLayout tblTeam2 = null;
    private Button btnSave = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        try {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_statistic_match);
            this.db = Database.newInstance();
            this.getAllViews();
            this.registrateEventhandlers();
            this.initializeTextWatcher();
            this.setContent();
            this.makePlayerRows();
        }
        catch (Exception e)
        {
            Toast toast = Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_LONG);
            toast.show();
        }
    }

    /***********Listener************/
    public interface OnMatchResultChangedListener extends EventListener
    {
        void handleMatchResultChanged();
    }

    private static OnMatchResultChangedListener listener = null;            // !!! statisch

    public static void addOnMatchResultChangedListener(OnMatchResultChangedListener l)
    {
        listener = l;
    }
    /***********************/

    private void getAllViews() throws Exception
    {
        this.txtResultTeam1 = (TextView) findViewById(R.id.txtResultTeam1);
        this.txtResultTeam2 = (TextView) findViewById(R.id.txtResultTeam2);
        this.tblTeam1 = (TableLayout) findViewById(R.id.tblTeam1);
        this.tblTeam2 = (TableLayout) findViewById(R.id.tblTeam2);
        this.btnSave = (Button) findViewById(R.id.btnSave);
    }

    private void registrateEventhandlers() throws Exception {
        this.btnSave.setOnClickListener(this);
    }

    private void initializeTextWatcher() throws Exception {
        this.textWatcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                int goals = 0;

                try {
                    goals = countGoals(tblTeam1);
                    txtResultTeam1.setText("" + goals);

                    goals = countGoals(tblTeam2);
                    txtResultTeam2.setText("" + goals);
                }
                catch (Exception e)
                {
                    Toast toast = Toast.makeText(getApplicationContext(), "error: " + e.getMessage(), Toast.LENGTH_LONG);
                    toast.show();
                }
            }
        };
    }

    //Setzt die übergebenen Parameter vom Intent
    private void setContent() throws Exception
    {
        String selectedMatchDate;
        Date date = null;
        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");

        selectedMatchDate = getIntent().getStringExtra("intentSelectedMatchDate").toString();
        date = sdf.parse(selectedMatchDate);
        this.selectedMatch = this.db.getSpecifyMatch(new Match(date));
    }

    private void makePlayerRows() throws Exception
    {
        //DOM-Baum-ähnliches "anhängen" der Elemente in die Gui
        //1. Team
        for (Player player : this.selectedMatch.getTsTeam1())
        {
            TableRow tr = makeTableElements(player);

            //row in table "anhängen"
            this.tblTeam1.addView(tr, new TableLayout.LayoutParams(TableLayout.LayoutParams.MATCH_PARENT, TableLayout.LayoutParams.WRAP_CONTENT));
        }

        //2. Team
        for (Player player : this.selectedMatch.getTsTeam2())
        {
            TableRow tr = makeTableElements(player);

            //row in table "anhängen"
            this.tblTeam2.addView(tr, new TableLayout.LayoutParams(TableLayout.LayoutParams.MATCH_PARENT, TableLayout.LayoutParams.WRAP_CONTENT));
        }
    }

    //Macht die Elemente der TableRow (damit man oben nicht 2 mal fast den gleichen Code für Team1 und Team2 hat)
    private TableRow makeTableElements(Player player) throws Exception {
        TableRow tr = new TableRow(this);
        TableRow.LayoutParams tblRowLp = new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.WRAP_CONTENT);
        tr.setLayoutParams(tblRowLp);

        TextView playerName = new TextView(this);
        playerName.setBackgroundColor(Color.WHITE);
        playerName.setText(player.getName());

        EditText eTextGoals = new EditText(this);
        eTextGoals.setInputType(InputType.TYPE_CLASS_NUMBER);
        eTextGoals.setText("0");
        /** !!!!!!!! TextWatcher adden!!!!!!!!! **/
        eTextGoals.addTextChangedListener(this.textWatcher);

        tr.addView(playerName);           //"anhängen" in Row
        tr.addView(eTextGoals);

        return tr;
    }

    //Erhöht die GESAMTEN Tore ganz oben in der Gui, wenn bei einem Player die Tore erhöht werden
    @Override
    public void onClick(View v) {
        try {
            if (v == this.btnSave)
            {
                this.saveResult(this.tblTeam1);
                this.saveResult(this.tblTeam2);

                this.selectedMatch.setGoalsMadeTeam1(Integer.parseInt(this.txtResultTeam1.getText().toString()));
                this.selectedMatch.setGoalsMadeTeam2(Integer.parseInt(this.txtResultTeam2.getText().toString()));

                Toast toast = Toast.makeText(getApplicationContext(), "successfully saved", Toast.LENGTH_LONG);
                toast.show();

                // !!!! Listener !!!!!!!
                if (listener != null)       //...to avoid null-pointer Exception (listener muss gesetzt sein (also der gui zugewiesen sein (siehe initOtherComponents)))
                {
                    listener.handleMatchResultChanged();      //Listener ist ja in dem Fall die gui, also geht er zur Methode drüben
                }

                //Damit die Activity geschlossen wird
                this.finish();
            }
        }
        catch (Exception e)
        {
            Toast toast = Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_LONG);
            toast.show();
        }
    }

    //Zählt alle Tore des jeweiligen Teams zusammen
    private int countGoals(TableLayout tblTeam) throws Exception
    {
        int goals = 0;

        for (int i=0; i < tblTeam.getChildCount(); i++)
        {
            View rowView = tblTeam.getChildAt(i);

            if (rowView instanceof TableRow)
            {
                TableRow row = (TableRow) rowView;

                View eTextView = row.getChildAt(1);

                if (eTextView instanceof EditText)
                {
                    EditText eText = (EditText) eTextView;
                    String goalsString = eText.getText().toString();

                    //Braucht man, weil wenn User in EditText hineinklickt und zahl löscht, würde eine Exception beim TextWatcher (afterTextChanged) kommen, weil ja keine Zahl drin ist
                    if (goalsString.equals(""))
                    {
                        goalsString = "0";
                    }

                    goals += Integer.parseInt(goalsString);
                }
            }
        }

        return goals;
    }

    private void saveResult(TableLayout tblTeam) throws Exception {
        int goals = 0;

        for (int i=0; i < tblTeam.getChildCount(); i++)
        {
            View rowView = tblTeam.getChildAt(i);

            if (rowView instanceof TableRow)
            {
                TableRow row = (TableRow) rowView;

                View txtPlayerNameView = row.getChildAt(0);
                View eTextView = row.getChildAt(1);

                if (txtPlayerNameView instanceof TextView)
                {
                    TextView txtPlayerName = (TextView) txtPlayerNameView;
                    String playerName = txtPlayerName.getText().toString();

                    if (eTextView instanceof EditText)
                    {
                        EditText eText = (EditText) eTextView;
                        String goalsString = eText.getText().toString();

                        //Braucht man, weil wenn User in EditText hineinklickt und zahl löscht, würde eine Exception beim TextWatcher (afterTextChanged) kommen, weil ja keine Zahl drin ist
                        if (goalsString.equals(""))
                        {
                            goalsString = "0";
                        }

                        goals += Integer.parseInt(goalsString);

                        /*** Player updaten ***/
                        Player player = new Player(playerName);
                        Statistic statistic = new Statistic(this.selectedMatch);

                        if (tblTeam == this.tblTeam1)
                        {
                            //Damit man nicht 2 mal die gleiche Statistik haben kann
                            if (this.selectedMatch.getTsTeam1().ceiling(player).getTsStatistic().contains(statistic) == false)
                            {
                                statistic = new Statistic(this.selectedMatch, goals, 0, 0, 0, 0, 0);
                                this.selectedMatch.getTsTeam1().ceiling(player).addStatistic(statistic);
                            }
                        }
                        else
                            if (tblTeam == this.tblTeam2)
                            {
                                statistic = new Statistic(this.selectedMatch, goals, 0, 0, 0, 0, 0);
                                this.selectedMatch.getTsTeam2().ceiling(player).addStatistic(statistic);
                            }
                    }
                }
            }
        }
    }
}
