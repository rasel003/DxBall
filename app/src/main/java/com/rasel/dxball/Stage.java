package com.rasel.dxball;

import android.content.Context;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

class Stage {
    Context context;
    static float leftBar, rightBar, topBar, bottomBar;
    private float canvasWidth, canvasHeight, xBallValue, yBallValue, dx, dy, radius;
    private Canvas canvas;
    private Paint paint;
    private List<Brick> listBrick;
    int score, numberOfBrick;
    boolean newLife;
    static boolean isPause;
    Ball objBall;

    void Win() {
        if(score==numberOfBrick){
            Intent intent = new Intent(context,MainActivity.class);
            context.startActivity(intent);
        }
    }

    private void upDateBallPossition() {
        if (xBallValue <= radius || xBallValue >= canvasWidth - radius) {
            dx = -dx;
        }
        if (yBallValue <= radius+100 || yBallValue >= (canvas.getHeight()) - radius) {
            dy = -dy;
        }
        xBallValue -= dx;
        yBallValue -= dy;
    }

    void colides() {  //collision id in the parameter
        List<Brick> toRemove = new ArrayList<>();
        for (Brick obj : listBrick) {
            if (yBallValue - obj.getBottom() < 8 && Math.abs(obj.getBottom() - yBallValue) < 8 && xBallValue >= obj.getLeft() &&
                    xBallValue <= obj.getRight()) {
                dy = -dy;
                toRemove.add(obj);
                Log.e("rasel", "Collided");
                score +=1;
            }
        }
        if (xBallValue >= leftBar && xBallValue <= rightBar && topBar - yBallValue < 8) {
            Log.e("rasel", "ball Y: " + yBallValue);
            Log.e("rasel", "bar  Y: " + topBar);
            dy = -dy;

        }
       listBrick.removeAll(toRemove);

    }

    void updateScore(Canvas canvas, Paint paint) {
        paint.setTextSize(75);
        canvas.drawText("Score: "+score,760,100,paint);
    }

    void pause() {

    }

    public void createObjectToDraw() {
        Brick objBrick;
        float valueX = 60, valueY = 160;

        for (int i = 0; i < numberOfBrick; i++) {
            if (valueX + 260 > canvas.getWidth()) {
                valueX = 60;
                valueY += 120;
            }
            objBrick = new Brick(valueX, valueY, valueX + 200, valueY + 100, canvas, paint);
            listBrick.add(objBrick);
            valueX = valueX + 220;
        }
    }

    Stage(Context context, Canvas canvas, Paint paint) {
        this.context = context;
        this.canvas = canvas;
        this.paint = paint;
        radius = 40;
        canvasWidth = canvas.getWidth();
        canvasHeight = canvas.getHeight();
        leftBar = (canvas.getWidth() / 2) - 150;
        rightBar = leftBar + 300;
        topBar = canvas.getHeight() - 100;
        bottomBar = canvas.getHeight() - 50;
        xBallValue = leftBar+120;
        yBallValue = topBar-radius+5;
        dx = 8;
        dy = 8;
        score = 0;
        numberOfBrick = 10;
        isPause = true;

        listBrick = new ArrayList<>();
        createObjectToDraw();
    }

    void drawStage(Canvas canvas, Paint paint) {

            objBall = new Ball(xBallValue, yBallValue, canvas, paint);
            objBall.Paint(canvas, paint);

            Bar objBar = new Bar(leftBar, topBar, rightBar, bottomBar, canvas, paint);
            objBar.Paint(canvas, paint);
            for (Brick obj : listBrick) {
                obj.Paint(canvas, paint);
            }
            if(!isPause){
                upDateBallPossition();
            }
            colides();
            updateScore(canvas, paint);
    }
}
