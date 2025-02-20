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

        // Initialisation des vues
        directionText = findViewById(R.id.directionText);
        layout = findViewById(R.id.mainLayout);
        backButton = findViewById(R.id.backButton);
        toolbar = findViewById(R.id.toolbar);

        // Configurer la Toolbar
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayShowTitleEnabled(false); // Masquer le titre par défaut
        }

        // Initialisation du GestureDetector
        gestureDetector = new GestureDetector(this, this);

        // Gestion du clic sur le bouton de retour
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish(); // Ferme l'activité actuelle et retourne à la précédente
            }
        });
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return gestureDetector.onTouchEvent(event) || super.onTouchEvent(event);
    }

    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
        float diffX = e2.getX() - e1.getX();
        float diffY = e2.getY() - e1.getY();

        if (Math.abs(diffX) > Math.abs(diffY)) {  // Mouvement horizontal
            if (diffX > 0) {
                showDirection("➡️ Droite", "#2196F3"); // Bleu
            } else {
                showDirection("⬅️ Gauche", "#F44336"); // Rouge
            }
        } else {  // Mouvement vertical
            if (diffY > 0) {
                showDirection("⬇️ Bas", "#4CAF50"); // Vert
            } else {
                showDirection("⬆️ Haut", "#FFEB3B"); // Jaune
            }
        }
        return true;
    }

    private void showDirection(String text, String color) {
        // Animation de fondu
        AlphaAnimation fade = new AlphaAnimation(0.3f, 1.0f);
        fade.setDuration(500);
        directionText.startAnimation(fade);

        // Animation de zoom
        ScaleAnimation zoom = new ScaleAnimation(
                1.0f, 1.2f,  // Zoom avant
                1.0f, 1.2f,  // Zoom avant
                Animation.RELATIVE_TO_SELF, 0.5f,
                Animation.RELATIVE_TO_SELF, 0.5f
        );
        zoom.setDuration(300);
        zoom.setRepeatCount(1);
        zoom.setRepeatMode(Animation.REVERSE);
        directionText.startAnimation(zoom);

        // Animation de translation
        TranslateAnimation translate = new TranslateAnimation(
                Animation.RELATIVE_TO_SELF, 0.0f,
                Animation.RELATIVE_TO_SELF, 0.0f,
                Animation.RELATIVE_TO_SELF, 0.5f,
                Animation.RELATIVE_TO_SELF, 0.0f
        );
        translate.setDuration(500);
        directionText.startAnimation(translate);

        // Animation du fond
        ValueAnimator colorAnimator = ValueAnimator.ofObject(new android.animation.ArgbEvaluator(), Color.parseColor("#121212"), Color.parseColor(color));
        colorAnimator.setDuration(500);
        colorAnimator.addUpdateListener(animator -> layout.setBackgroundColor((int) animator.getAnimatedValue()));
        colorAnimator.start();

        // Changer le texte de direction
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
