package com.example.epubreader;

import android.app.DownloadManager;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import android.os.Environment;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.folioreader.Config;
import com.folioreader.FolioReader;

import java.io.File;

public class MainActivity extends AppCompatActivity {
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        progressDialog=ProgressDialog.show(MainActivity.this,"Please wait","Hold on......",true,true);
        setContentView(R.layout.activity_main);
        String value=getIntent().getStringExtra("file");
      File f=new File(String.valueOf(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS)));


       String temp="";
       String temp1="";
       for (int i=value.length()-1;i>=0;i--)
       {
           if (value.charAt(i)=='/')
               break;
           else
               temp=temp+value.charAt(i);

       }
       for (int i=temp.length()-1;i>=0;i--)
       temp1=temp1+temp.charAt(i);
        File fx=new File(String.valueOf(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS))+"/"+temp1);
        if (!fx.exists()) {

            DownloadManager downloadManager = (DownloadManager) getSystemService(Context.DOWNLOAD_SERVICE);
            Uri uri = Uri.parse(value);
            DownloadManager.Request request = new DownloadManager.Request(uri);
          //  request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
            request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, temp1);
            Long reference = downloadManager.enqueue(request);
            final File fx1=fx;
            final File fx2=f;
            final String tempx=temp1;
            new Handler().postDelayed(new Runnable() {

                /*
                     * Showing splash screen with a timer. This will be useful when you
                     * want to show case your app logo / company
                     */
                @Override
                public void run() {
                  if (fx1.exists())

                    test(fx1,fx2,tempx);
                  else
                      Toast.makeText(MainActivity.this, "Wait", Toast.LENGTH_SHORT).show();

                }
            }, 10000);



        }
        if (fx.exists())
            test(fx,f,temp1);


/*
       //File directory = new File(Environment.getDataDirectory() + "/RobotiumTestLog/");
        File mydir = this.getDir("usersx", Context.MODE_APPEND);
        if (!mydir.exists())
        {
            System.err.println("HELLO");
            mydir.mkdirs();
        }
        if (mydir.exists())
        {
            System.err.println(mydir.getPath());

        }
        mydir.delete();
        System.err.println(mydir.exists());
       System.err.println(mydir.getPath());

    /*   if (directory.exists())
       {
           Toast.makeText(this, "Done", Toast.LENGTH_SHORT).show();
       }
       if (!directory.exists())
       {
           System.err.println(directory.canWrite());
           if(!directory.mkdir())
               System.err.println("Hello");
           else
               directory.mkdir();
       } */
    }
    void test(File fx,File f,String temp1)
    {
        if (fx.exists()) {
            progressDialog.dismiss();
            System.err.println("Kaam set");

            Config config = new Config().setThemeColor(R.color.colorPrimaryDark);
            FolioReader folioReader = FolioReader.getInstance(getApplicationContext());
            System.out.println(folioReader);
            folioReader.setConfig(config, true).openBook(f.getPath() + "/" + temp1);
            finish();
        }
    }




    }


