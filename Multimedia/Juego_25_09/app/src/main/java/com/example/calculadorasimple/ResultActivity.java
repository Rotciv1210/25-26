package com.example.calculadorasimple;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ResultActivity extends AppCompatActivity {

    TextView txtResultado;
    Button btnVolver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        btnVolver = findViewById(R.id.btnVolver);
        txtResultado = findViewById(R.id.txtResultado);

        Intent intent = getIntent();

        if (intent != null && intent.hasExtra("resultado")) {
            String operacion = intent.getStringExtra("operacion");
            double resultado = Double.parseDouble(intent.getStringExtra("resultado"));
            txtResultado.setText("El resultado de la " + operacion + " es: " + resultado);
        } else {
            txtResultado.setText("Error al recibir los datos");
        }

        btnVolver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
