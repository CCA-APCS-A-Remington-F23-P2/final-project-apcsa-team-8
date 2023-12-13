import java.awt.Color;
import java.awt.Graphics;
import java.io.File;
import java.net.URL;
import java.awt.Image;
import javax.imageio.ImageIO;

public class Terrain {

  private Level level1 = new Level("lvl.txt");
  private TerrainBlock[][] level1Terrain;

  public Terrain(){
    level1Terrain = level1.getTerrain();
    System.out.println(level1Terrain.length);
    for(TerrainBlock[] row : level1Terrain){
      for(TerrainBlock block : row){
        System.out.println("Created: " + block);
      }
    }
  }

  public void draw(Graphics window){
    for(TerrainBlock[] row : level1Terrain){
      for(TerrainBlock block : row){
        block.draw(window);
      }
    }
  }
}