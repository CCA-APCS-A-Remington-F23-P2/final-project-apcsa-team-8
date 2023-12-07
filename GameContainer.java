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


public class GameContainer extends Canvas implements KeyListener, Runnable {

  HashMap<String, Boolean> keyMap = new HashMap<String, Boolean>();
  private BufferedImage back;

  public GameContainer() {
    this.addKeyListener(this);
    new Thread(this).start();
    setVisible(true);
    Collidable.setFrameSize(getWidth(), getHeight());


    

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

    gameWindow.setColor(Color.RED);
    gameWindow.fillRect(0, 0, getWidth(), getHeight());















    

    twoDGraph.drawImage(back, null, 0, 0);
  }

  public void keyPressed(KeyEvent e) {

    if (e.getKeyCode() == KeyEvent.VK_LEFT)
    {
      keyMap.put("left", true);
    }
    if (e.getKeyCode() == KeyEvent.VK_RIGHT)
    {
      keyMap.put("right", true);
    }
    if (e.getKeyCode() == KeyEvent.VK_UP)
    {
      keyMap.put("up", true);
    }
    if (e.getKeyCode() == KeyEvent.VK_DOWN)
    {
      keyMap.put("down", true);
    }
    if (e.getKeyCode() == KeyEvent.VK_SPACE)
    {
      keyMap.put("space", true);
    }
    repaint();
  }

  public void keyReleased(KeyEvent e) {
    if (e.getKeyCode() == KeyEvent.VK_LEFT)
    {
      keyMap.put("left", false);
    }
    if (e.getKeyCode() == KeyEvent.VK_RIGHT)
    {
      keyMap.put("right", false);
    }
    if (e.getKeyCode() == KeyEvent.VK_UP)
    {
      keyMap.put("up", false);
    }
    if (e.getKeyCode() == KeyEvent.VK_DOWN)
    {
      keyMap.put("down", false);
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
      Timer.sleep(10);
      repaint();
    }
  }
}