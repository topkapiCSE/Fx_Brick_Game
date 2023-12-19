package com.example.fx_game;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Paint;
import javafx.util.Duration;

public class Game extends Pane implements GameOver {

    private Ball ball;
    private Bar bar;
    private GameLabel gameLabel;
    private Timeline timeline;
    private boolean startGame = false;
    private final int countOfRows = 5;
    private final int countOfBricksInRow = 5;

    private Brick [][] bricks = new Brick[countOfRows][countOfBricksInRow];
    Game(){
        setBackground(Background.fill(Paint.valueOf("yellow")));
        setWidth(Main.width);
        setHeight(Main.height);

        ball = new Ball(this::gameOver);
        bar = new Bar();
        gameLabel = new GameLabel();

        getChildren().add(ball);
        getChildren().add(bar);
        getChildren().add(gameLabel);

        setOnMouseMoved(e -> bar.setX(e.getX() - Bar.width / 2));
        setOnMouseClicked(this::startGame);
        for (int i=0;i<countOfRows;i++){
            for (int j=0;j<countOfBricksInRow;j++){
                bricks[i][j] = new Brick(i,j);
                getChildren().add(bricks[i][j]);
            }
        }

        timeline = new Timeline(new KeyFrame(Duration.millis(5),this::move));
        timeline.setCycleCount(-1);
    }

    private void startGame(MouseEvent mouseEvent) {
        if (startGame) return;
        timeline.play();
    }


    private void move(ActionEvent actionEvent) {
        CollisionDetect.CollisionFrom collision = null;
        ball.move();

        collision = bar.isCollision(ball);
        if(collision != null){
            ball.setDirection(collision);
            return;
        }

        for (int i=0;i<countOfRows;i++){
            for (int j=0;j<countOfBricksInRow;j++){
                collision = bricks[i][j].isCollision(ball);
                if(collision != null){
                    ball.setDirection(collision);
                }
            }
        }
    }

    @Override
    public void gameOver() {
        startGame = false;
        timeline.stop();
        gameLabel.show("OYUN BİTTİ \n Yeniden başlatmak için tıklayın");

    }
}
