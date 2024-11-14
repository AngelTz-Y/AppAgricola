package com.example.appagricola.Model;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import com.example.appagricola.R;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Botón Gestionar Ubicaciones
        Button btnUbicaciones = findViewById(R.id.btnUbicaciones);
        btnUbicaciones.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, ActivityUbicaciones.class);
            startActivity(intent);
        });

        // Botón Gestionar Sensores
        Button btnSensores = findViewById(R.id.btnSensores);
        btnSensores.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, ActivitySensores.class);
            startActivity(intent);
        });

        // Botón Ver Historial
        Button btnHistorial = findViewById(R.id.btnHistorial);
        btnHistorial.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, ActivityHistorial.class);
            startActivity(intent);
        });
    }
}
