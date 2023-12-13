import javax.swing.JFrame;
import java.awt.Component;

public class GameManager extends JFrame{
  public static final int WIDTH = 800;
  public static final int HEIGHT = 600;

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
