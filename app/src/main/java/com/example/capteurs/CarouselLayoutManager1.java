package com.example.capteurs;

import android.content.Context;
import android.view.View;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class CarouselLayoutManager1 extends LinearLayoutManager {

    public CarouselLayoutManager1(Context context) {
        super(context, HORIZONTAL, false);
    }

    @Override
    public void onLayoutChildren(RecyclerView.Recycler recycler, RecyclerView.State state) {
        super.onLayoutChildren(recycler, state);
        scaleItems();
    }

    @Override
    public int scrollHorizontallyBy(int dx, RecyclerView.Recycler recycler, RecyclerView.State state) {
        // Ne pas limiter le défilement dans aucune direction
        int scrolled = super.scrollHorizontallyBy(dx, recycler, state);
        if (scrolled != 0) {
            scaleItems();
        }
        return scrolled;
    }

    @Override
    public boolean canScrollHorizontally() {
        // Assurez-vous que le défilement horizontal est activé
        return true;
    }

    private void scaleItems() {
        float center = getWidth() / 2.0f;

        for (int i = 0; i < getChildCount(); i++) {
            View child = getChildAt(i);
            float childCenter = (getDecoratedLeft(child) + getDecoratedRight(child)) / 2.0f;
            float distanceFromCenter = Math.abs(center - childCenter);

            // Items closer to center are larger, items farther are smaller
            float scale = 1.0f - (distanceFromCenter / getWidth() * 0.3f);
            scale = Math.max(0.7f, scale); // Limite l'échelle minimale à 0.7

            child.setScaleX(scale);
            child.setScaleY(scale);

            // Adjust alpha for a nicer visual effect
            child.setAlpha(scale);
        }
    }
}