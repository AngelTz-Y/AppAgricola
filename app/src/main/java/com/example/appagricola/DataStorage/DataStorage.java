package com.example.appagricola.DataStorage;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DataStorage {

    // Listas para almacenar las entidades del sistema
    public static List<DataModels.Ubicacion> ubicaciones = new ArrayList<>();
    public static List<DataModels.Tipo> tipos = new ArrayList<>();
    public static List<DataModels.Sensor> sensores = new ArrayList<>();
    public static List<DataModels.Registro> registros = new ArrayList<>();

    // Contadores de ID para asignar IDs únicos a cada tipo de entidad
    private static int nextUbicacionId = 4; // Empieza en 4, ya que hay tres ubicaciones de muestra
    public static int nextSensorId = 3;     // Empieza en 3, ya que hay dos sensores de muestra
    public static int nextRegistroId = 3;   // Empieza en 3, ya que hay dos registros de muestra

    // Inicializador estático para cargar datos de muestra al inicio
    static {
        inicializarDatosDeMuestra();
    }

    // Método para inicializar datos de muestra
    public static void inicializarDatosDeMuestra() {
        // Agregar tipos de sensores de muestra
        tipos.add(new DataModels.Tipo(1, "Temperatura"));
        tipos.add(new DataModels.Tipo(2, "Humedad"));

        // Agregar ubicaciones de muestra
        ubicaciones.add(new DataModels.Ubicacion(1, "Invernadero", "Área principal de cultivo"));
        ubicaciones.add(new DataModels.Ubicacion(2, "Campo Este", "Campo al este de la granja"));
        ubicaciones.add(new DataModels.Ubicacion(3, "Almacén", "Espacio de almacenamiento"));

        // Agregar sensores de muestra con ubicaciones y tipos ya definidos
        DataModels.Sensor sensor1 = new DataModels.Sensor(1, "Sensor 1", "Sensor de temperatura", 25.0f, tipos.get(0), ubicaciones.get(0));
        DataModels.Sensor sensor2 = new DataModels.Sensor(2, "Sensor 2", "Sensor de humedad", 60.0f, tipos.get(1), ubicaciones.get(1));
        sensores.add(sensor1);
        sensores.add(sensor2);

        // Agregar registros de muestra asociados a los sensores anteriores
        registros.add(new DataModels.Registro(1, new Date(), 23.5f, sensor1));
        registros.add(new DataModels.Registro(2, new Date(), 61.0f, sensor2));
    }

    // Método de búsqueda por ID que busca en ubicaciones, sensores y registros
    public static Object buscarPorId(int id) {
        // Buscar en ubicaciones
        for (DataModels.Ubicacion ubicacion : ubicaciones) {
            if (ubicacion.getId() == id) {
                return ubicacion;
            }
        }
        // Buscar en sensores
        for (DataModels.Sensor sensor : sensores) {
            if (sensor.getId() == id) {
                return sensor;
            }
        }
        // Buscar en registros
        for (DataModels.Registro registro : registros) {
            if (registro.getId() == id) {
                return registro;
            }
        }
        return null; // Si no se encuentra ninguna entidad con el ID dado
    }

    // Método para agregar una nueva ubicación con un ID único
    public static DataModels.Ubicacion agregarUbicacion(String nombre, String descripcion) {
        DataModels.Ubicacion nuevaUbicacion = new DataModels.Ubicacion(nextUbicacionId++, nombre, descripcion);
        ubicaciones.add(nuevaUbicacion);
        return nuevaUbicacion;
    }

    // Método para agregar un nuevo sensor con un ID único
    public static DataModels.Sensor agregarSensor(String nombre, String descripcion, float ideal, DataModels.Tipo tipo, DataModels.Ubicacion ubicacion) {
        DataModels.Sensor nuevoSensor = new DataModels.Sensor(nextSensorId++, nombre, descripcion, ideal, tipo, ubicacion);
        sensores.add(nuevoSensor);
        return nuevoSensor;
    }

    // Método para agregar un nuevo registro con un ID único
    public static DataModels.Registro agregarRegistro(float lectura, DataModels.Sensor sensor) {
        DataModels.Registro nuevoRegistro = new DataModels.Registro(nextRegistroId++, new Date(), lectura, sensor);
        registros.add(nuevoRegistro);
        return nuevoRegistro;
    }
}