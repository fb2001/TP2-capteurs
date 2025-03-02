package com.example.capteurs;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class SensorDetailActivity1 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sensor_detail1);

        TextView textViewDetails = findViewById(R.id.textViewDetails);

        String name = getIntent().getStringExtra("name");
        String vendor = getIntent().getStringExtra("vendor");
        float resolution = getIntent().getFloatExtra("resolution", 0);
        float power = getIntent().getFloatExtra("power", 0);
        float maxRange = getIntent().getFloatExtra("maxRange", 0);
        int minDelay = getIntent().getIntExtra("minDelay", 0);
        int couleur = getIntent().getIntExtra("couleur", 0);

        String details = "Nom : " + name + "\n"
                + "Fabricant : " + vendor + "\n"
                + "Résolution : " + resolution + "\n"
                + "Consommation : " + power + " mA\n"
                + "Portée Max : " + maxRange + "\n"
                + "Délai Min : " + minDelay + " µs";


        Button buttonretour = findViewById(R.id.buttonretour);
        buttonretour.setOnClickListener(v -> finish());
        buttonretour.setBackgroundColor(couleur);
        textViewDetails.setText(details);
        textViewDetails.setTextColor(couleur);
    }
}
