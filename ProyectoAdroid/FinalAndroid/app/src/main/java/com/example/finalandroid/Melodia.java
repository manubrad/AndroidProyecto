package com.example.finalandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

public class Melodia extends AppCompatActivity {
    SoundPool sp;
    int sonidoDeReproduccion, chulo, borracho, animador;
    MediaPlayer mp, mc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_melodia);
        getSupportActionBar().setIcon(R.mipmap.ic_launcher);
        mp = MediaPlayer.create(this, R.raw.musica);
        mc = MediaPlayer.create(this, R.raw.mensaje);
        sp = new SoundPool(1, AudioManager.STREAM_MUSIC, 1);


        chulo = sp.load(this, R.raw.sonido, 1);
        animador = sp.load(this, R.raw.bob, 1);


        sp.play(sonidoDeReproduccion, 1, 1, 1, 0, 0);
    }

    public void chulo(View view) {
        sp.play(chulo, 1, 1, 1, 0, 0);

    }

    public void animador(View view) {

        sp.play(animador, 1, 1, 1, 0, 0);
    }



    public void cantante(View view) {

        mc.start();
    }

    public void bajo(View view) {

        mp.start();

    }
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.overflow, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.item1) {
            sp.play(sonidoDeReproduccion, 1, 1, 1, 0, 0);
            Toast.makeText(this, "Login", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(this, Login.class);
            startActivity(intent);
            finish();
        } else if (id == R.id.item2) {
            sp.play(sonidoDeReproduccion, 1, 1, 1, 0, 0);
            Intent intent = new Intent(this, ComprarRaziel.class);
            startActivity(intent);
            finish();
            Toast.makeText(this, "Comprar", Toast.LENGTH_SHORT).show();
        } else if (id == R.id.item3) {
            sp.play(sonidoDeReproduccion, 1, 1, 1, 0, 0);
            Intent intent = new Intent(this, Melodia.class);
            startActivity(intent);
            finish();
            Toast.makeText(this, "Melodia", Toast.LENGTH_SHORT).show();

        }
        return super.onOptionsItemSelected(item);
    }

}

