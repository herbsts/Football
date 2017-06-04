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
 * Last edit by Stefan Herbst on 31.05.2017: Finished "player/auth"
 */

public class PlayerController extends AsyncTask<Object, Void, String> {
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
                //Returned den gefundenen Player, wenn die Login-Daten übereinstimmen, bzw. einen Player mit id = -1, wenn die Login-Daten nicht stimmen
                if (command[1].equals("player/auth")) {
                    String newPlayer = gson.toJson(command[2]);
                    url = new URL(URI_FIX + command[1]);
                    HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
                    urlConnection.setDoOutput(true);
                    urlConnection.setRequestMethod("POST");
                    urlConnection.setRequestProperty("Content-Type", "application/json");

                    byte[] outputBytes = newPlayer.getBytes("UTF-8");
                    urlConnection.setRequestProperty("Content-Length", Integer.toString(outputBytes.length));
                    OutputStream os = urlConnection.getOutputStream();
                    os.write(outputBytes);

                    reader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
                    StringBuilder sb = new StringBuilder();
                    String line = null;

                    while((line = reader.readLine()) != null) {
                        sb.append(line);
                    }

                    response = sb.toString();

                    os.flush();
                    os.close();
                    reader.close();
                    urlConnection.disconnect();
                }
            }
            else
                if (command[0].equals("GET")) {
                    //Returned einen String, bestehend aus einer Liste aller Player
                    if (command[1].equals("player")) {
                        url = new URL(URI_FIX + command[1]);
                        URLConnection conn = url.openConnection();

                        reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                        StringBuilder sb = new StringBuilder();
                        String line = null;

                        while((line = reader.readLine()) != null) {
                            sb.append(line);
                        }

                        response = sb.toString();
                        reader.close();
                    }
                }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return response;
    }
}