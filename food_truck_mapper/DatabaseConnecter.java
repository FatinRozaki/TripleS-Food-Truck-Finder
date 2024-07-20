package dev.lab.food_truck_mapper;

import android.util.Log;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class DatabaseConnecter {
    public static String connectToServer() {
        try {
            URL url = new URL("http://192.168.56.1/informatios/all.php");
            //...
            return "Connection successful";
        } catch (MalformedURLException e) {
            Log.e("DatabaseConnecter", "Error creating URL", e);
            return "Connection failed";
        }
    }
}