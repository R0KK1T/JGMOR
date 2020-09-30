package edu.chalmers.projecttemplate.model.snakemodel;


import edu.chalmers.projecttemplate.controller.snakecontroller.SnakeController;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.*;


public class GUI {

    public static JFrame f1;
    public static JPanel p1;



    private SnakeController snakeGame;
    private Font fontMenu;
    private Font fontHeader;

    public GUI() {
        snakeGame = new SnakeController();
        snakeGame.setSnakeGame(snakeGame);
    }


    public void createGameWindow() {

        f1 = new JFrame("Snake");
        f1.setSize(675, 395);
        f1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        f1.setLocationRelativeTo(null);
        p1 = new Draw();
        p1.setLayout(null);
        f1.add(p1);

        //Create Fonts
        fontMenu = new Font( Font.SANS_SERIF, Font.PLAIN, 12 );
        fontHeader = new Font( Font.SANS_SERIF, Font.BOLD, 16 );

        f1.requestFocus();
        f1.repaint();
        f1.setVisible(true);

        f1.addKeyListener(new KeyListener() {

            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {
                if(snakeGame.isStarted()) {

                    //Pause Menu
                    if(snakeGame.isPause()){

                        checkPauseMenuInputs(e);

                        //Current Game
                    }else if(!snakeGame.isPause()) {


                    }

                    //Game Over Menu
                }else if(snakeGame.isFinished()) {


                    //Start Menu
                }else if(snakeGame.isStartmenu()) {


                }
            }

            @Override
            public void keyReleased(KeyEvent e) {

            }

        });

    }


    class Draw extends JPanel {

        private static final long serialVersionUID = 1L;

        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2 = (Graphics2D) g;

            if (snakeGame.isStarted()) {

                 // Draw start menu
            }

        }
    }


    // -----------KeyListener-Methods---------------------

    public void checkPauseMenuInputs(KeyEvent e) {
        //ESC
        if(e.getKeyCode()==27) {
            snakeGame.setPause(false);
            //Up
        }else if(e.getKeyCode()==38) {
            if(snakeGame.getMenuSelection().equals("Exit game")) {
                snakeGame.setMenuSelection("Back to main menu");
                GUI.f1.repaint();
            }else if(snakeGame.getMenuSelection().equals("Back to main menu")) {
                snakeGame.setMenuSelection("Restart game");
                GUI.f1.repaint();
            }
            //Down
        }else if(e.getKeyCode()==40) {
            if(snakeGame.getMenuSelection().equals("Restart game")) {
                snakeGame.setMenuSelection("Back to main menu");
                GUI.f1.repaint();
            }else if(snakeGame.getMenuSelection().equals("Back to main menu")) {
                snakeGame.setMenuSelection("Exit game");
                GUI.f1.repaint();
            }

        }
    }


    public void setSnakeGame(SnakeController snakeGame) {
        this.snakeGame = snakeGame;
    }
}


