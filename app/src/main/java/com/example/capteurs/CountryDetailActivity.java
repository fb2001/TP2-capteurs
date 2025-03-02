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
        ImageView countryImage = findViewById(R.id.country_image);
        TextView countryName = findViewById(R.id.country_name);
        TextView countryDescription = findViewById(R.id.country_description);
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            int imageResId = extras.getInt("image");
            String name = extras.getString("name");
            String description = extras.getString("description");
            countryImage.setImageResource(imageResId);
            countryName.setText(name);
            countryDescription.setText(description);
        }
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("");
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);}
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
