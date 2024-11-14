package com.example.appagricola.Model;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.example.appagricola.DataStorage.DataModels;
import com.example.appagricola.DataStorage.DataStorage;
import com.example.appagricola.R;

public class ActivityBuscar extends AppCompatActivity {

    private EditText editTextBuscarId;
    private Button buttonBuscar;
    private LinearLayout layoutResultado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buscar);

        // Inicializar vistas
        editTextBuscarId = findViewById(R.id.editTextBuscarId);
        buttonBuscar = findViewById(R.id.buttonBuscar);
        layoutResultado = findViewById(R.id.layoutResultado);

        // Configurar el botón de búsqueda
        buttonBuscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buscarPorId();
            }
        });
    }

    private void buscarPorId() {
        layoutResultado.removeAllViews(); // Limpiar resultados previos

        String idText = editTextBuscarId.getText().toString().trim();
        if (idText.isEmpty()) {
            Toast.makeText(this, "Por favor, ingresa un ID válido", Toast.LENGTH_SHORT).show();
            return;
        }

        int id = Integer.parseInt(idText);
        Object entidad = DataStorage.buscarPorId(id);

        if (entidad instanceof DataModels.Ubicacion) {
            mostrarInfoCompleta((DataModels.Ubicacion) entidad);
        } else if (entidad instanceof DataModels.Sensor) {
            mostrarInfoCompleta(((DataModels.Sensor) entidad).getUbicacion());
        } else if (entidad instanceof DataModels.Registro) {
            mostrarInfoCompleta(((DataModels.Registro) entidad).getSensor().getUbicacion());
        } else {
            Toast.makeText(this, "No se encontró una entidad con el ID: " + id, Toast.LENGTH_SHORT).show();
        }
    }

    private void mostrarInfoCompleta(DataModels.Ubicacion ubicacion) {
        LayoutInflater inflater = LayoutInflater.from(this);
        View view = inflater.inflate(R.layout.item_registro, layoutResultado, false);

        // Ubicación
        TextView textViewUbicacion = view.findViewById(R.id.textViewUbicacion);
        TextView textViewDescripcionUbicacion = view.findViewById(R.id.textViewDescripcionUbicacion);
        textViewUbicacion.setText(ubicacion.getNombre());
        textViewDescripcionUbicacion.setText(ubicacion.getDescripcion());

        // Sensor asociado a la ubicación
        for (DataModels.Sensor sensor : DataStorage.sensores) {
            if (sensor.getUbicacion().getId() == ubicacion.getId()) {
                TextView textViewSensorNombre = view.findViewById(R.id.textViewSensorNombre);
                TextView textViewDescripcionSensor = view.findViewById(R.id.textViewDescripcionSensor);
                TextView textViewTipo = view.findViewById(R.id.textViewTipo);
                TextView textViewIdeal = view.findViewById(R.id.textViewIdeal);

                textViewSensorNombre.setText(sensor.getNombre());
                textViewDescripcionSensor.setText(sensor.getDescripcion());
                textViewTipo.setText(sensor.getTipo().getNombre());
                textViewIdeal.setText(String.valueOf(sensor.getIdeal()));

                // Registro asociado al sensor
                for (DataModels.Registro registro : DataStorage.registros) {
                    if (registro.getSensor().getId() == sensor.getId()) {
                        TextView textViewLectura = view.findViewById(R.id.textViewLectura);
                        TextView textViewFecha = view.findViewById(R.id.textViewFecha);

                        textViewLectura.setText(String.valueOf(registro.getLectura()));
                        textViewFecha.setText(registro.getInstante().toString());
                    }
                }
            }
        }

        layoutResultado.addView(view);
    }
}
