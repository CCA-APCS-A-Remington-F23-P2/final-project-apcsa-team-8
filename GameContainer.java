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


public class GameContainer extends Canvas implements KeyListener, Runnable {

  Character starman = new Character(300, 400, 50, 50, 1);
  
  //higher levels will have faster & more asteroids
  Asteroid asteroid = new Asteroid(300, 100, 50, 50, 2);

  HashMap<String, Boolean> keyMap = new HashMap<String, Boolean>();
  private BufferedImage back;
  private boolean[] keys;

  private Image leftWalk;
  private Image rightWalk;
  private Image starJump;
  private Image stand;

  private static int HIGH_SCORE;
  private int lives = 3;
  private int score = 0;


  private static long timer = 0;
  private static long timer2 = 0;

  public GameContainer() {
    this.addKeyListener(this);
    new Thread(this).start();
    setVisible(true);
    Collidable.setFrameSize(getWidth(), getHeight());
    keys = new boolean[5];
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
    // gameWindow.drawString("Lives: " + lives, 300, 300);
    // gameWindow.drawString("Score: " + score, 300, 500);

    //starman.draw(window);
starman.draw(gameWindow);
asteroid.draw(gameWindow);
    


    if(System.currentTimeMillis()>timer2){
      asteroid.move("hdhd");
      
      timer2 = System.currentTimeMillis()+1000;
    }


    //when asteroid collides with ground, delete and reinitialize at top right corner
  
    if (asteroid.didCollide(starman)){
      lives--;
      starman.setPos(100, 100);
      //gameWindow.drawString("Uh oh!", 300, 300);
    //   //pause screen
    //   if (lives == 0){
    //     gameWindow.setColor(Color.BLACK);
    //     gameWindow.fillRect(0, 0, getWidth(), getHeight());
    //     gameWindow.setColor(Color.WHITE);
    //     gameWindow.drawString("GAME OVER", getWidth()/2-100, getHeight()/2);
    //     gameWindow.drawString("Score: " + score, getWidth()/2-100, getHeight()/2+50);
    //     gameWindow.drawString("High Score: " + HIGH_SCORE, getWidth()/2-100, getHeight()/2+100);
    // }
    }
    


    // if(keyMap.get("LEFT")){
    if (keys[0] && starman.getX() > 0){
      starman.move("LEFT");
      if(System.currentTimeMillis() > timer){ 
        walkForward();
        timer = System.currentTimeMillis() + 350;
        }
      //starman.draw(gameWindow);
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
    
    // else if (keys[4]){
    //   starman.jump();
    // }
    
    twoDGraph.drawImage(back, null, 0, 0);
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

    }
    if (e.getKeyCode() == KeyEvent.VK_RIGHT)
    {
      //keyMap.put("right", true);
      keys[1] = true;
    }
    if (e.getKeyCode() == KeyEvent.VK_UP)
    {
      //keyMap.put("up", true);
      keys[2] = true;
    }
    if (e.getKeyCode() == KeyEvent.VK_DOWN)
    {
      //keyMap.put("down", true);
      keys[3] = true;
    }
    if (e.getKeyCode() == KeyEvent.VK_SPACE)
    {
      //keyMap.put("space", true);
      keys[4] = true;
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