package demolition;

import processing.core.PApplet;
import processing.core.PImage;
/**
 * Enemy class to do the enemy operations
 */
public class Enemy implements PlayerLife{
    /**
     * protected integer x-axis
     */
    protected int x;
    /**
     * protected integer y-axis
     */
    protected int y;
    /**
     * protected PImage for enemy
     */
    protected PImage sprite;
    /**
     * protected boolean whether enemy is alive or not
     */
    protected boolean isAlive;
    /**
     * protected integer for enemy's speed
     */
    protected int moveSpeed;
    /**
     * protected integer of time counter
     */
    protected int timeCounter = 0;
    /**
     * protected integer for enemy's life
     */
    protected int EnemyLife;
    /**
     * set the amount for enemy's life
     * @param EnemyLife
     */
    public void setPEnemyLife(int EnemyLife) {
        this.EnemyLife = EnemyLife;
    }
    /**
     * constructor for enemy and set isAlive to true
     * and speed to 32
     * and life to 1
     * @param x,x-axis
     * @param y,y-axis
     * @param sprite,PImage for enemy
     */
    public Enemy(int x, int y, PImage sprite){
        this.x = x;
        this.y = y;
        this.sprite = sprite;
        this.moveSpeed = 32;
        this.isAlive = true;
        this.EnemyLife = 1;
    }
    /**
     * logic for enemy extended by other class
     */
    public void tick(){
        
    }
    /**
     * draw method draw onto the screen
     * @param app,PApplet App
     */
    public void draw(PApplet app){
        app.image(this.sprite, this.x, this.y-getHeight()/2+8);
    }
    /**
     * get sprite hight
     * @return this.sprite.height, integer value
     */
    public int getHeight(){
        return this.sprite.height;
    }
    /**
     * get sprite width
     * @return this.sprite.width, integer value
     */
    public int getWidth(){
        return this.sprite.width;
    }
    /**
     * reduce enemy life
     */
    public void reduceEnemyLife(){
        this.EnemyLife --;
    }
    /**
     * get enemy's life
     * @return EnemyLife,integer value
     */
    public int getEnemyLife() {
        return EnemyLife;
    }
    /**
     * whether enemy is alive or not
     * @return this.isAlive
     */
    public boolean isAlive(){
        return this.isAlive;
    }
    /**
     * setup a new sprite for enemy
     * @param sprite,PImage
     */
    public void setSprite(PImage sprite) {
        this.sprite = sprite;
    }
    /**
     * get x-axis for enemy
     * @return this.x
     */
    public int getX(){
        return this.x;
    }
    /**
     * get y-axis for enemy
     * @return this.y
     */
    public int getY(){
        return this.y;
    }
    /**
     * set isAlive to false, enemy dead
     */
    public void dead(){
        this.isAlive = false;
    }
    
}
