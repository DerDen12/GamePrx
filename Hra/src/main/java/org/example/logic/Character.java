package org.example.logic;

import java.awt.*;

public class Character extends Entity {
    public int health;
    private boolean invincible;

    public int damage;



    public Character(int x, int y, String url) {
        super(x,y,url);
        this.health = 5;
        this.invincible = false;
        this.damage = 1;
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

    public int getHealth(int i) {
        return health;
    }

    public boolean isCollided (Rectangle otherObject) {
        return getRectangle().intersects(otherObject);
    }


}
