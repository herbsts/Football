package herbsts.soccer;

import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.app.AlertDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
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
            this.getAllViews();
            this.registrateEventhandlers();
            this.fillSpinnerPositions();
            this.setContent();
        }
        catch (Exception e)
        {
            Toast toast = Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_LONG);
            toast.show();
        }
    }

    //Setzt die übergebenen Parameter vom Intent und die jeweiligen Werte vom Player (Position vom Spinner und ob er 'active' ist)
    private void setContent() throws Exception
    {
        this.playerName = getIntent().getStringExtra("intentPlayerName").toString();

        Player player = this.db.getSpecifyPlayer(new Player(playerName));

        //Position des Players herausfinden
        Position pos = player.getSelectedPosition();

        ArrayAdapter<Position> myAdap = (ArrayAdapter) this.spPosition.getAdapter(); //cast to an ArrayAdapter
        int spinnerPosition = myAdap.getPosition(pos);

        //set the default according to value
        this.spPosition.setSelection(spinnerPosition);

        boolean isActive = player.isActive();
        this.chBoxActive.setChecked(isActive);
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

    private void fillSpinnerPositions() throws Exception
    {
        //Holt die Werte des Enums und füllt den Spinner
        ArrayAdapter<Position> adapterPositions = new ArrayAdapter<Position>(this, android.R.layout.simple_spinner_item, Position.values());
        this.spPosition.setAdapter(adapterPositions);
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

                Toast toast = Toast.makeText(getApplicationContext(), "Player succesfully saved.", Toast.LENGTH_LONG);
                toast.show();

                // !!! Damit die Activity geschlossen wird, und der User wieder auf seinem Startbildschirm ist
                this.finish();
            }
        }
        catch (Exception e)
        {
            Toast toast = Toast.makeText(getApplicationContext(), "Player not saved. Error: " + e.getMessage(), Toast.LENGTH_LONG);
            toast.show();
        }
    }
}
