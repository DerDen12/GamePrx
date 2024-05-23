package org.example;

import org.example.logic.*;
import org.example.logic.Character;

import java.awt.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Timer;
import java.util.TimerTask;

public class GameLogic {
    private ArrayList<BackGround> backgrounds;
    private Character ball;
    private ArrayList<Enemy> enemies;
    private ArrayList<Wall> walls;
    private final int BALL_STEPS = 20;
    int level;


    public GameLogic() {
        this.ball = null;
        this.walls = new ArrayList<>();
        this.enemies = new ArrayList<>();
        this.backgrounds = new ArrayList<>();
    }


    public void initialize() {
        level = 1;

        ball = new Character(450, 150, "bombgreen.jpg");

        Enemy enemy1 = new Enemy(250, 250, "enemyHoplit/hoplitPředekIdle.png");
        enemies.add(enemy1);
        System.out.println(level);
        Wall wall1 = new Wall(0, 90, 1200, 90);

        walls.add(wall1);

        StartEnemySpawner();
        StartEnemySpawner2();
        levelBackGround();

    }
    private void levelBackGround() {
        if (level == 1) {
           BackGround background = new BackGround("pozadi1.png");
           backgrounds.add(background);

        }

    }
    private void spawnEnemyHoplit() {
        int x = (int) (Math.random() * 800);
        String url = "enemyHoplit/hoplitPředekIdle.png";
        int y = 0;
        Enemy newEnemy = new Enemy(x, y, url);
        synchronized (enemies) {
            enemies.add(newEnemy);
        }
    }

    private void StartEnemySpawner() {
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                spawnEnemyHoplit();
            }
        },2000,6000);
    }

    private void StartEnemySpawner2() {
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                spawnEnemyHoplit();
            }
        },4000,8000);
    }


    public void update() {
        synchronized (enemies) {
            Iterator<Enemy> iterator = enemies.iterator();
            while (iterator.hasNext()) {
                Enemy enemy = iterator.next();
                int differenceX = Math.abs(ball.getCoord().x - enemy.getCoord().x);
                int differenceY = Math.abs(ball.getCoord().y - enemy.getCoord().y);

                if (differenceX > differenceY) {
                // Move horizontally
                if(ball.getCoord().x - enemy.getCoord().x > 0) {
                    enemy.move(3, Direction.RIGHT);
                    enemy.setAnimation("enemyHoplit/hoplitVpravo.gif");
                } else {
                    enemy.move(3, Direction.LEFT);
                    enemy.setAnimation("enemyHoplit/hoplitVlevo.gif");
                }
            } else {
                // Move vertically
                if(ball.getCoord().y - enemy.getCoord().y > 0) {
                    enemy.move(3, Direction.DOWN);
                    enemy.setAnimation("enemyHoplit/hoplitPředek.gif");
                } else {
                    enemy.move(3, Direction.UP);
                    enemy.setAnimation("enemyHoplit/hoplitZezadu.gif");
                }
            }


            if (ball.isCollided(enemy.getRectangle())&& ball.health !=-1) {
                System.out.println(ball.health);
                enemyTouchedPlayer();
            }
            }
        }
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
            }, 3000);
        }
    }
    public void movePlayer(Direction direction) {
        ball.move(BALL_STEPS, direction);


    }

    public Character getBall() {
        return ball;
    }

    public ArrayList<Enemy> getEnemies() {
        return enemies;
    }
    public ArrayList<Wall> getWalls() {
        return walls;
    }

    public ArrayList<BackGround> getBackGround() {
        return backgrounds;
    }
}
