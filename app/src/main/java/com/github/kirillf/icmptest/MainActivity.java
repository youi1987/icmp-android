package com.github.kirillf.icmptest;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private final String LOG_TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    public void onClick(View v) {
        Log.d(LOG_TAG, "ping button clicked");
        new AsyncTask<Void, Void, Long>() {
            String ip = "8.8.8.8";
            @Override
            protected Long doInBackground(Void... params) {
                return pingJNI(ip, 5);
            }

            @Override
            protected void onPostExecute(Long time) {
                String messgage = String.format("ping %s in %d s", ip, time);
                TextView helloText = (TextView)findViewById(R.id.helloText);
                helloText.setText(messgage);
            }
        }.execute();

    }

    public native long pingJNI(String host, int count);

    static {
        System.loadLibrary("icmpnative");
    }
}
