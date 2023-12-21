import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Canvas;
import java.awt.event.ActionEvent;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
import static java.lang.Character.*;
import java.awt.image.BufferedImage;
import java.awt.event.ActionListener;
import java.awt.Image;
import javax.imageio.ImageIO;
import java.util.*;
import java.util.ArrayList;
import java.util.function.*;
//import CommTon.*;
//import TerrainGeneration.*;


public class GameContainer extends Canvas implements KeyListener, Runnable {

  //Character starman = new Character(100, 100, 50, 50, 1);

  //higher levels will have faster & more asteroids
  //ArrayList asteroids = new ArrayList<asteroid>();

  private Character starman;
  private ArrayList<Asteroid> asteroids;
 // private Terrain terrain; 
  private Asteroid asteroids1;
  private Asteroid asteroids2;
  private Asteroid asteroids3; 

  private MovingThing[] pointsL1;

  HashMap<String, Boolean> keyMap = new HashMap<String, Boolean>();
  private BufferedImage back;
  private boolean[] keys;

  private Image leftWalk;
  private Image rightWalk;
  private Image starJump;
  private Image stand;

  private static long timer = 0;
  private static long timer2;

  private boolean isPaused = false;
  private int isNewPlayer = 0;


  public GameContainer() {
    starman = new Character(100, 500, 50, 50, 1);
    pointsL1 = new MovingThing[2];  
    
    asteroids = new ArrayList<Asteroid>();
    asteroids1 = new Asteroid(300, 100, 50, 50, 1);
    asteroids2 = new Asteroid(500, 100, 50, 50, 1);
    asteroids3 = new Asteroid(100, 100, 50, 50, 1);
    asteroids.add(asteroids1);
    asteroids.add(asteroids2);
    asteroids.add(asteroids3);

    pointsL1[0] = new Points(100, 100, 50, 50);
    pointsL1[1] = new Points(200, 100, 50, 50);

    

    timer2 = System.currentTimeMillis() + 200;

    //terrain = new Terrain();
   // terrain.setSelectedLevel(0);
    
    this.addKeyListener(this);
    new Thread(this).start();
    setVisible(true);
    Collidable.setFrameSize(getWidth(), getHeight());
    keys = new boolean[5];
    //isPaused = true;
    try{
      leftWalk = ImageIO.read(getClass().getResource("leftForwardWalk.png"));

      rightWalk = ImageIO.read(getClass().getResource("rightForwardWalk.png"));

      starJump = ImageIO.read(getClass().getResource("jumpStarman.png"));

      stand = ImageIO.read(getClass().getResource("standingStarman.png"));
      }
      catch (Exception e){

      }

  }

  public void update(Graphics window) {
    //REVIEW THIS 
    // while(System.currentTimeMillis()<20000){
    // startScreen(window);
    // }
    paint(window);
    
  }

  public void paint( Graphics window ) {
    Graphics2D twoDGraph = (Graphics2D)window;
    if (back==null)
      back = (BufferedImage)(createImage(getWidth(),getHeight()));
    Graphics gameWindow = back.createGraphics();
    gameWindow.clearRect(0,0,getWidth(),getHeight());

    gameWindow.setColor(Color.BLACK);
    gameWindow.fillRect(0, 0, getWidth(), getHeight());

    if(starman.getX() == 100 && starman.getY() == 500){
      gameWindow.setColor(Color.WHITE);
      gameWindow.drawString("Welcome to Starman!", 300, 250);
      gameWindow.drawString("High score: " + starman.getHighScore(), 300, 300);
      gameWindow.drawString("Lives" + starman.getLives(), 300, 350);
      gameWindow.drawString("Press Space to start.", 300, 400);
    }
      
    
    /*while(System.currentTimeMillis()<20000){
      startScreen(window);
    }*/ 
    
    starman.draw(gameWindow);

    for (Asteroid asteroid : asteroids) {
        asteroid.draw(gameWindow);
    }

    moveAsteroid();

    for (Asteroid asteroid : asteroids) {
        if(asteroid.getY()>= 600){
          if(System.currentTimeMillis() > timer2){
          asteroid.setRandPos();
            timer2 = System.currentTimeMillis() + 350;
          }
        }
    }
    
    if(isPaused == false){
   

    //when asteroid collides with ground, delete and reinitialize at top right corner

    for(Asteroid as : asteroids){
    if (as.didCollide(starman)){
      starman.removeLife();
      isPaused = true;
      //set random new position at top and also change direction
      //as.setRandPos();
    }
    }  

      for(int i = 0; i<pointsL1.length; i++){ 
          pointsL1[i].draw(gameWindow); 
      if (pointsL1[i].didCollide(starman)){
        starman.addScore();
        if(pointsL1[i] instanceof Points){
          if(pointsL1[i] instanceof PowerUp){
            starman.setSpeed(((PowerUp)pointsL1[i]).getSpeed()); 
          }
       // ((Points)pointsL1[i]).removePoints((Points)(pointsL1[i]));
          
        }  
        //set random new position at top and also change direction
          pointsL1[i].setRandPos();
      }
}  

  
    if (keys[0] && starman.getX() > 0){
      starman.move("LEFT");
      
      if(System.currentTimeMillis() > timer){ 
        walkForward();
        timer = System.currentTimeMillis() + 350;
        }
    }
    else if (keys[1] && starman.getX() < 800){
      
      starman.move("RIGHT");
      if(System.currentTimeMillis() > timer){ 
      walkForward();
      timer = System.currentTimeMillis() + 350;
      }

    } 
    else if (keys[2] && starman.getY() > 0){

      starman.move("UP");
      starman.setImage(starJump);

    }
    else if(keys[3] && starman.getY() < 600){
      
      starman.move("DOWN");
      //if starman collides with ground, set image to stand
    }
    }

    // else if (keys[4]){
    //   starman.jump();
    // }

    if (isPaused == true){
      pauseScreen(gameWindow);
      if (keys[4]){
        isPaused = false;
      }
    }

    
    twoDGraph.drawImage(back, null, 0, 0);
  }
 

    public void pauseScreen(Graphics window){
      isPaused = true;
      setBackground(Color.BLACK);
      window.setColor(Color.WHITE);
      if(isNewPlayer == 0){
        starman.setPos(100, 500);
      }
      
      if(starman.getLives() > 0){
        window.drawString("You lost a life!", 300, 250);
        window.drawString("Lives remaining: " + starman.getLives(), 300, 300);
        window.drawString("Score: " + starman.getScore(), 300, 350);
        window.drawString("Press Space to resume.", 300, 400);
        if(keys[4]){
          isPaused = false;
        }
      }
        
      else if(starman.getLives() == 0){
        window.drawString("Game Over!", 300, 250);
        window.drawString("Score: " + starman.getScore(), 300, 300);
        if(starman.getScore() > starman.getHighScore()){
          starman.setHIGHScore(starman.getScore());
          //update highscore text file
        }
        window.drawString("High Score: " + starman.getHighScore(), 300, 350);
        window.drawString("Press Space to start over.", 300, 400);
        //start game over
        isNewPlayer = 0;
        starman.setPos(100, 500);
        starman.setScore(0);
        starman.setLives(3);

        if(keys[4]){
          starman.setPos(100, 500);
        }
      }
    }
    

    // public void endLevelScreen(Graphics window){
    //   //if level one or two is completed
    //     window.setColor(Color.BLACK);
    //     window.drawString("Congrats! You finished the level.", 300, 300);
    //     window.drawString("Lives remaining: " + starman.getLives(), 300, 350);
    //     window.drawString("Final Score: " + starman.getScore(), 300, 400);
    //     window.drawString("High Score: " + starman.getHighScore(), 300, 450);
    //     //add congrats if a new high score is set
    //     window.drawString("Press Enter to continue to the next level.", 300, 500);
    // }

    private void moveAsteroid() {
        int direction = (int) (Math.random() * 2);
        for (Asteroid asteroid : asteroids) {
            if (direction == 0) {
                asteroid.move("LEFT");
            } else {
                asteroid.move("RIGHT");
            }
        }
    }

  public void walkForward(){

      //image = ImageIO.read();
    if(starman.getImage()==rightWalk){
      starman.setImage(leftWalk);
    } else{
      starman.setImage(rightWalk);
    }
  }


  

  public void keyPressed(KeyEvent e) {

    if (e.getKeyCode() == KeyEvent.VK_LEFT)
    {
      //keyMap.put("left", true);
      keys[0] = true;
      isNewPlayer++;

    }
    if (e.getKeyCode() == KeyEvent.VK_RIGHT)
    {
      //keyMap.put("right", true);
      keys[1] = true;
      isNewPlayer++;
    }
    if (e.getKeyCode() == KeyEvent.VK_UP)
    {
      //keyMap.put("up", true);
      keys[2] = true;
      isNewPlayer++;
    }
    if (e.getKeyCode() == KeyEvent.VK_DOWN)
    {
      //keyMap.put("down", true);
      keys[3] = true;
      isNewPlayer++;
    }
    if (e.getKeyCode() == KeyEvent.VK_SPACE)
    {
      //keyMap.put("space", true);
      keys[4] = true;
      isNewPlayer++;
    }
    repaint();
  }

  public void keyReleased(KeyEvent e) {
    if (e.getKeyCode() == KeyEvent.VK_LEFT)
      {
        //keyMap.put("left", true);
        keys[0] = false;

      }
      if (e.getKeyCode() == KeyEvent.VK_RIGHT)
      {
        //keyMap.put("right", true);
        keys[1] = false;
      }
      if (e.getKeyCode() == KeyEvent.VK_UP)
      {
        //keyMap.put("up", true);
        keys[2] = false;

      }
      if (e.getKeyCode() == KeyEvent.VK_DOWN)
      {
        //keyMap.put("down", true);
        keys[3] = false;
      }
      if (e.getKeyCode() == KeyEvent.VK_SPACE)
      {
        //keyMap.put("space", true);
        keys[4] = false;
      }
      repaint();
  }

  public void keyTyped(KeyEvent e) {
    //no code needed here
  }

  public void run() {
    while(true) {
      Timer.sleep(10);
      repaint();
    }
  }
}