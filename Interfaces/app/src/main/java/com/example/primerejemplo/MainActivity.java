package com.example.primerejemplo;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private Button btnDiasNacimiento;
    private Button btnCalcularIMC;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Conectar los botones con las variables usando los IDs de tu XML
        btnDiasNacimiento = findViewById(R.id.botonDias);
        btnCalcularIMC = findViewById(R.id.button2);

        btnDiasNacimiento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, DiasNacimientoActivity.class);
                startActivity(intent);
            }
        });

        btnCalcularIMC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, IMCActivity.class);
                startActivity(intent);
            }
        });
    }
}