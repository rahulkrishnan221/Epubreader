package com.example.epubreader;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.folioreader.Config;
import com.folioreader.FolioReader;

public class splash extends AppCompatActivity {
    private static int SPLASH_TIME_OUT = 2000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        new Handler().postDelayed(new Runnable() {

            /*
                 * Showing splash screen with a timer. This will be useful when you
                 * want to show case your app logo / company
                 */
            @Override
            public void run() {
                // This method will be executed once the timer is over
                // Start your app main activity

                startActivity(new Intent(splash.this,MainActivity.class));
                // close this activity
           /*     Config config = new Config().setThemeColor(R.color.colorPrimaryDark);
                FolioReader folioReader = FolioReader.getInstance(getApplicationContext());
                System.out.println(folioReader);
                folioReader.setConfig(config, true).openBook(R.raw.adventures);
                */
                finish();
            }
        }, SPLASH_TIME_OUT);
    }
}
