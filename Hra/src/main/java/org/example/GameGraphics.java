package org.example;

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

        setSize(1200, 860);
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
            g.drawImage(logic.getBall().getImage(), logic.getBall().getX(), logic.getBall().getY(), new ImageObserver() {
                @Override
                public boolean imageUpdate(Image img, int infoflags, int x, int y, int width, int height) {
                    return false;
                }
            });
            for (Wall wall: logic.getWalls()) {
                if (wall.isActive()){
                    g.setColor(wall.getColor());
                    g.drawLine(wall.getCoordStart().x, wall.getCoordStart().y, wall.getCoordEnd().x, wall.getCoordEnd().y);
                }
            }
            for (Enemy enemy : logic.getEnemies()) {
                g.drawImage(enemy.getImage(), enemy.getCoord().x, enemy.getCoord().y, this);
            }
        }
    }
}
