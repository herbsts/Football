package herbsts.soccer.pkgController;

import android.os.AsyncTask;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created by Lorenz on 24.05.2017.
 */

public class PlayerController extends AsyncTask<String, Void, String> {
    private static final String URI_FIX = "http://localhost:8080/Soccer_Webservice/resources/";

    @Override
    protected String doInBackground(String... command) {
        BufferedReader reader = null;
        String content = null;
        URL url = null;
        BufferedWriter writer = null;

        try {
            if (command[0].equals("POST")) {
                if (command[1].equals("player")) {
                    url = new URL(URI_FIX + "player/" + command[1]);
                    //send data to server
                    URLConnection conn = url.openConnection();
                    //get data from server
                    reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));

                    StringBuilder sb = new StringBuilder();
                    String line = null;

                    while ((line = reader.readLine()) != null) {
                        sb.append(line);
                    }

                    content = sb.toString();
                }
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        return content;
    }
}
