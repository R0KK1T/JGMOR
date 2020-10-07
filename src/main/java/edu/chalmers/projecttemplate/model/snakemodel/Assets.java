package edu.chalmers.projecttemplate.model.snakemodel;

import javafx.scene.image.ImageView;

abstract class Assets {
    static ImageView snake_head = new ImageView(Assets.class.getClassLoader().getResource("snakeresources/images/head.png").toString());
    static ImageView snake_body = new ImageView(Assets.class.getClassLoader().getResource("snakeresources/images/body.png").toString());
    static ImageView apple = new ImageView(Assets.class.getClassLoader().getResource("snakeresources/images/apple.png").toString());
}