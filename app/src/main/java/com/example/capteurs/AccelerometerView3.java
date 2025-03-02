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
    private static final float SMOOTHING_FACTOR = 0.1f;
    private Paint paintBackground;
    private Paint paintCircle;
    private Paint paintLine;

    public AccelerometerView3(Context context, AttributeSet attrs) {
        super(context, attrs);
        paintBackground = new Paint();
        paintCircle = new Paint();
        paintCircle.setStyle(Paint.Style.FILL);
        paintLine = new Paint();
        paintLine.setStrokeWidth(12);
    }

    public void updateValues(float x, float y, float z) {
        smoothedX = SMOOTHING_FACTOR * x + (1 - SMOOTHING_FACTOR) * smoothedX;
        smoothedY = SMOOTHING_FACTOR * y + (1 - SMOOTHING_FACTOR) * smoothedY;
        smoothedZ = SMOOTHING_FACTOR * z + (1 - SMOOTHING_FACTOR) * smoothedZ;
        this.x = smoothedX;
        this.y = smoothedY;
        this.z = smoothedZ;
        invalidate();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int width = getWidth();
        int height = getHeight();
        float radius = Math.min(width, height) / 3.5f;

        paintBackground.setColor(Color.LTGRAY);
        canvas.drawRect(0, 0, width, height, paintBackground);

        int circleColor = getBackgroundColor(x, y, z);
        paintCircle.setColor(circleColor);

        canvas.drawCircle(width / 2, height / 2, radius, paintCircle);

        float angle = (float) Math.toDegrees(Math.atan2(y, x));

        paintLine.setColor(Color.WHITE);
        canvas.save();
        canvas.translate(width / 2, height / 2);
        canvas.rotate(angle);
        canvas.drawLine(0, 0, radius, 0, paintLine);
        canvas.restore();
    }

    private int getBackgroundColor(float x, float y, float z) {
        float maxVal = 10.0f;
        float magnitude = (float) Math.sqrt(x * x + y * y + z * z);
        if (magnitude > maxVal) {
            magnitude = maxVal;}
        if (magnitude < (maxVal / 3)) {
            return Color.GREEN;
        } else if (magnitude < (2 * maxVal / 3)) {
            return Color.BLACK;
        } else {
            return Color.RED;
        }
    }

}
