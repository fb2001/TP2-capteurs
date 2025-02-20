package com.example.capteurs;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.hardware.Sensor;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class SensorAdapter1 extends RecyclerView.Adapter<SensorAdapter1.SensorViewHolder> {

    private Context context;
    private List<Sensor> sensorList;
    private static final String TAG = "SensorAdapter";

    public SensorAdapter1(Context context, List<Sensor> sensorList) {
        this.context = context;
        this.sensorList = sensorList;
    }

    @NonNull
    @Override
    public SensorViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.sensor_item1, parent, false);
        return new SensorViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SensorViewHolder holder, int position) {
        Sensor sensor = sensorList.get(position);
        holder.sensorName.setText(sensor.getName());
        holder.sensorIcon.setText(getEmojiForSensorType(sensor.getType()));

        // Génération d'une couleur pseudo-aléatoire basée sur le type de capteur
        final int couleur = getColorForSensorType(sensor.getType());

        holder.itemView.setOnClickListener(v -> {
            try {
                Intent intent = new Intent(context, SensorDetailActivity1.class);
                intent.putExtra("name", sensor.getName());
                intent.putExtra("vendor", sensor.getVendor());
                intent.putExtra("resolution", sensor.getResolution());
                intent.putExtra("power", sensor.getPower());
                intent.putExtra("maxRange", sensor.getMaximumRange());
                intent.putExtra("minDelay", sensor.getMinDelay());
                intent.putExtra("couleur", couleur);
                context.startActivity(intent);
                Log.d(TAG, "Navigation vers SensorDetailActivity pour le capteur: " + sensor.getName());
            } catch (Exception e) {
                Log.e(TAG, "Erreur lors de la navigation: " + e.getMessage());
                e.printStackTrace();
            }
        });
    }

    @Override
    public int getItemCount() {
        return sensorList.size();
    }

    // Méthode pour obtenir une couleur basée sur le type de capteur
    private int getColorForSensorType(int sensorType) {
        switch (sensorType) {
            case Sensor.TYPE_ACCELEROMETER:
                return Color.rgb(255, 64, 64); // Rouge
            case Sensor.TYPE_GYROSCOPE:
                return Color.rgb(64, 64, 255); // Bleu
            case Sensor.TYPE_LIGHT:
                return Color.rgb(255, 215, 64); // Jaune
            case Sensor.TYPE_PRESSURE:
                return Color.rgb(64, 192, 255); // Bleu clair
            case Sensor.TYPE_PROXIMITY:
                return Color.rgb(255, 128, 64); // Orange
            case Sensor.TYPE_MAGNETIC_FIELD:
                return Color.rgb(128, 64, 255); // Violet
            case Sensor.TYPE_AMBIENT_TEMPERATURE:
                return Color.rgb(255, 64, 192); // Rose
            case Sensor.TYPE_GRAVITY:
                return Color.rgb(64, 192, 64); // Vert
            case Sensor.TYPE_ROTATION_VECTOR:
                return Color.rgb(192, 64, 192); // Mauve
            default:
                return Color.rgb(128, 128, 128); // Gris
        }
    }

    public static class SensorViewHolder extends RecyclerView.ViewHolder {
        TextView sensorIcon;
        TextView sensorName;

        public SensorViewHolder(@NonNull View itemView) {
            super(itemView);
            sensorIcon = itemView.findViewById(R.id.sensorIcon);
            sensorName = itemView.findViewById(R.id.sensorName);
        }
    }

    // Méthode pour associer un emoji à chaque capteur
    private String getEmojiForSensorType(int sensorType) {
        switch (sensorType) {
            case Sensor.TYPE_ACCELEROMETER:
                return "⚡"; // Accéléromètre
            case Sensor.TYPE_GYROSCOPE:
                return "🌀"; // Gyroscope
            case Sensor.TYPE_LIGHT:
                return "💡"; // Capteur de lumière
            case Sensor.TYPE_PRESSURE:
                return "🌡"; // Baromètre
            case Sensor.TYPE_PROXIMITY:
                return "📏"; // Proximité
            case Sensor.TYPE_MAGNETIC_FIELD:
                return "🧲"; // Champ magnétique
            case Sensor.TYPE_AMBIENT_TEMPERATURE:
                return "🔥"; // Température ambiante
            case Sensor.TYPE_GRAVITY:
                return "🌍"; // Gravité
            case Sensor.TYPE_ROTATION_VECTOR:
                return "🔄"; // Rotation
            default:
                return "📡"; // Capteur inconnu
        }
    }
}