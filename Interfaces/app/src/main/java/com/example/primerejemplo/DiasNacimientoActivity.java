package com.example.primerejemplo;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class DiasNacimientoActivity extends AppCompatActivity {

    private EditText etNombre;
    private EditText etApellidos;
    private EditText etFechaNacimiento;
    private Button btnCalcular;
    private Button btnVolver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_date); // Asegúrate de que este layout existe

        // Inicializar vistas
        etNombre = findViewById(R.id.etNombre);
        etApellidos = findViewById(R.id.etApellidos);
        etFechaNacimiento = findViewById(R.id.etFechaNacimiento);
        btnCalcular = findViewById(R.id.btnCalcular);
        btnVolver = findViewById(R.id.btnVolver);

        // Establecer listener en el botón
        btnCalcular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nombre = etNombre.getText().toString();
                String apellidos = etApellidos.getText().toString();
                String fechaNacimientoStr = etFechaNacimiento.getText().toString();

                // Validar entrada
                if (nombre.isEmpty() || apellidos.isEmpty() || fechaNacimientoStr.isEmpty()) {
                    Toast.makeText(DiasNacimientoActivity.this, "Por favor, complete todos los campos.", Toast.LENGTH_SHORT).show();
                    return;
                }

                // Parsear fecha de nacimiento
                SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
                try {
                    Date fechaNacimiento = dateFormat.parse(fechaNacimientoStr);
                    long diasDesdeNacimiento = calcularDiasDesdeNacimiento(fechaNacimiento);

                    // Enviar datos a la actividad de resultados
                    Intent intent = new Intent(DiasNacimientoActivity.this, ResultActivity.class);
                    intent.putExtra("nombre", nombre);
                    intent.putExtra("apellidos", apellidos);
                    intent.putExtra("dias", diasDesdeNacimiento);
                    startActivity(intent);

                } catch (ParseException e) {
                    Toast.makeText(DiasNacimientoActivity.this, "Formato de fecha incorrecto. Use dd/MM/yyyy", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnVolver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish(); // Cierra esta actividad y regresa a la anterior (MainActivity)
            }
        });
    }

    private long calcularDiasDesdeNacimiento(Date fechaNacimiento) {
        Date fechaActual = new Date();
        long diferenciaMilisegundos = fechaActual.getTime() - fechaNacimiento.getTime();
        return diferenciaMilisegundos / (1000 * 60 * 60 * 24); // Convertir a días
    }
}