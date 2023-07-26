package demolition;

import processing.core.PImage;
/**
 * Block type of goal
 */
public class Goal extends Types{
    /**
     * constructor for Goal which extends Types
     * @param x,x-axis
     * @param y,y-axis
     * @param sprite,PImage for the Goal
     */
    public Goal(int x, int y, PImage sprite) {
        super(x, y, sprite);
    }
    /**
     * get x-axis for the goal
     * @return this.x
     */
    public int getX(){
        return this.x;
    }
    /**
     * get y-axis for the goal
     * @return this.y
     */
    public int getY(){
        return this.y;
    }
    
}
