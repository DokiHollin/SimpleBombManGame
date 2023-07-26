package demolition;

import processing.core.PApplet;
import processing.core.PImage;
/**
 * Player class include all the player's operations
 */
public class Player implements PlayerLife{
    /**
     * protected x-axis
     */
    protected int x;
    /**
     * protected y-axis
     */
    protected int y;
    /**
     * protected sprite PImage
     */
    protected PImage sprite;
    /**
     * protected boolean whether player is alive or not
     */
    protected boolean isAlive;
    /**
     * protected integer for movement speed of the player
     */
    protected int moveSpeed;
    /**
     * static variable for player's facing direction
     */
    public static String direction; 
    /**
     * protected integer of time counter
     */
    protected int timeCounter = 0;
    /**
     * private integer for player's life
     */
    private int playerLife;
    /**
     * set player's life
     * @param playerLife
     */
    public void setPlayerLife(int playerLife) {
        this.playerLife = playerLife;
    }
    /**
     * constructor for Player
     * @param x-axis
     * @param y,y-axis
     * @param sprite,PImage
     * @param dropBomb, Bomb object
     */
    public Player(int x, int y, PImage sprite, Bomb dropBomb){
        this.x = x;
        this.y = y;
        this.sprite = sprite;
        this.moveSpeed = 32;
        this.isAlive = true;
        this.playerLife = App.lives;
    }
    /**
     * logic movement for the player
     */
    public void tick(){
        if (Player.direction == "D"){
            for (String each: App.mapObstacle.values()){
                if ((String.valueOf(this.x)+String.valueOf(this.y + moveSpeed)).equals(each)){
                    return;
                }
            }
            this.y += moveSpeed;
        }
        if (Player.direction == "U"){
            for (String each: App.mapObstacle.values()){
                if ((String.valueOf(this.x)+String.valueOf(this.y - moveSpeed)).equals(each)){
                    return;
                }
            }
            this.y -= moveSpeed;
        }
        if (Player.direction == "R"){
            for (String each: App.mapObstacle.values()){
                if ((String.valueOf(this.x + moveSpeed)+String.valueOf(this.y)).equals(each)){
                    return;
                }
            }
            this.x += moveSpeed;
        }
        if (Player.direction == "L"){
            for (String each: App.mapObstacle.values()){
                if ((String.valueOf(this.x - moveSpeed)+String.valueOf(this.y)).equals(each)){
                    return;
                }
            }
            this.x -= moveSpeed;
        }
    }
    /**
     * draw onto the screen for the player
     * @param app,PApplet App
     */
    public void draw(PApplet app){
        if (this.isAlive()){
            app.image(this.sprite, this.x, this.y-getHeight()/2+8);
        }
        
    }
    /**
     * method used to check whether the explosion hitted
     * on player, and reduce player's life.
     * then restart the game
     */
    public void checkCollision(){
        if ((String.valueOf(this.x)+String.valueOf(this.y)).equals(String.valueOf(App.redEnemy.getX())+String.valueOf(App.redEnemy.getY())) && App.redEnemy.isAlive()){
            App.lives --;
            App.restart();
        }else if((String.valueOf(this.x)+String.valueOf(this.y)).equals(String.valueOf(App.yellowEnemy.getX())+String.valueOf(App.yellowEnemy.getY()))&& App.yellowEnemy.isAlive()){
            App.lives --;
            App.restart();
        }
    }

    /**
     * method to check player walked on the wall
     */
    public void faceWallCheck(){

    }
    /**
     * get the x-axis
     * @return x
     */
    public int getX() {
        return x;
    }
    /**
     * set up the x-axis
     * @param x,x-axis
     */
    public void setX(int x) {
        this.x = x;
    }
    /**
     * get the y-axis
     * @return y
     */
    public int getY() {
        return y;
    }
    /**
     * set up the y-axis
     * @param y,y-axis
     */
    public void setY(int y) {
        this.y = y;
    }
    /**
     * get the PImage for the player
     * @return sprite
     */
    public PImage getSprite() {
        return sprite;
    }
    /**
     * set the player's PImage
     * @return this.sprite
     */
    public void setSprite(PImage sprite) {
        this.sprite = sprite;
    }
    /**
     * return whether player is alive or dead
     * @return isAlive
     */
    public boolean isAlive() {
        return isAlive;
    }
    /**
     * set the state of the player(dead or alive)
     * @param isAlive
     */
    public void setAlive(boolean isAlive) {
        this.isAlive = isAlive;
    }
    /**
     * get the sprite height
     * @return this.sprite.height
     */
    public int getHeight(){
        return this.sprite.height;
    }
    /**
     * get the sprite width
     * @return this.sprite.width
     */
    public int getWidth(){
        return this.sprite.width;
    }
    /**
     * reduce player's life by one
     */
    public void reducePlayerLife(){
        this.playerLife --;
    }
    /**
     * get the player current life amount
     * @return playerLife
     */
    public int getPlayerLife() {
        return playerLife;
    }
    /**
     * get the player's move speed
     * @return moveSpeed
     */
    public int getMoveSpeed(){
        return moveSpeed;
    }

    
    
}
