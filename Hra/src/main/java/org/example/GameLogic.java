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
    private Character character;
    private ArrayList<Enemy> enemies;
    private ArrayList<Wall> walls;
    private final int BALL_STEPS = 20;
    private ArrayList<BossEnemy> bossEnemies;
    int level;
    public GameLogic() {
        this.character = null;
        this.walls = new ArrayList<>();
        this.enemies = new ArrayList<>();
        this.backgrounds = new ArrayList<>();
        this.bossEnemies = new ArrayList<>();
    }
    public void initialize() {
        level = 1;
        character = new Character(450, 150, "bombgreen.jpg");
        System.out.println(level);
        Wall wall1 = new Wall(0, 90, 1200, 90);
        Wall wall2 = new Wall(0, 570, 420, 570);
        Wall wall3 = new Wall(550, 570, 1200, 570);
        walls.add(wall1);
        walls.add(wall2);
        walls.add(wall3);
        startEnemySpawner();
        levelBackGround();
    }
    private void levelBackGround() {
        if (level == 1) {
           BackGround background1 = new BackGround("pozadi1.png");
           backgrounds.add(background1);
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
    private void enemyWave() {
        int x = (int) (Math.random()*600) + 400;
        String url = "enemyHoplit/hoplitPředekIdle.png";
        int y = 0;
        Enemy newEnemy1 = new Enemy(x, y, url);
        Enemy newEnemy2 = new Enemy(x, y, url);
        Enemy newEnemy3 = new Enemy(x, y, url);
        Enemy newEnemy4 = new Enemy(x, y, url);
        Enemy newEnemy5 = new Enemy(x, y, url);


        synchronized (enemies) {

            enemies.add(newEnemy1);
            enemies.add(newEnemy2);
            enemies.add(newEnemy3);
            enemies.add(newEnemy4);
            enemies.add(newEnemy5);
        }
    }


    private void startEnemyWave() {
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                enemyWave();
            }
        },10000,16000);
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
    public void update() {
        synchronized (enemies) {
            Iterator<Enemy> iterator = enemies.iterator();
            while (iterator.hasNext()) {
                Enemy enemy = iterator.next();
                int differenceX = Math.abs(character.getCoord().x - enemy.getCoord().x);
                int differenceY = Math.abs(character.getCoord().y - enemy.getCoord().y);

                if (!enemy.isDamaged()) {
                if (differenceX > differenceY) {
                // Move horizontally
                if(character.getCoord().x - enemy.getCoord().x > 0) {
                    enemy.move(3, Direction.RIGHT);
                    enemy.setAnimation("enemyHoplit/hoplitVpravo.gif");
                } else {
                    enemy.move(3, Direction.LEFT);
                    enemy.setAnimation("enemyHoplit/hoplitVlevo.gif");
                }
            } else {
                // Move vertically
                if(character.getCoord().y - enemy.getCoord().y > 0) {
                    enemy.move(3, Direction.DOWN);
                    enemy.setAnimation("enemyHoplit/hoplitPředek.gif");

                } else {
                    enemy.move(3, Direction.UP);
                    enemy.setAnimation("enemyHoplit/hoplitZezadu.gif");
                }
            }
            if (character.isCollided(enemy.getRectangle())&& character.health !=-1) {
                enemyTouchedPlayer();
                }
              }
            }
            enemyDeath();
        }
        for (Wall wall: walls) {
            if (character.isCollided(wall.getRectangle())){
                System.out.println("dotekzdi");
                wall.inactivate();
            }
        }
    }
        public boolean predictBallCollision(Direction direction){
            return predictCollision(direction, character, BALL_STEPS);
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
        if (!character.isInvincible() && character.health !=0 ) {
            int updatedHealth = character.getHealth()-1;
            character.setHealth(updatedHealth);
            character.setInvincible(true);
            Timer timer = new Timer();
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    character.setInvincible(false);

                }
            }, 3000);
        }
    }
    public void enemyDeath() {
        synchronized (enemies) {
            Iterator<Enemy> iterator = enemies.iterator();
            while (iterator.hasNext()) {
                Enemy enemy = iterator.next();
                if (enemy.getHealth() <= 0) {
                    iterator.remove();
                }
            }
        }
    }
    public void playerDeath() {
        if (character.health <=0 ) {
            gameover();
        }
    }
    public void gameover() {

    }
    public void movePlayer(Direction direction) {
        character.move(BALL_STEPS, direction);
        setCharacterAnimation(direction);
    }

    private void setCharacterAnimation(Direction direction) {
        switch (direction) {
            case LEFT:
                character.setAnimation("hrdinadoleva.gif");
                break;
            case RIGHT:
                character.setAnimation("hrdinadoprava.gif");
                break;
            case UP:
                character.setAnimation("hlavnipostavazezadu.gif");
                break;
            case DOWN:
                character.setAnimation("hlavnipostavapředek.gif");
                break;
        }
    }
    public void playerAttack() {
        Rectangle attackRange = new Rectangle(character.getX() - 10, character.getY() - 20, character.getWidth() +50, character.getHeight() + 50);
        int damage = character.getDamage();
        System.out.println("Attack range: " + attackRange);

        synchronized (enemies) {
            for (Enemy enemy : enemies) {
                if (attackRange.intersects(enemy.getRectangle()) && enemy.health !=0) {
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
        return character;
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