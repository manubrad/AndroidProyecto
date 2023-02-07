package com.example.finalandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

public class MenuPrincipal extends AppCompatActivity {

    SoundPool sp;
    int sonidoDeReproduccion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_principal);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setIcon(R.mipmap.ic_launcher);


        sp = new SoundPool(1, AudioManager.STREAM_MUSIC, 1);
        sonidoDeReproduccion = sp.load(this, R.raw.arpacampanas, 1);
    }
    public void play(View view) {
        sp.play(sonidoDeReproduccion, 1, 1, 1, 0, 0);
        Intent intent = new Intent(this, Melodia.class);
        startActivity(intent);
        finish();
    }


    public void Comprar(View view) {
        sp.play(sonidoDeReproduccion, 1, 1, 1, 0, 0);
        Intent intent = new Intent(this, ComprarRaziel.class);
        startActivity(intent);
        finish();
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