package herbsts.soccer;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
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
            this.registrateEventhandlers();
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

    private void fillSpinnerPlayer()
    {
        ArrayAdapter<Player> adapterPlayer = new ArrayAdapter<Player>(this, android.R.layout.simple_spinner_item, this.db.getArrayListPlayer());

        this.spPlayer.setAdapter(adapterPlayer);
    }

    @Override
    public void onClick(View view) {
        try
        {
            if (view == this.btnProfile)
            {
                this.txtMessage.setText("profile changed");
            }
            else
                if (view == this.btnAdd)
                {
                    AlertDialog.Builder builder = new AlertDialog.Builder(this);
                    builder.setTitle("New Player");

                    // Set up the input
                    final EditText input = new EditText(this);
                    // Specify the type of input expected; this, for example, sets the input as text
                    input.setInputType(InputType.TYPE_CLASS_TEXT);
                    builder.setView(input);

                    // Set up the buttons
                    builder.setPositiveButton("Add", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            try
                            {
                                String name = input.getText().toString();
                                Player player = new Player(name, false, false, false, false, true);
                                db.addPlayer(player);
                                fillSpinnerPlayer();

                                txtMessage.setText(player + " added");
                            }
                            catch (Exception e)
                            {
                                txtMessage.setText("error: " + e.getMessage());
                            }
                        }
                    });
                    builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            try
                            {
                                dialog.cancel();
                            }
                            catch (Exception e)
                            {
                                txtMessage.setText("error: " + e.getMessage());
                            }
                        }
                    });

                    builder.show();
                }
                else
                    if (view == this.btnRemove)
                    {
                        Player player = (Player) this.spPlayer.getSelectedItem();
                        //this.db.removePlayer(player);
                        this.fillSpinnerPlayer();           //nicht vergessen ComboBox zu refreshen

                        this.txtMessage.setText(player + " removed");
                    }
        }
        catch (Exception e)
        {
            this.txtMessage.setText("error: " + e.getMessage());
        }
    }
}
