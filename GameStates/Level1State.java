import java.awt.*;
import java.awt.event.KeyEvent;
import Sprites.*;

public class Level1State extends GameState {
  public static Terrain terrain;
  public static Player player;
  public static AsteroidBelt asteroidBelt;

  public Level1State(GameStateManager gsm) {
    this.gsm = gsm;
    init();
  }

  public void init() {
    terrain = new Terrain();
    terrain.setSelectedLevel(0);
    player = new Player(100, 50, 8, 14);
    asteroidBelt = new AsteroidBelt(2, 3);
  }

  public void update() {
    
  }

  public void draw(Graphics2D g) {
    terrain.draw(g);
    player.update(g, terrain.getTerrain());
    asteroidBelt.update(g, terrain.getTerrain(), player);
  }

  public void keyPressed(int k) {
    switch(k){
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
  }

  public void keyReleased(int k) {
    switch(k) {
      case KeyEvent.VK_LEFT:
        player.setVelocityX(0.0);
        break;
      case KeyEvent.VK_RIGHT:
        player.setVelocityX(0.0);
        break;
    }
  }
}