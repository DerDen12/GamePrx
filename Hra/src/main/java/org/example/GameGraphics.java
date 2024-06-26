package org.example;

import org.example.logic.*;

import javax.swing.*;
import java.awt.*;
import java.awt.image.ImageObserver;
import java.util.Objects;

public class GameGraphics extends JFrame {
    Draw draw;
    GameLogic logic;
    private GameMenu menu;
    private HealthDisplay healthDisplay;
    private Image coinImage;
    private Image gameOverImage;
    public GameGraphics(GameLogic logic) throws HeadlessException {

        this.draw = new Draw();
        this.logic = logic;
        this.menu = logic.getMenu();
        healthDisplay = new HealthDisplay(0,0,"health_heart.png");
        coinImage = new ImageIcon(Objects.requireNonNull(getClass().getResource("/MinceDisplay.png"))).getImage();
        gameOverImage = new ImageIcon(Objects.requireNonNull(getClass().getResource("/Gameover.png"))).getImage();

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
            if (menu.isVisible()) {
                int menuWidth = getWidth();
                int menuHeight = getHeight();

                logic.getMenu().setWidth(menuWidth);
                logic.getMenu().setHeight(menuHeight);
                g.drawImage(logic.getMenu().getImage(), 0, 0, menuWidth, menuHeight, this);
            } else {
                if (logic.isGameOver()) {
                    g.drawImage(gameOverImage, 0, 0, getWidth(), getHeight(), this);
                } else {
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
                   for (Coins coin : logic.getCoins()) {
                     g.drawImage(coin.getImage(), coin.getX(), coin.getY(), this);
                   }
                   Image image = healthDisplay.getImage();
                   g.drawImage(image,-100,530,this);
                   g.setColor(Color.BLACK);
                   g.setFont(new Font("Arial", Font.BOLD, 40));
                   g.drawString(String.valueOf(logic.getBall().getHealth()), healthDisplay.getX()+65 + healthDisplay.getWidth(), healthDisplay.getY()+735 + healthDisplay.getHeight());

                   g.drawImage(coinImage, 30, 565, this);
                   g.drawString(logic.getBall().getCoinsString(), 185, 735);

                    Boat boat = logic.getBoat();
                    g.drawImage(boat.getImage(), boat.getX(), boat.getY(), this);
                }
            }
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
