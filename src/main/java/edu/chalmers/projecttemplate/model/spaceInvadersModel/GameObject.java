package edu.chalmers.projecttemplate.model.spaceInvadersModel;

import edu.chalmers.projecttemplate.model.common.IRectangle;
import edu.chalmers.projecttemplate.model.common.IRepresentable;
import edu.chalmers.projecttemplate.model.common.Rectangle;

/**
 * Represents an abstract game object for the old retro game Space Invaders
 *
 */
public abstract class GameObject implements IHitable, IRepresentable {

    private final String type;
    private final IRectangle hitbox;

    /**
     * Constructs a GameObject with specified position, size, speed and type
     *
     * @param xPos the start x position of the GameObject
     * @param yPos the start y position of the GameObject
     * @param width the width of the GameObject
     * @param height the height of the GameObject
     * @param type the type of GameObject used for identification
     */
    protected GameObject(int xPos, int yPos, int width, int height, String type) {
        hitbox = new Rectangle(xPos, yPos, width, height);
        this.type = type;
    }

    /**
     * Returns the x position of the GameObject
     *
     * @return int x position of the GameObject
     */
    public int getX(){
        return hitbox.getX();
    }

    /**
     * Returns the y position of the GameObject
     *
     * @return int y position of the GameObject
     */
    public int getY(){
        return hitbox.getY();
    }

    /**
     * Returns the width of the GameObject
     *
     * @return int width of the GameObject
     */
    public int getWidth(){
        return hitbox.getWidth();
    }

    /**
     * Returns the height of the GameObject
     *
     * @return int height of the GameObject
     */
    public int getHeight(){
        return hitbox.getHeight();
    }

    /**
     * Returns the string type representing what type of GameObject this is
     *
     * @return string type for identifying object
     */
    public String getType() {
        return type;
    }

    /**
     * Returns the rectangle representing the hitbox of the GameObject
     *
     * @return rectangle hitbox of the GameObject
     */
    public IRectangle getHitbox(){
        return hitbox;
    }

}
