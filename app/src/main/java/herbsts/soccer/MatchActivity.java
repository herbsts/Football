package herbsts.soccer;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.CancellationException;

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
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        try
        {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_match);
            this.db = Database.newInstance();
            this.getAllViews();
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
        this.dpMatch = (DatePicker) findViewById(R.id.dpMatch);
        this.btnAdd = (Button) findViewById((R.id.btnAdd));
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
                //works!
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
}
