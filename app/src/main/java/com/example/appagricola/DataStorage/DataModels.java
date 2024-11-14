package com.example.appagricola.DataStorage;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DataModels {

    // Almacenamiento temporal de ubicaciones (companion object equivalente en Java)
    public static List<Ubicacion> ubicaciones = new ArrayList<>();

    // Clase Ubicacion
    public static class Ubicacion {
        private int id;
        private String nombre;
        private String descripcion;

        public Ubicacion(int id, String nombre, String descripcion) {
            this.id = id;
            this.nombre = nombre;
            this.descripcion = descripcion;
        }

        // Getters y Setters
        public int getId() { return id; }
        public void setId(int id) { this.id = id; }

        public String getNombre() { return nombre; }
        public void setNombre(String nombre) { this.nombre = nombre; }

        public String getDescripcion() { return descripcion; }
        public void setDescripcion(String descripcion) { this.descripcion = descripcion; }
    }

    // Clase Tipo
    public static class Tipo {
        private int id;
        private String nombre;

        public Tipo(int id, String nombre) {
            this.id = id;
            this.nombre = nombre;
        }

        // Getters y Setters
        public int getId() { return id; }
        public void setId(int id) { this.id = id; }

        public String getNombre() { return nombre; }
        public void setNombre(String nombre) { this.nombre = nombre; }
    }

    // Clase Sensor
    public static class Sensor {
        private int id;
        private String nombre;
        private String descripcion;
        private float ideal;
        private Tipo tipo;
        private Ubicacion ubicacion;

        public Sensor(int id, String nombre, String descripcion, float ideal, Tipo tipo, Ubicacion ubicacion) {
            this.id = id;
            this.nombre = nombre;
            this.descripcion = descripcion;
            this.ideal = ideal;
            this.tipo = tipo;
            this.ubicacion = ubicacion;
        }

        // Getters y Setters
        public int getId() { return id; }
        public void setId(int id) { this.id = id; }

        public String getNombre() { return nombre; }
        public void setNombre(String nombre) { this.nombre = nombre; }

        public String getDescripcion() { return descripcion; }
        public void setDescripcion(String descripcion) { this.descripcion = descripcion; }

        public float getIdeal() { return ideal; }
        public void setIdeal(float ideal) { this.ideal = ideal; }

        public Tipo getTipo() { return tipo; }
        public void setTipo(Tipo tipo) { this.tipo = tipo; }

        public Ubicacion getUbicacion() { return ubicacion; }
        public void setUbicacion(Ubicacion ubicacion) { this.ubicacion = ubicacion; }
    }

    // Clase Registro
    public static class Registro {
        private int id;
        private Date instante;
        private float lectura;
        private Sensor sensor;

        public Registro(int id, Date instante, float lectura, Sensor sensor) {
            this.id = id;
            this.instante = instante;
            this.lectura = lectura;
            this.sensor = sensor;
        }

        // Getters y Setters
        public int getId() { return id; }
        public void setId(int id) { this.id = id; }

        public Date getInstante() { return instante; }
        public void setInstante(Date instante) { this.instante = instante; }

        public float getLectura() { return lectura; }
        public void setLectura(float lectura) { this.lectura = lectura; }

        public Sensor getSensor() { return sensor; }
        public void setSensor(Sensor sensor) { this.sensor = sensor; }
    }
}