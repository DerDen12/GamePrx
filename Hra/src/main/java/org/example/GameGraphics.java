package org.example;

import org.example.logic.BackGround;
import org.example.logic.Enemy;
import org.example.logic.Wall;

import javax.swing.*;
import java.awt.*;
import java.awt.image.ImageObserver;

public class GameGraphics extends JFrame {
    Draw draw;
    GameLogic logic;
    public GameGraphics(GameLogic logic) throws HeadlessException {

        this.draw = new Draw();
        this.logic = logic;

        setSize(900, 820);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
        setTitle("Game");

        add(draw);
    }

    public void render(GameLogic logic) {
        this.logic = logic;
        repaint();
    }

    public class Draw extends JPanel{
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            for (BackGround background : logic.getBackGround()) {
                g.drawImage(background.getImage(), 0, 0, background.getWidth(), background.getHeight(), this);
            }
            g.drawImage(logic.getBall().getImage(), logic.getBall().getX(), logic.getBall().getY(), this);
            for (Wall wall: logic.getWalls()) {
                    //g.setColor(new Color(0,0,0,0));
                    g.drawLine(wall.getCoordStart().x, wall.getCoordStart().y, wall.getCoordEnd().x, wall.getCoordEnd().y);
            }
            for (Enemy enemy : logic.getEnemies()) {
                g.drawImage(enemy.getImage(), enemy.getCoord().x, enemy.getCoord().y, this);
            }
        }
    }
}

