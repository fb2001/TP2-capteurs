package com.example.capteurs;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        Button btn1 = (Button) findViewById(R.id.buttonlistecapteurs);
        btn1.setOnClickListener(v -> {
            Intent intent = new Intent();
            intent.setClass(MainActivity.this, MainActivity1.class);
            startActivity(intent);
        });

        Button btn2 = (Button) findViewById(R.id.buttoncapteursindisponible);
        btn2.setOnClickListener(v -> {
            Intent intent = new Intent();
            intent.setClass(MainActivity.this, MainActivity2.class);
            startActivity(intent);
        });


        Button btn3 = (Button) findViewById(R.id.buttonaccel);
        btn3.setOnClickListener(v -> {
            Intent intent = new Intent();
            intent.setClass(MainActivity.this, MainActivity3.class);
            startActivity(intent);
        });

        Button btn4 = (Button) findViewById(R.id.buttonmouvement);
        btn4.setOnClickListener(v -> {
            Intent intent = new Intent();
            intent.setClass(MainActivity.this, MainActivity4.class);
            startActivity(intent);
        });




    }
}