package edu.chalmers.projecttemplate.controller.pongcontroller;

import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class PongController {

    private void keyPressed(KeyEvent event) {
        KeyCode kc = event.getCode();
        switch (kc) {
            case W:
                break;
            case S:
                break;
            default:  // Nothing
        }
    }

    private void keyReleased(KeyEvent event) {
        KeyCode kc = event.getCode();
        switch (kc) {
            case S:
            case W:
                break;
            default: // Nothing
        }
    }
}
