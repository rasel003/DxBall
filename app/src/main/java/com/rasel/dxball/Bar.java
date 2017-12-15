package com.rasel.dxball;

import android.graphics.Canvas;
import android.graphics.Paint;

/**
 * Created by RaSeL on 30-Nov-17.
 */

public class Bar extends InterDrawable {
    private float left, top, right, bottom;
    Bar(float left, float top, float right, float bottom, Canvas canvas, Paint paint){
        this.left = left;
        this.top = top;
        this.bottom = bottom;
        this.right = right;
        this.canvas = canvas;
        this.paint = paint;
    }
    @Override
    void Paint() {
        canvas.drawRect(left, top, right, bottom, paint);
    }

    @Override
    void collideWiht() {

    }
}
