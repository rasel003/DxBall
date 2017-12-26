package com.rasel.dxball;

/**
 * Created by RaSeL on 14-Nov-17.
 */

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.MotionEvent;
import android.view.View;

public class GameCanvas extends View {
    Context context;
    Paint paint;
    Stage stageObj;
    boolean firstTime = true;
    private static int counter = 0;

    protected void onDraw(Canvas canvas) {
        if (firstTime) {
            firstTime = false;
            stageObj = new Stage(context, canvas, paint);
        }
        paint.setTextAlign(Paint.Align.LEFT);
        paint.setTextSize(75);
        canvas.drawText("Level 1", 60, 100, paint);
        stageObj.drawStage(canvas, paint);

        invalidate();
    }

    public class touchHandler implements OnTouchListener {
        @Override
        public boolean onTouch(View view, MotionEvent motionEvent) {

            switch (motionEvent.getAction()) {
                case MotionEvent.ACTION_MOVE:
                    Stage.leftBar = motionEvent.getX();
                    Stage.rightBar = Stage.leftBar + 300;
                    counter++;
                    break;
                case MotionEvent.ACTION_UP:
                    if (counter < 10) {
                        if (Stage.isPause) {
                            Stage.isPause = false;
                        } else if (!Stage.isPause) {
                            Stage.isPause = true;
                        }
                    }
                    counter = 0;
                    break;
            }
            return true;
        }
    }

    public GameCanvas(Context context) {
        super(context);
        paint = new Paint();
        this.context = context;
        this.setOnTouchListener(new touchHandler());

    }
}
