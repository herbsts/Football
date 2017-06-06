package herbsts.soccer;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import herbsts.soccer.pkgData.Player;

/**
 * Created by Lorenz Fritz
 * Written by Stefan Herbst and Lorenz Fritz
 * Last edit by Stefan Herbst on 31.05.2017: Finished Login via Webservice
 */

public class Signup extends AppCompatActivity implements View.OnClickListener {
    /*
     *non-gui-attributes
     */
    private Database db = null;

    /*
     *gui-attributes
     */
    private EditText txtName = null;
    private EditText txtPlayerPassword = null;
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
        this.txtPlayerPassword = (EditText) findViewById(R.id.txtPlayerPassword);
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
                if (this.txtName.getText().length() > 0 && this.txtPlayerPassword.getText().length() > 0) {
                    if (db.checkPlayerWebservice(new Player(this.txtName.getText().toString(), this.txtPlayerPassword.getText().toString()))) {
                        Intent intent = new Intent(this, MainGui.class);
                        intent.putExtra("intentPlayerName", txtName.getText().toString());
                        startActivity(intent);
                    }
                    else {
                        Toast toast = Toast.makeText(getApplicationContext(), "Invalid Username/Password!", Toast.LENGTH_LONG);
                        toast.show();
                    }
                }
                else
                {
                    Toast toast = Toast.makeText(getApplicationContext(), "Please enter a name/password!", Toast.LENGTH_LONG);
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