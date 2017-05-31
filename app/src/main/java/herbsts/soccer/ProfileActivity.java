package herbsts.soccer;
 //
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import herbsts.soccer.pkgData.Player;

public class ProfileActivity extends AppCompatActivity implements View.OnClickListener {
    /*
    non-gui-attributes
     */
    private Database db = null;

    /*
    gui-attributes
     */
    private Button btnAddPlayer = null;
    private ListView listPlayers = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        try
        {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_profile);
            this.db = Database.newInstance();
            this.setTitle("Player Management");
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
        this.listPlayers = (ListView) findViewById(R.id.listPlayer);
        this.btnAddPlayer = (Button) findViewById(R.id.btnAddPlayer);
    }

    private void registrateEventhandlers() throws Exception
    {
        this.btnAddPlayer.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        try
        {
            if (view == this.btnAddPlayer)
            {
                //Intent intent = new Intent(this, AddProfileActivity.class);
                //startActivity(intent);
            }
        }
        catch (Exception e)
        {
            Toast toast = Toast.makeText(getApplicationContext(), "Player not saved. Error: " + e.getMessage(), Toast.LENGTH_LONG);
            toast.show();
        }
    }
}
