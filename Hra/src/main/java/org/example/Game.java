package org.example;


import org.example.logic.Direction;

import javax.swing.*;
import java.awt.event.*;

public class Game {
    GameLogic logic;
    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new Game();
            }
        });
    }

    public Game() {
        logic = new GameLogic();
        GameGraphics graphic = new GameGraphics(logic);
        logic.initialize();
        graphic.render(logic);
        boolean isGameOver = false;

        graphic.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {
                switch (e.getKeyCode()){
                    case KeyEvent.VK_LEFT:
                        controlledMove(Direction.LEFT);
                        break;
                    case KeyEvent.VK_RIGHT:
                        controlledMove(Direction.RIGHT);
                        break;
                    case KeyEvent.VK_UP:
                        controlledMove(Direction.UP);
                        break;
                    case KeyEvent.VK_DOWN:
                        controlledMove(Direction.DOWN);
                        break;
                }

            }

            @Override
            public void keyReleased(KeyEvent e) {

            }
        });

        graphic.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int differenceX = e.getX() - logic.getBall().getX();
                int differenceY = e.getY() - logic.getBall().getY() - graphic.getInsets().top;
                if (differenceX > 0 && differenceX < logic.getBall().getWidth() && differenceY > 0 && differenceY < logic.getBall().getHeight()){
                    logic.getBall().move(20,Direction.RIGHT);
                }

            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });

        Timer timer = new Timer(100, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                logic.update();
                graphic.render(logic);
            }
        });

        timer.start();
        /*
        ;
        while (!isGameOver){
            logic.update();
            graphic.render();
        }
         */
    }
    private void controlledMove(Direction direction) {
        if (!logic.predictBallCollision(direction)){
            logic.movePlayer(direction);

        }
    }
    public GameLogic getLogic() {
        return logic;
    }
}
