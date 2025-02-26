package com.example.capteurs;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivityFragex2 extends AppCompatActivity implements CountryListFragment.OnCountrySelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_frag_ex2);

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragment_container, new CountryListFragment())
                    .commit();
        }
    }

    public void showCountryDetail(int image, String name, String description) {
        CountryDetailFragment detailFragment = CountryDetailFragment.newInstance(image, name, description);
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, detailFragment)
                .addToBackStack(null)
                .commit();
    }


    @Override
    public void onCountrySelected(int image, String name, String description) {
        showCountryDetail(image, name, description);

    }
}
