package demolition;

import processing.core.PImage;
import java.util.Random;
/**
 * Red enemy's operations
 */
public class Red extends Enemy{
    /**
     * integer facing direction
     */
    private int facingDirection;
    /**
     * Random variable generate random number
     */
    private Random rand;
    /**
     * constructor for Red
     * @param x,x-axis
     * @param y,y-axis
     * @param sprite,PImage for red enemy
     */
    public Red(int x, int y, PImage sprite) {
        super(x, y, sprite);
        facingDirection = 0;
        this.rand = new Random();
        timeCounter = 0;
    }
    /**
     * logic method to check whether the red
     * enemy walked onto the wall
     * check whether red enemy walked into
     * the explosion area
     * and dead when hitted by explosion
     */
    public void tick(){
        if (facingDirection == 0){
            for (String each: App.mapObstacle.values()){
                if ((String.valueOf(this.x)+String.valueOf(this.y + moveSpeed)).equals(each)){
                    facingDirection = rand.nextInt(4)+0; 
                    while(true){
                        if (facingDirection == 1){
                            if (App.mapInverseObstacle.get(String.valueOf(this.x - moveSpeed)+String.valueOf(this.y)).equals("broken") || App.mapInverseObstacle.get(String.valueOf(this.x - moveSpeed)+String.valueOf(this.y)).equals("solid")){
                                facingDirection = rand.nextInt(4)+0;
                                continue;
                            }else{
                                return;
                            }
                        }else if (facingDirection == 3){
                            if (App.mapInverseObstacle.get(String.valueOf(this.x + moveSpeed)+String.valueOf(this.y)).equals("broken") || App.mapInverseObstacle.get(String.valueOf(this.x + moveSpeed)+String.valueOf(this.y)).equals("solid")){
                                facingDirection = rand.nextInt(4)+0;
                                continue;
                            }else{
                                return;
                            }
                        }else if (facingDirection == 2){
                            if (App.mapInverseObstacle.get(String.valueOf(this.x)+String.valueOf(this.y - moveSpeed)).equals("broken") || App.mapInverseObstacle.get(String.valueOf(this.x)+String.valueOf(this.y - moveSpeed)).equals("solid")){
                                facingDirection = rand.nextInt(4)+0;
                                continue;
                            }else{
                                return;
                            }
                        }else{
                            facingDirection = rand.nextInt(4)+0;
                        }

                    }
                }
            }
            this.y += moveSpeed; 
            App.moveTime = 0;   
            
        }
        if (facingDirection == 1){
            for (String each: App.mapObstacle.values()){
                if ((String.valueOf(this.x - moveSpeed)+String.valueOf(this.y)).equals(each)){
                    facingDirection = rand.nextInt(4)+0;            
                    while(true){
                        if (facingDirection == 0){
                            if (App.mapInverseObstacle.get(String.valueOf(this.x)+String.valueOf(this.y + moveSpeed)).equals("broken") || App.mapInverseObstacle.get(String.valueOf(this.x)+String.valueOf(this.y + moveSpeed)).equals("solid")){
                                facingDirection = rand.nextInt(4)+0;
                                continue;
                            }else{
                                return;
                            }
                        }else if (facingDirection == 3){
                            if (App.mapInverseObstacle.get(String.valueOf(this.x + moveSpeed)+String.valueOf(this.y)).equals("broken") || App.mapInverseObstacle.get(String.valueOf(this.x + moveSpeed)+String.valueOf(this.y)).equals("solid")){
                                facingDirection = rand.nextInt(4)+0;
                                continue;
                            }else{
                                return;
                            }
                        }else if (facingDirection == 2){
                            if (App.mapInverseObstacle.get(String.valueOf(this.x)+String.valueOf(this.y - moveSpeed)).equals("broken") || App.mapInverseObstacle.get(String.valueOf(this.x)+String.valueOf(this.y - moveSpeed)).equals("solid")){
                                facingDirection = rand.nextInt(4)+0;
                                continue;
                            }else{
                                return;
                            }
                        }else{
                            facingDirection = rand.nextInt(4)+0;
                        }

                    }
                }
            }
            this.x -= moveSpeed;
            App.moveTime = 0;
        }
        if (facingDirection == 2){
            for (String each: App.mapObstacle.values()){
                if ((String.valueOf(this.x)+String.valueOf(this.y - moveSpeed)).equals(each)){
                    facingDirection = rand.nextInt(4)+0; 
                    while(true){
                        if (facingDirection == 0){
                            if (App.mapInverseObstacle.get(String.valueOf(this.x)+String.valueOf(this.y + moveSpeed)).equals("broken") || App.mapInverseObstacle.get(String.valueOf(this.x)+String.valueOf(this.y + moveSpeed)).equals("solid")){
                                facingDirection = rand.nextInt(4)+0;
                                continue;
                            }else{
                                return;
                            }
                        }else if (facingDirection == 1){
                            if (App.mapInverseObstacle.get(String.valueOf(this.x - moveSpeed)+String.valueOf(this.y)).equals("broken") || App.mapInverseObstacle.get(String.valueOf(this.x - moveSpeed)+String.valueOf(this.y)).equals("solid")){
                                facingDirection = rand.nextInt(4)+0;
                                continue;
                            }else{
                                return;
                            }
                        }else if (facingDirection == 3){
                            if (App.mapInverseObstacle.get(String.valueOf(this.x + moveSpeed)+String.valueOf(this.y)).equals("broken") || App.mapInverseObstacle.get(String.valueOf(this.x + moveSpeed)+String.valueOf(this.y)).equals("solid")){
                                facingDirection = rand.nextInt(4)+0;
                                continue;
                            }else{
                                return;
                            }
                        }else{
                            facingDirection = rand.nextInt(4)+0;
                        }

                    }
                }
            }
            this.y -= moveSpeed;
            App.moveTime = 0;
        }
        if (facingDirection == 3){
            for (String each: App.mapObstacle.values()){
                if ((String.valueOf(this.x + moveSpeed)+String.valueOf(this.y)).equals(each)){
                    facingDirection = rand.nextInt(4)+0;
                    while(true){
                        if (facingDirection == 0){
                            if (App.mapInverseObstacle.get(String.valueOf(this.x)+String.valueOf(this.y + moveSpeed)).equals("broken") || App.mapInverseObstacle.get(String.valueOf(this.x)+String.valueOf(this.y + moveSpeed)).equals("solid")){
                                facingDirection = rand.nextInt(4)+0;
                                continue;
                            }else{
                                return;
                            }
                        }else if (facingDirection == 1){
                            if (App.mapInverseObstacle.get(String.valueOf(this.x - moveSpeed)+String.valueOf(this.y)).equals("broken") || App.mapInverseObstacle.get(String.valueOf(this.x - moveSpeed)+String.valueOf(this.y)).equals("solid")){
                                facingDirection = rand.nextInt(4)+0;
                                continue;
                            }else{
                                return;
                            }
                        }else if (facingDirection == 2){
                            if (App.mapInverseObstacle.get(String.valueOf(this.x)+String.valueOf(this.y - moveSpeed)).equals("broken") || App.mapInverseObstacle.get(String.valueOf(this.x)+String.valueOf(this.y - moveSpeed)).equals("solid")){
                                facingDirection = rand.nextInt(4)+0;
                                continue;
                            }else{
                                return;
                            }
                        }else{
                            facingDirection = rand.nextInt(4)+0;
                        }

                    } 
                }
            }
            this.x += moveSpeed;
            App.moveTime = 0;

        }

    
    }
    /**
     * set x-axsi for red enemy
     * @param x
     */
    public void setRedX(int x){
        this.x = x;
    }
    /**
     * set y-axsi for red enemy
     * @param y
     */
    public void setRedY(int y){
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
     * set the facing direction by receiving integer
     * @param facingDirection,integer number
     */
    public void setFacing(int facingDirection){
        this.facingDirection = facingDirection;
    }

    
}
