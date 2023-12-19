package com.example.fx_game;

public interface CollisionDetect {
    enum CollisionFrom{VERTICAL,HORIZONTAL};
    public static final int verticalCollision = 1;
    public CollisionFrom isCollision(Ball ball);
}
