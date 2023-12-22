import java.util.ArrayList;
import TerrainGeneration.*;
import java.awt.Graphics;
import java.awt.Graphics2D;

public class AsteroidBelt {


  private ArrayList<Asteroid> asteroids = new ArrayList<Asteroid>();

  public AsteroidBelt(int num, int speed){
    for(int i = 0; i < num; i++){
      asteroids.add(new Asteroid(speed));
    }
  }

  public void update(Graphics window, ArrayList<Block> blocks, Player player){
    for(Asteroid a : asteroids){
      a.update(window, blocks, player);
    }
  }

  public String toString() {
    String output = "";
    for(Asteroid a : asteroids){
      output += a.toString() + "\n";
    }
    return output;
  }
}