package com.rasel.dxball;

/**
 * Created by RaSeL on 14-Nov-17.
 */

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.MotionEvent;
import android.view.View;

public class GameCanvas extends View {
    Context context;
    Paint paint;
    Stage stageObj;
    boolean firstTime = true;
    private static int counter = 0;
    String level;
    Canvas canvas;

    protected void onDraw(Canvas canvas) {
        this.canvas =canvas;
        paint.setColor(Color.LTGRAY);
        paint.setStyle(Paint.Style.FILL);
        canvas.drawPaint(paint);
        if (firstTime) {
            firstTime = false;
            stageObj = new Stage(context, canvas, paint,level);
        }
        paint.setColor(Color.RED);
        paint.setTextAlign(Paint.Align.LEFT);
        paint.setTextSize(75);
        canvas.drawText("Level "+level, canvas.getWidth()/2 -100, 100, paint);
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
                    if(Stage.rightBar > canvas.getWidth()){
                        Stage.rightBar = canvas.getWidth()-4;
                        Stage.leftBar = Stage.rightBar - 300;
                    }
                    if(Stage.leftBar < 3){
                        Stage.leftBar = 3;
                        Stage.rightBar = Stage.leftBar + 300;
                    }
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

    public GameCanvas(Context context, String str) {
        super(context);
        this.level=str;
        paint = new Paint();
        this.context = context;
        this.setOnTouchListener(new touchHandler());

    }
}
