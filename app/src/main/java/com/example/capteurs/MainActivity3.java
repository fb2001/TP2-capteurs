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
import androidx.appcompat.widget.Toolbar;

public class MainActivity3 extends AppCompatActivity implements SensorEventListener {

    private AccelerometerView3 accelerometerView;
    private TextView txtValues;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }
        SensorManager sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        accelerometerView = findViewById(R.id.accelerometerView);
        txtValues = findViewById(R.id.txtValues);
        if (sensorManager != null) {
            Sensor accelerator = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);

            if (accelerator != null) {
                sensorManager.registerListener(this, accelerator, SensorManager.SENSOR_DELAY_UI);
            }
        } else {
            Toast.makeText(this, "L'Accelerometer n'est pas dispo", Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        if (event.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
            accelerometerView.updateValues(event.values[0], event.values[1], event.values[2]);
            txtValues.setText(
                    String.format("X: %.2f Y: %.2f Z: %.2f", event.values[0], event.values[1], event.values[2])
            );
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
    }
}