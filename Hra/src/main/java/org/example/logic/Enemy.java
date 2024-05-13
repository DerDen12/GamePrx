package org.example.logic;

public class Enemy extends Entity {
    private int damage;
    private int health;


    public Enemy(int x, int y, String url, int damage,int health) {
        super(x, y, url, health);
        this.damage = damage;
    }
}
