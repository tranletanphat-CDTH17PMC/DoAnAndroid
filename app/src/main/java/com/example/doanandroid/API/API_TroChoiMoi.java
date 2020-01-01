package com.example.doanandroid.API;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class API_TroChoiMoi {
     public static String getDSCauHoi(String linhVucId) {
        HttpURLConnection urlConnection = null;
        BufferedReader reader = null;
        String jSonCauHoi = null;
        try {
            URL requestURL = new URL("http://10.0.3.2:8000/api/cau-hoi/thuoc?linhVucId=" + linhVucId);
            urlConnection = (HttpURLConnection) requestURL.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();

            InputStream inputStream = urlConnection.getInputStream();

            reader = new BufferedReader(new InputStreamReader(inputStream));

            StringBuilder builder = new StringBuilder();

            String line;

            while ((line = reader.readLine()) != null)
            {
                builder.append(line);
            }
            jSonCauHoi = builder.toString();
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
        return jSonCauHoi;
    }
}
