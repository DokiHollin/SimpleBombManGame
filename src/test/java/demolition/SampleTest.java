package demolition;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import processing.core.*;

// Because this test actually run the programs, so it takes time for the bomb
// to be exploded, so it takes around 1min to finish the test
// I think it better display the actual test movement during the game
// and it makes me eaiser to debug the game
// sorry about the long time for test the game
public class SampleTest {
    // setup before the test
    @Before
    public void move(PApplet app, String direction){
        
        App.facingDirection = direction;
        Player.direction = direction;
        app.draw();
    }
    //test frame size
    @Test
    public void simpleTest() {
        assertEquals(480, App.HEIGHT);
        assertEquals(480, App.WIDTH);
        assertEquals(60, App.FPS); 
    }

    //test move speed
    @Test
    public void speedTest(){
        App app = new App();
        app.noLoop();
        PApplet.runSketch(new String[] {"App"}, app);
        app.delay(1000);
        app.setup();
        app.delay(1000);
        app.draw();
        assertEquals(32, App.player.getMoveSpeed());
    }
    //check location after move once
    @Test
    public void moveOneTime(){
        App app = new App();
        PApplet.runSketch(new String[] {"App"}, app);
        app.noLoop();
        app.delay(1000);
        // app.setup();
        app.draw();

        app.delay(1000);
        move(app,"R");
        assertEquals(64, App.player.getX());
        assertEquals(96, App.player.getY());
        
        
        
    }
    // set bomb and check bomblist
    @Test
    public void setBomb(){
        App app = new App();
        PApplet.runSketch(new String[] {"App"}, app);
        app.noLoop();
        app.delay(1000);
        app.setup();
        app.delay(1000);
        app.draw();
        app.setBomb = true;
        app.draw();
        assertEquals(1, App.bombList.size());
    }
    // bomb break wall test
    @Test
    public void bombBreakWall(){
        App app = new App();
        PApplet.runSketch(new String[] {"App"}, app);
        app.noLoop();
        app.delay(1000);
        app.draw();
        move(app,"D");
        move(app,"D");
        app.setBomb = true;
        app.draw();
        app.delay(1000);
        app.draw();
        assertEquals(32, App.player.getX());
        assertEquals(160, App.player.getY());  
        app.loop();
        move(app,"U");
        move(app,"U");
        move(app,"R");
        app.delay(3000);
        assertTrue(App.bombList.size() != 0);
        assertEquals("empty", App.mapInverseObstacle.get("32192"));
        App.restart();
        app.draw();
        app.setBomb = true;
        for(Bomb each: App.bombList){
            each.setX(160);
            each.setY(96);
            each.setFinished();
        }
        app.draw();
        App.restart();
        app.draw();
        app.setBomb = true;
        for(Bomb each: App.bombList){
            each.setX(128);
            each.setY(96);
            each.setFinished();
        }
        app.draw();
        App.restart();
        app.draw();
        app.setBomb = true;
        for(Bomb each: App.bombList){
            each.setX(384);
            each.setY(224);
            each.setFinished();
        }
        app.draw();
        App.restart();
        app.draw();
        app.setBomb = true;
        for(Bomb each: App.bombList){
            each.setX(416);
            each.setY(224);
            each.setFinished();
        }
        app.draw();
        app.setBomb = true;
        app.draw();
        for(Bomb each: App.bombList){
            each.setX(128);
            each.setY(96);
            each.setFinished();
        }
        app.draw();
        assertTrue(App.mapInverseObstacle.get("19296") == "empty");
    }
    // player hitted by bomb and lose life test
    @Test
    public void loseLifeTest(){
        App app = new App();
        PApplet.runSketch(new String[] {"App"}, app);
        // app.noLoop();
        app.delay(1000);
        app.setBomb = true;
        app.draw();
        app.delay(500);
        move(app,"R");
        app.draw();
        app.delay(3000);
        assertEquals(2, App.lives);
        app.setBomb = true;
        app.draw();
        app.delay(500);
        move(app,"R");
        move(app,"R");
        app.delay(3000);
        assertEquals(1, App.lives);
    }

    // red enemy dead test
    @Test
    public void redDeadTest(){
        App app = new App();
        PApplet.runSketch(new String[] {"App"}, app);
        app.noLoop();
        app.delay(1000);
        app.draw();
        move(app,"D");
        move(app,"D");;
        move(app,"R");
        move(app,"R");
        move(app,"D");
        move(app,"D");
        app.loop();
        app.draw();
        app.delay(4500);
        app.setBomb = true;
        app.delay(100);
        move(app,"L");
        move(app,"L");
        move(app,"D");
        app.delay(3000);
        assertFalse(App.redEnemy.isAlive());
    }

    // exploded on different wall
    @Test
    public void explosionOnDifferentPlaceTest(){
        App app = new App();
        PApplet.runSketch(new String[] {"App"}, app);
        app.delay(1000);
        app.draw();
        App.lives ++;
        App.lives ++;
        App.lives ++;
        App.lives ++;
        move(app,"R");
        app.setBomb = true;
        app.delay(100);
        move(app,"L");
        app.draw();
        app.delay(3000);
        move(app,"R");
        move(app,"R");
        app.setBomb = true;
        app.delay(100);
        move(app,"L");
        move(app,"L");
        app.draw();
        app.delay(3000);
        move(app,"D");
        move(app,"D");
        app.setBomb = true;
        app.delay(100);
        move(app,"U");
        move(app,"U");
        app.draw();
        app.delay(3000);
        assertTrue(App.player.isAlive());
        app.setBomb = true;
        app.delay(100);
        move(app,"D");
        app.draw();
        app.delay(3000);
        assertTrue(App.player.isAlive());
    }
    // yellow dead by hitted on explosion
    @Test
    public void explosionOnYellow(){
        App app = new App();
        PApplet.runSketch(new String[] {"App"}, app);
        app.noLoop();
        app.delay(1000);
        app.setBomb = true;
        app.draw();
        for (Bomb each: App.bombList){
            each.setX(160);
            each.setY(352);
        }
        app.draw();
        app.delay(3000);
        App.restart();
        app.setBomb = true;
        app.draw();
        for (Bomb each: App.bombList){
            each.setX(160);
            each.setY(416);
            each.setImage(App.bombImage.get(7));
            each.getX();
            each.getY();
            each.bombCurrentState();
            App.yellowEnemy.setYellowX(160);
            App.yellowEnemy.setYellowY(416);
            each.setFinished();
        }
        app.draw();
        App.restart();
        app.setBomb = true;
        app.draw();
        for (Bomb each: App.bombList){
            each.setX(128);
            each.setY(416);
            App.yellowEnemy.setYellowX(160);
            App.yellowEnemy.setYellowY(416);
            each.setFinished();
        }
        app.draw();
        app.draw();
        App.restart();
        app.setBomb = true;
        app.draw();
        for (Bomb each: App.bombList){
            each.setX(96);
            each.setY(416);
            App.yellowEnemy.setYellowX(160);
            App.yellowEnemy.setYellowY(416);
            each.setFinished();
        }
        app.draw();
        App.restart();
        app.setBomb = true;
        app.draw();
        for (Bomb each: App.bombList){
            each.setX(160);
            each.setY(416);
            App.yellowEnemy.setYellowX(96);
            App.yellowEnemy.setYellowY(416);
            each.setFinished();
        }
        App.restart();
        app.setBomb = true;
        app.draw();
        for (Bomb each: App.bombList){
            each.setX(160);
            each.setY(416);
            App.yellowEnemy.setYellowX(128);
            App.yellowEnemy.setYellowY(416);
            each.setFinished();
        }
        app.draw();
        App.restart();
        app.setBomb = true;
        app.draw();
        for (Bomb each: App.bombList){
            each.setX(26);
            each.setY(288);
            App.yellowEnemy.setYellowX(26);
            App.yellowEnemy.setYellowY(320);
            each.setFinished();
        }
        app.draw();
        App.restart();
        app.setBomb = true;
        app.draw();
        for (Bomb each: App.bombList){
            each.setX(26);
            each.setY(288);
            App.yellowEnemy.setYellowX(26);
            App.yellowEnemy.setYellowY(352);
            each.setFinished();
        }
        App.restart();
        app.setBomb = true;
        app.draw();
        for (Bomb each: App.bombList){
            each.setX(26);
            each.setY(288);
            App.yellowEnemy.setYellowX(26);
            App.yellowEnemy.setYellowY(256);
            each.setFinished();
        }
        App.restart();
        app.setBomb = true;
        app.draw();
        for (Bomb each: App.bombList){
            each.setX(26);
            each.setY(288);
            App.yellowEnemy.setYellowX(26);
            App.yellowEnemy.setYellowY(224);
            each.setFinished();
        }
        assertTrue(App.bombList.size() >= 5);
    }
    // explosion on red, and red dead
    @Test
    public void explosionOnRed(){
        App app = new App();
        PApplet.runSketch(new String[] {"App"}, app);
        app.delay(1000);
        app.setBomb = true;
        app.draw();
        for (Bomb each: App.bombList){
            each.setX(160);
            each.setY(352);
        }
        app.draw();
        app.delay(3000);
        App.restart();
        app.setBomb = true;
        app.draw();
        for (Bomb each: App.bombList){
            each.setX(160);
            each.setY(416);
            each.setImage(App.bombImage.get(7));
            each.getX();
            each.getY();
            each.bombCurrentState();
            App.redEnemy.setRedX(160);
            App.redEnemy.setRedY(416);
            each.setFinished();
        }
        app.draw();
        App.restart();
        app.setBomb = true;
        app.draw();
        for (Bomb each: App.bombList){
            each.setX(128);
            each.setY(416);
            App.redEnemy.setRedX(160);
            App.redEnemy.setRedY(416);
            each.setFinished();
        }
        app.draw();
        app.draw();
        App.restart();
        app.setBomb = true;
        app.draw();
        for (Bomb each: App.bombList){
            each.setX(96);
            each.setY(416);
            App.redEnemy.setRedX(160);
            App.redEnemy.setRedY(416);
            each.setFinished();
        }
        app.draw();
        App.restart();
        app.setBomb = true;
        app.draw();
        for (Bomb each: App.bombList){
            each.setX(160);
            each.setY(416);
            App.redEnemy.setRedX(96);
            App.redEnemy.setRedY(416);
            each.setFinished();
        }
        App.restart();
        app.setBomb = true;
        app.draw();
        for (Bomb each: App.bombList){
            each.setX(160);
            each.setY(416);
            App.redEnemy.setRedX(128);
            App.redEnemy.setRedY(416);
            each.setFinished();
        }
        app.draw();
        App.restart();
        app.setBomb = true;
        app.draw();
        for (Bomb each: App.bombList){
            each.setX(26);
            each.setY(288);
            App.redEnemy.setRedX(26);
            App.redEnemy.setRedY(320);
            each.setFinished();
        }
        app.draw();
        App.restart();
        app.setBomb = true;
        app.draw();
        for (Bomb each: App.bombList){
            each.setX(26);
            each.setY(288);
            App.redEnemy.setRedX(26);
            App.redEnemy.setRedY(352);
            each.setFinished();
        }
        App.restart();
        app.setBomb = true;
        app.draw();
        for (Bomb each: App.bombList){
            each.setX(26);
            each.setY(288);
            App.redEnemy.setRedX(26);
            App.redEnemy.setRedY(256);
            each.setFinished();
        }
        App.restart();
        app.setBomb = true;
        app.draw();
        for (Bomb each: App.bombList){
            each.setX(26);
            each.setY(288);
            App.redEnemy.setRedX(26);
            App.redEnemy.setRedY(224);
            each.setFinished();
        }
        assertEquals(10 , App.bombList.size());
        App.redEnemy.setFacing(1);
        App.redEnemy.setRedX(96);
        App.redEnemy.setRedY(196);
        App.redEnemy.tick();
        app.draw();
        App.redEnemy.setFacing(2);
        App.redEnemy.setRedX(64);
        App.redEnemy.setRedY(160);
        App.redEnemy.tick();
    }
    //pass level one test
    @Test
    public void enterSecondLevelTest(){
        App app = new App();
        PApplet.runSketch(new String[] {"App"}, app);
        app.delay(1000);
        App.player.setX(416);
        App.player.setY(416);
        app.draw();
        assertTrue(App.levelOnePass);
        assertEquals(32, App.player.getX());
        assertEquals(256, App.player.getY());
    }
    //explosion area test
    @Test
    public void explosionVerticalTwogrids(){
        App app = new App();
        PApplet.runSketch(new String[] {"App"}, app);
        app.delay(1000);
        app.setBomb = true;
        app.draw();
        for(Bomb each: App.bombList){
            each.setX(32);
            each.setY(128);
            each.setFinished();
        }
        App.player.setX(96);
        App.player.setY(320);
        app.draw();
        app.delay(1000);
        assertEquals("empty",App.mapInverseObstacle.get("32192"));
    }
    //red walked onto wall test
    @Test
    public void redHittedOnWall(){
        App app = new App();
        PApplet.runSketch(new String[] {"App"}, app);
        app.noLoop();
        app.delay(1000);
        app.draw();
        app.keyPressed();
        app.keyCode = 37;
        app.keyCounter = 0;
        app.keyPressed();
        app.keyCode = 38;
        app.keyCounter = 0;
        app.keyPressed();
        app.keyCode = 39;
        app.keyCounter = 0;
        app.keyPressed();
        app.keyCode = 40;
        app.keyCounter = 0;
        app.keyPressed();
        app.keyCode = 32;
        app.keyCounter = 0;
        app.keyPressed();
        app.draw();
        App.redEnemy.setFacing(1);
        App.redEnemy.setRedX(32);
        App.redEnemy.setRedY(224);
        app.draw();
        App.redEnemy.setFacing(3);
        app.draw();
        assertTrue(App.redEnemy.isAlive());
    }
    //red facing onto wall
    @Test
    public void redFacing2(){
        App app = new App();
        PApplet.runSketch(new String[] {"App"}, app);
        app.delay(1000);
        app.draw();
        App.redEnemy.setFacing(2);
        app.draw();
        app.delay(120);
        assertTrue(App.redEnemy.isAlive());
    }
    //red facing onto wall
    @Test
    public void redFacing1(){
        App app = new App();
        PApplet.runSketch(new String[] {"App"}, app);
        app.delay(1000);
        app.draw();
        App.redEnemy.setFacing(1);
        app.draw();
        app.delay(120);
        assertTrue(App.redEnemy.isAlive());
    }
    //yellow facing onto wall
    @Test
    public void yellowFacing3(){
        App app = new App();
        PApplet.runSketch(new String[] {"App"}, app);
        app.delay(1000);
        app.draw();
        App.yellowEnemy.setFacing(3);
        app.draw();
        app.delay(120);
        assertTrue(App.redEnemy.isAlive());
    }
    //red enemy dead
    @Test 
    public void redEnemyDead(){
        App app = new App();
        PApplet.runSketch(new String[] {"App"}, app);
        app.delay(1000);
        app.setBomb = true;
        app.draw();
        for (Bomb each: App.bombList){
            each.setX(160);
            each.setY(128);
            App.redEnemy.setRedX(160);
            App.redEnemy.setRedY(96);
            each.setFinished();
        }
        app.draw();
        assertFalse(App.redEnemy.isAlive());
    }
    //red enemy dead in different explosion area
    @Test 
    public void redEnemyDead2(){
        App app = new App();
        PApplet.runSketch(new String[] {"App"}, app);
        app.delay(1000);
        app.setBomb = true;
        app.draw();
        for (Bomb each: App.bombList){
            each.setX(160);
            each.setY(160);
            App.redEnemy.setRedX(160);
            App.redEnemy.setRedY(96);
            each.setFinished();
        }
        app.draw();
        assertFalse(App.redEnemy.isAlive());
    }

    

}
