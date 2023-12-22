import java.awt.*;
import java.awt.event.KeyEvent;

public class MenuState extends GameState {

  private Collidable bg;

  private int currentChoice = 0;
  private String[] options = { "Start", "Level Select", "Quit"};

  private Color color;
  private Color titleColor;
  private Font font;
  private Font titleFont;

  public MenuState(GameStateManager gsm) {
    this.gsm = gsm;

    try{
      bg = new Collidable(0, 0, GameManager.WIDTH, GameManager.HEIGHT, "GameStates/stars.jpg");

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
    g.drawString("Choose Your Adventure", 45, 75);

    g.setFont(font);
    for(int i = 0; i < options.length; i++){
      if(i == currentChoice) g.setColor(Color.WHITE);
      else g.setColor(Color.RED);

      g.drawString(options[i], 135, 140 + i * 15);
    }

  }

  private void select() {
    if(currentChoice == 0){
      gsm.setState(2);
    }
    if(currentChoice == 1){
      gsm.setState(1);
    }
    if(currentChoice == 2){
      System.exit(0);
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