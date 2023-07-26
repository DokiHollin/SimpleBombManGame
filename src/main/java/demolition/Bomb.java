package demolition;

import processing.core.PApplet;
import processing.core.PImage;
/**
 * All bomb and explosion operations
 */
public class Bomb extends PApplet{
    /**
    * x-axis for bomb
    */
    private int x;
    /**
    * y-axis for bomb
    */
    private int y;
    /**
    * PImage for the bomb which shown on the screen
    */
    private PImage sprite;
    /**
    * to judge the state of the bomb, whether it need explode
    */
    private int bombState = 0;
    /**
    * to judge whether the explosion finished
    */
    private boolean isFinished;
    /**
    * time counter for the bomb
    */
    private int timeCounter;
    /**
    * time counter for explosion
    */
    private int explosionTime;
    /**
    * whether the bomb exploded
    */
    private boolean close;
    /**
    * whether the left explosion area touched by player
    */
    private boolean leftOneHitted = false;
    /**
    * whether solid exploded
    */
    private boolean isSolid;
    /**
    * furthest left explosion area touched
    */
    private boolean leftTwoHitted = false;
    /**
    * furthest left explosion area touched by solid wall
    */
    private boolean left2isSolid;
    /**
    * used to make sure each explosion hit player only once
    */
    private int leftAccessCounter = 0;
    /**
    * right explosion area touched
    */
    private boolean rightOneHitted = false;
    /**
    * right explosion area touched by solid wall
    */
    private boolean right1isSolid;
    /**
    * used to make sure each explosion hit player only once
    */
    private int rightAccessCounter = 0;
    /**
    * furthest right explosion area touched
    */
    private boolean rightTwoHitted = false;
    /**
    * furthest right explosion area touched by solid wall
    */
    private boolean right2isSolid;
    /**
    * down explosion area touched
    */
    private boolean downOneHitted = false;
    /**
    * down explosion area touched by solid wall
    */
    private boolean down1isSolid;
    /**
    * used to make sure each explosion hit player only once
    */
    private int downAccessCounter = 0;
    /**
    * furthest down explosion area touched
    */
    private boolean downTwoHitted = false;
    /**
    * furthest down explosion area touched by solid wall
    */
    private boolean down2isSolid;
    /**
    * up explosion area touched
    */
    private boolean upOneHitted = false;
    /**
    * up explosion area touched by solid wall
    */
    private boolean up1isSolid;
    /**
    * used to make sure each explosion hit player only once
    */
    private int upAccessCounter = 0;
    /**
    * furthest up explosion area touched
    */
    private boolean upTwoHitted = false;
    /**
    * furthest up explosion area touched by solid wall
    */
    private boolean up2isSolid;
    /**
    * used to make sure each explosion hit player only once
    */
    private int playerOnExplosionCenterCounter = 0;
    /**
    * used to make sure each explosion hit player only once
    */
    private int enemyOnExplosionCenterCounter = 0;
    /**
    * used to make sure each explosion hit player only once
    */
    private int yellowOnExplosionCenterCounter = 0;

    /**
    * Constructor for a Bomb, requires x-axis
    * u-axis, and the image for the bomb
    * @param x, x-axis on the map
    * @param y, y-axis on the map
    * @param sprite, PImage for the bomb
    */
    public Bomb(int x, int y, PImage sprite){
        this.x = x;
        this.y = y;
        this.sprite = sprite;
        this.isFinished = false;
        this.timeCounter = 0;
        this.explosionTime = 0;
        this.close = false;
    }
    /**
    * method to check whether the bomb is finished  and ready to explode
    * if not change the image for bomb
    */
    public void tick(){
        if (!isFinished){
            if (bombState == 8){
                isFinished = true;
            }else{
                if (timeCounter > 60*0.25){
                    sprite = App.bombImage.get(bombState);
                    bombState ++;
                    timeCounter = 0;
                }   
            }
    
            timeCounter ++;
        }
        
    }
    /**
    * main method to run the logic and draw the bomb on the map
    * when finished also run the explosion
    * check whether it hit player enemy, or walls
    * reflect the logic and draw onto the screen
    * @param app, PApplet type for the program
    */
    public void draw(PApplet app){
        if (!isFinished){
            app.image(this.sprite, this.x, this.y);
        } 
        else{
            if (!close){
                if(explosionTime < 60*0.5){
                    app.image(App.explosionImage.get(0), this.x, this.y);//centre
                    if((String.valueOf(this.x)+String.valueOf(this.y)).equals(String.valueOf(App.player.getX())+String.valueOf(App.player.getY()))){
                        if (playerOnExplosionCenterCounter == 0){
                            hitOnPlayer();
                            return;
                              
                        }    
                    }
                    if((String.valueOf(this.x - 32)+String.valueOf(this.y)).equals(String.valueOf(App.player.getX())+String.valueOf(App.player.getY()))){
                        if (playerOnExplosionCenterCounter == 0){
                            hitOnPlayer(); 
                            return;
                              
                        }
                    }
                    if((String.valueOf(this.x - 64)+String.valueOf(this.y)).equals(String.valueOf(App.player.getX())+String.valueOf(App.player.getY())) && App.mapInverseObstacle.get(String.valueOf(this.x - 32)+String.valueOf(this.y)).equals("empty")){
                        if (playerOnExplosionCenterCounter == 0){
                            hitOnPlayer(); 
                            return; 
                        }
                    }
                    if((String.valueOf(this.x + 32)+String.valueOf(this.y)).equals(String.valueOf(App.player.getX())+String.valueOf(App.player.getY()))){
                        if (playerOnExplosionCenterCounter == 0){
                            hitOnPlayer();  
                            return;
                        }
                    }
                    if((String.valueOf(this.x + 64)+String.valueOf(this.y)).equals(String.valueOf(App.player.getX())+String.valueOf(App.player.getY())) && App.mapInverseObstacle.get(String.valueOf(this.x + 32)+String.valueOf(this.y)).equals("empty")){
                        if (playerOnExplosionCenterCounter == 0){
                            hitOnPlayer();
                            return;
  
                        }
                    }
                    if((String.valueOf(this.x)+String.valueOf(this.y - 32)).equals(String.valueOf(App.player.getX())+String.valueOf(App.player.getY()))){
                        if (playerOnExplosionCenterCounter == 0){
                            hitOnPlayer();  
                            return;
                        }
                    }
                    if((String.valueOf(this.x)+String.valueOf(this.y - 64)).equals(String.valueOf(App.player.getX())+String.valueOf(App.player.getY())) && App.mapInverseObstacle.get(String.valueOf(this.x)+String.valueOf(this.y - 32)).equals("empty")){
                        if (playerOnExplosionCenterCounter == 0){
                            hitOnPlayer();  
                            return; 
                        }
                    }
                    if((String.valueOf(this.x)+String.valueOf(this.y + 32)).equals(String.valueOf(App.player.getX())+String.valueOf(App.player.getY()))){
                        if (playerOnExplosionCenterCounter == 0){
                            hitOnPlayer();   
                            return; 
                        }
                    }
                    if((String.valueOf(this.x)+String.valueOf(this.y + 64)).equals(String.valueOf(App.player.getX())+String.valueOf(App.player.getY())) && App.mapInverseObstacle.get(String.valueOf(this.x)+String.valueOf(this.y + 32)).equals("empty")){
                        if (playerOnExplosionCenterCounter == 0){
                            hitOnPlayer();  
                            return;  
                        }
                    }
//-----------------------------------------------------------------------------------------------------------------------------------------------
                    if (App.redExist){   
                        if((String.valueOf(this.x)+String.valueOf(this.y)).equals(String.valueOf(App.redEnemy.getX())+String.valueOf(App.redEnemy.getY()))){
                            if (enemyOnExplosionCenterCounter == 0){
                                App.redEnemy.dead(); 
                                enemyOnExplosionCenterCounter ++;    
                            }    
                        }
                        if((String.valueOf(this.x - 32)+String.valueOf(this.y)).equals(String.valueOf(App.redEnemy.getX())+String.valueOf(App.redEnemy.getY()))){
                            if (enemyOnExplosionCenterCounter == 0){
                                App.redEnemy.dead(); 
                                enemyOnExplosionCenterCounter ++;    
                            }
                        }
                        if((String.valueOf(this.x - 64)+String.valueOf(this.y)).equals(String.valueOf(App.redEnemy.getX())+String.valueOf(App.redEnemy.getY())) && App.mapInverseObstacle.get(String.valueOf(this.x - 32)+String.valueOf(this.y)).equals("empty")){
                            if (enemyOnExplosionCenterCounter == 0){
                                App.redEnemy.dead(); 
                                enemyOnExplosionCenterCounter ++;    
                            }
                        }
                        if((String.valueOf(this.x + 32)+String.valueOf(this.y)).equals(String.valueOf(App.redEnemy.getX())+String.valueOf(App.redEnemy.getY()))){
                            if (enemyOnExplosionCenterCounter == 0){
                                App.redEnemy.dead(); 
                                enemyOnExplosionCenterCounter ++;    
                            }
                        }
                        if((String.valueOf(this.x + 64)+String.valueOf(this.y)).equals(String.valueOf(App.redEnemy.getX())+String.valueOf(App.redEnemy.getY())) && App.mapInverseObstacle.get(String.valueOf(this.x + 32)+String.valueOf(this.y)).equals("empty")){
                            if (enemyOnExplosionCenterCounter == 0){
                                App.redEnemy.dead(); 
                                enemyOnExplosionCenterCounter ++;    
                            }
                        }
                        if((String.valueOf(this.x)+String.valueOf(this.y - 32)).equals(String.valueOf(App.redEnemy.getX())+String.valueOf(App.redEnemy.getY()))){
                            if (enemyOnExplosionCenterCounter == 0){
                                App.redEnemy.dead(); 
                                enemyOnExplosionCenterCounter ++;    
                            }
                        }
                        if((String.valueOf(this.x)+String.valueOf(this.y - 64)).equals(String.valueOf(App.redEnemy.getX())+String.valueOf(App.redEnemy.getY())) && App.mapInverseObstacle.get(String.valueOf(this.x)+String.valueOf(this.y - 32)).equals("empty")){
                            if (enemyOnExplosionCenterCounter == 0){
                                App.redEnemy.dead(); 
                                enemyOnExplosionCenterCounter ++;    
                            }
                        }
                        if((String.valueOf(this.x)+String.valueOf(this.y + 32)).equals(String.valueOf(App.redEnemy.getX())+String.valueOf(App.redEnemy.getY()))){
                            if (enemyOnExplosionCenterCounter == 0){
                                App.redEnemy.dead(); 
                                enemyOnExplosionCenterCounter ++;    
                            }
                        }
                        if((String.valueOf(this.x)+String.valueOf(this.y + 64)).equals(String.valueOf(App.redEnemy.getX())+String.valueOf(App.redEnemy.getY())) && App.mapInverseObstacle.get(String.valueOf(this.x)+String.valueOf(this.y + 32)).equals("empty")){
                            if (enemyOnExplosionCenterCounter == 0){
                                App.redEnemy.dead(); 
                                enemyOnExplosionCenterCounter ++;    
                            }
                        }
                    }
//-----------------------------------------------------------------------------------------------------------------------------------------------
                    if (App.yellowExist){   
                        if((String.valueOf(this.x)+String.valueOf(this.y)).equals(String.valueOf(App.yellowEnemy.getX())+String.valueOf(App.yellowEnemy.getY()))){
                            if (yellowOnExplosionCenterCounter == 0){
                                App.yellowEnemy.dead(); 
                                yellowOnExplosionCenterCounter ++;    
                            }    
                        }
                        if((String.valueOf(this.x - 32)+String.valueOf(this.y)).equals(String.valueOf(App.yellowEnemy.getX())+String.valueOf(App.yellowEnemy.getY()))){
                            if (yellowOnExplosionCenterCounter == 0){
                                App.yellowEnemy.dead(); 
                                yellowOnExplosionCenterCounter ++;    
                            }
                        }
                        if((String.valueOf(this.x - 64)+String.valueOf(this.y)).equals(String.valueOf(App.yellowEnemy.getX())+String.valueOf(App.yellowEnemy.getY())) && App.mapInverseObstacle.get(String.valueOf(this.x - 32)+String.valueOf(this.y)).equals("empty")){
                            if (yellowOnExplosionCenterCounter == 0){
                                App.yellowEnemy.dead(); 
                                yellowOnExplosionCenterCounter ++;    
                            }
                        }
                        if((String.valueOf(this.x + 32)+String.valueOf(this.y)).equals(String.valueOf(App.yellowEnemy.getX())+String.valueOf(App.yellowEnemy.getY()))){
                            if (yellowOnExplosionCenterCounter == 0){
                                App.yellowEnemy.dead(); 
                                yellowOnExplosionCenterCounter ++;    
                            }
                        }
                        if((String.valueOf(this.x + 64)+String.valueOf(this.y)).equals(String.valueOf(App.yellowEnemy.getX())+String.valueOf(App.yellowEnemy.getY())) && App.mapInverseObstacle.get(String.valueOf(this.x + 32)+String.valueOf(this.y)).equals("empty")){
                            if (yellowOnExplosionCenterCounter == 0){
                                App.yellowEnemy.dead(); 
                                yellowOnExplosionCenterCounter ++;    
                            }
                        }
                        if((String.valueOf(this.x)+String.valueOf(this.y - 32)).equals(String.valueOf(App.yellowEnemy.getX())+String.valueOf(App.yellowEnemy.getY()))){
                            if (yellowOnExplosionCenterCounter == 0){
                                App.yellowEnemy.dead(); 
                                yellowOnExplosionCenterCounter ++;    
                            }
                        }
                        if((String.valueOf(this.x)+String.valueOf(this.y - 64)).equals(String.valueOf(App.yellowEnemy.getX())+String.valueOf(App.yellowEnemy.getY())) && App.mapInverseObstacle.get(String.valueOf(this.x)+String.valueOf(this.y - 32)).equals("empty")){
                            if (yellowOnExplosionCenterCounter == 0){
                                App.yellowEnemy.dead(); 
                                yellowOnExplosionCenterCounter ++;    
                            }
                        }
                        if((String.valueOf(this.x)+String.valueOf(this.y + 32)).equals(String.valueOf(App.yellowEnemy.getX())+String.valueOf(App.yellowEnemy.getY()))){
                            if (yellowOnExplosionCenterCounter == 0){
                                App.yellowEnemy.dead(); 
                                yellowOnExplosionCenterCounter ++;    
                            }
                        }
                        if((String.valueOf(this.x)+String.valueOf(this.y + 64)).equals(String.valueOf(App.yellowEnemy.getX())+String.valueOf(App.yellowEnemy.getY())) && App.mapInverseObstacle.get(String.valueOf(this.x)+String.valueOf(this.y + 32)).equals("empty")){
                            if (yellowOnExplosionCenterCounter == 0){
                                App.yellowEnemy.dead(); 
                                yellowOnExplosionCenterCounter ++;    
                            }
                        }
                    }
//-------------------------------------------------------------------------------------------------------------------------------------
                    if (leftAccessCounter == 0){
                        for (String each: App.mapObstacle.keySet()){
                            if ((String.valueOf(this.x - 32)+String.valueOf(this.y)).equals(App.mapObstacle.get(each))){ //horizontal left
                                leftAccessCounter ++;
                                leftOneHitted = true;
                                if (String.valueOf(each.charAt(0)).equals("b")){
                                    isSolid = false;
                                    int temp = 0;
                                    int temp1 = 0;
                                    for(Broken eachBroken: App.broken){
                                        if ((String.valueOf(eachBroken.getX())+String.valueOf(eachBroken.getY())).equals((String.valueOf(this.x - 32)+String.valueOf(this.y)))){
                                            temp1 ++;    
                                            break;    
                                        }
                                        else{
                                            temp ++;
                                        }
                                    }
                                    if (temp1 != 0){
                                        App.broken.remove(temp);
                                        App.empty.add(new Empty(this.x - 32 , this.y , App.emptyImage));
                                        App.mapObstacle.remove(each);
                                        App.mapInverseObstacle.remove((String.valueOf(this.x - 32)+String.valueOf(this.y)));
                                        App.mapInverseObstacle.put((String.valueOf(this.x - 32)+String.valueOf(this.y)), "empty");
                                        break;  
                                    }
                                    
                                }else{
                                    isSolid = true;
                                    break;
                                }
                            }
                            
                        }
                        for (String each: App.mapObstacle.keySet()){  
                            if (!leftOneHitted){
                                leftAccessCounter ++;
                                // System.out.println("left1 no obstacle");
                                if ((String.valueOf(this.x - 64)+String.valueOf(this.y)).equals(App.mapObstacle.get(each))){ //horizontal left2
                                    leftTwoHitted = true;
                                    if (String.valueOf(each.charAt(0)).equals("b")){
                                        left2isSolid = false;
                                        int temp = 0;
                                        int temp1 = 0;
                                        for(Broken eachBroken: App.broken){
                                            if ((String.valueOf(eachBroken.getX())+String.valueOf(eachBroken.getY())).equals((String.valueOf(this.x - 64)+String.valueOf(this.y)))){
                                                temp1 ++;    
                                                break;    
                                            }
                                            else{
                                                temp ++;
                                            }
                                        }
                                        if (temp1 != 0){
                                            App.broken.remove(temp);
                                            App.empty.add(new Empty(this.x - 64 , this.y , App.emptyImage));
                                            App.mapObstacle.remove(each);
                                            App.mapInverseObstacle.remove((String.valueOf(this.x - 64)+String.valueOf(this.y)));
                                            App.mapInverseObstacle.put((String.valueOf(this.x - 64)+String.valueOf(this.y)), "empty");
                                            break;  
                                        }
                                        
                                    }else{
                                        left2isSolid = true;
                                        break;
                                    }
        
                                }
                               
                            }
                        }
                        
                    }
                    if (rightAccessCounter == 0){
                        for (String each: App.mapObstacle.keySet()){
                            if ((String.valueOf(this.x + 32)+String.valueOf(this.y)).equals(App.mapObstacle.get(each))){ //horizontal right
                                rightAccessCounter ++;
                                rightOneHitted = true;
                                if (String.valueOf(each.charAt(0)).equals("b")){
                                    right1isSolid = false;
                                    int temp = 0;
                                    int temp1 = 0;
                                    for(Broken eachBroken: App.broken){
                                        if ((String.valueOf(eachBroken.getX())+String.valueOf(eachBroken.getY())).equals((String.valueOf(this.x + 32)+String.valueOf(this.y)))){
                                            temp1 ++;    
                                            break;    
                                        }
                                        else{
                                            temp ++;
                                        }
                                    }
                                    if (temp1 != 0){
                                        App.broken.remove(temp);
                                        App.empty.add(new Empty(this.x + 32 , this.y , App.emptyImage));
                                        App.mapObstacle.remove(each);
                                        App.mapInverseObstacle.remove((String.valueOf(this.x + 32)+String.valueOf(this.y)));
                                        App.mapInverseObstacle.put((String.valueOf(this.x + 32)+String.valueOf(this.y)), "empty");
                                        break;  
                                    }
                                    
                                }else{
                                    right1isSolid = true;
                                    break;
                                }
                            } 
                            
                        }
                        for (String each: App.mapObstacle.keySet()){  
                            if (!rightOneHitted){
                                rightAccessCounter ++;
                                // System.out.println("right1 no obstacle");
                                if ((String.valueOf(this.x + 64)+String.valueOf(this.y)).equals(App.mapObstacle.get(each))){ //horizontal right2
                                    rightTwoHitted = true;
                                    if (String.valueOf(each.charAt(0)).equals("b")){
                                        right2isSolid = false;
                                        int temp = 0;
                                        int temp1 = 0;
                                        for(Broken eachBroken: App.broken){
                                            if ((String.valueOf(eachBroken.getX())+String.valueOf(eachBroken.getY())).equals((String.valueOf(this.x + 64)+String.valueOf(this.y)))){
                                                temp1 ++;    
                                                break;    
                                            }
                                            else{
                                                temp ++;
                                            }
                                        }
                                        if (temp1 != 0){
                                            App.broken.remove(temp);
                                            App.empty.add(new Empty(this.x + 64 , this.y , App.emptyImage));
                                            App.mapObstacle.remove(each);
                                            App.mapInverseObstacle.remove((String.valueOf(this.x + 64)+String.valueOf(this.y)));
                                            App.mapInverseObstacle.put((String.valueOf(this.x + 64)+String.valueOf(this.y)), "empty");
                                            break;  
                                        }
                                        
                                    }else{
                                        right2isSolid = true;
                                        break;
                                    }
        
                                }
                                
                            }
                        }
                        
                    }

                    if (upAccessCounter == 0){
                        for (String each: App.mapObstacle.keySet()){
                            if ((String.valueOf(this.x)+String.valueOf(this.y - 32)).equals(App.mapObstacle.get(each))){ //vertical up
                                upAccessCounter ++;
                                upOneHitted = true;
                                if (String.valueOf(each.charAt(0)).equals("b")){
                                    up1isSolid = false;
                                    int temp = 0;
                                    int temp1 = 0;
                                    for(Broken eachBroken: App.broken){
                                        if ((String.valueOf(eachBroken.getX())+String.valueOf(eachBroken.getY())).equals((String.valueOf(this.x)+String.valueOf(this.y-32)))){
                                            temp1 ++;    
                                            break;    
                                        }
                                        else{
                                            temp ++;
                                        }
                                    }
                                    if (temp1 != 0){
                                        App.broken.remove(temp);
                                        App.empty.add(new Empty(this.x, this.y - 32 , App.emptyImage));
                                        App.mapObstacle.remove(each);
                                        App.mapInverseObstacle.remove((String.valueOf(this.x)+String.valueOf(this.y - 32)));
                                        App.mapInverseObstacle.put((String.valueOf(this.x)+String.valueOf(this.y - 32)), "empty");
                                        break;  
                                    }
                                    
                                }else{
                                    up1isSolid = true;
                                    break;
                                }
                            }
                            
                            
                        }
                        for (String each: App.mapObstacle.keySet()){  
                            if (!upOneHitted){
                                upAccessCounter ++;
                                // System.out.println("right1 no obstacle");
                                if ((String.valueOf(this.x)+String.valueOf(this.y - 64)).equals(App.mapObstacle.get(each))){ //vertical up2
                                    upTwoHitted = true;
                                    if (String.valueOf(each.charAt(0)).equals("b")){
                                        up2isSolid = false;
                                        int temp = 0;
                                        int temp1 = 0;
                                        for(Broken eachBroken: App.broken){
                                            if ((String.valueOf(eachBroken.getX())+String.valueOf(eachBroken.getY())).equals((String.valueOf(this.x)+String.valueOf(this.y - 64)))){
                                                temp1 ++;    
                                                break;    
                                            }
                                            else{
                                                temp ++;
                                            }
                                        }
                                        if (temp1 != 0){
                                            App.broken.remove(temp);
                                            App.empty.add(new Empty(this.x, this.y -64 , App.emptyImage));
                                            App.mapObstacle.remove(each);
                                            App.mapInverseObstacle.remove((String.valueOf(this.x)+String.valueOf(this.y - 64)));
                                            App.mapInverseObstacle.put((String.valueOf(this.x)+String.valueOf(this.y - 64)), "empty");
                                            break;  
                                        }
                                        
                                    }else{
                                        up2isSolid = true;
                                        break;
                                    }
        
                                }
                            
                            }
                        }
                        
                    }

                    if (downAccessCounter == 0){
                        for (String each: App.mapObstacle.keySet()){
                            if ((String.valueOf(this.x)+String.valueOf(this.y + 32)).equals(App.mapObstacle.get(each))){ //vertical down
                                downAccessCounter ++;
                                downOneHitted = true;
                                if (String.valueOf(each.charAt(0)).equals("b")){
                                    down1isSolid = false;
                                    int temp = 0;
                                    int temp1 = 0;
                                    for(Broken eachBroken: App.broken){
                                        if ((String.valueOf(eachBroken.getX())+String.valueOf(eachBroken.getY())).equals((String.valueOf(this.x)+String.valueOf(this.y+32)))){
                                            temp1 ++;    
                                            break;    
                                        }
                                        else{
                                            temp ++;
                                        }
                                    }
                                    if (temp1 != 0){
                                        App.broken.remove(temp);
                                        App.empty.add(new Empty(this.x, this.y + 32 , App.emptyImage));
                                        App.mapObstacle.remove(each);
                                        App.mapInverseObstacle.remove((String.valueOf(this.x)+String.valueOf(this.y + 32)));
                                        App.mapInverseObstacle.put((String.valueOf(this.x)+String.valueOf(this.y + 32)), "empty");
                                        break;  
                                    }
                                    
                                }else{
                                    down1isSolid = true;
                                    break;
                                }
                            }
                            
                        }
                        for (String each: App.mapObstacle.keySet()){  
                            if (!downOneHitted){
                                downAccessCounter ++;
                                if ((String.valueOf(this.x)+String.valueOf(this.y + 64)).equals(App.mapObstacle.get(each))){ //vertical down2
                                    downTwoHitted = true;
                                    if (String.valueOf(each.charAt(0)).equals("b")){
                                        down2isSolid = false;
                                        int temp = 0;
                                        int temp1 = 0;
                                        for(Broken eachBroken: App.broken){
                                            if ((String.valueOf(eachBroken.getX())+String.valueOf(eachBroken.getY())).equals((String.valueOf(this.x)+String.valueOf(this.y + 64)))){
                                                temp1 ++;    
                                                break;    
                                            }
                                            else{
                                                temp ++;
                                            }
                                        }
                                        if (temp1 != 0){
                                            App.broken.remove(temp);
                                            App.empty.add(new Empty(this.x, this.y + 64 , App.emptyImage));
                                            App.mapObstacle.remove(each);
                                            App.mapInverseObstacle.remove((String.valueOf(this.x)+String.valueOf(this.y + 64)));
                                            App.mapInverseObstacle.put((String.valueOf(this.x)+String.valueOf(this.y + 64)), "empty");
                                            break;  
                                        }
                                        
                                    }else{
                                        down2isSolid = true;
                                        break;
                                    }
        
                                }
                                
                            }
                        }
                        
                    }
                    
                    
                    if (leftOneHitted){
                        if (!isSolid){
                            app.image(App.explosionImage.get(1), this.x-32, this.y); //horizontal left    
                        }
                    }else if (!leftTwoHitted){
                        app.image(App.explosionImage.get(1), this.x-32, this.y); //horizontal left
                        app.image(App.explosionImage.get(4), this.x-64, this.y); //end left
                    }

                    if(!leftOneHitted && leftTwoHitted){
                        if (!left2isSolid){
                            app.image(App.explosionImage.get(4), this.x-64, this.y); //end left
                            app.image(App.explosionImage.get(1), this.x-32, this.y); //horizontal left    
                        }else{
                            app.image(App.explosionImage.get(1), this.x-32, this.y); //horizontal left
                        }     
                    }

                    if (rightOneHitted){
                        if (!right1isSolid){
                            app.image(App.explosionImage.get(1), this.x+32, this.y); //horizontal right   
                        }
                    }else if (!rightTwoHitted){
                        app.image(App.explosionImage.get(1), this.x+32, this.y); //horizontal right
                        app.image(App.explosionImage.get(5), this.x+64, this.y); //end right
                    }

                    if(!rightOneHitted && rightTwoHitted){
                        if (!right2isSolid){
                            app.image(App.explosionImage.get(5), this.x+64, this.y); //end right
                            app.image(App.explosionImage.get(1), this.x+32, this.y); //horizontal right    
                        }else{
                            app.image(App.explosionImage.get(1), this.x+32, this.y); //horizontal right
                        }     
                    }

                    if (upOneHitted){
                        if (!up1isSolid){
                            app.image(App.explosionImage.get(2), this.x, this.y - 32); //vertical up    
                        }
                    }else if (!upTwoHitted){
                        app.image(App.explosionImage.get(2), this.x, this.y-32); //vertical up
                        app.image(App.explosionImage.get(6), this.x, this.y-64); //end up
                    }

                    if(!upOneHitted && upTwoHitted){
                        if (!up2isSolid){
                            app.image(App.explosionImage.get(6), this.x, this.y-64); //end up
                            app.image(App.explosionImage.get(2), this.x, this.y-32); //vertical up   
                        }else{
                            app.image(App.explosionImage.get(2), this.x, this.y-32); //vertical up
                        }     
                    }

                    if (downOneHitted){
                        if (!down1isSolid){
                            app.image(App.explosionImage.get(2), this.x, this.y + 32); //vertical down    
                        }
                    }else if (!downTwoHitted){
                        app.image(App.explosionImage.get(2), this.x, this.y+32); //vertical down
                        app.image(App.explosionImage.get(3), this.x, this.y+64); //end down
                    }

                    if(!downOneHitted && downTwoHitted){
                        if (!down2isSolid){
                            app.image(App.explosionImage.get(3), this.x, this.y+64); //end down
                            app.image(App.explosionImage.get(2), this.x, this.y+32); //vertical down   
                        }else{
                            app.image(App.explosionImage.get(2), this.x, this.y+32); //vertical down
                        }     
                    }

                    // app.image(App.explosionImage.get(0), this.x, this.y);//centre
                    // app.image(App.explosionImage.get(1), this.x-32, this.y); //horizontal left
                    // app.image(App.explosionImage.get(1), this.x+32, this.y); //horizontal right
                    // app.image(App.explosionImage.get(2), this.x, this.y-32); //vertical up
                    // app.image(App.explosionImage.get(2), this.x, this.y+32); //vertical down
                    // app.image(App.explosionImage.get(3), this.x, this.y+64); //end bottom
                    // app.image(App.explosionImage.get(4), this.x-64, this.y); //end left
                    // app.image(App.explosionImage.get(5), this.x+64, this.y); //end right
                    // app.image(App.explosionImage.get(6), this.x, this.y-64); //end top
                    explosionTime ++;    
                }else{
                    close = true;
                    playerOnExplosionCenterCounter = 0;
                    return;
                    
                }     
            }
            
               
        }
        
            
    }
    /**
    * setup and change the sprite for the bomb 
    * @param sprite,PImage for the sprite
    */
    public void setImage(PImage sprite){
        this.sprite = sprite;
    }
    /**
    * change the x-axis for the bomb
    * @param x, integer for new x-axis of the bomb
    */
    public void setX(int x){
        this.x = x;
    }
    /**
    * change the y-axis for the bomb
    * @param y, integer for new y-axis of the bomb
    */
    public void setY(int y){
        this.y = y;
    }
    /**
    * get the x-axis for the bomb
    * @return this.x
    */
    public int getX(){
        return this.x;
    }
    /**
    * get the y-axis for the bomb
    * @return this.y
    */
    public int getY(){
        return this.y;
    }
    /**
    * get the current bombstate for the bomb
    * @return bombState
    */
    public int bombCurrentState(){
        return bombState;
    }
    /**
    * set the isfinished to true
    * to say the bomb ready to explode
    */
    public void setFinished(){
        this.isFinished = true;
    }
    /**
    * whether bomb is finished loading
    * @return this.isFinished
    */
    public boolean isFinishedBomb(){
        return this.isFinished;
    }
    /**
    * whether bomb is closed
    * @return this.close
    */
    public boolean close(){
        return this.close;
    }
    /**
    * decrease the player's life by one
    * if in second level restart second level's map
    * else restart first level's map
    */
    public void hitOnPlayer(){
        App.lives --;
        explosionTime = 100;
        if (!App.levelOnePass){
            App.restart();
        }else{
            App.secondLevel();
        }
        
        playerOnExplosionCenterCounter ++;
    }

}
