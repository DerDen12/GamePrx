package org.example.logic;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;

public class GameOver {
    Image image;
    int x;
    int y;

    public GameOver(String url) {
        ImageIcon ii = new ImageIcon(Objects.requireNonNull(getClass().getResource("/" + url)));
        this.image = ii.getImage();

    }
}
