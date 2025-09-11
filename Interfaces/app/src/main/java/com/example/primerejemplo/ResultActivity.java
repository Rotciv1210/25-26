package com.example.primerejemplo;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class ResultActivity extends AppCompatActivity {

    private TextView tvResultado;
    private Button btnSalir;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        // Inicializar vistas
        tvResultado = findViewById(R.id.tvResultado);
        btnSalir = findViewById(R.id.btnSalir);

        // Obtener datos del Intent
        String nombre = getIntent().getStringExtra("nombre");
        String apellidos = getIntent().getStringExtra("apellidos");
        long diasDesdeNacimiento = getIntent().getLongExtra("dias", 0);

        // Mostrar el resultado
        tvResultado.setText("Hola, " + nombre + " " + apellidos + ".\nHan pasado " + diasDesdeNacimiento + " días desde tu nacimiento.");

        // Configurar el botón de salir
        btnSalir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Cierra la aplicación
                finishAffinity();  // Cierra todas las actividades y la aplicación
            }
        });
    }
}