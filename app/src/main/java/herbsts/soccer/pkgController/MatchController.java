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
 * Created by Lorenz Fritz
 * Written by Stefan Herbst and Lorenz Fritz
 * Last edit by Lorenz Fritz on 04.06.2017: Finished "match"
 */

public class MatchController extends AsyncTask<Object, Void, String> {
    private static final String URI_FIX = "http://212.152.179.116:8080/Soccer_Webservice/resources/";       //interne IP: 192.168.142.143    externe IP: 212.152.179.116

    @Override
    protected String doInBackground(Object... command) {
        BufferedReader reader = null;
        BufferedWriter writer = null;
        String response = null;
        URL url = null;
        Gson gson = new Gson();

        try {
            if (command[0].equals("POST")) {
                //Returned das geaddete Match, wenn die Daten passen bzw ein Match mit id = -1, wenn die Daten nicht stimmen.
                if (command[1].equals("match")) {
                    String newMatch = gson.toJson(command[2]);
                    url = new URL(URI_FIX + command[1]);
                    HttpURLConnection httpUrlConnection = (HttpURLConnection) url.openConnection();
                    httpUrlConnection.setDoOutput(true);
                    httpUrlConnection.setRequestMethod("POST");
                    httpUrlConnection.setRequestProperty("Content-Type", "application/json");

                    byte[] outputBytes = newMatch.getBytes("UTF-8");
                    httpUrlConnection.setRequestProperty("Content-Length", Integer.toString(outputBytes.length));
                    OutputStream os = httpUrlConnection.getOutputStream();
                    os.write(outputBytes);

                    reader = new BufferedReader(new InputStreamReader(httpUrlConnection.getInputStream()));
                    StringBuilder sb = new StringBuilder();
                    String line = null;

                    while((line = reader.readLine()) != null) {
                        sb.append(line);
                    }

                    response = sb.toString();       //hier ist im response ist das Match als String

                    os.flush();
                    os.close();
                    reader.close();
                    httpUrlConnection.disconnect();
                }
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return response;
    }
}
