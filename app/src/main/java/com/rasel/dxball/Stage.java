package com.rasel.dxball;

import android.graphics.Canvas;
import android.graphics.Paint;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

class Stage {
     private final float leftBar, rightBar,topBar,bottomBar;
     private float canvasWidth, canvasHeight; private Canvas canvas; private Paint paint;
     private List<Brick> listBrick ;
     int score;
     int time;
     boolean newLife;

     void Win(){

     }
     void colides(){  //collision id in the parameter

     }
     void updateScore(){

     }
     void pause(){

     }
     private void createObjectToDraw(){
         Brick objBrick; Random random = new Random();

         for(int i=0; i<10; i++){
             float valueX = random.nextInt((int)canvasWidth-400);
             float valueY = random.nextInt((int)canvasWidth/2-400);
             objBrick = new Brick(valueX,valueY,valueX+300,valueY+300,canvas,paint);
             listBrick.add(objBrick);
         }
     }
     Stage(Canvas canvas, Paint paint){
         this.canvas = canvas; this.paint = paint;
         canvasWidth = canvas.getWidth();
         canvasHeight = canvas.getHeight();
         leftBar = (canvas.getWidth() / 2) - 150;
         rightBar = (canvas.getWidth() / 2) + 150;
         topBar = canvas.getHeight() - 100;
         bottomBar = canvas.getHeight() - 50;

         listBrick = new ArrayList<>();
         createObjectToDraw();
     }
     void drawStage(){
         Ball objBall = new Ball(canvasWidth/2, canvasHeight/2, canvas, paint);
         objBall.Paint();

         Bar objBar = new Bar(leftBar, topBar, rightBar, bottomBar, canvas, paint);
         objBar.Paint();
         for(Brick obj: listBrick){
             obj.Paint();
         }
     }


}
