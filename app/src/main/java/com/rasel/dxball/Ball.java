package com.rasel.dxball;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.Log;

/**
 * Created by RaSeL on 30-Nov-17.
 */

public class Ball extends InterDrawable {
    private float radius;
    Ball(float xValue, float yValue, Canvas canvas, Paint paint){
        this.radius = radius;
        this.x = xValue;
        this.y = yValue;
        this.canvas = canvas;
        this.paint = paint;
    }
    @Override
    void Paint(Canvas canvas, Paint paint) {
        canvas.drawCircle(x+=30,y, 40, paint);
    }

    @Override
    void collideWiht() {

    }
}
