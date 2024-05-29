package org.example.logic;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;

public class GameMenu {
    int width;
    int height;
    Image image;
    int x;
    int y;
    private boolean visible;
    public GameMenu(int x, int y, String url) {
        ImageIcon ii = new ImageIcon(Objects.requireNonNull(getClass().getResource("/" + url)));
        this.image = ii.getImage();
        this.y = y;
        this.x = x;

    }

    public void setWidth(int width) {
        this.width = width;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public Image getImage() {
        return image;
    }
    public int getX() {
        return x;
    }
    public int getY() {
        return y;
    }
    public boolean isVisible() {
        return visible;
    }
    public void setVisible(boolean visible) {
        this.visible = visible;
    }
}

