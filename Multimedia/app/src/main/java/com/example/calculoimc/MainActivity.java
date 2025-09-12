package com.example.calculoimc;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.snackbar.Snackbar;

public class MainActivity extends AppCompatActivity {

    private EditText editTextNombre;
    TextView txtSaludo;
    Button btnCalcular;

    RadioButton rbHombre;

    RadioButton rbMujer;

    SeekBar peso;

    SeekBar altura;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        editTextNombre = findViewById(R.id.editTextNombre);
        txtSaludo = findViewById(R.id.txtSaludo);
        btnCalcular = findViewById(R.id.btnCalcular);
        rbHombre = findViewById(R.id.rbHombre);
        rbMujer = findViewById(R.id.rbMujer);
        peso = findViewById(R.id.peso);
        altura = findViewById(R.id.altura);

//listener para que el boton funcione
        btnCalcular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tratamiento = "";
                boolean error = false;
                if(!editTextNombre.getText().isEmpty()){

                    if(rbHombre.isChecked()){
                        tratamiento = "Don ";
                    } else if (rbMujer.isChecked()){
                        tratamiento = "Doña ";
                    } else{
                        mensajeError("Tienes que seleccionar el genero",v);
                        error =true;
                    }

                    double imc = 0;
                    try{
                        imc = (peso.getProgress() / (altura.getProgress() / 100.0)) * (altura.getProgress() / 100.0);
                    } catch (Exception e){

                        mensajeError("No se puede dividir por 0", v);
                    }



                    if(!error){
                        txtSaludo.setText("Hola "+ tratamiento + editTextNombre.getText()+ " tu IMC es: " + imc);
                    }
                } else {
                    mensajeError("No puedes dejar el nombre vacio",v);
                }
            }
        });

        peso.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

                Log.d("Victor ",""+progress);
                txtSaludo.setText(String.valueOf(progress));

            }



            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });


        altura.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

                Log.d("Victor",""+ progress);
                txtSaludo.setText(String.valueOf(progress));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }




    private void mensajeError(String mensaje, View v){

        Snackbar.make(v,mensaje,Snackbar.LENGTH_LONG).show();

    }


}