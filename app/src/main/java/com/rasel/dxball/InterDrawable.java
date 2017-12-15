package com.rasel.dxball;

import android.graphics.Canvas;
import android.graphics.Paint;

/**
 * Created by RaSeL on 30-Nov-17.
 */

abstract class InterDrawable {
    float x, y;
    Canvas canvas; Paint paint;

    abstract void Paint();
    abstract void collideWiht();
}
