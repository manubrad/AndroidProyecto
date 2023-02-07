package com.example.finalandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;

public class splash extends AppCompatActivity {
private final int DURACION_SPLASH=4000;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);
        MediaPlayer mp = MediaPlayer. create(this, R.raw.gallina_loca_toque_de_carga);
        mp.start() ;
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run () {
                Intent intent = new Intent(splash.this, Login.class);
                startActivity(intent);
                finish();
            };


        },DURACION_SPLASH);

    }

}