package com.example.appagricola.Model;

import android.os.Bundle;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.appagricola.Adapters.RegistroAdapter;
import com.example.appagricola.DataStorage.DataModels;
import com.example.appagricola.DataStorage.DataStorage;
import com.example.appagricola.R;
import java.util.List;

public class ActivityHistorial extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RegistroAdapter registroAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_historial);

        recyclerView = findViewById(R.id.recyclerViewHistorial);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Cargar todos los registros
        List<DataModels.Registro> registros = DataStorage.registros;
        if (registros.isEmpty()) {
            Toast.makeText(this, "No hay registros disponibles en el historial", Toast.LENGTH_SHORT).show();
        } else {
            registroAdapter = new RegistroAdapter(registros);
            recyclerView.setAdapter(registroAdapter);
        }
    }
}
