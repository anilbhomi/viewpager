package com.aanealoneal.viewpager.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.ProgressBar;

import com.aanealoneal.viewpager.R;

public class SplashActivity extends Activity {

    private ProgressBar pbSplashScreen;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        pbSplashScreen = findViewById(R.id.pb_splash_screen);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        new Thread(new Runnable() {
            @Override
            public void run() {
                doWork();
                startUp();
            }
        }).start();
    }

    private void startUp() {
        Intent mainIntent = new Intent(SplashActivity.this, LoginActivity.class);
        SplashActivity.this.startActivity(mainIntent);
        SplashActivity.this.finish();
    }

    private void doWork() {
        for(int progress=0;progress<100;progress+=1){
            try {
                Thread.sleep(30);
                pbSplashScreen.setProgress(progress);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }
}
