package herbsts.soccer;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.NumberPicker;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;

public class StatisticMatchActivity extends AppCompatActivity {
    /*
    non-gui-attributes
     */
    private Database db = null;
    private Match selectedMatch = null;

    /*
    gui-attributes
     */
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

        TextView textView = new TextView(this);
        textView.setBackgroundColor(Color.WHITE);
        textView.setText(player.getName());

        NumberPicker nPicker = new NumberPicker(this);
        nPicker.setBackgroundColor(Color.WHITE);
        nPicker.setWrapSelectorWheel(true);
        nPicker.setMinValue(0);

        tr.addView(textView);           //"anhängen" in Row
        tr.addView(nPicker);

        return tr;
    }
}
