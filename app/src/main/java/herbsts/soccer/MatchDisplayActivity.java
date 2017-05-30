package herbsts.soccer;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;

import herbsts.soccer.pkgData.Match;

public class MatchDisplayActivity extends AppCompatActivity implements StatisticMatchActivity.OnMatchResultChangedListener {
    /*
        non-gui-attributes
    */
    private Database db = null;

    /*
    gui-attributes
     */
    private ListView lvMatch = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        try
        {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_match_display);
            this.db = Database.newInstance();
            this.getAllViews();
            this.makeMatchRows();
            this.registrateEventhandlers();
        }
        catch (Exception e)
        {
            Toast toast = Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_LONG);
            toast.show();
        }
    }

    private void getAllViews() throws Exception
    {
        this.lvMatch = (ListView) findViewById(R.id.lvMatch);
    }

    private void registrateEventhandlers() throws Exception
    {
        registerForContextMenu(lvMatch);            //Damit sich das Menü für Edit und Delete von den Matches aufklappt
        /**********Listener (statisch implementiert, weil man ja NICHT die Instanz der StatisticMatchActivity zum registrieren hat**********/
        StatisticMatchActivity.addOnMatchResultChangedListener(this);
    }

    @Override
    public void handleMatchResultChanged() {
        try {
            this.makeMatchRows();
        }
        catch (Exception e)
        {
            Toast toast = Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_LONG);
            toast.show();
        }
    }

    //Makes rows with all Matches in the table
    private void makeMatchRows() throws Exception
    {
        ArrayAdapter<Match> arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, this.db.getArrayListMatches());
        this.lvMatch.setAdapter(arrayAdapter);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.match_floating_context_menu, menu);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        String lvItemString, dateString;

        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        final int selectedRowIndex = info.position;

        try {
            switch (item.getItemId()) {
                case R.id.menuItemEdit:
                    lvItemString = lvMatch.getItemAtPosition(selectedRowIndex).toString();
                    dateString = lvItemString.split(",")[1];       // !!! Filtert das Datum OHNE dem ausgeschriebenen Tag davor heraus
                    dateString.trim();

                    Intent intent = new Intent(this, StatisticMatchActivity.class);
                    intent.putExtra("intentSelectedMatchDate", dateString);     //Weil compareTo nach Datum vergleicht kann man im neuen Intent das Match mit dem Date holen
                    startActivity(intent);

                    return true;

                case R.id.menuItemDelete:
                    SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");

                    lvItemString = lvMatch.getItemAtPosition(selectedRowIndex).toString();
                    dateString = lvItemString.split(",")[1];       // !!! Filtert das Datum OHNE dem ausgeschriebenen Tag davor heraus
                    dateString.trim();

                    Date date = sdf.parse(dateString);

                    //Match hier in der Match-Activity ins Team hinzufügen und dann später wenn "Add" geklickt wird, das Team von hier ins Match kopieren
                    final Match selectedMatch = this.db.getTsMatches().ceiling(new Match(date));

                    /**********Alert Dialog************/

                    AlertDialog.Builder builder = new AlertDialog.Builder(this);
                    builder.setMessage("Delete?");
                    builder.setCancelable(true);

                    builder.setPositiveButton(
                            "Yes",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    try {
                                        db.removeMatch(selectedMatch);
                                        makeMatchRows();
                                    } catch (Exception e) {
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

                    return true;

                default:
                    return super.onContextItemSelected(item);
            }
        }
        catch (Exception e)
        {
            Toast toast = Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_LONG);
            toast.show();
            return false;
        }
    }
}
