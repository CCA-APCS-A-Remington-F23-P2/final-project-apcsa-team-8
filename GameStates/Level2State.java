import java.awt.*;
import java.awt.event.KeyEvent;
import Sprites.*;
import Common.*;

public class Level2State extends GameState {
  public static Terrain terrain;
  public static Player player;
  public static AsteroidBelt asteroidBelt;

  public static Collidable powerUp;

  Timer scoreTimer;

  public Level2State(GameStateManager gsm) {
    this.gsm = gsm;
    init();
  }

  public void init() {
    terrain = new Terrain();
    terrain.setSelectedLevel(1);
    player = new Player(50, 150, 8, 14);
    asteroidBelt = new AsteroidBelt(3, 4);
    scoreTimer = new Timer(25000);
    powerUp = new Collidable(50, 50, 10, 10, "GameStates/powerup.png");
  }

  public void update() {
    Collidable futurePose = new Collidable(player.nextPose(), player.getSize());
    if(futurePose.collidesWithType(3, terrain.getTerrain())){
      gsm.setState(3);
    }
    if(player.isDead(terrain.getTerrain())){
      gsm.setState(3);
    }
  }

  public void draw(Graphics2D g) {
    terrain.draw(g);
    powerUp.draw(g);
    player.update(g, terrain.getTerrain());
    asteroidBelt.update(g, terrain.getTerrain(), player);
    g.setColor(Color.RED);
    g.drawString("Score: " + player.getScore(), 160, 20);
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
      case KeyEvent.VK_R:
        gsm.setState(4);
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