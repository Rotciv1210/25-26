package com.example.layouts;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    String apikey = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ2aWN0b3IucGVyZWlyYS5yaWdhbEBjb2xleGlvLWthcmJvLmNvbSIsImp0aSI6ImQ2OGQ5OWUwLThkYjMtNDA5OS1hNzRhLWFmMjgzN2JiYzNkZiIsImlzcyI6IkFFTUVUIiwiaWF0IjoxNzU4NTQ2NDQyLCJ1c2VySWQiOiJkNjhkOTllMC04ZGIzLTQwOTktYTc0YS1hZjI4MzdiYmMzZGYiLCJyb2xlIjoiIn0.rMBINEp5pb4YhaavvKiOkfxBj_JX_DCBuraDFXj_3rk";

    RequestQueue cola;

    TextView txtTemperatura;

    TextView txtEstadoCielo;

    TextView txtMaxima;

    TextView txtMinima;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        txtTemperatura = findViewById(R.id.txtTemperatura);
        txtEstadoCielo = findViewById(R.id.txtEstadoCielo);
        txtMaxima = findViewById(R.id.txtMaxima);
        txtMinima = findViewById(R.id.txtMinima);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });



        cola = Volley.newRequestQueue(this);
        String url = "https://opendata.aemet.es/opendata/api/prediccion/especifica/municipio/diaria/15030?api_key="+apikey;


        JsonObjectRequest peticion = new JsonObjectRequest(
                Request.Method.GET,
                url,
                null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        Log.d("Moncho", jsonObject.toString());

                        try{
                            if(jsonObject.getString("descripcion").equals("exito")){
                                Log.d("Moncho", jsonObject.getString("datos"));
                                pedirDatos(jsonObject.getString("datos"));
                            }
                        } catch (JSONException e){
                            Log.d("Moncho", e.getMessage());

                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {

            }
        }
        );

        cola.add(peticion);
    }

    private void pedirDatos(String url){

        Log.d("Moncho2", "URL: "+ url);

        JsonArrayRequest peticion = new JsonArrayRequest(
                Request.Method.GET,
                url,
                null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray jsonArray) {

                        Log.d("Moncho2",""+jsonArray.length());
                        try {
                            //String temperatura = jsonArray.getJSONObject(0).getJSONObject("prediccion").getJSONArray("dia").getJSONObject(0).
                            //        getJSONObject("temperatura").getString("maxima");
                            //Log.d("Moncho2", temperatura);
                            String estadoCielo = jsonArray.getJSONObject(0).getJSONObject("prediccion").getJSONArray("dia").getJSONObject(0).
                                    getJSONArray("estadoCielo").getJSONObject(2).getString("descripcion");
                            Log.d("Moncho2",estadoCielo);
                            String maxima = jsonArray.getJSONObject(0).getJSONObject("prediccion").getJSONArray("dia").getJSONObject(0).
                                    getJSONObject("temperatura").getString("maxima");
                            String minima = jsonArray.getJSONObject(0).getJSONObject("prediccion").getJSONArray("dia").getJSONObject(0).
                                    getJSONObject("temperatura").getString("minima");
                            int temperatura = (Integer.parseInt(maxima)+ Integer.parseInt(minima)) /2;
                            //String tempMedia = String.valueOf(temperatura);
                            txtTemperatura.setText(temperatura+"º");
                            txtEstadoCielo.setText(estadoCielo);
                            txtMaxima.setText("Maxima "+maxima + "º");
                            txtMinima.setText("Minima "+minima + "º");
                        } catch (JSONException e) {
                            throw new RuntimeException(e);
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {

                    }
                }
        );


        cola.add(peticion);


    }
}