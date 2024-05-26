package org.example.logic;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;

public class HealthDisplay {
    int width;
    int height;
    Image image;
    int x;
    int y;
    public HealthDisplay(int x,int y,String url) {
        ImageIcon ii = new ImageIcon(Objects.requireNonNull(getClass().getResource("/" + url)));
        this.image = ii.getImage();
        this.y = y;
        this.x = x;

    }
    public int getWidth() {
        return width;
    }
    public int getHeight() {
        return height;
    }
    public Image getImage() {
        return image;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}

