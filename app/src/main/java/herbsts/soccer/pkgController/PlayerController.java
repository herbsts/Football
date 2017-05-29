package herbsts.soccer.pkgController;

import android.os.AsyncTask;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created by Lorenz on 24.05.2017.
 */

public class PlayerController extends AsyncTask<Object, Void, String> {
    private static final String URI_FIX = "http://192.168.142.143/Soccer_Webservice/resources/";

    @Override
    protected String doInBackground(Object... command) {
        BufferedReader reader = null;
        BufferedWriter writer = null;
        String response = null;
        URL url = null;
        Gson gson = null;

        try {
            if (command[0].equals("POST")) {
                if (command[1].equals("player/auth")) {
                    OutputStream os = null;
                    gson = new Gson();
                    String playerString = gson.toJson(command[2]);          // ACHTUNG: Kann NUR in Klasse MIT 'extends AsynchTask<>' gemacht werden

                    url = new URL(URI_FIX + command[1]);
                    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                    conn.setDoOutput(true);
                    conn.setRequestMethod("POST");
                    conn.setRequestProperty("Content-Type", "application/json");

                    byte[] outputBytes = playerString.getBytes("UTF-8");
                    conn.setRequestProperty("Content-Length", Integer.toString(outputBytes.length));
                    os = conn.getOutputStream();
                    os.write(outputBytes);

                    response = Integer.toString(conn.getResponseCode());

                    os.flush();
                    os.close();
                    conn.disconnect();

/*
                    url = new URL(URI_FIX + command[1]);
                    //send data to server
                    URLConnection conn = url.openConnection();
                    //get data from server
                    reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));

                    StringBuilder sb = new StringBuilder();
                    String line = null;

                    while ((line = reader.readLine()) != null) {
                        sb.append(line);
                    }

                    content = sb.toString();*/
                }
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        return response;
    }
}
