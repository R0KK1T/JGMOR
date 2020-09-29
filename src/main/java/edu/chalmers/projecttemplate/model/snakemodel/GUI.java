package edu.chalmers.projecttemplate.model.snakemodel;

import edu.chalmers.projecttemplate.controller.snakecontroller.SnakeController;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.*;


public class GUI {

    public static JFrame f1;

    public GUI() {

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


    }
}


