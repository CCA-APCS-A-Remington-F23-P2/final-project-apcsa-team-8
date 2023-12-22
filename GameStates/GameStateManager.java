import java.util.*;
import java.awt.*;

public class GameStateManager {

  private ArrayList<GameState> states;
  private int currentState;

  public enum State {
    MENU(0),
    LEVEL_SELECT(1),
    LEVEL1(2),
    ENDSCREEN(3);

    public int state;

    private State(int state) {
      this.state = state;
    }
  }

  public GameStateManager() {

    states = new ArrayList<GameState>();

    currentState = 0;
    states.add(new MenuState(this));
    states.add(new LevelSelectState(this));
    states.add(new Level1State(this));
    states.add(new LevelEndScreen(this));
  }

  public void setState(int state){
    currentState = state;

    states.get(currentState).init();
  }

  public void update() {
    states.get(currentState).update();
  }

  public void draw(Graphics2D graph) {
    states.get(currentState).draw(graph);
  }

  public void keyPressed(int key) {
   states.get(currentState).keyPressed(key); 
  }

  public void keyReleased(int key) {
    states.get(currentState).keyReleased(key);
  }
}