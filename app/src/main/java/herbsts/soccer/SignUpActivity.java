package herbsts.soccer;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.Toast;

import static herbsts.soccer.R.id.txtMessage;

public class SignUpActivity extends AppCompatActivity implements View.OnClickListener {
    /*
    non-gui-attributes
     */
    private Database db = null;

    /*
    gui-attributes
     */
    private EditText txtName = null;
    private Spinner spPosition = null;
    private ImageButton btnAddPlayer = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        try
        {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.signup);

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
        this.txtName = (EditText) findViewById(R.id.playerName);
        this.spPosition = (Spinner) findViewById(R.id.playerPosition);
        this.btnAddPlayer = (ImageButton) findViewById(R.id.addUser);
    }

    //e.g. onClick
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
                //Überprüfung, dass ein Name eingegeben wurde
                if (this.txtName.getText() != null)
                {
                    if (this.db.getTsPlayer().contains(new Player(this.txtName.getText().toString())) == false)
                    {
                        int helpReturn;

                        String name = this.txtName.getText().toString();
                        Player player = new Player(name, false, false, false, false, true);
                        helpReturn = db.addPlayer(player);

                        //Wenn Player nicht hinzugefügt wurde
                        if (helpReturn != 1)
                        {
                            Toast toast = Toast.makeText(getApplicationContext(), "Player not added. May it already exists.", Toast.LENGTH_LONG);
                            toast.show();
                        }
                        else
                        {
                            /*
                            normales Login
                             */
                        }
                    }
                    else
                    {
                        /*
                        normales Login ohne neuen Player hinzufügen
                         */
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
}
