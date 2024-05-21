package org.example.logic;

import javax.swing.*;
import java.util.Objects;

public class Enemy extends Entity {
    private int damage;
    private int health;

    private String currentAnimation;


    public Enemy(int x, int y, String url, int damage,int health) {
        super(x, y, url, health,damage);
    }

    public String getCurrentAnimation() {
        return currentAnimation;
    }

    public void setAnimation(String url) {
        this.image = new ImageIcon(Objects.requireNonNull(getClass().getResource("/" + url))).getImage();
    }
}
