package com.example.fx_game;

import javafx.scene.shape.Rectangle;

public class Bar extends Rectangle implements CollisionDetect{

    public static final double width = 100.0d;
    public static final double height = 20.0d;
    Bar(){
        super(Main.width/2, Main.height - 40,100, 20);
        setArcHeight(20);
        setArcWidth(20);
    }


    @Override
    public CollisionFrom isCollision(Ball ball) {
        if(ball.getDirection() == Ball.Direction.TOP_RIGHT || ball.getDirection() == Ball.Direction.TOP_LEFT) return null;

        if(getBoundsInParent().intersects(ball.getBoundsInParent())){
            double ballPosY = ball.getCenterY();

            if(getY()<ballPosY && getY()+height > ballPosY){
                return CollisionFrom.VERTICAL;
            }else {
                return CollisionFrom.HORIZONTAL;
            }
        }
        return null;
    }
}
