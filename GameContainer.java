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

  private Terrain terrain;
  private Player player;
  private AsteroidBelt asteroidBelt;

  public GameContainer() {
    this.addKeyListener(this);
    new Thread(this).start();
    setVisible(true);
    
    terrain = new Terrain();
    terrain.setSelectedLevel(1);
    player = new Player(100, 100, 15, 30);
    asteroidBelt = new AsteroidBelt(2, 3);
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
    
    terrain.draw(gameWindow);
    player.update(gameWindow, terrain.getTerrain());
    asteroidBelt.update(gameWindow, terrain.getTerrain(), player);

    
    twoDGraph.drawImage(back, null, 0, 0);
  }

  public void keyPressed(KeyEvent e) {

    switch(e.getKeyCode()){
      case KeyEvent.VK_UP:
        player.jump();
        break;
      case KeyEvent.VK_DOWN:
        player.fall();
        break;
      case KeyEvent.VK_LEFT:
        player.walkBackwards();
        break;
      case KeyEvent.VK_RIGHT:
        player.walkForwards();
        break;
      case KeyEvent.VK_SPACE:
        player.jump();
        break;
      case KeyEvent.VK_ESCAPE:
        System.exit(0);
        break;
      case KeyEvent.VK_1:
        terrain.setSelectedLevel(0);
        break;
      case KeyEvent.VK_2:
        terrain.setSelectedLevel(1);
        break;
    }
    repaint();
  }

  public void keyReleased(KeyEvent e) {
    if (e.getKeyCode() == KeyEvent.VK_LEFT)
    {
      keyMap.put("left", false);
      player.setVelocityX(0.0);
    }
    if (e.getKeyCode() == KeyEvent.VK_RIGHT)
    {
      keyMap.put("right", false);
      player.setVelocityX(0.0);
    }
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
      Timer.sleep(15);
      repaint();
    }
  }
}