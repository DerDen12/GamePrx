package org.example.logic;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;

public class BackGround {
    int width;
    int height;
    Image image;
    public BackGround(String url) {
        ImageIcon ii = new ImageIcon(Objects.requireNonNull(getClass().getResource("/" + url)));
        this.image = ii.getImage();

        this.width = ii.getIconWidth();
        this.height = ii.getIconHeight();
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
    public void setImage(Image image) {
        this.image = image;
    }
}