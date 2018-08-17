package com.example.epubreader;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.folioreader.Config;
import com.folioreader.FolioReader;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Config config = new Config().setThemeColor(R.color.colorPrimaryDark);
        FolioReader folioReader = FolioReader.getInstance(getApplicationContext());
        System.out.println(folioReader);
        folioReader.setConfig(config, true).openBook(R.raw.adventures);
        finish();
    }
}
