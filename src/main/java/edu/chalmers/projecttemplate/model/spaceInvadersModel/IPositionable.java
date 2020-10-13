package edu.chalmers.projecttemplate.model.spaceInvadersModel;

import edu.chalmers.projecttemplate.model.common.Rectangle;

public interface IPositionable {

    int getXpos();

    int getYpos();

    int getWidth();

    int getHeight();

    String getType();

    Rectangle getHitbox();
}
