package org.example.logic;

import java.awt.*;

public class Ball extends Entity {
    private int Health;



    public Ball(int x, int y, String url,int health) {
        super(x,y,url);


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

    public int getHealth() {
        return Health;
    }

    public boolean isCollided (Rectangle otherObject) {
        return getRectangle().intersects(otherObject);
    }


}
