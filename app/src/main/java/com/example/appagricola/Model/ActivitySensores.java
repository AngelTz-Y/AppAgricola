package com.example.appagricola.Model;

import android.content.Intent;
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
import java.util.Date;
import java.util.List;

public class ActivitySensores extends AppCompatActivity {

    private static final int MAX_DESCRIPTION_LENGTH = 30;
    private static final int MAX_NAME_LENGTH = 15;
    private Spinner tipoSensorSpinner;
    private Spinner ubicacionSensorSpinner;
    private EditText editTextNombre;
    private EditText editTextDescripcion;
    private EditText editTextIdeal;
    private Button buttonGuardar;
    private Button buttonBuscar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sensores);

        // Inicializar vistas
        tipoSensorSpinner = findViewById(R.id.tipoSensorSpinner);
        ubicacionSensorSpinner = findViewById(R.id.ubicacionSensorSpinner);
        editTextNombre = findViewById(R.id.editTextNombreSensor);
        editTextDescripcion = findViewById(R.id.editTextDescripcionSensor);
        editTextIdeal = findViewById(R.id.editTextIdealSensor);
        buttonGuardar = findViewById(R.id.buttonGuardarSensor);
        buttonBuscar = findViewById(R.id.buttonBuscarSensor);

        // Cargar datos en los Spinners
        cargarDatosSpinner();

        // Configurar el botón guardar
        buttonGuardar.setOnClickListener(v -> guardarSensor());

        // Configurar el botón buscar
        buttonBuscar.setOnClickListener(v -> abrirBuscarActivity());
    }

    @Override
    protected void onResume() {
        super.onResume();
        cargarDatosSpinner();
    }

    private void cargarDatosSpinner() {
        List<String> tipos = new ArrayList<>();
        for (DataModels.Tipo tipo : DataStorage.tipos) {
            tipos.add(tipo.getNombre());
        }

        ArrayAdapter<String> tipoAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, tipos);
        tipoAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        tipoSensorSpinner.setAdapter(tipoAdapter);

        List<String> ubicacionNombres = new ArrayList<>();
        for (DataModels.Ubicacion ubicacion : DataStorage.ubicaciones) {
            ubicacionNombres.add(ubicacion.getNombre());
        }

        ArrayAdapter<String> ubicacionAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, ubicacionNombres);
        ubicacionAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        ubicacionSensorSpinner.setAdapter(ubicacionAdapter);
    }

    private void guardarSensor() {
        String nombre = editTextNombre.getText().toString().trim();
        String descripcion = editTextDescripcion.getText().toString().trim();
        String idealText = editTextIdeal.getText().toString().trim();

        // Validar el nombre
        if (nombre.isEmpty()) {
            Toast.makeText(this, "El nombre es obligatorio", Toast.LENGTH_SHORT).show();
            return;
        }
        if (nombre.length() < 5 || nombre.length() > MAX_NAME_LENGTH) {
            Toast.makeText(this, "El nombre debe tener entre 5 y 15 caracteres", Toast.LENGTH_SHORT).show();
            return;
        }

        // Validar la descripción
        if (descripcion.length() > MAX_DESCRIPTION_LENGTH) {
            Toast.makeText(this, "La descripción debe tener un máximo de 30 caracteres", Toast.LENGTH_SHORT).show();
            return;
        }

        // Validar el valor ideal
        float ideal;
        try {
            ideal = Float.parseFloat(idealText);
            if (ideal < 0) {
                Toast.makeText(this, "Por favor, ingresa un valor positivo para el ideal", Toast.LENGTH_SHORT).show();
                return;
            }
        } catch (NumberFormatException e) {
            Toast.makeText(this, "Por favor, ingresa un valor ideal válido", Toast.LENGTH_SHORT).show();
            return;
        }

        // Obtener tipo y ubicación seleccionados
        String tipoSeleccionado = tipoSensorSpinner.getSelectedItem().toString();
        DataModels.Tipo tipo = DataStorage.tipos.stream().filter(t -> t.getNombre().equals(tipoSeleccionado)).findFirst().orElse(null);

        DataModels.Ubicacion ubicacionSeleccionada = DataStorage.ubicaciones.get(ubicacionSensorSpinner.getSelectedItemPosition());

        // Crear y guardar el sensor
        DataModels.Sensor sensor = new DataModels.Sensor(
                DataStorage.nextSensorId++,
                nombre,
                descripcion,
                ideal,
                tipo,
                ubicacionSeleccionada
        );
        DataStorage.sensores.add(sensor);

        // Crear y agregar un registro para el sensor
        float lecturaSimulada = ideal + (float) (Math.random() * 10 - 5);
        DataModels.Registro nuevoRegistro = new DataModels.Registro(DataStorage.nextRegistroId++, new Date(), lecturaSimulada, sensor);
        DataStorage.registros.add(nuevoRegistro);

        // Limpiar los campos de entrada
        editTextNombre.getText().clear();
        editTextDescripcion.getText().clear();
        editTextIdeal.getText().clear();

        Toast.makeText(this, "Sensor guardado exitosamente", Toast.LENGTH_SHORT).show();
    }

    private void abrirBuscarActivity() {
        Intent intent = new Intent(ActivitySensores.this, ActivityBuscar.class);
        startActivity(intent);
    }
}
