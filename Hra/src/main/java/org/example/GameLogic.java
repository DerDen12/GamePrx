package org.example;

import org.example.logic.Ball;
import org.example.logic.Direction;
import org.example.logic.Enemy;
import org.example.logic.Wall;

import java.awt.*;
import java.util.ArrayList;
import java.util.Timer;

public class GameLogic {
    private Ball ball;
    private ArrayList<Enemy> enemies;
    private ArrayList<Wall> walls;



    public GameLogic() {
        this.ball = null;
        this.walls = new ArrayList<>();
        this.enemies = new ArrayList<>();
    }


    public void initialize() {

        ball = new Ball(20, 20, "bombgreen.jpg",4);

        Enemy enemy1 = new Enemy(250, 250, "bomb.jpg",1,10);
        enemies.add(enemy1);


        Wall wall1 = new Wall(250, 30, 250, 130, Color.BLACK);
        Wall wall2 = new Wall(100, 50, 150, 50, Color.BLACK);
        walls.add(wall1);
        walls.add(wall2);
    }

    public void enemyTouchedPlayer () {
        if (!ball.isInvincible()) {
            ball.getHealth(ball.setHealth(ball.health- 1));

            ball.setInvincible(true);
            

        }

    }



    public void update() {
        for (Enemy enemy: enemies) {
         int differenceX = Math.abs(ball.getCoord().x - enemy.getCoord().x);
         int differenceY = Math.abs(ball.getCoord().y - enemy.getCoord().y);
         if (differenceX > differenceY) {
            // Direction LEFT, RIGHT
            if(ball.getCoord().x - enemy.getCoord().x > 0) {
                enemy.move(3, Direction.RIGHT);
            } else {
                enemy.move(3, Direction.LEFT);
            }
         }
         else {
            // Direction UP, DOWN
            if(ball.getCoord().y - enemy.getCoord().y > 0) {
                enemy.move(3, Direction.DOWN);
            } else {
                enemy.move(3, Direction.UP);
            }
         }
        }
        //ball.move(2, Direction.RIGHT);

        for (Wall wall: walls) {
            if (ball.isCollided(wall.getRectangle())){;
                System.out.println("dotekzdi"+ball.health);
            }
        }
        for (Enemy enemy: enemies) {
            if (ball.isCollided(enemy.getRectangle())&& ball.health !=-1) {
                System.out.println(ball.health);
                enemyTouchedPlayer();
            }
        }
    }

    public Ball getBall() {
        return ball;
    }

    public ArrayList<Enemy> getEnemies() {
        return enemies;
    }
    public ArrayList<Wall> getWalls() {
        return walls;
    }
}
