package org.example.logic;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;

public class Coins {
    public int amount;
    int x;
    int y;
    int width;
    int height;

    Image image;

    public void setgraphic(String url) {
        this.image = new ImageIcon(Objects.requireNonNull(getClass().getResource("/" + url))).getImage();
    }


    public Coins(int amount, int x, int y) {
        this.amount = amount;
        this.x = x;
        this.y = y;
        this.width = 16;
        this.height = 16;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public Rectangle getRectangle() {
        return new Rectangle(getX(), getY(), getWidth(), getHeight());
    }

    public Image getImage() {
        return image;
    }
}
