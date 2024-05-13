package org.example;

import org.example.logic.Ball;
import org.example.logic.Direction;
import org.example.logic.Enemy;
import org.example.logic.Wall;

import java.awt.*;
import java.util.ArrayList;

public class GameLogic {
    private Ball ball;
    private Enemy enemy;
    private ArrayList<Wall> walls;


    public GameLogic() {
        this.ball = null;
        this.walls = new ArrayList<>();
    }


    public void initialize() {

        ball = new Ball(20, 20, "bombgreen.jpg",4);

        enemy = new Enemy(250, 250, "bomb.jpg",1);
        Enemy enemy2 = new Enemy(250, 250, "bomb.jpg",1);
        Enemy enemy3 = new Enemy(250, 250, "bomb.jpg",2);

        Wall wall1 = new Wall(250, 30, 250, 130, Color.BLACK);
        Wall wall2 = new Wall(100, 50, 150, 50, Color.BLACK);
        walls.add(wall1);
        walls.add(wall2);
    }
    public String healthFunctionOnTouch(Ball health) {
        if (ball.isCollided(enemy.getRectangle)) {
            ball.health - 1;
        }
        return "enemyTouched"+" a počet životu je "+health;
    }


    public void update() {
        int differenceX = Math.abs(ball.getCoord().x - enemy.getCoord().x);
        int differenceY = Math.abs(ball.getCoord().y - enemy.getCoord().y);
        if (differenceX > differenceY) {
            // Direction LEFT, RIGHT
            if(ball.getCoord().x - enemy.getCoord().x > 0) {
                enemy.move(5, Direction.RIGHT);
            } else {
                enemy.move(5, Direction.LEFT);
            }
        }
        else {
            // Direction UP, DOWN
            if(ball.getCoord().y - enemy.getCoord().y > 0) {
                enemy.move(5, Direction.DOWN);
            } else {
                enemy.move(5, Direction.UP);
            }
        }
        //ball.move(2, Direction.RIGHT);

        for (Wall wall: walls) {
            if (ball.isCollided(wall.getRectangle())){
                wall.inactivate();
            }
        }
    }

    public Ball getBall() {
        return ball;
    }

    public Enemy getEnemy() {
        return enemy;
    }

    public ArrayList<Wall> getWalls() {
        return walls;
    }
}
