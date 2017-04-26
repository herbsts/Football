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

import java.util.Calendar;
import java.util.Date;

public class MatchActivity extends AppCompatActivity implements View.OnClickListener{
    /*
    non-gui-attributes
     */
    private Database db = null;

    /*
    gui-attributes
     */
    private DatePicker dpMatch = null;
    private Button btnAdd = null;
    private TableLayout tblPlayer = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        try
        {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_match);
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
        this.tblPlayer = (TableLayout) findViewById(R.id.tblPlayer);
    }

    private void registrateEventhandlers() throws Exception
    {
        this.btnAdd.setOnClickListener(this);
    }

    public void onClick(View view) {
        try
        {
            if (view == this.btnAdd)
            {
                Calendar c = Calendar.getInstance();
                c.set(this.dpMatch.getYear()+1900, this.dpMatch.getMonth(), this.dpMatch.getDayOfMonth());
                Date date = c.getTime();
                Toast toast = Toast.makeText(getApplicationContext(), "Match date: "+date.toString(), Toast.LENGTH_LONG);
                toast.show();

                // !!! Damit die Activity geschlossen wird, und der User wieder auf seinem Startbildschirm ist
                //this.finish();
            }
        }
        catch (Exception e)
        {
            Toast toast = Toast.makeText(getApplicationContext(), "Player not saved. Error: " + e.getMessage(), Toast.LENGTH_LONG);
            toast.show();
        }
    }

    //Make the rows with all Players in the table
    private void makePlayerRows()
    {
        //DOM-Baum-ähnliches "anhängen" der Elemente in die Gui
        for (Player player : this.db.getTsPlayer())
        {
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

            this.tblPlayer.addView(tr, new TableLayout.LayoutParams(TableLayout.LayoutParams.MATCH_PARENT, TableLayout.LayoutParams.WRAP_CONTENT));
        }
    }
}
