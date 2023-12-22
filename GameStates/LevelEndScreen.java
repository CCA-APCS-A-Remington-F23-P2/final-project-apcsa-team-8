import java.awt.*;
import java.awt.event.KeyEvent;

public class LevelEndScreen extends GameState {

  private Collidable bg;

  private int currentChoice = 0;
  private String[] options = { "Return", "Home" };

  private Color color;
  private Color titleColor;
  private Font font;
  private Font titleFont;
  

  public LevelEndScreen(GameStateManager gsm) {
    this(gsm, null);
  }

  public LevelEndScreen(GameStateManager gsm, GameState next) {
    this.gsm = gsm;

    try{
      bg = new Collidable(0, 0, GameManager.WIDTH, GameManager.HEIGHT, Color.BLUE);

      titleColor = new Color(128, 0,0);
      titleFont = new Font("Arial", Font.BOLD, 18);
      font = new Font("Arial", Font.PLAIN, 12);
    } catch(Exception e) {
      e.printStackTrace();
    }
  }

  public void init() {

  }

  public void update() {
    //bg.update();
  }

  public void draw(Graphics2D g) {
    bg.draw(g);

    g.setColor(titleColor);
    g.setFont(titleFont);
    g.drawString("Score: " + Player.score, 75, 75);
    g.drawString("HighScore: " + Player.getHighScore(), 75, 100);

    g.setFont(font);
    for(int i = 0; i < options.length; i++){
      if(i == currentChoice) g.setColor(Color.WHITE);
      else g.setColor(Color.RED);

      g.drawString(options[i], 145, 140 + i * 15);
    }

  }

  private void select() {
    if(currentChoice == 0){
      gsm.setState(1);
    }
    if(currentChoice == 1){
      gsm.setState(0);
    }
  }

  public void keyPressed(int k) {
    if(k == KeyEvent.VK_ENTER) {
      select();
    }
    if(k == KeyEvent.VK_UP) {
      currentChoice--;
      if(currentChoice < 0) currentChoice = options.length - 1;
    }
    if(k == KeyEvent.VK_DOWN) {
      currentChoice++;
      if(currentChoice > options.length - 1) currentChoice = 0;
    }
  }

  public void keyReleased(int k) {

  }
}