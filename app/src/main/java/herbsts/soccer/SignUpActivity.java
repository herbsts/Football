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
    private EditText txtPassword = null;
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
        this.txtPassword = (EditText) findViewById(R.id.txtPlayerPassword);
        this.btnAddPlayer = (Button) findViewById(R.id.btnLogin);
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
                if (this.txtName.getText().toString().length() > 0)
                {
                    if (this.db.authPlayer(new Player(this.txtName.getText().toString(), this.txtPassword.getText().toString())))
                    {
                        /*
                        normales Login
                         */
                        Intent intent = new Intent(this, MainGui.class);
                        intent.putExtra("intentPlayerName", this.txtName.getText().toString());
                        startActivity(intent);
                    }
                    else
                    {
                        Toast toast = Toast.makeText(getApplicationContext(), "Please enter a name.", Toast.LENGTH_LONG);
                        toast.show();
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
