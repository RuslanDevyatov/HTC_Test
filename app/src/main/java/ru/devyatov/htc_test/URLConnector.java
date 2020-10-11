package ru.devyatov.htc_test;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class URLConnector {

    private static final String URL = "http://www.mocky.io/v2/5ddcd3673400005800eae483";

    static JSONObject getJSONObject() {

        JSONObject JSONObject = null;
        HttpURLConnection urlConnection = null;
        BufferedReader bufferedReader = null;
        String requestResult;

        try {
            java.net.URL requestURL = new URL(URL);

            urlConnection = (HttpURLConnection) requestURL.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();

            InputStream inputStream = urlConnection.getInputStream();

            bufferedReader = new BufferedReader(new InputStreamReader(inputStream));

            StringBuilder strBuilder = new StringBuilder();
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                strBuilder.append(line);
                strBuilder.append("\n");
            }

            if (strBuilder.length() == 0) {
                return null;
            }

            requestResult = strBuilder.toString();
            JSONObject = new JSONObject(requestResult);
            JSONObject = JSONObject.getJSONObject("company");

        } catch (IOException | JSONException e) {
            e.printStackTrace();
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }

            if (bufferedReader != null) {
                try {
                    bufferedReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return JSONObject;
    }
}
