package com.example.android.myapplication;

/**
 * Created by mahak on 6/10/17.
 */

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;

import io.netopen.hotbitmapgg.library.view.RingProgressBar;

public class Progressbar extends AppCompatActivity {

    RingProgressBar ringProgressBar1, ringProgressBar2;

    int progress=0;

    Handler myhandler=new Handler()
    {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if(msg.what==0)
            {
                if(progress<100)
                {
                    progress++;
                    ringProgressBar1.setProgress(progress);
                    ringProgressBar2.setProgress(progress);
                }
            }
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ringProgressBar1= (RingProgressBar)findViewById(R.id.p1);
        ringProgressBar2=(RingProgressBar)findViewById(R.id.p2);

//        ringProgressBar1.setOnProgressListener(new RingProgressBar.OnProgressListener() {
//            @Override
//            public void progressToComplete() {
//                Toast.makeText(Progressbar.this, "Completed!!!",Toast.LENGTH_SHORT).show();
//            }
//        } );

        new Thread(new Runnable() {
            @Override
            public void run() {
                for(int i=0;i<100;i++)
                {
                    try
                    {
                        Thread.sleep(100);
                        myhandler.sendEmptyMessage(0);
                    } catch (InterruptedException e) {
                        e.printStackTrace();

                    }
                }
            }
        }).start();
    }
}


