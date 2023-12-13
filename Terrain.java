import java.awt.Color;
import java.awt.Graphics;
import java.io.File;
import java.net.URL;
import java.awt.Image;
import javax.imageio.ImageIO;

public class Terrain {

  private Level level1 = new Level("lvl.txt");

  public void draw(Graphics window){
    for(TerrainBlock block : level1.getBlockArray()){
      block.draw(window);
    }
  }
}