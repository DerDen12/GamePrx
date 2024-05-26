package org.example.logic;

import javax.swing.*;
import java.util.Objects;

public class BossEnemy extends Entity {
    public BossEnemy(int x, int y, String url) {
        super(x, y, url);

    }
    public void setAnimationBoss(String url) {
        this.image = new ImageIcon(Objects.requireNonNull(getClass().getResource("/" + url))).getImage();
    }
}
