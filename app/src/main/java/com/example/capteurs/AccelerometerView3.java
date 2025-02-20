package com.example.capteurs;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

public class AccelerometerView3 extends View {

    private float x, y, z;
    private float smoothedX, smoothedY, smoothedZ;
    private static final float SMOOTHING_FACTOR = 0.1f;  // Facteur de lissage (entre 0 et 1)
    private Paint paintBackground;
    private Paint paintCircle;
    private Paint paintLine;

    public AccelerometerView3(Context context, AttributeSet attrs) {
        super(context, attrs);
        paintBackground = new Paint();
        paintCircle = new Paint();
        paintCircle.setStyle(Paint.Style.FILL);

        paintLine = new Paint();
        paintLine.setStrokeWidth(12);  // Épaisseur de la ligne
    }

    // Met à jour les valeurs de l'accéléromètre
    public void updateValues(float x, float y, float z) {
        // Lissage des valeurs avant de les utiliser
        smoothedX = SMOOTHING_FACTOR * x + (1 - SMOOTHING_FACTOR) * smoothedX;
        smoothedY = SMOOTHING_FACTOR * y + (1 - SMOOTHING_FACTOR) * smoothedY;
        smoothedZ = SMOOTHING_FACTOR * z + (1 - SMOOTHING_FACTOR) * smoothedZ;

        this.x = smoothedX;
        this.y = smoothedY;
        this.z = smoothedZ;

        invalidate();  // Redessine la vue
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        int width = getWidth();
        int height = getHeight();
        float radius = Math.min(width, height) / 3.5f;

        // Couleur de fond gris flouté
        paintBackground.setColor(Color.LTGRAY);  // Gris clair pour un fond neutre
        canvas.drawRect(0, 0, width, height, paintBackground);  // Dessiner le fond gris

        // Calculer la couleur du cercle (utilisation de la même logique que pour le fond)
        int circleColor = getBackgroundColor(x, y, z); // Utiliser la même logique que pour le fond
        paintCircle.setColor(circleColor);

        // Dessiner le cercle de l'accéléromètre
        canvas.drawCircle(width / 2, height / 2, radius, paintCircle);

        // Calculer l'angle en fonction des valeurs de l'accéléromètre
        float angle = (float) Math.toDegrees(Math.atan2(y, x));

        // Dessiner le trait représentant l'angle de l'accéléromètre
        paintLine.setColor(Color.WHITE);  // Couleur de la ligne (blanc)
        canvas.save();
        canvas.translate(width / 2, height / 2);
        canvas.rotate(angle);
        canvas.drawLine(0, 0, radius, 0, paintLine);
        canvas.restore();
    }

    // Déterminer la couleur de fond en fonction des valeurs de l'accéléromètre
    private int getBackgroundColor(float x, float y, float z) {
        // Prendre la valeur absolue des axes pour éviter les signes négatifs
        float maxVal = 10.0f;  // Valeur de référence pour la normalisation
        float magnitude = (float) Math.sqrt(x * x + y * y + z * z);  // Calculer l'intensité des valeurs

        // Limiter la magnitude à une valeur maximale pour éviter qu'elle soit trop grande
        if (magnitude > maxVal) {
            magnitude = maxVal;
        }

        // Répartition en trois catégories de couleurs
        if (magnitude < (maxVal / 3)) {
            return Color.GREEN;  // Valeurs faibles -> Vert
        } else if (magnitude < (2 * maxVal / 3)) {
            return Color.BLACK;  // Valeurs moyennes -> Noir
        } else {
            return Color.RED;  // Valeurs élevées -> Rouge
        }
    }

}
