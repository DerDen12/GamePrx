package org.example.logic;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;

public class Character extends Entity {
    public int health;
    private boolean invincible;
    public int damage;
    private int coins;



    public Character(int x, int y, String url) {
        super(x,y,url);
        this.health = 8;
        this.invincible = false;
        this.damage = 25;
        this.coins = 0;
    }
    public void setAnimation(String url) {
        this.image = new ImageIcon(Objects.requireNonNull(getClass().getResource("/" + url))).getImage();
    }
    public void addCoins(int amount) {
        this.coins += amount;
    }

    public int getCoins() {
        return coins;
    }
    public String getCoinsString() {
        return String.valueOf(coins);
    }

    public void setCoins(int coins) {
        this.coins = coins;
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
