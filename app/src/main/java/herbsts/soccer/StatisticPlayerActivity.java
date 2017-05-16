package herbsts.soccer;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import java.util.TreeSet;

public class StatisticPlayerActivity extends AppCompatActivity {
    /*
    non-gui-attributes
     */
    private Database db = null;
    private Player player = null;

    /*
    gui-attributes
     */
    private TextView txtGoalsPlayer = null;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        try {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_statistic_player);
            this.db = Database.newInstance();
            this.getAllViews();
            this.setContent();
            this.fillStatistic();
        }
        catch (Exception e)
        {
            Toast toast = Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_LONG);
            toast.show();
        }
    }

    private void getAllViews() throws Exception {
        this.txtGoalsPlayer = (TextView) findViewById(R.id.txtGoalsPlayer);
    }

    private void setContent() throws Exception {
        String playerName = getIntent().getStringExtra("intentPlayerName").toString();
        this.player = this.db.getTsPlayer().ceiling(new Player(playerName));
    }

    private void fillStatistic() throws Exception {
        this.txtGoalsPlayer.setText("" + this.getGoalsShotStatistic());
    }

    private int getGoalsShotStatistic() throws Exception {
        int goalsShot = 0;

        for (Statistic statistic : this.player.getTsStatistic())
        {
            goalsShot += statistic.getGoalsShot();
        }

        return goalsShot;
    }
}
