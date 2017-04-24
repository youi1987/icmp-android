package com.github.kirillf.icmptest;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private final String LOG_TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        int res = pingJNI("8.8.8.8", 5);
        Log.d(LOG_TAG, "Ping res: " + res);
    }

    public void onClick(View v) {
        Log.d(LOG_TAG, "ping button clicked");
        String address = "10.0.2.2";
        int time = pingJNI(address, 5);
        Toast.makeText(this, String.format("ping %s in %d s", address, time),
                Toast.LENGTH_SHORT).show();
    }

    public native int pingJNI(String host, int count);

    static {
        System.loadLibrary("icmpnative");
    }
}
