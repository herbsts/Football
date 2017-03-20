package herbsts.soccer;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

//Comment after commit
public class MainActivity extends AppCompatActivity implements View.OnClickListener {
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
    private Button btnProfile = null;
    private Button btnAdd = null;
    private Button btnRemove = null;


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
        this.btnProfile = (Button) findViewById(R.id.btnProfile);
        this.btnAdd = (Button) findViewById(R.id.btnAdd);
        this.btnRemove = (Button) findViewById(R.id.btnRemove);
    }

    //e.g. onClick
    private void registrateEventhandlers() throws Exception
    {
        this.btnProfile.setOnClickListener(this);
        this.btnAdd.setOnClickListener(this);
        this.btnRemove.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view == this.btnProfile)
        {
            this.txtMessage.setText("profile changed");
        }
        else
            if (view == this.btnAdd)
            {
                this.txtMessage.setText("player added");
            }
            else
                if (view == this.btnRemove)
                {
                    this.txtMessage.setText("player removed");
                }
    }
}
