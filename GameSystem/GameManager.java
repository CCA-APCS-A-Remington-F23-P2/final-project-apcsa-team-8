import javax.swing.JFrame;
import java.awt.Component;
import Common.*;

public class GameManager extends JFrame{
  public static final int WIDTH = 640;
  public static final int HEIGHT = 480;

  public static final Collidable BOTTOM_EDGE = new Collidable(0, HEIGHT, WIDTH, 1);
  public static final Collidable TOP_EDGE = new Collidable(0, -1, WIDTH, 1);
  public static final Collidable LEFT_EDGE = new Collidable(-1, 0, 1, HEIGHT);
  public static final Collidable RIGHT_EDGE = new Collidable(WIDTH, 0, 1, HEIGHT);

  public GameManager() {
    super("Lunar");
    GameContainer game = new GameContainer();
    getContentPane().add(game);
    setSize(640,480);
    setResizable(false);
    setDefaultCloseOperation(
      JFrame.EXIT_ON_CLOSE);
    ((Component) game).setFocusable(true);
    pack();
    setVisible(true);
  }
}
