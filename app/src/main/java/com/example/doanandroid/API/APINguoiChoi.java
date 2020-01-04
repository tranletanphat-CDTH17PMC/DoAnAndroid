package com.example.doanandroid.API;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

public class APINguoiChoi {
    public static String getAPINguoiChoi(String username, String password) {
        HttpURLConnection urlConnection = null;
        BufferedReader reader = null;
        String jSonNguoiChoi = null;
        try {
            URL requestURL = new URL("http://10.0.3.2:8000/api/dang-nhap");
            urlConnection = (HttpURLConnection) requestURL.openConnection();
            String urlParams = "username=" + URLEncoder.encode(username, "UTF-8") +
                    "&password=" + URLEncoder.encode(password, "UTF-8");
            urlConnection.setDoOutput(true);
            urlConnection.setRequestMethod("POST");
            urlConnection.connect();
            DataOutputStream dos = new DataOutputStream(urlConnection.getOutputStream());
            // chuyern user va pass cho web server
            dos.writeBytes(urlParams);
            dos.flush();
            dos.close();
            InputStream inputStream = urlConnection.getInputStream();

            reader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));

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

    public static String getDSAPINguoiChoi() {
        HttpURLConnection urlConnection = null;
        BufferedReader reader = null;
        String jSonNguoiChoi = null;
        try {
            URL requestURL = new URL("http://10.0.3.2:8000/api/nguoi-choi/danh-sach");
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
