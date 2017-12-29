package com.rasel.dxball;

import android.content.Context;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

class Stage {
    private Context context;
    static float leftBar, rightBar;
    private float topBar, bottomBar;
    private float canvasWidth, canvasHeight, xBallValue, yBallValue, dx, dy, radius;
    private Canvas canvas;
    private Paint paint;
    private List<Brick> listBrick;
    private int score, numberOfBrick;
    boolean newLife;
    private int life;
    static boolean isPause;

    private void Win() {
        if (score == numberOfBrick) {
            isPause=true;
            Intent intent = new Intent(context, MainActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent);
        }
    }

    private void checkDeadOrAlive() {
        if (yBallValue > canvasHeight - 130) {
            life -= 1;
            xBallValue = leftBar + 120;
            yBallValue = topBar - radius + 5;
            isPause = true;
            dx = Math.abs(dx);
            dy = Math.abs(dy);
        }
        if (life < 0) {
            life = 0;
            Intent intent = new Intent(context, MainActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent);
        }
    }

    private void upDateBallPossition() {
        if (xBallValue <= radius || xBallValue >= canvasWidth - radius) {
            dx = -dx;
        }
        if (yBallValue <= radius + 100 || yBallValue >= (canvas.getHeight()) - radius) {
            dy = -dy;
        }
        xBallValue -= dx;
        yBallValue -= dy;
    }

    private void colides() {  //collision id in the parameter
        List<Brick> toRemove = new ArrayList<>();
        Log.d("rasel","values of Ball Left: "+(xBallValue-radius));
        Log.d("rasel","values of Ball Right: "+(xBallValue+radius));
        Log.d("rasel","values of Ball Top: "+yBallValue);
        Log.d("rasel","values of Ball Bottom: "+yBallValue);
        int ball = 1;
        for (Brick obj : listBrick) {
            Log.e("rasel","Ball 1");
           Log.e("rasel","Bottom; "+obj.getBottom());
           Log.e("rasel","Left; "+obj.getLeft());
            Log.e("rasel","Right; "+obj.getRight());

            if ((yBallValue + radius) - obj.getBottom() <= 5 && xBallValue + radius >= obj.getLeft() &&
                    xBallValue - radius <= obj.getRight()) {
                dy = -dy;
                toRemove.add(obj);
                score += 1;
            }
        }
        if (xBallValue + radius >= leftBar && xBallValue - radius <= rightBar && topBar - (yBallValue + radius) <= dx) {
            dy = -dy;
            if(isPause){
                dx = - Math.abs(dx);
                dy = Math.abs(dy);
            }

        }
        listBrick.removeAll(toRemove);

    }

    private void updateScore(Canvas canvas, Paint paint) {
        paint.setTextSize(75);
        canvas.drawText("Score: " + score, canvasWidth - 300, 100, paint);
        paint.setColor(Color.CYAN);
        canvas.drawText("Life: " + life, 60, 100, paint);
    }

    private void createObjectToDraw() {
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

    Stage(Context context, Canvas canvas, Paint paint, String str) {
        String level = str;
        level = level.trim();
        String line = "";
        StringBuilder finalString = new StringBuilder();
        InputStream inputStream = null;
        if (level.equals("1")) {
            inputStream = context.getResources().openRawResource(R.raw.level1);
        } else if (level.equals("2")) {
            inputStream = context.getResources().openRawResource(R.raw.level2);
        }
        try {
            if (inputStream != null) {
                BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

                while ((line = reader.readLine()) != null) {
                    finalString.append(line);
                }
            } else {
                Log.d("rasel", "Input Stream is Null");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        String brick = finalString.substring(finalString.indexOf("=") + 1, finalString.indexOf(" "));
        String speed = finalString.substring(finalString.indexOf("Speed=") + 6);
        speed = speed.trim();
        brick = brick.trim();

        this.context = context;
        this.canvas = canvas;
        this.paint = paint;
        radius = 40;
        canvasWidth = canvas.getWidth();
        canvasHeight = canvas.getHeight();
        leftBar = (canvas.getWidth() / 2) - 150;
        rightBar = leftBar + 300;
        topBar = canvas.getHeight() - 200;
        bottomBar = canvas.getHeight() - 150;
        xBallValue = leftBar + 120;
        yBallValue = topBar - radius + 5;
        life = 3;

        score = 0;
        numberOfBrick = Integer.valueOf(brick);
        dx = dy = Integer.valueOf(speed);
        isPause = true;

        listBrick = new ArrayList<>();
        createObjectToDraw();
    }

    void drawStage(Canvas canvas, Paint paint) {

        Ball objBall = new Ball(xBallValue, yBallValue, canvas, paint);
        paint.setColor(Color.BLACK);
        objBall.Paint(canvas, paint);

        Bar objBar = new Bar(leftBar, topBar, rightBar, bottomBar, canvas, paint);
        paint.setColor(Color.RED);
        objBar.Paint(canvas, paint);
        paint.setColor(Color.BLUE);
        for (Brick obj : listBrick) {
            obj.Paint(canvas, paint);
        }
        checkDeadOrAlive();
        if (!isPause) {
            upDateBallPossition();
        } else {
            paint.setColor(Color.MAGENTA);
            canvas.drawText("TAP TO START ", canvasWidth / 2 - 300, canvasHeight / 2, paint);
        }
        colides();
        paint.setColor(Color.YELLOW);
        updateScore(canvas, paint);
        Win();
    }
}
