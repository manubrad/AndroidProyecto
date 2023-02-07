package com.example.finalandroid;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class Regist extends AppCompatActivity {
    RequestQueue requestQueue;
    EditText edtContrasenia, edtNombre, edtEmail;
    SoundPool sp;
    int sonidoDeReproduccion;
    Boolean bandera = false;
    private ImageView iv1;
    final int CAPTURA_IMAGEN = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_regist);
        sp = new SoundPool(1, AudioManager.STREAM_MUSIC, 1);
        sonidoDeReproduccion = sp.load(this, R.raw.arpacampanas, 1);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setIcon(R.mipmap.ic_launcher);
        edtContrasenia = findViewById(R.id.ContReg);
        edtNombre = findViewById(R.id.NomReg);
        edtEmail = findViewById(R.id.txtEmail);
        iv1 = findViewById(R.id.iv1);
        if (ContextCompat.checkSelfPermission(Regist.this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(Regist.this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(Regist.this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.CAMERA}, 1000);
        }

    }


    public void insertarProducto(View view) {
        sp.play(sonidoDeReproduccion , 1,1, 1, 0, 0);
        if (edtNombre.getText().toString().trim().isEmpty() || edtContrasenia.getText().toString().trim().isEmpty() || edtEmail.getText().toString().trim().isEmpty()) {

            Toast.makeText(getApplicationContext(), "Usuario o contraseña no pueden estar vacios", Toast.LENGTH_SHORT).show();

        } else if (edtContrasenia.getText().toString().trim().length() < 8) {
            Toast.makeText(getApplicationContext(), "Introduzca una contraseña de más de 8 caracteres", Toast.LENGTH_SHORT).show();

        }


        for (int i = 0; i < edtEmail.length(); i++) {
            if (edtEmail.getText().charAt(i) == '@') {
                bandera = true;
            } else {

                Toast.makeText(getApplicationContext(), "Introduzca el @ ", Toast.LENGTH_SHORT).show();
            }


        }


        if (bandera) {
            StringRequest stringRequest = new StringRequest(Request.Method.POST, "http://mrodriguez.jantoniosr.com/registrar_usuario/insertar_producto.php", response -> Toast.makeText(getApplicationContext(), "REGISTRADO CON ÉXITO", Toast.LENGTH_SHORT).show(), error -> Toast.makeText(getApplicationContext(), error.toString(), Toast.LENGTH_SHORT).show()) {
                @NonNull
                @Override
                protected Map<String, String> getParams() {
                    Map<String, String> parametros = new HashMap<>();
                    //ESTOS SON LAS $VARIABLES DEL MICROSERVICIO EN PHP
                    parametros.put("nombre", edtNombre.getText().toString());
                    parametros.put("contrasenia", edtContrasenia.getText().toString());
                    parametros.put("email", edtEmail.getText().toString());

                    return parametros;
                }
            };
            requestQueue = Volley.newRequestQueue(this);
            requestQueue.add(stringRequest);
            Intent intent = new Intent(this, Login.class);
            startActivity(intent);
            finish();
        }

    }


    public void TomarFoto(View v) {
        Intent intento1 = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intento1, CAPTURA_IMAGEN);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == CAPTURA_IMAGEN && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            Bitmap bitmap1 = (Bitmap) extras.get("data");
            iv1.setImageBitmap(bitmap1);
            try {
                FileOutputStream fos = openFileOutput(crearNombreArchivoJPG(),
                        Context.MODE_PRIVATE);
                bitmap1.compress(Bitmap.CompressFormat.JPEG, 100, fos);
                fos.close();
            } catch (Exception e) {
            }
        }
    }

    private String crearNombreArchivoJPG() {
        String fecha = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        return "TuFotoRaziel_" + fecha + ".jpg";
    }
    public void PInic(View view) {
        sp.play(sonidoDeReproduccion, 1, 1, 1, 0, 0);
        Intent PestañaInicial = new Intent(this, Login.class);
        startActivity(PestañaInicial);
    }
}