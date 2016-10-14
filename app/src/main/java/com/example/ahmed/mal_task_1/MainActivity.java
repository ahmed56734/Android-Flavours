package com.example.ahmed.mal_task_1;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.GridView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private static final String stringUrl = "https://gist.githubusercontent.com/anonymous/0adcc7e908f7bf2dd9380a89f13c9b28/raw/33c9520e0b9c38d4a3f56e6c8fb7a74d27dd87c4/blob.json";
    static Map<String, Integer> imagesID ;

    private VersionAdapter arrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imagesID = new HashMap<>();
        imagesID.put("Cupcake", R.drawable.cupcake);
        imagesID.put("Donut", R.drawable.donut);
        imagesID.put("Éclair", R.drawable.eclair);
        imagesID.put("Froyo", R.drawable.froyo);
        imagesID.put("Gingerbread", R.drawable.gingerbread);
        imagesID.put("Honeycomb", R.drawable.honeycomb);
        imagesID.put("Ice cream Sandwich", R.drawable.ice_cream_sandwich);
        imagesID.put("Jelly Bean", R.drawable.jelly_bean);
        imagesID.put("Kitkat", R.drawable.kitkat);
        imagesID.put("Lollipop", R.drawable.lollipop);
        imagesID.put("Marshmallow", R.drawable.marshmallow);

        Task task = new Task();
        task.execute(stringUrl);
    }


    public class Task extends AsyncTask<String, Void, List<AndroidVersion> >{

        @Override
        protected List<AndroidVersion> doInBackground(String... params) {

            return Utils.fetchAndroidData(params[0]);

        }

        @Override
        protected void onPostExecute(List<AndroidVersion> androidVersions) {

            arrayAdapter = new VersionAdapter(MainActivity.this, androidVersions);

            GridView gridView = (GridView) findViewById(R.id.activity_main);
            gridView.setAdapter(arrayAdapter);
        }
    }
}
