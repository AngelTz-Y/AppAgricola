package com.example.appagricola.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appagricola.DataStorage.DataModels;
import com.example.appagricola.R;

import java.util.List;

public class SensorAdapter extends RecyclerView.Adapter<SensorAdapter.SensorViewHolder> {

    private List<DataModels.Sensor> sensores;

    public SensorAdapter(List<DataModels.Sensor> sensores) {
        this.sensores = sensores;
    }

    public static class SensorViewHolder extends RecyclerView.ViewHolder {
        TextView textViewNombre;
        TextView textViewDescripcion;
        TextView textViewIdeal;

        public SensorViewHolder(View view) {
            super(view);

            textViewDescripcion = view.findViewById(R.id.textViewDescripcionSensor);
        }
    }

    @NonNull
    @Override
    public SensorViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_registro, parent, false);
        return new SensorViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SensorViewHolder holder, int position) {
        DataModels.Sensor sensor = sensores.get(position);
        holder.textViewNombre.setText("Nombre: " + sensor.getNombre());
        holder.textViewDescripcion.setText("Descripción: " + sensor.getDescripcion());
        holder.textViewIdeal.setText("Ideal: " + sensor.getIdeal());
    }

    @Override
    public int getItemCount() {
        return sensores.size();
    }
}
