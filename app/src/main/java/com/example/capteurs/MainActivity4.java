package com.example.capteurs;

import android.animation.ValueAnimator;
import android.graphics.Color;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class MainActivity4 extends AppCompatActivity implements GestureDetector.OnGestureListener {

    private TextView directionText;
    private GestureDetector gestureDetector;
    private RelativeLayout layout;
    private Button backButton;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);
        directionText = findViewById(R.id.directionText);
        layout = findViewById(R.id.mainLayout);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }
        gestureDetector = new GestureDetector(this, this);
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return gestureDetector.onTouchEvent(event) || super.onTouchEvent(event);
    }

    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
        float diffX = e2.getX() - e1.getX();
        float diffY = e2.getY() - e1.getY();

        if (Math.abs(diffX) > Math.abs(diffY)) {
            if (diffX > 0) {
                showDirection("➡️ Droite", "#2196F3");
            } else {
                showDirection("⬅️ Gauche", "#F44336");
            }
        } else {
            if (diffY > 0) {
                showDirection("⬇️ Bas", "#4CAF50");
            } else {
                showDirection("⬆️ Haut", "#FFEB3B");
            }
        }
        return true;
    }

    private void showDirection(String text, String color) {
        AlphaAnimation fade = new AlphaAnimation(0.3f, 1.0f);
        fade.setDuration(500);
        directionText.startAnimation(fade);
        ScaleAnimation zoom = new ScaleAnimation(
                1.0f, 1.2f,
                1.0f, 1.2f,
                Animation.RELATIVE_TO_SELF, 0.5f,
                Animation.RELATIVE_TO_SELF, 0.5f
        );
        zoom.setDuration(300);
        zoom.setRepeatCount(1);
        zoom.setRepeatMode(Animation.REVERSE);
        directionText.startAnimation(zoom);
        TranslateAnimation translate = new TranslateAnimation(
                Animation.RELATIVE_TO_SELF, 0.0f,
                Animation.RELATIVE_TO_SELF, 0.0f,
                Animation.RELATIVE_TO_SELF, 0.5f,
                Animation.RELATIVE_TO_SELF, 0.0f
        );
        translate.setDuration(500);
        directionText.startAnimation(translate);
        ValueAnimator colorAnimator = ValueAnimator.ofObject(new android.animation.ArgbEvaluator(), Color.parseColor("#121212"), Color.parseColor(color));
        colorAnimator.setDuration(500);
        colorAnimator.addUpdateListener(animator -> layout.setBackgroundColor((int) animator.getAnimatedValue()));
        colorAnimator.start();
        directionText.setText(text);
    }

    @Override
    public boolean onDown(MotionEvent e) { return false; }
    @Override
    public void onShowPress(MotionEvent e) {}
    @Override
    public boolean onSingleTapUp(MotionEvent e) { return false; }
    @Override
    public void onLongPress(MotionEvent e) {}
    @Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
        return false;
    }
}