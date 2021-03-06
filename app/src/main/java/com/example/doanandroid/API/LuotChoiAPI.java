package com.example.doanandroid.API;

import org.json.JSONException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class LuotChoiAPI {
    public static String getLichSuChoi(String NguoiChoiID) {
        HttpURLConnection urlConnection = null;
        BufferedReader reader = null;
        String jSonNguoiChoi = null;
        try {
            URL requestURL = new URL("http://10.0.3.2:8000/api/nguoi-choi/show-luot-choi?NguoiChoiID=" + NguoiChoiID);
            urlConnection = (HttpURLConnection) requestURL.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();

            InputStream inputStream = urlConnection.getInputStream();

            reader = new BufferedReader(new InputStreamReader(inputStream));

            StringBuilder builder = new StringBuilder();

            String line;

            while ((line = reader.readLine()) != null) {
                builder.append(line);
            }
            jSonNguoiChoi = builder.toString();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return jSonNguoiChoi;
    }
}
