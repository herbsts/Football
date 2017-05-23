package herbsts.soccer;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.Toast;


public class SignUpActivity extends AppCompatActivity implements View.OnClickListener {
    /*
    non-gui-attributes
     */
    private Database db = null;

    /*
    gui-attributes
     */
    private EditText txtName = null;
    //private Spinner spPosition = null;
    private Button btnAddPlayer = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        try
        {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.signup);

            this.db = Database.newInstance();
            this.getAllViews();
            this.registrateEventhandlers();
            //fillSpinnerPositions();

        }
        catch (Exception e)
        {
            Toast toast = Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_LONG);
            toast.show();
        }
    }

    private void getAllViews() throws Exception
    {
        this.txtName = (EditText) findViewById(R.id.txtPlayerName);
        //this.spPosition = (Spinner) findViewById(R.id.playerPosition);
        this.btnAddPlayer = (Button) findViewById(R.id.btnLogin);
    }

    //e.g. onClick
    private void registrateEventhandlers() throws Exception
    {
        this.btnAddPlayer.setOnClickListener(this);
    }

    /*
    private void fillSpinnerPositions() throws Exception
    {
        //Holt die Werte des Enums und füllt den Spinner
        ArrayAdapter<Position> adapterPositions = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, Position.values());
        this.spPosition.setAdapter(adapterPositions);
    }
    */
    @Override
    public void onClick(View view) {
        try
        {
            if (view == this.btnAddPlayer)
            {
                //Überprüfung, dass ein Name eingegeben wurde
                if (this.txtName.getText() != null)
                {
                    if (!this.db.getTsPlayer().contains(new Player(this.txtName.getText().toString())))
                    {
                        /*
                        Hinzufügen des neuen Players
                         */
                        boolean helpReturn;

                        String name = this.txtName.getText().toString();
                        Player newPlayer = new Player(name, false, false, false, false, true);
                        helpReturn = db.addPlayer(newPlayer);

                        //Wenn Player nicht hinzugefügt wurde
                        if (!helpReturn)
                        {
                            Toast toast = Toast.makeText(getApplicationContext(), "Player not added. May it already exists.", Toast.LENGTH_LONG);
                            toast.show();
                        }
                        else
                        {
                            /*
                            Position beim Player updaten, weil man die Position beim neuen Spieler ja auswählen kann
                             */
                            //boolean isGoalie = false, isDefender = false, isMidfielder = false, isForward = false;
                            /*
                            String position = this.spPosition.getSelectedItem().toString();

                            switch (position)
                            {
                                case "Goalie":
                                    isGoalie = true;
                                    break;
                                case "Defender":
                                    isDefender = true;
                                    break;
                                case "Midfielder":
                                    isMidfielder = true;
                                    break;
                                case "Forward":
                                    isForward = true;
                                    break;
                            }
                            */
                            newPlayer.updateProfile(true, false, false, false, true);

                            /*
                            normales Login
                             */
                            Intent intent = new Intent(this, MainGui.class);
                            intent.putExtra("intentPlayerName", name);
                            startActivity(intent);
                        }
                    }
                    else
                    {
                        /*
                        normales Login ohne neuen Player hinzufügen
                         */
                        String playerName = this.txtName.getText().toString();

                        Intent intent = new Intent(this, MainGui.class);
                        intent.putExtra("intentPlayerName", playerName);
                        startActivity(intent);
                    }
                }
                else
                {
                    Toast toast = Toast.makeText(getApplicationContext(), "Please enter a name.", Toast.LENGTH_LONG);
                    toast.show();
                }
            }
        }
        catch (Exception e)
        {
            Toast toast = Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_LONG);
            toast.show();
        }
    }

    //Returnt die Position als Datentyp Position (=Enum)
    private Position getPosition(String posString)
    {
        Position pos = null;

        switch (posString)
        {
            case "Goalie":
                pos = Position.GOALIE;
                break;
            case "Defender":
                pos = Position.DEFENDER;
                break;
            case "Mitfielder":
                pos = Position.MIDFIELDER;
                break;
            case "Forward":
                pos = Position.FORWARD;
                break;
        }

        return pos;
    }
}
