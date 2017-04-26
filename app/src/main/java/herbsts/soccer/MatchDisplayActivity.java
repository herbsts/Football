package herbsts.soccer;

import android.content.DialogInterface;
import android.graphics.Color;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;

public class MatchDisplayActivity extends AppCompatActivity implements View.OnLongClickListener {
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
    private void makeMatchRows() throws Exception
    {
        //DOM-Baum-ähnliches "anhängen" der Elemente in die Gui
        for (Match match : this.db.getTsMatches())
        {
            TableRow tr = new TableRow(this);
            TableRow.LayoutParams tblRowLp = new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.WRAP_CONTENT);
            tr.setLayoutParams(tblRowLp);
            // !!! ACHTUNG: der OnLongClickListener wird hier bei jeder Row einzeln gesetzt und nicht auf die gesamte Table, damit man immer auf ein
            //einzelnes Match klicken muss und nicht irgendwo in Table klicken kann
            tr.setOnLongClickListener(this);

            TextView textView = new TextView(this);
            textView.setBackgroundColor(Color.WHITE);
            textView.setText(match.toString());     //Schreibt eh dass richtige anhand der toString-Methode

            tr.addView(textView);           //"anhängen" in Row

            //row in table "anhängen"
            this.tblMatch.addView(tr, new TableLayout.LayoutParams(TableLayout.LayoutParams.MATCH_PARENT, TableLayout.LayoutParams.WRAP_CONTENT));
        }
    }

    @Override
    public boolean onLongClick(View view) {
        boolean booleanReturn = true;

        try
        {
            if (view instanceof TableRow)
            {
                TableRow row = (TableRow) view;

                View viewTextView = row.getChildAt(0);

                if (viewTextView instanceof TextView)
                {
                    TextView textViewMatch = (TextView) viewTextView;

                    String dateString = textViewMatch.getText().toString().split(",")[0];
                    SimpleDateFormat sdf = new SimpleDateFormat();      //Kein Format drinnen, weil er default nehmen soll
                    Date date = sdf.parse(dateString);

                    //Match hier in der Match-Activity ins Team hinzufügen und dann später wenn "Add" geklickt wird, das Team von hier ins Match kopieren
                    final Match selectedMatch = this.db.getTsMatches().ceiling(new Match(date, -1, -1, null, null, null));

                    /**********Alert Dialog************/
                    AlertDialog.Builder builder = new AlertDialog.Builder(this);
                    builder.setMessage("Delete?");
                    builder.setCancelable(true);

                    builder.setPositiveButton(
                            "Yes",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    try
                                    {
                                        db.removeMatch(selectedMatch);
                                    }
                                    catch (Exception e)
                                    {
                                        Toast toast = Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_LONG);
                                        toast.show();
                                    }
                                }
                            });

                    builder.setNegativeButton(
                            "No",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    dialog.cancel();
                                }
                            });

                    AlertDialog alertDialog = builder.create();
                    alertDialog.show();
                }
            }
        }
        catch (Exception e)
        {
            booleanReturn = false;
            Toast toast = Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_LONG);
            toast.show();
        }

        return booleanReturn;
    }
}
