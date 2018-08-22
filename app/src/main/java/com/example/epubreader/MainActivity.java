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

        System.err.println(value);
       String temp="";
       int pos=0;
       String language="";
       String language1="";
       String bookname="";
       String bookname1="";
       String temp1="";
       for (int i=value.length()-1;i>=0;i--)
       {
           if (value.charAt(i)=='/') {
               pos=i;
               break;
           }
           else
               temp=temp+value.charAt(i);

       }
       temp1=reverser(temp);

       for (int i=pos-1;i>=0;i--)
       {
           if (value.charAt(i)=='/')
           {
               pos=i;
               break;
           }
           else
               language=language+value.charAt(i);
       }
       language1=reverser(language);

       for (int i=pos-1;i>=0;i--)
       {
           if (value.charAt(i)=='/')
           {
               pos=i;
               break;
           }
           else
               bookname=bookname+value.charAt(i);
       }
       bookname1=reverser(bookname);
       temp1=bookname1+"_"+temp1;
        File storageDir = new File(
                String.valueOf(Environment.getExternalStoragePublicDirectory("Ebooks"+"/"+language1+"/"+bookname1))
        );
        if(storageDir.exists())
            System.err.println("Do Nothing");
        if(!storageDir.exists())
       storageDir.mkdirs();

        System.err.println(storageDir.getPath());



        File fx=new File(String.valueOf(Environment.getExternalStoragePublicDirectory("Ebooks"+"/"+language1+"/"+bookname1+"/"+temp1)));
        if (!fx.exists()) {

            DownloadManager downloadManager = (DownloadManager) getSystemService(Context.DOWNLOAD_SERVICE);
            Uri uri = Uri.parse(value);
            DownloadManager.Request request = new DownloadManager.Request(uri);
          //  request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
            request.setDestinationInExternalPublicDir("Ebooks"+"/"+language1+"/"+bookname1, temp1);
            request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE);
            request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
            Long reference = downloadManager.enqueue(request);
            final File fx1=fx;
            final File fx2=fx;
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
            test(fx,fx,temp1);




    }



    public String reverser(String z)
    {
        String z1="";
        for (int i=z.length()-1;i>=0;i--)
            z1=z1+z.charAt(i);
        return z1;
    }
    void test(File fx,File f,String temp1)
    {
        if (fx.exists()) {
            progressDialog.dismiss();
            System.err.println("Kaam set");


            Config config = new Config().setThemeColor(R.color.colorPrimaryDark);
            FolioReader folioReader = FolioReader.getInstance(getApplicationContext());
            System.out.println(fx.getPath());
            folioReader.setConfig(config, true).openBook(fx.getPath());
finish();
        }

    }





    }


