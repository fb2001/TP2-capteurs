package com.example.capteurs;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity3 extends AppCompatActivity implements SensorEventListener {

    private AccelerometerView3 accelerometerView;  // Référence à la vue personnalisée
    private TextView txtValues;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        SensorManager sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);

        accelerometerView = findViewById(R.id.accelerometerView);  // Initialiser la vue personnalisée
        txtValues = findViewById(R.id.txtValues);  // Initialiser le TextView pour afficher les valeurs

        if (sensorManager != null) {
            Sensor accelerator = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);

            if (accelerator != null) {
                sensorManager.registerListener(this, accelerator, SensorManager.SENSOR_DELAY_UI);
            }
        } else {
            Toast.makeText(this, "L'Accelerometer n'est pas dispo", Toast.LENGTH_SHORT).show();
        }

        Button buttonretour = (Button) findViewById(R.id.buttonretour);
        buttonretour.setOnClickListener(v -> finish());

    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        if (event.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
            // Mettre à jour les valeurs dans la vue personnalisée
            accelerometerView.updateValues(event.values[0], event.values[1], event.values[2]);

            // Afficher les valeurs dans le TextView pour la visibilité
            txtValues.setText(
                    String.format("X: %.2f Y: %.2f Z: %.2f", event.values[0], event.values[1], event.values[2])
            );
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
    }
}
