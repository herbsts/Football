package herbsts.soccer;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Spinner;
import android.widget.TextView;

//Comment after commit
public class MainActivity extends AppCompatActivity {
    /*
    non-gui-attributes
     */
    private Database db = null;

    /*
    gui-attributes
     */
    private TextView txtMessage = null;
    private Spinner spPlayer = null;
    private Spinner spMatch = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        try
        {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);

            this.db = Database.newInstance();
            this.getAllViews();
        }
        catch (Exception e)
        {
            this.txtMessage.setText("error: " + e.getMessage());
        }
    }

    private void getAllViews() throws Exception
    {
        this.txtMessage = (TextView) findViewById(R.id.txtMessage);
        this.spPlayer = (Spinner) findViewById(R.id.spPlayer);
        this.spMatch = (Spinner) findViewById(R.id.spMatch);
    }

    //e.g. onClick
    private void registrateEventhandlers() throws Exception
    {

    }
}
