package com.example.doanandroid.ManHinhDangNhap.ManHinhChinh;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class APIQuanLiTaiKhoan {
    static String getAPIDSLinhVuc() {
        HttpURLConnection urlConnection = null;
        BufferedReader reader = null;
        String jSonLinhVuc = null;
        try {
            URL requestURL = new URL("http://10.0.3.2:8000/api/linh-vuc/danh-sach");
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
            jSonLinhVuc = builder.toString();
        }catch (IOException e)
        {
            e.printStackTrace();
        }finally {
            if(urlConnection != null)
            {
                urlConnection.disconnect();
            }
            if(reader != null)
            {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return jSonLinhVuc;
    }
}
