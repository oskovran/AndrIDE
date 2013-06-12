package com.oskovran.andride;

import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.Menu;
import android.content.res.AssetManager;

import java.io.InputStream;

public class AndrideActivity extends Activity {

    private static final String LOG_NAME = AndrideActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Read the contents of our asset
        try {
            AssetManager assets = getApplicationContext().getAssets();
            InputStream is = assets.open("classes.dex");
            Log.i(LOG_NAME, is.toString());
        } catch(Exception e) {
            Log.e(LOG_NAME, e.getMessage());
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.andride, menu);
        return true;
    }
    
}
