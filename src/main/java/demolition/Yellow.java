package demolition;

import processing.core.PImage;
/**
 * Yellow enemy's operations
 */
public class Yellow extends Enemy{
    /**
     * integer facing direction
     */
    private int facingDirection;
    /**
     * constructor for yellow
     * @param x,x-axis
     * @param y,y-axis
     * @param sprite,PImage for yellow enemy
     */
    public Yellow(int x, int y, PImage sprite) {
        super(x, y, sprite);
        facingDirection = 0;
    }
    /**
     * logic method to check whether the yellow
     * enemy walked onto the wall
     * check whether yellow enemy walked into
     * the explosion area
     * and dead when hitted by explosion
     */
    public void tick(){
        if (facingDirection == 0){
            for (String each: App.mapObstacle.values()){
                if ((String.valueOf(this.x)+String.valueOf(this.y + moveSpeed)).equals(each)){
                    facingDirection = 1;
                    return;
                }
            }
            this.y += moveSpeed;
            App.yellowMoveTime = 0;
        }

        if (facingDirection == 1){
            for (String each: App.mapObstacle.values()){
                if ((String.valueOf(this.x - moveSpeed)+String.valueOf(this.y)).equals(each)){
                    facingDirection = 2;
                    return;
                }
            }
            this.x -= moveSpeed;
            App.yellowMoveTime = 0;
        }

        if (facingDirection == 2){
            for (String each: App.mapObstacle.values()){
                if ((String.valueOf(this.x)+String.valueOf(this.y - moveSpeed)).equals(each)){
                    facingDirection = 3;
                    return;
                }
            }
            this.y -= moveSpeed;
            App.yellowMoveTime = 0;
        }
        if (facingDirection == 3){
            for (String each: App.mapObstacle.values()){
                if ((String.valueOf(this.x + moveSpeed)+String.valueOf(this.y)).equals(each)){
                    facingDirection = 0;
                    return;
                }
            }
            this.x += moveSpeed;
            App.yellowMoveTime = 0;
        }
        
    }
    /**
     * set the yellow enemy's x-axis
     * @param x
     */
    public void setYellowX(int x){
        this.x = x;
    }
    /**
     * set the yellow enemy's y-axis
     * @param y
     */
    public void setYellowY(int y){
        this.y = y;
    }
    /**
     * get the facing direction
     * @return this.facingDirection
     */
    public int getFacing(){
        return this.facingDirection;
    }
    /**
     * set the facing direction by received integer
     * @param facing,integer numebr
     */
    public void setFacing(int facing){
        this.facingDirection = facing;
    }
}
