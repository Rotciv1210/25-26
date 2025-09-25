package com.example.calculadorasimple;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    Button btnSuma;
    Button btnResta;
    Button btnMultiplicacion;
    Button btnDividir;

    TextView txtN1;
    TextView txtN2;
    EditText editTxtN1;
    EditText editTxtN2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnSuma = findViewById(R.id.btnSuma);
        btnResta = findViewById(R.id.btnResta);
        btnMultiplicacion = findViewById(R.id.btnMultiplicacion);
        btnDividir = findViewById(R.id.btnDivision);
        txtN1 = findViewById(R.id.txtN1);
        txtN2 = findViewById(R.id.txtN2);
        editTxtN1 = findViewById(R.id.editTxtN1);
        editTxtN2 = findViewById(R.id.editTxtN2);

        btnSuma.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                double resultado = sumar();
                Intent intent = new Intent(MainActivity.this, ResultActivity.class);
                intent.putExtra("operacion", "sumar");
                intent.putExtra("resultado", String.valueOf(resultado));
                startActivity(intent);
            }
        });


        btnResta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                double resultado = restar();
                Intent intent = new Intent(MainActivity.this, ResultActivity.class);
                intent.putExtra("operacion", "restar");
                intent.putExtra("resultado", String.valueOf(resultado));
                startActivity(intent);
            }
        });


        btnMultiplicacion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                double resultado = multiplicar();
                Intent intent = new Intent(MainActivity.this, ResultActivity.class);
                intent.putExtra("operacion", "multiplicación");
                intent.putExtra("resultado", String.valueOf(resultado));
                startActivity(intent);
            }
        });

        btnDividir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                double resultado = dividir();
                Intent intent = new Intent(MainActivity.this, ResultActivity.class);
                intent.putExtra("operacion", "división");
                intent.putExtra("resultado", String.valueOf(resultado));
                startActivity(intent);
            }
        });
    }

    private double sumar(){
        double n1 = Double.parseDouble(editTxtN1.getText().toString());
        double n2 = Double.parseDouble(editTxtN2.getText().toString());
        return n1 + n2;
    }

    private double restar(){
        double n1 = Double.parseDouble(editTxtN1.getText().toString());
        double n2 = Double.parseDouble(editTxtN2.getText().toString());
        return n1 - n2;
    }

    private double multiplicar(){
        double n1 = Double.parseDouble(editTxtN1.getText().toString());
        double n2 = Double.parseDouble(editTxtN2.getText().toString());
        return n1 * n2;
    }

    private double dividir(){
        double n1 = Double.parseDouble(editTxtN1.getText().toString());
        double n2 = Double.parseDouble(editTxtN2.getText().toString());
        return n1 / n2;
    }

}