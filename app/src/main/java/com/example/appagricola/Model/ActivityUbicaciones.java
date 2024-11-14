package com.example.appagricola.Model;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.appagricola.DataStorage.DataModels;
import com.example.appagricola.DataStorage.DataStorage;
import com.example.appagricola.R;

import java.util.ArrayList;
import java.util.List;

public class ActivityUbicaciones extends AppCompatActivity {

    private static final int MAX_DESCRIPTION_LENGTH = 30;
    private static final int MAX_NAME_LENGTH = 15;
    private EditText editTextNombre;
    private EditText editTextDescripcion;
    private Button buttonGuardar;
    private Spinner spinnerUbicaciones;
    private ArrayAdapter<String> ubicacionAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ubicaciones);

        // Inicializar vistas
        editTextNombre = findViewById(R.id.editTextNombreUbicacion);
        editTextDescripcion = findViewById(R.id.editTextDescripcionUbicacion);
        buttonGuardar = findViewById(R.id.buttonGuardarUbicacion);
        spinnerUbicaciones = findViewById(R.id.ubicacionesSpinner);

        // Cargar datos de muestra en el Spinner
        cargarDatosUbicaciones();

        // Configurar el botón guardar
        buttonGuardar.setOnClickListener(v -> guardarUbicacion());
    }

    private void cargarDatosUbicaciones() {
        if (DataStorage.ubicaciones.isEmpty()) {
            DataStorage.inicializarDatosDeMuestra();
        }

        List<String> ubicacionNombres = new ArrayList<>();
        for (DataModels.Ubicacion ubicacion : DataStorage.ubicaciones) {
            ubicacionNombres.add(ubicacion.getNombre());
        }

        ubicacionAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, ubicacionNombres);
        ubicacionAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerUbicaciones.setAdapter(ubicacionAdapter);
    }

    private void guardarUbicacion() {
        String nombre = editTextNombre.getText().toString().trim();
        String descripcion = editTextDescripcion.getText().toString().trim();

        // Validación del nombre
        if (nombre.isEmpty()) {
            Toast.makeText(this, "Nombre de la ubicación es obligatorio", Toast.LENGTH_SHORT).show();
            return;
        }
        if (nombre.length() < 5 || nombre.length() > MAX_NAME_LENGTH) {
            Toast.makeText(this, "El nombre debe tener entre 5 y 15 caracteres", Toast.LENGTH_SHORT).show();
            return;
        }

        // Validación de la descripción
        if (descripcion.length() > MAX_DESCRIPTION_LENGTH) {
            Toast.makeText(this, "La descripción debe tener un máximo de 30 caracteres", Toast.LENGTH_SHORT).show();
            return;
        }

        // Crear y guardar la ubicación
        DataModels.Ubicacion ubicacion = new DataModels.Ubicacion(
                DataStorage.ubicaciones.size() + 1,
                nombre,
                descripcion
        );
        DataStorage.ubicaciones.add(ubicacion);
        actualizarSpinnerUbicaciones();

        // Limpiar los campos de entrada
        editTextNombre.getText().clear();
        editTextDescripcion.getText().clear();

        Toast.makeText(this, "Ubicación guardada exitosamente", Toast.LENGTH_SHORT).show();
    }

    private void actualizarSpinnerUbicaciones() {
        List<String> ubicacionNombres = new ArrayList<>();
        for (DataModels.Ubicacion ubicacion : DataStorage.ubicaciones) {
            ubicacionNombres.add(ubicacion.getNombre());
        }

        ubicacionAdapter.clear();
        ubicacionAdapter.addAll(ubicacionNombres);
        ubicacionAdapter.notifyDataSetChanged();
        spinnerUbicaciones.setSelection(ubicacionAdapter.getCount() - 1);
    }
}
