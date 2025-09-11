package com.example.primerejemplo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    private EditText etNombre, etApellidos, etFechaNacimiento;
    private Button btnCalcular;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_date);

        // Inicializar vistas
        etNombre = findViewById(R.id.etNombre);
        etApellidos = findViewById(R.id.etApellidos);
        etFechaNacimiento = findViewById(R.id.etFechaNacimiento);
        btnCalcular = findViewById(R.id.btnCalcular);

        // Establecer listener en el botón
        btnCalcular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nombre = etNombre.getText().toString();
                String apellidos = etApellidos.getText().toString();
                String fechaNacimientoStr = etFechaNacimiento.getText().toString();

                // Validar entrada
                if (nombre.isEmpty() || apellidos.isEmpty() || fechaNacimientoStr.isEmpty()) {
                    Toast.makeText(MainActivity.this, "Por favor, complete todos los campos.", Toast.LENGTH_SHORT).show();
                    return;
                }

                // Parsear fecha de nacimiento
                SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
                try {
                    Date fechaNacimiento = dateFormat.parse(fechaNacimientoStr);
                    long diasDesdeNacimiento = calcularDiasDesdeNacimiento(fechaNacimiento);

                    // Enviar datos a la siguiente actividad
                    Intent intent = new Intent(MainActivity.this, ResultActivity.class);
                    intent.putExtra("nombre", nombre);
                    intent.putExtra("apellidos", apellidos);
                    intent.putExtra("dias", diasDesdeNacimiento);
                    startActivity(intent);

                } catch (ParseException e) {
                    Toast.makeText(MainActivity.this, "Formato de fecha incorrecto.", Toast.LENGTH_SHORT).show();
                    e.printStackTrace();
                }
            }
        });
    }

    private long calcularDiasDesdeNacimiento(Date fechaNacimiento) {
        Date fechaActual = new Date();
        long diferenciaMilisegundos = fechaActual.getTime() - fechaNacimiento.getTime();
        return diferenciaMilisegundos / (1000 * 60 * 60 * 24); // Convertir a días
    }
}