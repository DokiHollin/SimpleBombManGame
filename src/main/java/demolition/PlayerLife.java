package demolition;

import processing.core.PApplet;
import processing.core.PImage;
/**
 * Interface include all the duplicated method by movable objects
 */
public interface PlayerLife {
    /**
     * logic method
     */
    public abstract void tick();
    /**
     * boolean for is alive or not
     * @return
     */
    public abstract boolean isAlive();
    /**
     * draw method
     * @param app
     */
    public abstract void draw(PApplet app);
    /**
     * get sprite height
     * @return
     */
    public abstract int getHeight();
    /**
     * get sprite width
     * @return
     */
    public abstract int getWidth();
    /**
     * setup the new PImage
     * @param sprite
     */
    public abstract void setSprite(PImage sprite);
    /**
     * get x-axis
     * @return
     */
    public abstract int getX();
    /**
     * get y-axis
     * @return
     */
    public abstract int getY();
}
