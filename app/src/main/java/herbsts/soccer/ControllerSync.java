package herbsts.soccer;

import android.os.AsyncTask;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created by Stefan Herbst on 03.05.2017.
 */

public class ControllerSync extends AsyncTask<String, Void, String> {
    private static final String URI_FIX = "http://192.168.194.156:8080/CanteenServer/webresources/";

    public ControllerSync() throws Exception{
    }

    @Override
    protected String doInBackground(String... command){
        BufferedReader reader = null;
        String content = null;
        URL url = null;

        try{
            if(command[0].equals("LIST")){
                url = new URL(URI_FIX + "FoodList/03.05.2017" + command[1]);
                URLConnection conn = url.openConnection();
                reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                StringBuilder sb = new StringBuilder();
                String line = null;

                while((line = reader.readLine()) != null){
                    sb.append(line);
                }
                content = sb.toString();
                System.out.println(content);
            }
        }
        catch (MalformedURLException e) {
            e.printStackTrace();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        return "ok";
    }
}