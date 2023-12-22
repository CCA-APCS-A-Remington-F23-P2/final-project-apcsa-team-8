import javax.swing.JPanel;


import java.awt.Graphics2D;
import java.awt.image.*;
import java.awt.event.*;
import java.awt.*;
import java.util.*;
import java.util.function.*;
import Common.*;
import TerrainGeneration.*;
import GameStates.*;
import static java.lang.Character.*;


public class GameContainer extends JPanel implements KeyListener, Runnable {


  private BufferedImage image;
  private Graphics2D g;

  public static final int WIDTH = 320;
  public static final int HEIGHT = 240;
  public static final int SCALE = 2;

  private Thread  thread;
  private boolean running;
  private int FPS = 60;
  private long targetTime = 1000 / FPS;

  private GameStateManager gsm;


  public GameContainer() {
    super();
    setPreferredSize(new Dimension(WIDTH * SCALE, HEIGHT * SCALE));
    setFocusable(true);
    requestFocus();
  }

  public void addNotify() {
    super.addNotify();
    if(thread == null){
      thread = new Thread(this);
      addKeyListener(this);
      thread.start();
    }
  }

  private void init() {
    image = new BufferedImage(
      WIDTH, 
      HEIGHT, 
      BufferedImage.TYPE_INT_RGB);
    g = (Graphics2D) image.getGraphics();
    running = true;

    gsm = new GameStateManager();
  }

  public void run() {
    init();

    Timer timer = new Timer(targetTime);

    while(running){

      timer.reset();

      update();
      draw();
      drawToScreen();

      timer.sleep(targetTime);
    }
  }

  public void update() {
    gsm.update();
  }

  public void draw() {
    gsm.draw(g);
  }

  private void drawToScreen(){
    Graphics g2 = getGraphics();
    g2.drawImage(image, 0, 0, WIDTH * SCALE, HEIGHT * SCALE, null);
    g2.dispose();
  }

  public void keyTyped(KeyEvent key){

  }

  public void keyPressed(KeyEvent key){
    gsm.keyPressed(key.getKeyCode());
  }

  public void keyReleased(KeyEvent key){
    gsm.keyReleased(key.getKeyCode());
  }
}