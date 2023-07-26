package demolition;

import processing.core.PImage; 
import processing.core.PApplet;
/**
 * All types of block which inherited by all types
 */
public class Types {
    protected int x;
    protected int y;

    protected PImage sprite;
    /**
     * constructor for types which means the different block types
     * @param x,x-axis
     * @param y,y-axis
     * @param sprite,PImage for the blocks
     */
    public Types(int x , int y, PImage sprite){
        this.x = x;
        this.y = y;
        this.sprite = sprite;
    }
    /**
     * logic method for block
     */
    public void tick(){
        return;
    }
    /**
     * draw method for block draw onto the screen
     * @param app,PApplet for App
     */
    public void draw(PApplet app){
        app.image(this.sprite, this.x, this.y);
    }
    /**
     * get x-axis
     * @return this.x
     */
    public int getX() {
        return this.x;
    }
    /**
     * get y-axis
     * @return this.y
     */
    public int getY() {
        return this.y;
    }
    /**
     * get sprite width
     * @return this.sprite.width
     */
    public int getWidth() {
        return this.sprite.width;
    }
    /**
     * get sprite height
     * @return this.sprite.height
     */
    public int getHeight() {
        return this.sprite.height;
    }
}
