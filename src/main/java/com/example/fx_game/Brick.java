package com.example.fx_game;

import javafx.scene.shape.Rectangle;

public class Brick extends Rectangle implements CollisionDetect {

    public static final int width = 100;
    public static final int height = 20;
    public static final int margin = 15;

    private boolean isVisible = true;

    Brick(int row, int col){
        super(col*width + margin*(col+1),row*height + margin*(row+3) ,width,height);
        setArcHeight(20);
        setArcWidth(20);
    }

    @Override
    public CollisionFrom isCollision(Ball ball) {
        if (!isVisible)return null;

        if(getBoundsInParent().intersects(ball.getBoundsInParent())){
            double ballPosY = ball.getCenterY();
            isVisible = false;
            setVisible(false);

            if(getY()<ballPosY && getY()+height > ballPosY){
                return CollisionFrom.VERTICAL;
            }else {
                return CollisionFrom.HORIZONTAL;
            }
        }
        return null;
    }
}
