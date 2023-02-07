package com.example.finalandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class Login extends AppCompatActivity {
    RequestQueue requestQueue;
    EditText edtNombre, edtContrasenia;
    Button aceptar;
    int contador = 0;
    SoundPool sp;
    int sonidoDeReproduccion ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setIcon(R.mipmap.ic_launcher);
        setContentView(R.layout.activity_login);
        edtContrasenia = findViewById(R.id.ContLog);
        edtNombre = findViewById(R.id.NomLog);
        aceptar = findViewById(R.id.ButtBuscar);

        sp = new SoundPool(1, AudioManager. STREAM_MUSIC, 1);
        sonidoDeReproduccion = sp.load(this, R.raw.arpacampanas, 1);

    }


    public void buscarUsuario(View view) {
        sp.play(sonidoDeReproduccion , 1,1, 1, 0, 0);
        if (edtNombre.getText().toString().trim().isEmpty() || edtContrasenia.getText().toString().trim().isEmpty()) {

            Toast.makeText(getApplicationContext(), "Usuario o contraseña no pueden estar vacios", Toast.LENGTH_SHORT).show();

        } else if (edtContrasenia.getText().toString().trim().length() < 8) {
            Toast.makeText(getApplicationContext(), "Introduzca una contraseña de más de 8 caracteres", Toast.LENGTH_SHORT).show();

        } else {

            JsonArrayRequest jsonArrayRequest = new JsonArrayRequest("http://mrodriguez.jantoniosr.com/registrar_usuario/buscar_producto.php?nombre=" + edtNombre.getText(), response -> {
                JSONObject jsonObject;
                for (int i = 0; i < response.length(); i++) {
                    try {

                        jsonObject = response.getJSONObject(i);
                        //edtNombre.setText(jsonObject.getString("nombre"));
                        //edtContrasenia.setText(jsonObject.getString("contrasenia"));
                         Toast.makeText(getApplicationContext(), "Busqueda realizada", Toast.LENGTH_SHORT).show();


                        if((jsonObject.getString("contrasenia").equals(edtContrasenia.getText().toString())) && (jsonObject.getString("nombre").equalsIgnoreCase(edtNombre.getText().toString()))  ){
                            Toast.makeText(getApplicationContext(), " CONTRASEÑA y USUARIO CORRECTO", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(this, MenuPrincipal.class);
                            startActivity(intent);
                            finish();
                        }else {

                            Toast.makeText(getApplicationContext(), " CONTRASEÑA O USUARIO INCORRECTO" + "INTENTO" + contador, Toast.LENGTH_SHORT).show();
                            contador++;
                        }
                        if(contador==5){
                            aceptar.setEnabled(false);
                            aceptar.setBackgroundColor(Color.RED);
                            Toast.makeText(getApplicationContext(), " Por seguridad se ha bloqueado la aplicación,revise su email para el link de contraseña", Toast.LENGTH_SHORT).show();
                        }

                    } catch (JSONException e) {
                        Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
            }, error -> Toast.makeText(getApplicationContext(), "USUARIO INCORRECTO", Toast.LENGTH_SHORT).show()
            );
            requestQueue = Volley.newRequestQueue(this);
            requestQueue.add(jsonArrayRequest);

        }
    }


        public void Registro (View view){
            sp.play(sonidoDeReproduccion , 1,1, 1, 0, 0);
            Intent intent = new Intent(this, Regist.class);
            startActivity(intent);
            finish();

        }



    }

