package org.example.logic;

import java.awt.*;

public class Ball extends Entity {
    public int health;
    private boolean invincible;



    public Ball(int x, int y, String url,int health, int damage) {
        super(x,y,url,health,damage);
        this.health = 3;
        this.invincible = false;
    }


    public boolean isInvincible() {
        return invincible;
    }

    public void setInvincible(boolean invincible) {
        this.invincible = invincible;
    }

    public int setHealth(int health) {
        this.health = health;
        return health;
    }

    public int getHealth(int health) {
        return health;
    }

    public boolean isCollided (Rectangle otherObject) {
        return getRectangle().intersects(otherObject);
    }


}
