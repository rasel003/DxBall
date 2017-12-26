package com.rasel.dxball;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.Log;

/**
 * Created by RaSeL on 30-Nov-17.
 */

public class Brick extends InterDrawable {
    private float left, top, right, bottom;

    Brick(float left, float top, float right, float bottom, Canvas canvas, Paint paint) {
        this.left = left;
        this.top = top;
        this.bottom = bottom;
        this.right = right;
        this.canvas = canvas;
        this.paint = paint;
    }

    @Override
    void Paint(Canvas canvas, Paint paint) {
        if (canvas == null || paint == null) {
            Log.i("rasel", "Canvas or Pain is Null");
            return;
        }
        canvas.drawRect(left, top, right, bottom, paint);
    }
    public float getLeft(){ return left; }
    public float getRight(){ return right; }
    public float getTop(){ return top; }
    public float getBottom(){ return bottom; }

    @Override
    void collideWiht() {

    }
}
