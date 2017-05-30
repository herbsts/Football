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
    private static final String URI_FIX = "http://192.168.142.143:8080/Soccer_Webservice/resources/";

    @Override
    protected String doInBackground(Object... command) {
        BufferedReader reader = null;
        BufferedWriter writer = null;
        String response = null;
        URL url = null;
        Gson gson = new Gson();

        try {
            if (command[0].equals("POST")) {
                if (command[1].equals("player/auth")) {
                    String newPlayer = gson.toJson(command[2]);

                    url = new URL(URI_FIX + command[1]);
                    HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
                    urlConnection.setDoOutput(true);
                    urlConnection.setRequestMethod("POST");
                    //urlConnection.setRequestProperty("Content-Type", "application/json");

                    byte[] outputBytes = newPlayer.getBytes("UTF-8");
                    //urlConnection.setRequestProperty("Content-Length", Integer.toString(outputBytes.length));
                    OutputStream os = urlConnection.getOutputStream();
                    os.write(outputBytes);

                    reader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
                    StringBuilder sb = new StringBuilder();
                    String line = null;

                    while((line = reader.readLine()) != null) {
                        sb.append(line);
                    }

                    response = sb.toString();

                    response = Integer.toString(urlConnection.getResponseCode());
                    System.out.println("***********************************"+response+"***********************************");
                    os.flush();
                    os.close();
                    reader.close();
                    urlConnection.disconnect();


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
