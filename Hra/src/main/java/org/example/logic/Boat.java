package org.example.logic;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;

public class Boat extends Entity{

    public Boat(int x, int y, String url) {
        super(x, y, url);
        ImageIcon ii = new ImageIcon(Objects.requireNonNull(getClass().getResource("/" + url)));
        this.image = ii.getImage();
        this.width = ii.getIconWidth();
        this.height = ii.getIconHeight();
    }

    public Rectangle getRectangle() {
        return new Rectangle(getX(), getY(), getWidth(), getHeight());
    }

}
