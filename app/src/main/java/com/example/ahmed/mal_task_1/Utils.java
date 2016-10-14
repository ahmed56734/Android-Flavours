package com.example.ahmed.mal_task_1;

import android.util.Log;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by ahmed on 10/14/16.
 */

public class Utils {

    public static final String LOG_TAG = Utils.class.getSimpleName();


    public static List<AndroidVersion> fetchAndroidData(String stringUrl){

        URL url = null;
        String jsonResponse = null;

        try {
             url = new URL(stringUrl);
        } catch (MalformedURLException e) {
            Log.e(LOG_TAG, "error creating url", e);
        }

        try {

            jsonResponse = makeHTTPRequest(url);

        } catch (IOException e){
            Log.e(LOG_TAG, "error http request", e);
        }

        List<AndroidVersion> androidVersions = extractDataFromJSON(jsonResponse);

        return androidVersions;
    }

    private static String makeHTTPRequest(URL url) throws  IOException{
        String json = "";
        HttpURLConnection urlConnection = null;
        InputStream inputStream = null;

        try {
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();

            if(urlConnection.getResponseCode() == 200){     //200 ok

                inputStream = urlConnection.getInputStream();
                BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, Charset.forName("UTF-8")));
                StringBuilder stringBuilder = new StringBuilder();
                String line = reader.readLine();
                while (line != null){
                    stringBuilder.append(line);
                    line = reader.readLine();
                }

                json = stringBuilder.toString();


            }
            else{
                Log.e(LOG_TAG, "error wih respond code : " + urlConnection.getResponseCode());
            }



        } catch (IOException e) {
            Log.e(LOG_TAG, "error with url connection", e);

        } finally {

            if(urlConnection != null)
                urlConnection.disconnect();

            if (inputStream != null)
                inputStream.close();
        }

        return json;
    }

    private static List<AndroidVersion> extractDataFromJSON(String json){

        if(json == null)
            return null;

        try {
            JSONObject baseJSON = new JSONObject(json);
            JSONArray androidList = baseJSON.getJSONArray("android");
            JSONObject versions = androidList.getJSONObject(1);
            JSONArray versionsList = versions.getJSONArray("versions");

            List<AndroidVersion> androidVersions = new ArrayList<AndroidVersion>();
            String name ;
            String version;
            String apiLevel;
            for (int i = 0, n = versionsList.length(); i < n; i++){

                JSONObject androidVersion = versionsList.getJSONObject(i);
                name = androidVersion.getString("name");
                version = androidVersion.getString("version");
                apiLevel = androidVersion.getString("api_level");

                androidVersions.add(new AndroidVersion(name, version, apiLevel, MainActivity.imagesID.get(name)));
            }

            return androidVersions;

        }catch (JSONException e){
            Log.e(LOG_TAG, "error parsing json", e);
        }


        return null;

    }




}
