package com.example.appagricola.Model;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appagricola.Adapters.SensorAdapter;
import com.example.appagricola.DataStorage.DataModels;
import com.example.appagricola.R;
import com.example.appagricola.datos.Repositorio;

import java.util.List;

public class ActivityListaSensores extends AppCompatActivity {

    private RecyclerView recyclerView;
    private SensorAdapter sensorAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_sensores);

        recyclerView = findViewById(R.id.recyclerViewSensores);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Obtener sensores de muestra desde el repositorio
        List<DataModels.Sensor> sensores = Repositorio.getInstance().getSensores();

        if (sensores.isEmpty()) {
            Toast.makeText(this, "No hay sensores disponibles", Toast.LENGTH_SHORT).show();
        } else {
            sensorAdapter = new SensorAdapter(sensores);
            recyclerView.setAdapter(sensorAdapter);
        }
    }
}
