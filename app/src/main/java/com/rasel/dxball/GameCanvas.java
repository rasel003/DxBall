package com.rasel.dxball;

/**
 * Created by RaSeL on 14-Nov-17.
 */

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.KeyEvent;
import android.view.View;

public class GameCanvas extends View {

    Paint paint; Stage stageObj;
    float x = 0, y = 0, radius = 40, dx = 8, dy = 8;
    boolean firstTime = true;

    public void setBarValue(int keyCode) {
        if (keyCode == KeyEvent.KEYCODE_DPAD_LEFT)
            this.x += 2;

    }

    protected void calculateNextPos(Canvas canvas) {
        if (x <= radius || x >= (canvas.getWidth()) - radius) {
            dx = -dx;
        }
        if (y <= radius || y >= (canvas.getHeight()) - radius) {
            dy = -dy;
        }
    }

    protected void onDraw(Canvas canvas) {
        if (firstTime) {
            firstTime = false;
            x = canvas.getWidth() / 2;
            y = canvas.getHeight() / 2;
            stageObj = new Stage(canvas,paint);
        }
       /* calculateNextPos(canvas);
        x -=dx; y -=dy;
        canvas.drawRGB(255, 255, 255);
        paint.setColor(Color.RED);
        paint.setStyle(Style.FILL);
        canvas.drawCircle(x,y, 40, paint);
        canvas.drawRect((canvas.getWidth()/2)-150, canvas.getHeight()-100, (canvas.getWidth()/2)+150, canvas.getHeight()-50, paint); */
       stageObj.drawStage();

        invalidate();
    }

    public GameCanvas(Context context) {
        super(context);
        paint = new Paint();
    }
}
