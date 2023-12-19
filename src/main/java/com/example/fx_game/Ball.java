package com.example.fx_game;

import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;

public class Ball extends Circle {


    public enum Direction{TOP_RIGHT,TOP_LEFT,BOTTOM_RIGHT,BOTTOM_LEFT};
    private Direction direction = Direction.TOP_RIGHT;
    private int posX;
    private int posY;
    private final int radius = 5;
    private GameOver gameOver;
    Ball(GameOver gameOver){
        super(Main.width/2, Main.height/2,10, Paint.valueOf("red"));
        posX = (int) getCenterX();
        posY = (int) getCenterY();
        this.gameOver = gameOver;
    }

    public void setDirection(CollisionDetect.CollisionFrom collision) {
        switch (direction){
            case TOP_LEFT:
                direction = collision == CollisionDetect.CollisionFrom.VERTICAL ?  Direction.TOP_RIGHT : Direction.BOTTOM_LEFT;
                break;
            case TOP_RIGHT:
                direction = collision == CollisionDetect.CollisionFrom.VERTICAL ?  Direction.TOP_LEFT : Direction.BOTTOM_RIGHT;
                break;
            case BOTTOM_LEFT:
                direction = collision == CollisionDetect.CollisionFrom.VERTICAL ?  Direction.BOTTOM_RIGHT : Direction.TOP_LEFT;
                break;
            case BOTTOM_RIGHT:
                direction = collision == CollisionDetect.CollisionFrom.VERTICAL ?  Direction.BOTTOM_LEFT : Direction.TOP_RIGHT;
                break;
        }
    }

    public void move(){
        switch (direction) {
            case TOP_LEFT:
                posX--;
                posY--;
                if (posX - radius <= 0)
                    direction = Direction.TOP_RIGHT;
                if (posY - radius <= 0)
                    direction = Direction.BOTTOM_LEFT;
                break;
            case TOP_RIGHT:
                posX++;
                posY--;
                if (posX + radius >= Main.width)
                    direction = Direction.TOP_LEFT;
                if (posY - radius <= 0)
                    direction = Direction.BOTTOM_RIGHT;
                break;
            case BOTTOM_LEFT:
                posX--;
                posY++;
                if (posX - radius <= 0)
                    direction = Direction.BOTTOM_RIGHT;
                if (posY + radius >= Main.height) {
                    direction = Direction.TOP_LEFT; //gameOver
                    gameOver.gameOver();
                }
                break;
            case BOTTOM_RIGHT:
                posX++;
                posY++;
                if (posX + radius >= Main.width)
                    direction = Direction.BOTTOM_LEFT;
                if (posY + radius >= Main.height){
                    direction = Direction.TOP_RIGHT; //gameOver
                    gameOver.gameOver();
                }
                break;
        }
        setCenterX(posX);
        setCenterY(posY);
    }

    public Direction getDirection() {
        return direction;
    }

}
