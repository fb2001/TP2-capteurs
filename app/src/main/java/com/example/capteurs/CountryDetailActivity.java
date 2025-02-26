package com.example.capteurs;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class CountryDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_country_detail);

        // Initialisation des vues
        ImageView countryImage = findViewById(R.id.country_image);
        TextView countryName = findViewById(R.id.country_name);
        TextView countryDescription = findViewById(R.id.country_description);

        // Récupérer les données passées via l'Intent
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            int imageResId = extras.getInt("image");
            String name = extras.getString("name");
            String description = extras.getString("description");

            // Afficher les données
            countryImage.setImageResource(imageResId);
            countryName.setText(name);
            countryDescription.setText(description);
        }

        // Initialiser la Toolbar
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("");


        // Activer le bouton de retour dans la Toolbar
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }
    }

    // Gérer l'action du bouton de retour
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();  // Retour à l'écran précédent
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
