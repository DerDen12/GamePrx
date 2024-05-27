package org.example;

import org.example.logic.BackGround;
import org.example.logic.Enemy;
import org.example.logic.HealthDisplay;
import org.example.logic.Wall;

import javax.swing.*;
import java.awt.*;
import java.awt.image.ImageObserver;

public class GameGraphics extends JFrame {
    Draw draw;
    GameLogic logic;
    private HealthDisplay healthDisplay;
    public GameGraphics(GameLogic logic) throws HeadlessException {

        this.draw = new Draw();
        this.logic = logic;
        healthDisplay = new HealthDisplay(0,0,"health_heart.png");

        setSize(900, 820);
        setResizable(false);
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
                drawHealthBar(g, enemy);
            }
            Image image = healthDisplay.getImage();
            g.drawImage(image,-100,530,this);
            g.setColor(Color.WHITE);
            g.setFont(new Font("Arial", Font.BOLD, 40));
            g.drawString(String.valueOf(logic.getBall().getHealth()), healthDisplay.getX()+65 + healthDisplay.getWidth(), healthDisplay.getY()+735 + healthDisplay.getHeight());
        }
        private void drawHealthBar(Graphics g, Enemy enemy) {
            int barWidth = 50;
            int barHeight = 5;
            int barX = enemy.getCoord().x + (enemy.getWidth() - barWidth) / 2;
            int barY = enemy.getCoord().y - barHeight - 2;

            int health = enemy.getHealth();
            int maxHealth = enemy.getMaxHealth();
            Color healthColor;

            if (health > 50) {
                int green = 255;
                int red = (int) (255 * ((100 - health) / 50.0));
                healthColor = new Color(red, green , 0);
            } else {
                int red = 255;
                int green = (int) (255 * ((health) / 50.0));
                healthColor = new Color(red, green , 0);
            }

            g.fillRect(barX, barY, barWidth, barHeight);

            g.setColor(healthColor);
            int healthBarWidth = (int) (((double) health / maxHealth) * barWidth);
            g.fillRect(barX, barY, healthBarWidth, barHeight);

            g.setColor(Color.BLACK);
            g.drawRect(barX, barY, barWidth, barHeight);
        }
    }
}
