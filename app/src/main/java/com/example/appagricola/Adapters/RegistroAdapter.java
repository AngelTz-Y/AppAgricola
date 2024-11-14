package com.example.appagricola.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.appagricola.R;
import com.example.appagricola.DataStorage.DataModels;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;

public class RegistroAdapter extends RecyclerView.Adapter<RegistroAdapter.RegistroViewHolder> {

    private List<DataModels.Registro> registros;


    public RegistroAdapter(List<DataModels.Registro> registros) {
        this.registros = registros;
    }

    public static class RegistroViewHolder extends RecyclerView.ViewHolder {
        TextView textViewUbicacion;
        TextView textViewDescripcionUbicacion;
        TextView textViewSensorNombre;
        TextView textViewTipo;
        TextView textViewDescripcionSensor;
        TextView textViewIdeal;
        TextView textViewLectura;
        TextView textViewFecha;

        public RegistroViewHolder(View view) {
            super(view);
            textViewUbicacion = view.findViewById(R.id.textViewUbicacion);
            textViewDescripcionUbicacion = view.findViewById(R.id.textViewDescripcionUbicacion);
            textViewSensorNombre = view.findViewById(R.id.textViewSensorNombre);
            textViewTipo = view.findViewById(R.id.textViewTipo);
            textViewDescripcionSensor = view.findViewById(R.id.textViewDescripcionSensor);
            textViewIdeal = view.findViewById(R.id.textViewIdeal);
            textViewLectura = view.findViewById(R.id.textViewLectura);
            textViewFecha = view.findViewById(R.id.textViewFecha);
        }
    }

    @NonNull
    @Override
    public RegistroViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_registro, parent, false);
        return new RegistroViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RegistroViewHolder holder, int position) {
        DataModels.Registro registro = registros.get(position);
        DataModels.Sensor sensor = registro.getSensor();
        DataModels.Ubicacion ubicacion = sensor.getUbicacion();

        // Datos de Ubicación
        holder.textViewUbicacion.setText("Ubicación: " + ubicacion.getNombre());
        holder.textViewDescripcionUbicacion.setText("Descripción: " + ubicacion.getDescripcion());

        // Datos del Sensor
        holder.textViewSensorNombre.setText("Sensor: " + sensor.getNombre());
        holder.textViewTipo.setText("Tipo: " + sensor.getTipo().getNombre());
        holder.textViewDescripcionSensor.setText("Descripción: " + sensor.getDescripcion());
        holder.textViewIdeal.setText("Valor Ideal: " + sensor.getIdeal());

        // Datos del Registro
        holder.textViewLectura.setText("Lectura: " + registro.getLectura());
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
        dateFormat.setTimeZone(TimeZone.getTimeZone("America/Santiago")); // Zona horaria de Chile
        holder.textViewFecha.setText("Fecha: " + dateFormat.format(registro.getInstante()));
    }

    @Override
    public int getItemCount() {
        return registros.size();
    }
}
