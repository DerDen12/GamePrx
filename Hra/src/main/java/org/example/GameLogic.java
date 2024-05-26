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
    private ArrayList<BossEnemy> bossEnemies;
    int level;
    public GameLogic() {
        this.ball = null;
        this.walls = new ArrayList<>();
        this.enemies = new ArrayList<>();
        this.backgrounds = new ArrayList<>();
        this.bossEnemies = new ArrayList<>();
    }
    public void initialize() {
        level = 1;
        ball = new Character(450, 150, "bombgreen.jpg");
        System.out.println(level);
        Wall wall1 = new Wall(0, 90, 1200, 90);
        Wall wall2 = new Wall(0, 570, 420, 570);
        Wall wall3 = new Wall(550, 570, 1200, 570);
        walls.add(wall1);
        walls.add(wall2);
        walls.add(wall3);
        startEnemySpawner();
        startEnemySpawner2();
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


    private void startEnemySpawner() {
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                spawnEnemyHoplit();
            }
        },2000,6000);
    }
    private void startEnemySpawner2() {
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

                if (!enemy.isDamaged()) {
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
                enemyTouchedPlayer();
            }
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
            int updatedHealth = ball.getHealth()-1;
            ball.setHealth(updatedHealth);
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
    public void BossTouchedPlayer () {
        if (!ball.isInvincible()) {
            int updatedHealth = ball.getHealth()-2;
            ball.setHealth(updatedHealth);
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
    public void playerAttack() {
        Rectangle attackRange = new Rectangle(ball.getX() - 20, ball.getY() - 20, ball.getWidth() + 40, ball.getHeight() + 40);
        int damage = ball.getDamage();
        System.out.println("Attack range: " + attackRange);

        synchronized (enemies) {
            for (Enemy enemy : enemies) {
                if (attackRange.intersects(enemy.getRectangle())) {
                    int newHealth = enemy.getHealth() - damage;
                    enemy.setHealth(newHealth);
                    System.out.println("životy nepiřtele jsou "+newHealth);
                    enemy.setDamaged(true);
                    enemy.setAnimation("Enemy_damaged.gif");
                    Timer timer = new Timer();
                    timer.schedule(new TimerTask() {
                        @Override
                        public void run() {
                            enemy.setDamaged(false);
                        }
                    },1000);
                }
            }
        }
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