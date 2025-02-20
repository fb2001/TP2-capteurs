package com.example.capteurs;

import android.app.Activity;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity2 extends Activity {

    private SensorManager sensorManager;
    private TextView statusTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);


        TextView textViewDetails = (TextView) findViewById(R.id.textViewDetails);
        textViewDetails.setText("Capteurs indisponibles :");


        // Initialisation des éléments de l'interface
        statusTextView = findViewById(R.id.statusTextView);

        // Initialisation du SensorManager
        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);

        // Vérification des capteurs manquants
        checkMissingSensors();

        Button button = (Button) findViewById(R.id.buttonretour);
        button.setOnClickListener(v -> finish());
        button.setBackgroundColor(0xff2196F3);


    }


    private void checkMissingSensors() {
        StringBuilder missingSensors = new StringBuilder();

        int[] sensorTypes = {
                Sensor.TYPE_ACCELEROMETER,
                Sensor.TYPE_GYROSCOPE,
                Sensor.TYPE_LIGHT,
                Sensor.TYPE_MAGNETIC_FIELD,
                Sensor.TYPE_ORIENTATION,
                Sensor.TYPE_PRESSURE,
                Sensor.TYPE_PROXIMITY,
                Sensor.TYPE_AMBIENT_TEMPERATURE,
                Sensor.TYPE_GRAVITY,
                Sensor.TYPE_LINEAR_ACCELERATION,
                Sensor.TYPE_ROTATION_VECTOR,
                Sensor.TYPE_RELATIVE_HUMIDITY,
                Sensor.TYPE_GEOMAGNETIC_ROTATION_VECTOR,
                Sensor.TYPE_STEP_COUNTER,
                Sensor.TYPE_STEP_DETECTOR,
                Sensor.TYPE_HEART_RATE,
        };

        // Test de chaque capteur
        for (int sensorType : sensorTypes) {
            Sensor sensor = sensorManager.getDefaultSensor(sensorType);
            if (sensor == null) {
                missingSensors.append(getSensorName(sensorType)).append("\n");
            }
        }

        if (missingSensors.length() > 0) {
            statusTextView.setText(missingSensors.toString());
            Toast.makeText(this, "Certains capteurs sont manquants.", Toast.LENGTH_LONG).show();
        } else {
            statusTextView.setText("Tous les capteurs sont disponibles.");
        }
    }


    private String getSensorName(int sensorType) {
        switch (sensorType) {
            case Sensor.TYPE_ACCELEROMETER: return "Accéléromètre";
            case Sensor.TYPE_GYROSCOPE: return "Gyroscope";
            case Sensor.TYPE_MAGNETIC_FIELD: return "Champ magnétique";
            case Sensor.TYPE_LIGHT: return "Lumière";
            case Sensor.TYPE_PROXIMITY: return "Proximité";
            case Sensor.TYPE_PRESSURE: return "Pression";
            case Sensor.TYPE_AMBIENT_TEMPERATURE: return "Température ambiante";
            case Sensor.TYPE_GRAVITY: return "Gravité";
            case Sensor.TYPE_LINEAR_ACCELERATION: return "Accélération linéaire";
            case Sensor.TYPE_ROTATION_VECTOR: return "Vecteur de rotation";
            case Sensor.TYPE_RELATIVE_HUMIDITY: return "Humidité relative";
            case Sensor.TYPE_GEOMAGNETIC_ROTATION_VECTOR: return "Rotation géomagnétique";
            case Sensor.TYPE_STEP_COUNTER: return "Compteur de pas";
            case Sensor.TYPE_STEP_DETECTOR: return "Détecteur de pas";
            case Sensor.TYPE_ORIENTATION: return "Orientation";
            case Sensor.TYPE_HEART_RATE: return "Fréquence cardiaque";
            default: return "Inconnu";
        }
    }
}