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
    super("GameManager");
    setSize(WIDTH,HEIGHT);

    GameContainer game = new GameContainer();
    ((Component) game).setFocusable(true);

    getContentPane().add(game);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setVisible(true);
  }
}
