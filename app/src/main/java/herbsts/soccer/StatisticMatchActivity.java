package herbsts.soccer;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.EditText;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;


public class StatisticMatchActivity extends AppCompatActivity implements View.OnClickListener {
    /*
    non-gui-attributes
     */
    private Database db = null;
    private Match selectedMatch = null;

    /*
    gui-attributes
     */
    private TextView txtResultTeam1 = null;
    private TextView txtResultTeam2 = null;
    private TableLayout tblTeam1 = null;
    private TableLayout tblTeam2 = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        try {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_statistic_match);
            this.db = Database.newInstance();
            this.getAllViews();
            this.setContent();
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
        this.txtResultTeam1 = (TextView) findViewById(R.id.txtResultTeam1);
        this.txtResultTeam2 = (TextView) findViewById(R.id.txtResultTeam2);
        this.tblTeam1 = (TableLayout) findViewById(R.id.tblTeam1);
        this.tblTeam2 = (TableLayout) findViewById(R.id.tblTeam2);
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
        eTextGoals.setOnClickListener(this);

        tr.addView(playerName);           //"anhängen" in Row
        tr.addView(eTextGoals);

        return tr;
    }

    //Erhöht die GESAMTEN Tore ganz oben in der Gui, wenn bei einem Player die Tore erhöht werden
    @Override
    public void onClick(View v) {
        try {
            if (v instanceof EditText)
            {
                int goals = 0;

                TableRow selectedRow = (TableRow) v.getParent();
                TableLayout selectedTable = (TableLayout) selectedRow.getParent();

                if (selectedTable == this.tblTeam1)
                {
                    goals = countGoals(this.tblTeam1);
                    this.txtResultTeam1.setText("" + goals);
                }
                else
                if (selectedTable == this.tblTeam2)
                {
                    goals = countGoals(this.tblTeam2);
                    this.txtResultTeam2.setText("" + goals);
                }
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

                    goals += Integer.parseInt(eText.getText().toString());
                }
            }
        }

        return goals;
    }
}
