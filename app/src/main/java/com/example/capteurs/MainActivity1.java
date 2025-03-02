package com.example.capteurs;

import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.PagerSnapHelper;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.carousel.CarouselLayoutManager;

import java.util.List;

public class MainActivity1 extends AppCompatActivity {

    private SensorManager sensorManager;
    private RecyclerView recyclerViewSensors;
    private SensorAdapter1 sensorAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main1);

        recyclerViewSensors = findViewById(R.id.recyclerViewSensors);

        CarouselLayoutManager1 layoutManager = new CarouselLayoutManager1(this);
        recyclerViewSensors.setLayoutManager(layoutManager);

        new PagerSnapHelper().attachToRecyclerView(recyclerViewSensors);
        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        listSensors();

        int horizontalPadding = (int) (getResources().getDisplayMetrics().density * 60);
        int verticalPadding = (int) (getResources().getDisplayMetrics().density * 20);
        recyclerViewSensors.setPadding(horizontalPadding, verticalPadding, horizontalPadding, verticalPadding);
        recyclerViewSensors.setClipToPadding(false);
        recyclerViewSensors.post(() -> {
            if (sensorAdapter.getItemCount() > 0) {
                recyclerViewSensors.smoothScrollToPosition(0);
            }
        });
    }

    private void listSensors() {
        List<Sensor> sensors = sensorManager.getSensorList(Sensor.TYPE_ALL);
        sensorAdapter = new SensorAdapter1(this, sensors);
        recyclerViewSensors.setAdapter(sensorAdapter);
    }
}
