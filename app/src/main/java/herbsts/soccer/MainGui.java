package herbsts.soccer;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

public class MainGui extends AppCompatActivity implements View.OnClickListener {
    /*
    non-gui-attributes
     */
    private String playerName = null;

    /*
    gui-attributes
     */
    private ImageButton btnStatistic = null;
    private ImageButton btnProfile = null;
    private ImageButton btnCalendar = null;
    private ImageButton btnTeam = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        try
        {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.main);

            //Namen aus Intent holen
            this.setContent();
            //Titel setzen
            this.setTitle(this.playerName);

            this.getAllViews();
            this.registrateEventhandlers();
        }
        catch (Exception e)
        {
            Toast toast = Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_LONG);
            toast.show();
        }
    }

    private void setContent() throws Exception
    {
        this.playerName = getIntent().getStringExtra("intentPlayerName").toString();
    }

    private void getAllViews() throws Exception
    {
        this.btnStatistic = (ImageButton) findViewById(R.id.imgbtnstatistics);
        this.btnProfile = (ImageButton) findViewById(R.id.imgbtnprofile);
        this.btnCalendar = (ImageButton) findViewById(R.id.imgbtncalendar);
        this.btnTeam = (ImageButton) findViewById(R.id.imgbtnteam);
    }

    //e.g. onClick
    private void registrateEventhandlers() throws Exception
    {
        this.btnStatistic.setOnClickListener(this);
        this.btnProfile.setOnClickListener(this);
        this.btnCalendar.setOnClickListener(this);
        this.btnTeam.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        try
        {
            if (view == this.btnStatistic)
            {
                Intent intent = new Intent(this, StatisticPlayerActivity.class);
                //intent.putExtra("intentPlayerName", this.playerName);
                startActivity(intent);
            }
            else if (view == this.btnProfile)
            {
                Intent intent = new Intent(this, ProfileActivity.class);
                //intent.putExtra("intentPlayerName", this.playerName);
                startActivity(intent);
            }
            else if (view == this.btnCalendar)
            {
                Intent intent = new Intent(this, MatchDisplayActivity.class);
                startActivity(intent);
            }
            else if (view == this.btnTeam){
                Intent intent = new Intent(this, MatchActivity.class);
                startActivity(intent);
            }
        }
        catch (Exception e)
        {
            Toast toast = Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_LONG);
            toast.show();
        }
    }
}
