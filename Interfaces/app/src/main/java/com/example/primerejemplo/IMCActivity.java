package com.example.primerejemplo;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.snackbar.Snackbar;

public class IMCActivity extends AppCompatActivity {

    private EditText editTextNombre;
    private Button btnCalcular;
    private RadioButton rbHombre;
    private RadioButton rbMujer;
    private EditText editTextPeso;
    private EditText editTextAltura;
    private Button btnVolver;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_imc);

        editTextNombre = findViewById(R.id.editTextNombre);
        btnCalcular = findViewById(R.id.btnCalcular);
        rbHombre = findViewById(R.id.rbHombre);
        rbMujer = findViewById(R.id.rbMujer);
        editTextPeso = findViewById(R.id.peso);
        editTextAltura = findViewById(R.id.altura);
        btnVolver = findViewById(R.id.btnVolver);


        btnCalcular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calcularIMC();
            }
        });

        btnVolver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish(); // Cierra esta actividad y regresa a la anterior (MainActivity)
            }
        });
    }



    private void calcularIMC() {
        String tratamiento = "";
        boolean error = false;
        double imc = 0;
        String clasificacion = "";

        if (editTextNombre.getText().toString().trim().isEmpty()) {
            mensajeError("No puedes dejar el nombre vacío", editTextNombre);
            return;
        }

        if (!rbHombre.isChecked() && !rbMujer.isChecked()) {
            mensajeError("Tienes que seleccionar el género", rbHombre);
            return;
        }

        if (rbHombre.isChecked()) {
            tratamiento = "Don ";
        } else if (rbMujer.isChecked()) {
            tratamiento = "Doña ";
        }

        if (editTextPeso.getText().toString().trim().isEmpty()) {
            mensajeError("Ingresa tu peso", editTextPeso);
            return;
        }

        if (editTextAltura.getText().toString().trim().isEmpty()) {
            mensajeError("Ingresa tu altura", editTextAltura);
            return;
        }

        try {
            double pesoValor = Double.parseDouble(editTextPeso.getText().toString());
            double alturaValor = Double.parseDouble(editTextAltura.getText().toString());

            if (pesoValor <= 0) {
                mensajeError("El peso debe ser mayor a 0", editTextPeso);
                return;
            }

            if (alturaValor <= 0) {
                mensajeError("La altura debe ser mayor a 0", editTextAltura);
                return;
            }

            imc = pesoValor / (alturaValor * alturaValor);

            if (imc < 18.5) {
                clasificacion = "Bajo peso";
            } else if (imc < 25) {
                clasificacion = "Peso ideal";
            } else if (imc < 30) {
                clasificacion = "Sobrepeso";
            } else {
                clasificacion = "Obesidad";
            }

            // Enviar resultados a ResultActivity
            Intent intent = new Intent(IMCActivity.this, ResultActivity.class);
            intent.putExtra("nombre", tratamiento + editTextNombre.getText().toString());
            intent.putExtra("imc", imc);
            intent.putExtra("clasificacion", clasificacion);
            startActivity(intent);

        } catch (NumberFormatException e) {
            mensajeError("Por favor, ingresa valores numéricos válidos", editTextPeso);
        }
    }

    private void mensajeError(String mensaje, View view) {
        Snackbar.make(view, mensaje, Snackbar.LENGTH_LONG).show();
        view.requestFocus();
    }
}