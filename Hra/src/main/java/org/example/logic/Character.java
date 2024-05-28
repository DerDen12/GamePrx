package org.example.logic;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Objects;

public class Character extends Entity {
    public int health;
    private boolean invincible;

    public int damage;



    public Character(int x, int y, String url) {
        super(x,y,url);
        this.health = 5;
        this.invincible = false;
        this.damage = 25;
    }
    public void setAnimation(String url) {
        this.image = new ImageIcon(Objects.requireNonNull(getClass().getResource("/" + url))).getImage();
    }

    public boolean isInvincible() {
        return invincible;
    }

    public void setInvincible(boolean invincible) {
        this.invincible = invincible;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getHealth() {
        return health;
    }

    public boolean isCollided (Rectangle otherObject) {
        return getRectangle().intersects(otherObject);
    }

    public int getDamage() {
        return damage;
    }
}
