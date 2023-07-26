package demolition;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import processing.data.*;
import processing.core.*;
import processing.core.PApplet;
import java.io.*;
/**
 * Main program class for running the game
 */
public class App extends PApplet {
    /**
    * Boolean variable to identify whether the player passed levelone
    */
    public static boolean levelOnePass;
    /**
    * PEont type to store the font
    */
    private PFont font;
    /**
    * screen width
    */
    public static final int WIDTH = 480;
    /**
    * screen height
    */
    public static final int HEIGHT = 480;
    /**
    * screen fps
    */
    public static final int FPS = 60;
    /**
    * to monitor the keyboard
    */
    public int keyCounter = 0;
    /**
    * store wallImage
    */
    private static PImage wallImage;
    /**
    * store wall'sImage
    */
    private static PImage brokenImage;
    /**
    * store broken wall's image
    */
    public static PImage emptyImage;
    /**
    * store goal's image
    */
    private static PImage goalImage;
    /**
    * store player current x-axis
    */
    private static int playerX;
    /**
    * store player current y-axis
    */
    private static int playerY;
    /**
    * store all the obstacles by type name then location
    */
    public static HashMap<String,String> mapObstacle;
    /**
    * store obstacle by store their location x-y first
    */
    public static HashMap<String,String> mapInverseObstacle;
    /**
    * store the original map structure
    */
    private static ArrayList<String> mapOrin;
    /**
    * store all the solid wall objects
    */
    private static ArrayList<Solid> solid;
    /**
    * store all the broken wall obejects
    */
    public static ArrayList<Broken> broken;
    /**
    * store all the empty objects
    */
    public static ArrayList<Empty> empty;
    /**
    * store all the goal object
    */
    private static ArrayList<Goal> goal;
    /**
    * store player leftmove png
    */
    public static ArrayList<PImage> leftImage;
    /**
    * store player rightmove png
    */
    public static ArrayList<PImage> rightImage;
    /**
    * store player upmove png
    */
    public static ArrayList<PImage> upImage;
    /**
    * store player downmove png
    */
    public static ArrayList<PImage> downImage;
    /**
    * store red enemy's left movement png
    */
    public static ArrayList<PImage> redLeftImage;
    /**
    * store red enemy's right movement png
    */
    public static ArrayList<PImage> redRightImage;
    /**
    * store red enemy's up movement png
    */
    public static ArrayList<PImage> redUpImage;
    /**
    * store red enemy's down movement png
    */
    public static ArrayList<PImage> redDownImage;
    /**
    * store yellow enemy's left movement png
    */
    
    public static ArrayList<PImage> yellowLeftImage;
    /**
    * store yellow enemy's right movement png
    */
    public static ArrayList<PImage> yellowRightImage;
    /**
    * store yellow enemy's up movement png
    */
    public static ArrayList<PImage> yellowUpImage;
    /**
    * store yellow enemy's down movement png
    */
    public static ArrayList<PImage> yellowDownImage;
    /**
    * player object
    */
    public static Player player;
    /**
    * red enemy object
    */
    public static Red redEnemy;
    /**
    * yellow enemy object
    */
    public static Yellow yellowEnemy;
    /**
    * boolean variable for whether red enemy dead or not
    */
    public static boolean redExist;
    /**
    * integer helped to identify the previous movement of the red enemy
    */
    int redPrevious;
    /**
    * integer count the graphic number for red enemy
    */
    private int redGraphicNum = 0;
    /**
    * integer to counts the run time for red enemy help to change image every 0.2 seconds
    */
    private static int redTimeCounter;
    /**
    * integer helps to judge whetehr red enemy can move automatically
    */
    public static int moveTime = 0;
    /**
    * boolean variable whether yellow enemy dead or not
    */
    public static boolean yellowExist;
    /**
    * integer helped to identify the previous movement of the yellow enemy
    */
    private int yellowPrevious;
    /**
    * integer count the graphic number for yellow enemy
    */
    private int yellowGraphicNum = 0;
    /**
    * integer to counts the run time for yellow enemy help to change image every 0.2 seconds
    */
    private static int yellowTimeCounter;
    /**
    * integer helps to judge whetehr yellow enemy can move automatically
    */
    public static int yellowMoveTime = 0;
    /**
    * ArrayList which store bomb image
    */
    public static ArrayList<PImage> bombImage;
    /**
    * ArrayList which store bomb type
    */
    public static ArrayList<Bomb> bombList;
    /**
    * Bomb type to drop a bomb onto the map
    */
    private static Bomb dropBomb;
    /**
    * boolean variable whether player should drop bomb
    */
    public boolean setBomb = false;
    /**
    * ArrayList which store explosion images
    */
    public static ArrayList<PImage> explosionImage;
    /**
    * String variable to store the direction of the player now are facing to
    */
    public static String facingDirection;
    /**
    * time counter for the player
    */
    private int timeCounter;
    /**
    * integer count the graphic number for player
    */
    private static int graphicNum = 0;
    /**
    * integer helped to identify the previous movement of the player
    */
    private String previous;
    /**
    * variable for player icon
    */
    private PImage playerIcon;
    /**
    * clock image
    */
    private PImage clockIcon;
    /**
    * player type class 
    */
    private Icons playerIcons;
    /**
    * clock type class 
    */
    private Icons clockIcons;
    /**
    * String variable for the json file
    */
    public static String fullFileName = "config.json";
    /**
    * String variable for the level one path
    */
    public static String levelOneFilePath;
    /**
    * String variabel for the level two path
    */
    public static String levelTwoFilePath;
    /**
    * the time limit for level one
    */
    public static int levelOneTime;
    /**
    * the time limit for level two
    */
    public static int levelTwoTime;
    /**
    * number of lifes for the player
    */
    public static int lives;  
    /**
    * time limit counter for each levels
    */
    private static int timeLimitCounter;

    /**
    * Constructor for App, used to initialize all the variables
    * default facing direction setted to downward
    */
    public App() {
        mapObstacle = new HashMap<String,String>();
        mapInverseObstacle = new HashMap<String,String>();
        mapOrin = new ArrayList<String>();
        solid = new ArrayList<Solid>();
        broken = new ArrayList<Broken>();
        empty = new ArrayList<Empty>();
        goal = new ArrayList<Goal>();
        leftImage = new ArrayList<PImage>();
        rightImage = new ArrayList<PImage>();
        upImage = new ArrayList<PImage>();
        downImage = new ArrayList<PImage>();
        bombImage = new ArrayList<PImage>();
        bombList = new ArrayList<Bomb>();
        explosionImage = new ArrayList<PImage>();

        redLeftImage = new ArrayList<PImage>();
        redRightImage = new ArrayList<PImage>();
        redUpImage = new ArrayList<PImage>();
        redDownImage = new ArrayList<PImage>();

        yellowLeftImage = new ArrayList<PImage>();
        yellowRightImage = new ArrayList<PImage>();
        yellowUpImage = new ArrayList<PImage>();
        yellowDownImage = new ArrayList<PImage>();

        facingDirection = "D";
    }
    /**
    * default setting for the screen size
    */
    public void settings() {
        size(WIDTH, HEIGHT);
    }
    /**
    * setup the game for the map,
    * load all the image inside of this file 
    * also the setup the location for each images
    */
    public void setup() {
        JSONObject object;
        try {
            object = new JSONObject(new FileReader(fullFileName));
            JSONArray objectLevel = object.getJSONArray("levels");
            ArrayList<String> temp = new ArrayList<String>();
            ArrayList<Integer> temp2 = new ArrayList<Integer>();
            for (int i = 0; i < objectLevel.size(); i++){
                JSONObject subObject = (JSONObject) objectLevel.get(i);
                temp.add((String)subObject.get("path"));
                temp2.add((Integer)subObject.get("time"));
            }
            levelOneFilePath = temp.get(0);
            levelTwoFilePath = temp.get(1);
            levelOneTime = temp2.get(0);
            levelTwoTime = temp2.get(1);
            lives = (int) object.get("lives"); 
        } catch (FileNotFoundException e) {
            levelOneFilePath = "level1.txt";
            levelTwoFilePath = "level2.txt";
            lives = 3;
            levelOneTime = 180;
            levelTwoTime = 180;
        }
        font = createFont("src/main/resources/PressStart2P-Regular.ttf", 20);
        textFont(font);
        frameRate(FPS);
        levelOnePass = false;
        redExist = false;
        yellowExist = false;
        timeLimitCounter = 0;
        wallImage = loadImage("src/main/resources/wall/solid.png");
        brokenImage = loadImage("src/main/resources/broken/broken.png");
        emptyImage = loadImage("src/main/resources/empty/empty.png");
        goalImage = loadImage("src/main/resources/goal/goal.png");
        playerIcon = loadImage("src/main/resources/icons/player.png");
        clockIcon = loadImage("src/main/resources/icons/clock.png");
        for (int i = 1; i <=4; i++){
            leftImage.add(loadImage("src/main/resources/player/player_left"+  i +".png"));
        }
        for (int i = 1; i <= 4; i++){
            rightImage.add(loadImage("src/main/resources/player/player_right"+  i +".png"));
        }
        for (int i = 1; i <= 4; i++){
            upImage.add(loadImage("src/main/resources/player/player_up"+  i +".png"));
        }
        for (int i = 1; i <= 4; i++){
            downImage.add(loadImage("src/main/resources/player/player"+  i +".png"));
        }
        for (int i = 1; i <= 8; i++){
            bombImage.add(loadImage("src/main/resources/bomb/bomb"+  i +".png"));
        }
//---------------------------------------------------------------------------- Red Enemy
        for (int i = 1; i <=4; i++){
            redLeftImage.add(loadImage("src/main/resources/red_enemy/red_left"+  i +".png"));
        }
        for (int i = 1; i <= 4; i++){
            redRightImage.add(loadImage("src/main/resources/red_enemy/red_right"+  i +".png"));
        }
        for (int i = 1; i <= 4; i++){
            redUpImage.add(loadImage("src/main/resources/red_enemy/red_up"+  i +".png"));
        }
        for (int i = 1; i <= 4; i++){
            redDownImage.add(loadImage("src/main/resources/red_enemy/red_down"+  i +".png"));
        }
//---------------------------------------------------------------------------- Yellow Enemy
        for (int i = 1; i <=4; i++){
            yellowLeftImage.add(loadImage("src/main/resources/yellow_enemy/yellow_left"+  i +".png"));
        }
        for (int i = 1; i <= 4; i++){
            yellowRightImage.add(loadImage("src/main/resources/yellow_enemy/yellow_right"+  i +".png"));
        }
        for (int i = 1; i <= 4; i++){
            yellowUpImage.add(loadImage("src/main/resources/yellow_enemy/yellow_up"+  i +".png"));
        }
        for (int i = 1; i <= 4; i++){
            yellowDownImage.add(loadImage("src/main/resources/yellow_enemy/yellow_down"+  i +".png"));
        }

        explosionImage.add(loadImage("src/main/resources/explosion/centre.png"));      
        explosionImage.add(loadImage("src/main/resources/explosion/horizontal.png"));
        explosionImage.add(loadImage("src/main/resources/explosion/vertical.png"));
        explosionImage.add(loadImage("src/main/resources/explosion/end_bottom.png"));
        explosionImage.add(loadImage("src/main/resources/explosion/end_left.png"));
        explosionImage.add(loadImage("src/main/resources/explosion/end_right.png"));
        explosionImage.add(loadImage("src/main/resources/explosion/end_top.png"));
        playerIcons = new Icons(140,20,playerIcon);
        clockIcons = new Icons(260,20,clockIcon);
        if (levelOneFilePath == null){
            levelOneFilePath = "level1.txt";
            levelTwoFilePath = "level2.txt";
            lives = 3;
            levelOneTime = 180;
            levelTwoTime = 180;    
        }
        File f = new File(levelOneFilePath);
        Scanner scan;
        try {
            scan = new Scanner(f);
            while (scan.hasNextLine()){
                String contents = scan.nextLine();
                mapOrin.add(contents);
                
            }
            int initialX = 0;
            int initialY = 64;
            int wallCounter = 0;
            int BrokenCounter = 0;
            for (String each: mapOrin){
                for (int i = 0; i < each.length(); i ++){
                    if ((String.valueOf(each.charAt(i))).equals("W")){

                        solid.add(new Solid(initialX , initialY , wallImage));
                        mapObstacle.put("solid"+wallCounter,(String.valueOf(initialX)+String.valueOf(initialY)));
                        mapInverseObstacle.put((String.valueOf(initialX)+String.valueOf(initialY)),"solid");
                        wallCounter ++;
                    }else if ((String.valueOf(each.charAt(i))).equals("B")){
                        
                        broken.add(new Broken(initialX , initialY , brokenImage));
                        mapObstacle.put("broken"+BrokenCounter,(String.valueOf(initialX)+String.valueOf(initialY))); 
                        mapInverseObstacle.put((String.valueOf(initialX)+String.valueOf(initialY)),"broken");
                        BrokenCounter ++;
                    }else if ((String.valueOf(each.charAt(i))).equals(" ")){
                        mapInverseObstacle.put((String.valueOf(initialX)+String.valueOf(initialY)),"empty");
                        empty.add(new Empty(initialX , initialY , emptyImage)); 

                    }else if ((String.valueOf(each.charAt(i))).equals("G")){
                        mapInverseObstacle.put((String.valueOf(initialX)+String.valueOf(initialY)),"goal");
                        goal.add(new Goal(initialX , initialY , goalImage)); 

                    }else if ((String.valueOf(each.charAt(i))).equals("Y")){
                        mapInverseObstacle.put((String.valueOf(initialX)+String.valueOf(initialY)),"yellow");
                        empty.add(new Empty(initialX , initialY , emptyImage));
                        yellowEnemy = new Yellow(initialX, initialY, yellowDownImage.get(0));  
                        yellowExist = true;
                    }else if ((String.valueOf(each.charAt(i))).equals("P")){
                        mapInverseObstacle.put((String.valueOf(initialX)+String.valueOf(initialY)),"player");
                        empty.add(new Empty(initialX , initialY , emptyImage));
                        playerX = initialX;
                        playerY = initialY;
                        player = new Player(playerX, playerY, downImage.get(graphicNum), dropBomb);
                    }else if ((String.valueOf(each.charAt(i))).equals("R")){
                        mapInverseObstacle.put((String.valueOf(initialX)+String.valueOf(initialY)),"red");
                        redEnemy = new Red(initialX, initialY, redDownImage.get(0));
                        empty.add(new Empty(initialX , initialY , emptyImage)); 
                        redEnemy.setRedX(initialX);
                        redEnemy.setRedY(initialY); 
                        redExist = true;

                    }
                        
                    initialX += 32;
                }
                initialX = 0; 
                initialY += 32;
            }
           
        } catch (FileNotFoundException e) {
            System.out.println("No such file exist.");
        }
        
        
        // Load images during setup
    }

    
    /**
    * main method to do all the game operations
    * judge whether level one passed and then change map construction
    * change each image for 0.2 seconds
    * run yellow and red enemies
    * also drop bomb and logic for explosion
    */
    public void draw() {
        // System.out.println(player.getX());
        // System.out.println(player.getY());
        background(255, 140, 0);
        if (levelOnePass){
            if (levelOneTime == 0 || player.getPlayerLife() == 0){
                text("GAME OVER", 140 , 240);
                return;
            }
            if (player.getX() == goal.get(0).getX() && player.getY() == goal.get(0).getY()){
                text("YOU WIN", 150 , 240);
                return;
            }
        }else{
            if (levelOneTime == 0 || player.getPlayerLife() == 0){
                text("GAME OVER", 140 , 240);
                return;
            }
            if (player.getX() == goal.get(0).getX() && player.getY() == goal.get(0).getY()){
                levelOnePass = true;
                secondLevel();
                return;
            }
        }
        
        playerIcons.draw(this);
        clockIcons.draw(this);
        fill(0, 0, 0);
        text(player.getPlayerLife(), 180 , 48);
        text(levelOneTime, 300 ,48);
        while (timeLimitCounter > 60){
            levelOneTime --;
            timeLimitCounter = 0;
        }

        for (Solid each: solid){
            each.draw(this); 
        }
        for (Broken each: broken){
            each.draw(this);    
        }
        for (Empty each: empty){
            each.draw(this);    
        }
        for (Goal each: goal){
            each.draw(this);    
        }
        if (bombList.size() != 0){
            for (Bomb eachBombs: bombList){
                if (!eachBombs.isFinishedBomb()){
                    eachBombs.tick();
                    eachBombs.draw(this); 
                        
                }else if (eachBombs.isFinishedBomb() && !eachBombs.close()){
                    eachBombs.draw(this);

                }  

                
            }
        }
        if(graphicNum == 3){
            graphicNum = 0;
        }
        if (player.getPlayerLife() <= 0){
            player.setAlive(false);
        }
        if (player.isAlive()){
            if (facingDirection == "D"){
                if (previous != "D"){
                    player.setSprite(downImage.get(graphicNum));   
                }
                if (timeCounter > 60*0.2){ 
                    graphicNum ++;
                    timeCounter = 0;
                    player.setSprite(downImage.get(graphicNum));
                } 
                player.tick();
                player.draw(this);
                previous = "D";               
            }else if (facingDirection == "U"){
                if (previous != "U"){
                    player.setSprite(upImage.get(graphicNum));   
                } 
                if (timeCounter > 60*0.2){ 
                    graphicNum ++;
                    timeCounter = 0;
                    player.setSprite(upImage.get(graphicNum));
                } 
                player.tick();
                player.draw(this);
                previous = "U";  
            }else if(facingDirection == "R"){
                if (previous != "R"){
                    player.setSprite(rightImage.get(graphicNum));    
                } 
                if (timeCounter > 60*0.2){ 
                    graphicNum ++;
                    timeCounter = 0;
                    player.setSprite(rightImage.get(graphicNum));
                } 
                player.tick();
                player.draw(this);
                previous = "R"; 
            }else if(facingDirection == "L"){
                if (previous != "L"){
                    player.setSprite(leftImage.get(graphicNum));   
                } 
                if (timeCounter > 60*0.2){ 
                    graphicNum ++;
                    timeCounter = 0;
                    player.setSprite(leftImage.get(graphicNum));
                } 
                player.tick();
                player.draw(this);
                previous = "L";
            }
            if (setBomb){
                bombList.add(new Bomb(player.getX(), player.getY(), bombImage.get(0)));
                setBomb = false;
            }
        }
//--------------------------------------------------------------------------
        if(redGraphicNum == 3){
            redGraphicNum = 0;
        }
        if (redExist){
            if(redEnemy.isAlive()){
                if (redEnemy.getFacing() == 0){
                    if (redPrevious != 0){
                        redEnemy.setSprite(redDownImage.get(redGraphicNum));   
                    }
                    if (redTimeCounter > 60*0.2){ 
                        redGraphicNum ++;
                        redTimeCounter = 0;
                        redEnemy.setSprite(redDownImage.get(redGraphicNum));
                    }
                    if (moveTime > 60){ 
                        redEnemy.tick();
                    }
                    redEnemy.draw(this);
                    redPrevious = 0; 
                }else if(redEnemy.getFacing() == 1){
                    if (redPrevious != 1){
                        redEnemy.setSprite(redLeftImage.get(redGraphicNum));   
                    } 
                    if (redTimeCounter > 60*0.2){ 
                        redGraphicNum ++;
                        redTimeCounter = 0;
                        redEnemy.setSprite(redLeftImage.get(redGraphicNum));
                    } 
                    if (moveTime > 60){
                        redEnemy.tick();
                    }
                    redEnemy.draw(this);
                    redPrevious = 1;              
                }else if (redEnemy.getFacing() == 2){
                    if (redPrevious != 2){
                        redEnemy.setSprite(redUpImage.get(redGraphicNum));   
                    } 
                    if (redTimeCounter > 60*0.2){ 
                        redGraphicNum ++;
                        redTimeCounter = 0;
                        redEnemy.setSprite(redUpImage.get(redGraphicNum));
                    } 
                    if (moveTime > 60){
                        redEnemy.tick();
                    }
                    
                    redEnemy.draw(this);
                    redPrevious = 2;  
                }else if(redEnemy.getFacing() == 3){
                    if (redPrevious != 3){
                        redEnemy.setSprite(redRightImage.get(redGraphicNum));    
                    } 
                    if (redTimeCounter > 60*0.2){ 
                        redGraphicNum ++;
                        redTimeCounter = 0;
                        redEnemy.setSprite(redRightImage.get(redGraphicNum));
                    } 
                    if (moveTime > 60){
                        redEnemy.tick();
                    }
                    redEnemy.draw(this);
                    redPrevious = 3; 
                }
            }
        }
// -----------------------------------------------------------------------------------------------;
        if(yellowGraphicNum == 3){
            yellowGraphicNum = 0;
        }
        if (yellowExist){
            if(yellowEnemy.isAlive()){
                if (yellowEnemy.getFacing() == 0){
                    if (yellowPrevious != 0){
                        yellowEnemy.setSprite(yellowDownImage.get(yellowGraphicNum));   
                    }
                    if (yellowTimeCounter > 60*0.2){ 
                        yellowGraphicNum ++;
                        yellowTimeCounter = 0;
                        yellowEnemy.setSprite(yellowDownImage.get(yellowGraphicNum));
                    }
                    if (yellowMoveTime > 60){ 
                        yellowEnemy.tick();
                    }
                    yellowEnemy.draw(this);
                    yellowPrevious = 0; 
                }else if(yellowEnemy.getFacing() == 1){
                    if (yellowPrevious != 1){
                        yellowEnemy.setSprite(yellowLeftImage.get(yellowGraphicNum));   
                    } 
                    if (yellowTimeCounter > 60*0.2){ 
                        yellowGraphicNum ++;
                        yellowTimeCounter = 0;
                        yellowEnemy.setSprite(yellowLeftImage.get(yellowGraphicNum));
                    } 
                    if (yellowMoveTime > 60){
                        yellowEnemy.tick();
                    }
                    yellowEnemy.draw(this);
                    yellowPrevious = 1;              
                }else if (yellowEnemy.getFacing() == 2){
                    if (yellowPrevious != 2){
                        yellowEnemy.setSprite(yellowUpImage.get(yellowGraphicNum));   
                    } 
                    if (yellowTimeCounter > 60*0.2){ 
                        yellowGraphicNum ++;
                        yellowTimeCounter = 0;
                        yellowEnemy.setSprite(yellowUpImage.get(yellowGraphicNum));
                    } 
                    if (yellowMoveTime > 60){
                        yellowEnemy.tick();
                    }
                    
                    yellowEnemy.draw(this);
                    yellowPrevious = 2;  
                }else if(yellowEnemy.getFacing() == 3){
                    if (yellowPrevious != 3){
                        yellowEnemy.setSprite(yellowRightImage.get(yellowGraphicNum));    
                    } 
                    if (yellowTimeCounter > 60*0.2){ 
                        yellowGraphicNum ++;
                        yellowTimeCounter = 0;
                        yellowEnemy.setSprite(yellowRightImage.get(yellowGraphicNum));
                    } 
                    if (yellowMoveTime > 60){
                        yellowEnemy.tick();
                    }
                    yellowEnemy.draw(this);
                    yellowPrevious = 3; 
                }
            }
        }
        // System.out.println(player.getY());
        player.checkCollision(); 
        timeLimitCounter ++;
        Player.direction = "null";
        timeCounter ++;
        redTimeCounter ++;
        moveTime ++;
        yellowTimeCounter ++;
        yellowMoveTime ++;
        // while(timeCounts > 60){
        //     // System.out.print(player.getX()+" "+ player.getY() +"\n");  
        //     // timeCounts = 0;
        //     System.out.println(mapObstacle.size());
        //     timeCounts = 0;
        // }
        
       
    }
    /**
     * get the original setup for the map structure
     * @return mapOrin
     */
    public ArrayList<String> getMapOrin(){
        return mapOrin;
    }
    /**
     * method to listen the keyboard
     * to judge the movement of the player
     */
    public void keyPressed() {
        // Left: 37
        // Up: 38
        // Right: 39
        // Down: 40
        if (this.keyCode == 37 && keyCounter == 0) {  //left
            keyCounter = 1;
            Player.direction = "L";
            facingDirection = "L";
        } else if (this.keyCode == 39 && keyCounter == 0) { //right
            keyCounter = 1;
            Player.direction = "R";
            facingDirection = "R";
        }else if (this.keyCode == 38 && keyCounter == 0) { //up
            keyCounter = 1;
            Player.direction = "U";
            facingDirection = "U";
        }else if (this.keyCode == 40 && keyCounter == 0) { //down
            keyCounter = 1;
            Player.direction = "D";
            facingDirection = "D";
        }else if (this.keyCode == 32 && keyCounter == 0) { //space
            keyCounter = 1;
            setBomb = true;
        }
    }
    /**
     * force player move once when user press the key
     */
    public void keyReleased(){
        keyCounter = 0;
    }
    /**
     * method to reconstruct the game
     */
    public static void restart(){
        File f = new File(levelOneFilePath);
        mapOrin.clear();
        solid.clear();
        mapObstacle.clear();
        mapInverseObstacle.clear();
        broken.clear();
        empty.clear();
        goal.clear();
        timeLimitCounter = 0;
        graphicNum = 0;
        Scanner scan;
        try {
            scan = new Scanner(f);
            while (scan.hasNextLine()){
                String contents = scan.nextLine();
                mapOrin.add(contents);
                
            }
            int initialX = 0;
            int initialY = 64;
            int wallCounter = 0;
            int BrokenCounter = 0;
            for (String each: mapOrin){
                for (int i = 0; i < each.length(); i ++){
                    if ((String.valueOf(each.charAt(i))).equals("W")){

                        solid.add(new Solid(initialX , initialY , wallImage));
                        mapObstacle.put("solid"+wallCounter,(String.valueOf(initialX)+String.valueOf(initialY)));
                        mapInverseObstacle.put((String.valueOf(initialX)+String.valueOf(initialY)),"solid");
                        wallCounter ++;
                    }else if ((String.valueOf(each.charAt(i))).equals("B")){
                        
                        broken.add(new Broken(initialX , initialY , brokenImage));
                        mapObstacle.put("broken"+BrokenCounter,(String.valueOf(initialX)+String.valueOf(initialY))); 
                        mapInverseObstacle.put((String.valueOf(initialX)+String.valueOf(initialY)),"broken");
                        BrokenCounter ++;
                    }else if ((String.valueOf(each.charAt(i))).equals(" ")){
                        mapInverseObstacle.put((String.valueOf(initialX)+String.valueOf(initialY)),"empty");
                        empty.add(new Empty(initialX , initialY , emptyImage)); 

                    }else if ((String.valueOf(each.charAt(i))).equals("G")){
                        mapInverseObstacle.put((String.valueOf(initialX)+String.valueOf(initialY)),"goal");
                        goal.add(new Goal(initialX , initialY , goalImage)); 

                    }else if ((String.valueOf(each.charAt(i))).equals("Y")){
                        mapInverseObstacle.put((String.valueOf(initialX)+String.valueOf(initialY)),"yellow");
                        empty.add(new Empty(initialX , initialY , emptyImage));
                        yellowEnemy = new Yellow(initialX, initialY, yellowDownImage.get(0));  
                        yellowExist = true;
                    }else if ((String.valueOf(each.charAt(i))).equals("P")){
                        mapInverseObstacle.put((String.valueOf(initialX)+String.valueOf(initialY)),"player");
                        empty.add(new Empty(initialX , initialY , emptyImage));
                        playerX = initialX;
                        playerY = initialY;
                        player = new Player(playerX, playerY, downImage.get(0), dropBomb);
                    }else if ((String.valueOf(each.charAt(i))).equals("R")){
                        mapInverseObstacle.put((String.valueOf(initialX)+String.valueOf(initialY)),"red");
                        redEnemy = new Red(initialX, initialY, redDownImage.get(0));
                        empty.add(new Empty(initialX , initialY , emptyImage)); 
                        redEnemy.setRedX(initialX);
                        redEnemy.setRedY(initialY); 
                        redExist = true;

                    }
                        
                    initialX += 32;
                }
                initialX = 0; 
                initialY += 32;
                facingDirection = "D";
                redTimeCounter = 0;
                yellowTimeCounter = 0;
                yellowMoveTime = 0;
                moveTime = 0;
            }
           
        } catch (FileNotFoundException e) {
            System.out.println("No such file exist.");
        }
    }
    /**
     * map construction for second level
     */
    public static void secondLevel(){
        File f = new File(levelTwoFilePath);
        mapOrin.clear();
        solid.clear();
        mapObstacle.clear();
        mapInverseObstacle.clear();
        broken.clear();
        empty.clear();
        goal.clear();
        timeLimitCounter = 0;
        graphicNum = 0;
        redEnemy.dead();
        yellowEnemy.dead();
        Scanner scan;
        try {
            scan = new Scanner(f);
            while (scan.hasNextLine()){
                String contents = scan.nextLine();
                mapOrin.add(contents);
                
            }
            int y = 64;
            int x = 0;
            while (y < 224){
                while (x < 480){
                    mapInverseObstacle.put((String.valueOf(x)+String.valueOf(y)),"empty");
                    empty.add(new Empty(x , y , emptyImage));
                    x += 32;
                }
                y+= 32;
                x = 0;
            }
            int initialX = 0;
            int initialY = 224;
            int wallCounter = 0;
            int BrokenCounter = 0;
            for (String each: mapOrin){
                for (int i = 0; i < each.length(); i ++){
                    if ((String.valueOf(each.charAt(i))).equals("W")){

                        solid.add(new Solid(initialX , initialY , wallImage));
                        mapObstacle.put("solid"+wallCounter,(String.valueOf(initialX)+String.valueOf(initialY)));
                        mapInverseObstacle.put((String.valueOf(initialX)+String.valueOf(initialY)),"solid");
                        wallCounter ++;
                    }else if ((String.valueOf(each.charAt(i))).equals("B")){
                        
                        broken.add(new Broken(initialX , initialY , brokenImage));
                        mapObstacle.put("broken"+BrokenCounter,(String.valueOf(initialX)+String.valueOf(initialY))); 
                        mapInverseObstacle.put((String.valueOf(initialX)+String.valueOf(initialY)),"broken");
                        BrokenCounter ++;
                    }else if ((String.valueOf(each.charAt(i))).equals(" ")){
                        mapInverseObstacle.put((String.valueOf(initialX)+String.valueOf(initialY)),"empty");
                        empty.add(new Empty(initialX , initialY , emptyImage)); 

                    }else if ((String.valueOf(each.charAt(i))).equals("G")){
                        mapInverseObstacle.put((String.valueOf(initialX)+String.valueOf(initialY)),"goal");
                        goal.add(new Goal(initialX , initialY , goalImage)); 
                    }else if ((String.valueOf(each.charAt(i))).equals("P")){
                        mapInverseObstacle.put((String.valueOf(initialX)+String.valueOf(initialY)),"player");
                        empty.add(new Empty(initialX , initialY , emptyImage));
                        playerX = initialX;
                        playerY = initialY;
                        player = new Player(playerX, playerY, downImage.get(0), dropBomb);
                    }
                        
                    initialX += 32;
                }
                initialX = 0; 
                initialY += 32;
                facingDirection = "D";
                redTimeCounter = 0;
                yellowTimeCounter = 0;
                yellowMoveTime = 0;
                moveTime = 0;
                levelOneTime = levelTwoTime;
            }
           
        } catch (FileNotFoundException e) {
            System.out.println("No such file exist.");
        }
    }



    public static void main(String[] args) { 
        PApplet.main("demolition.App");

        
        
         
    }
}
