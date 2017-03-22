package herbsts.soccer;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Spinner;
import android.widget.Toast;

import static herbsts.soccer.Position.*;

public class ProfileActivity extends AppCompatActivity implements View.OnClickListener {
    /*
    non-gui-attributes
     */
    private Database db = null;
    private String playerName = null;

    /*
    gui-attributes
     */
    private Spinner spPosition = null;
    private CheckBox chBoxActive = null;
    private Button btnSave = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        try
        {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_profile);
            this.db = Database.newInstance();
            this.setContent();
            this.getAllViews();
            this.registrateEventhandlers();
        }
        catch (Exception e)
        {
            Toast toast = Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_LONG);
            toast.show();
        }
    }

    //Setzt die übergebenen Parameter vom Intent
    private void setContent()
    {

    }

    private void getAllViews() throws Exception
    {
        this.spPosition = (Spinner) findViewById(R.id.spPosition);
        this.chBoxActive = (CheckBox) findViewById(R.id.chBoxActive);
        this.btnSave = (Button) findViewById(R.id.btnSave);
    }

    private void registrateEventhandlers() throws Exception
    {
        this.btnSave.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        try
        {
            if (view == this.btnSave)
            {
                boolean isGoalie = false, isDefender = false, isMidfielder = false, isForward = false, isActive = false;
                Position position = (Position) this.spPosition.getSelectedItem();

                //Setzt die jeweilige ausgewählte Position auf true
                switch (position)
                {
                    case GOALIE:
                        isGoalie = true;
                        break;
                    case DEFENDER:
                        isDefender = true;
                        break;
                    case MIDFIELDER:
                        isMidfielder = true;
                        break;
                    case FORWARD:
                        isForward = true;
                        break;
                }

                //aktiv nicht vergessen zu prüfen
                isActive = this.chBoxActive.isChecked();

                Player player = this.db.getTsPlayer().ceiling(new Player(this.playerName));
                player.updateProfile(isGoalie, isDefender, isMidfielder, isForward, isActive);          //updaten des Profiles des Spielers
            }
        }
        catch (Exception e)
        {
            Toast toast = Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_LONG);
            toast.show();
        }
    }
}
