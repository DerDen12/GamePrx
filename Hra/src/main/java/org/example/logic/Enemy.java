package org.example.logic;

import javax.swing.*;
import java.util.Objects;

public class Enemy extends Entity {

    public Enemy(int x, int y, String url) {
        super(x, y, url);

    }
    public void setAnimation(String url) {
        this.image = new ImageIcon(Objects.requireNonNull(getClass().getResource("/" + url))).getImage();
    }
}
