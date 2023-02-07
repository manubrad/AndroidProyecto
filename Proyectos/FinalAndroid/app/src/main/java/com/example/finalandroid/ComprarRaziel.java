package com.example.finalandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Objects;

public class ComprarRaziel extends AppCompatActivity {
    private TextView tv2;
    private TextView tv3;
    SoundPool sp;
    int sonidoDeReproduccion;

    private ListView lv1;
    private final String[] nombres = {"Chocolate", "Vainilla", "Pistacho", "Café"};
    EditText sd, helado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comprar_raziel);
        sp = new SoundPool(1, AudioManager.STREAM_MUSIC, 1);
        sonidoDeReproduccion = sp.load(this, R.raw.arpacampanas, 1);
        Objects.requireNonNull(getSupportActionBar()).setDisplayShowHomeEnabled(true);
        getSupportActionBar().setIcon(R.mipmap.ic_launcher);


        tv2 = findViewById(R.id.txtHelado);
        lv1 = findViewById(R.id.listHelado);
        tv3 = findViewById(R.id.salidaDato);
        sd = findViewById(R.id.salidaDato);
        sd.setEnabled(false);
        helado = findViewById(R.id.txtHelado);
        helado.setEnabled(false);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.listview, nombres);
        lv1.setAdapter(adapter);


        lv1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                tv2.setText("Helado  de " + lv1.getItemAtPosition(i));
            }
        });

    }


    public void PInicial(View view) {
        sp.play(sonidoDeReproduccion, 1, 1, 1, 0, 0);
        Intent PestañaInicial = new Intent(this, MenuPrincipal.class);
        startActivity(PestañaInicial);
    }


    public void pedirHelado(View view) {
        sp.play(sonidoDeReproduccion, 1, 1, 1, 0, 0);
        if (tv2.getText().toString().equals("Selecciona tu helado")) {
            tv3.setText("Niño pero elige un helado");
        } else {

            tv3.setText("PEDIDO :" + tv2.getText());


        }
    }

    //Método para mostrar y ocultar el menú
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
