package org.example;

import org.example.logic.*;

import java.awt.*;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class GameLogic {
    private Ball ball;
    private ArrayList<Enemy> enemies;
    private ArrayList<Wall> walls;

    private final int BALL_STEPS = 20;



    public GameLogic() {
        this.ball = null;
        this.walls = new ArrayList<>();
        this.enemies = new ArrayList<>();
    }


    public void initialize() {

        ball = new Ball(450, 150, "bombgreen.jpg",4);

        Enemy enemy1 = new Enemy(250, 250, "bomb.jpg",1,10);
        enemies.add(enemy1);


        Wall wall2 = new Wall(0, 90, 1200, 90, Color.BLACK);

        walls.add(wall2);

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
        for (Enemy enemy: enemies) {
            if (ball.isCollided(enemy.getRectangle())&& ball.health !=-1) {
                System.out.println(ball.health);
                enemyTouchedPlayer();
            }
        }
        //ball.move(2, Direction.RIGHT);

        for (Wall wall: walls) {
            if (ball.isCollided(wall.getRectangle())){
                System.out.println("dotekzdi");
                wall.inactivate();
            }
        }
    }
        public boolean predictBallCollision(Direction direction){
            return predictCollision(direction, ball, BALL_STEPS);
        }
        private boolean predictCollision(Direction direction, Entity entity, int steps) {
            Rectangle moveRectangle = new Rectangle();
            switch (direction) {
                case RIGHT -> {
                    moveRectangle = new Rectangle(entity.getX() + steps, entity.getY(), entity.getWidth(), entity.getHeight());
                }
                case LEFT -> {
                    moveRectangle = new Rectangle(entity.getX() - steps, entity.getY(), entity.getWidth(), entity.getHeight());
                }
                case UP -> {
                    moveRectangle = new Rectangle(entity.getX(), entity.getY() - steps, entity.getWidth(), entity.getHeight());
                }
                case DOWN -> {
                    moveRectangle = new Rectangle(entity.getX(), entity.getY() + steps, entity.getWidth(), entity.getHeight());
                }

            }
            for (Wall wall:walls) {
                if (moveRectangle.intersects(wall.getRectangle())){
                    return true;
                }
            }
            return false;
        }


    public void enemyTouchedPlayer () {
        if (!ball.isInvincible()) {
            ball.getHealth(ball.setHealth(ball.health- 1));

            ball.setInvincible(true);
            Timer timer = new Timer();
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    ball.setInvincible(false);

                }
            }, 2000);


        }


    }
    public void movePlayer(Direction direction) {
        ball.move(BALL_STEPS, direction);

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
