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
import java.util.*;
import java.util.function.*;
import Common.*;
import TerrainGeneration.*;


public class GameContainer extends Canvas implements KeyListener, Runnable {

  
  private HashMap<String, Boolean> keyMap = new HashMap<String, Boolean>();

  private BufferedImage back;

  private Moveable object;
  private Collidable floor;
  private Terrain terrain;

  private Timer jumpDelay;

  public GameContainer() {
    this.addKeyListener(this);
    new Thread(this).start();
    setVisible(true);

    object = new Moveable(100, 100, 10, 10, new Vector(0,0));
    floor = new Collidable(0, 200, 800, 20, Color.GREEN);
    terrain = new Terrain();
    terrain.setSelectedLevel(0);

    jumpDelay = new Timer(200);
  }

  public void update(Graphics window) {
    paint(window);
  }

  public void paint( Graphics window ) {
    //Graphics init
    Graphics2D twoDGraph = (Graphics2D)window;
    if (back==null)
      back = (BufferedImage)(createImage(getWidth(),getHeight()));
    Graphics gameWindow = back.createGraphics();
    gameWindow.clearRect(0,0,getWidth(),getHeight());

    
    // for(String i : keyMap.keySet()) {
    //   if (keyMap.get(i)) {
    //     System.out.println(i);
    //   }
    // }


    // floor.draw(gameWindow);
    terrain.draw(gameWindow);
    object.update(gameWindow, terrain.getTerrain());
    
    twoDGraph.drawImage(back, null, 0, 0);
  }

  public void jump(){
    if(jumpDelay.isFinished()){
      object.setVelocityY(-1 * 1.5);
    }
  }

  public void keyPressed(KeyEvent e) {

    double speed = 1.5;

    if (e.getKeyCode() == KeyEvent.VK_LEFT)
    {
      keyMap.put("left", true);
      object.setVelocityX(-speed);
    }
    if (e.getKeyCode() == KeyEvent.VK_RIGHT)
    {
      keyMap.put("right", true);
      object.setVelocityX(speed);
    }
    if (e.getKeyCode() == KeyEvent.VK_UP)
    {
      keyMap.put("up", true);
      jump();
      keyMap.remove("up");
    }
    if (e.getKeyCode() == KeyEvent.VK_DOWN)
    {
      keyMap.put("down", true);
      object.setVelocityY(speed);
    }
    if (e.getKeyCode() == KeyEvent.VK_SPACE)
    {
      keyMap.put("space", true);
      jump();
      keyMap.remove("space");
    }
    if(e.getKeyCode() == KeyEvent.VK_ENTER){
      System.exit(0);
    }
    repaint();
  }

  public void keyReleased(KeyEvent e) {
    if (e.getKeyCode() == KeyEvent.VK_LEFT)
    {
      keyMap.put("left", false);
      object.setVelocityX(0.0);
    }
    if (e.getKeyCode() == KeyEvent.VK_RIGHT)
    {
      keyMap.put("right", false);
      object.setVelocityX(0.0);
    }
    // if (e.getKeyCode() == KeyEvent.VK_UP)
    // {
    //   keyMap.put("up", false);
    //   object.setVelocityY(0.0);
    // }
    // if (e.getKeyCode() == KeyEvent.VK_DOWN)
    // {
    //   keyMap.put("down", false);
    //   object.setVelocityY(0.0);
    // }
    if (e.getKeyCode() == KeyEvent.VK_SPACE)
    {
      keyMap.put("space", false);
    }
    repaint();
  }

  public void keyTyped(KeyEvent e) {
    //no code needed here
  }

  public void run() {
    while(true) {
      //roughly 60 fps
      Timer.sleep(10);
      repaint();
    }
  }
}