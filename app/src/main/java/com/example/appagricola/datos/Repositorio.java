package com.example.appagricola.datos;

import com.example.appagricola.DataStorage.DataModels;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Repositorio {

    private static Repositorio instance = null;

    private List<DataModels.Tipo> tipos;
    private List<DataModels.Ubicacion> ubicaciones;
    private List<DataModels.Sensor> sensores;
    private List<DataModels.Registro> registros;
    private boolean datosDeMuestraCargados = false;

    private Repositorio() {
        inicializarDatos();
    }

    public static Repositorio getInstance() {
        if (instance == null) {
            instance = new Repositorio();
        }
        return instance;
    }

    private void inicializarDatos() {
        tipos = new ArrayList<>();
        tipos.add(new DataModels.Tipo(1, "Temperatura"));
        tipos.add(new DataModels.Tipo(2, "Humedad"));

        ubicaciones = new ArrayList<>();
        sensores = new ArrayList<>();
        registros = new ArrayList<>();

        // Cargar datos de muestra solo una vez
        if (!datosDeMuestraCargados) {
            ubicaciones.add(new DataModels.Ubicacion(1, "Invernadero", "Área principal de cultivo"));
            ubicaciones.add(new DataModels.Ubicacion(2, "Campo Este", "Campo al este de la granja"));
            ubicaciones.add(new DataModels.Ubicacion(3, "Almacén", "Espacio de almacenamiento"));

            DataModels.Sensor sensor1 = new DataModels.Sensor(1, "Sensor 1", "Sensor de temperatura", 25.0f, tipos.get(0), ubicaciones.get(0));
            DataModels.Sensor sensor2 = new DataModels.Sensor(2, "Sensor 2", "Sensor de humedad", 60.0f, tipos.get(1), ubicaciones.get(1));

            sensores.add(sensor1);
            sensores.add(sensor2);

            registros.add(new DataModels.Registro(1, new Date(), 23.5f, sensor1));
            registros.add(new DataModels.Registro(2, new Date(), 58.0f, sensor2));

            datosDeMuestraCargados = true;
        }
    }

    public List<DataModels.Tipo> getTipos() {
        return tipos;
    }

    public List<DataModels.Ubicacion> getUbicaciones() {
        return ubicaciones;
    }

    public List<DataModels.Sensor> getSensores() {
        return sensores;
    }

    public List<DataModels.Registro> getRegistros() {
        return registros;
    }

    public void agregarUbicacion(DataModels.Ubicacion ubicacion) {
        ubicaciones.add(ubicacion);
    }

    public void agregarSensor(DataModels.Sensor sensor) {
        sensores.add(sensor);
    }
}
