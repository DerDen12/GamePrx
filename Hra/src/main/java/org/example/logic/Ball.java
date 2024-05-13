package org.example.logic;

import java.awt.*;

public class Ball extends Entity {
    public int health;
    private boolean invincible;



    public Ball(int x, int y, String url,int health) {
        super(x,y,url,health);
        this.health = 3;
        this.invincible = false;



    }

    public boolean isInvincible() {
        return invincible;
    }

    public void setInvincible(boolean invincible) {
        this.invincible = invincible;
    }

    public int getX() {
        return coord.x;
    }

    public void setX(int x) {
        this.coord.x = x;
    }

    public int getY() {
        return coord.y;
    }

    public void setY(int y) {
        this.coord.y = y;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public Image getImage() {
        return image;
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
