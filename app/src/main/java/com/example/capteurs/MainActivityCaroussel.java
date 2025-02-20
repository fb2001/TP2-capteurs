package com.example.capteurs;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.appbar.MaterialToolbar;

import java.util.ArrayList;

public class MainActivityCaroussel extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maincaroussel);

        RecyclerView recyclerView = findViewById(R.id.recycler);
        MaterialToolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ArrayList<Integer> imageList = new ArrayList<>();
        imageList.add(R.drawable.capteurs);
        imageList.add(R.drawable.inexistant);
        imageList.add(R.drawable.accele);
        imageList.add(R.drawable.mouv);
        imageList.add(R.drawable.secouer);
        imageList.add(R.drawable.proxi);
        imageList.add(R.drawable.geolo);

        ImageAdapter adapter = new ImageAdapter(this, imageList);
        recyclerView.setAdapter(adapter);
        adapter.setOnItemClickListener(new ImageAdapter.OnItemClickListener() {
            @Override
            public void onClick(ImageView imageView, int imageResId, int position) {
                Intent intent;
                switch (position) {
                    case 0:
                        intent = new Intent(MainActivityCaroussel.this, MainActivity1.class);
                        break;
                    case 1:
                        intent = new Intent(MainActivityCaroussel.this, MainActivity2.class);
                        break;
                    case 2:
                        intent = new Intent(MainActivityCaroussel.this, MainActivity3.class);
                        break;
                    case 3:
                        intent = new Intent(MainActivityCaroussel.this, MainActivity4.class);
                        break;
                    default:
                        intent = new Intent(MainActivityCaroussel.this, ImageViewActivity.class);
                        intent.putExtra("image", imageResId);
                        break;
                }
                startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(MainActivityCaroussel.this, imageView, "image").toBundle());
            }
        });

        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(layoutManager);

        // Ajouter une décoration pour l'espacement entre les éléments
        recyclerView.addItemDecoration(new HorizontalSpaceItemDecoration(16));
        recyclerView.setAdapter(adapter);
    }
}