package herbsts.soccer;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

public class MatchDisplayActivity extends AppCompatActivity {
    /*
        non-gui-attributes
         */
    private Database db = null;

    /*
    gui-attributes
     */
    private TableLayout tblMatch = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        try
        {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_match_display);
            this.db = Database.newInstance();
            this.getAllViews();
            this.makeMatchRows();
        }
        catch (Exception e)
        {
            Toast toast = Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_LONG);
            toast.show();
        }
    }

    private void getAllViews() throws Exception
    {
        this.tblMatch = (TableLayout) findViewById(R.id.tblMatch);
    }

    //Makes rows with all Matches in the table
    private void makeMatchRows()
    {
        //DOM-Baum-ähnliches "anhängen" der Elemente in die Gui
        for (Match match : this.db.getTsMatches())
        {
            TableRow tr = new TableRow(this);
            TableRow.LayoutParams tblRowLp = new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.WRAP_CONTENT);
            tr.setLayoutParams(tblRowLp);

            TextView textView = new TextView(this);
            textView.setBackgroundColor(Color.WHITE);
            textView.setText(match.toString());     //Schreibt eh dass richtige anhand der toString-Methode

            tr.addView(textView);           //"anhängen" in Row

            //row in table "anhängen"
            this.tblMatch.addView(tr, new TableLayout.LayoutParams(TableLayout.LayoutParams.MATCH_PARENT, TableLayout.LayoutParams.WRAP_CONTENT));
        }
    }
}
