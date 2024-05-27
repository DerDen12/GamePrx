package org.example.logic;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;

public class Enemy extends Entity {
    public int health;
    private int maxHealth;
    boolean isDamaged;


    public Enemy(int x, int y, String url) {
        super(x, y, url);
        this.maxHealth = 100;
        this.health = maxHealth;
    }
    public void setAnimation(String url) {
        this.image = new ImageIcon(Objects.requireNonNull(getClass().getResource("/" + url))).getImage();
    }

    public int getHealth() {
        return health;
    }


    public void setHealth(int health) {
        this.health = health;
    }

    public boolean isDamaged() {
        return isDamaged;
    }

    public void setDamaged(boolean damaged) {
        isDamaged = damaged;
    }
    public Rectangle getRectangle() {
        return new Rectangle(getX(), getY(), getWidth(), getHeight());
    }

    public int getMaxHealth() {
        return maxHealth;
    }
}
