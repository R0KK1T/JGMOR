package edu.chalmers.projecttemplate.model.snakemodel;

import javafx.scene.image.ImageView;

abstract class Assets {
    //static ImageView snake_head = new ImageView(Assets.class.getClassLoader().getResource("snakeresources/image/head.png").toString());
    //static ImageView snake_body = new ImageView(Assets.class.getClassLoader().getResource("snakeresources/image/body.png").toString());
    static ImageView apple = new ImageView(Assets.class.getClassLoader().getResource("snakeresources/image/apple01.png").toString());

}