package com.example.primerejemplo;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class ResultActivity extends AppCompatActivity {

    private TextView tvResultado;
    private TextView tvTitulo;
    private Button btnVolver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        tvTitulo = findViewById(R.id.tvTitulo);
        tvResultado = findViewById(R.id.tvResultado);
        btnVolver = findViewById(R.id.btnVolver);

        Intent intent = getIntent();

        if (intent.hasExtra("dias")) {
            mostrarResultadoDiasNacimiento(intent);
        } else if (intent.hasExtra("imc")) {
            mostrarResultadoIMC(intent);
        } else {
            tvTitulo.setText("Error");
            tvResultado.setText("No se recibieron datos para mostrar");
        }

        btnVolver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void mostrarResultadoDiasNacimiento(Intent intent) {
        String nombre = intent.getStringExtra("nombre");
        String apellidos = intent.getStringExtra("apellidos");
        long diasDesdeNacimiento = intent.getLongExtra("dias", 0);

        tvTitulo.setText("Resultado - Días de Nacimiento");
        tvResultado.setText("Hola, " + nombre + " " + apellidos +
                ".\n\nHan pasado " + diasDesdeNacimiento + " días desde tu nacimiento.");
    }

    private void mostrarResultadoIMC(Intent intent) {
        String nombre = intent.getStringExtra("nombre");
        double imc = intent.getDoubleExtra("imc", 0);
        String clasificacion = intent.getStringExtra("clasificacion");

        tvTitulo.setText("Resultado - IMC");
        tvResultado.setText("Hola, " + nombre +
                "\n\nTu IMC es: " + String.format("%.2f", imc) +
                "\nClasificación: " + clasificacion);
    }
}