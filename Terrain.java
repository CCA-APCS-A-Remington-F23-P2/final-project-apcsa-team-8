import java.awt.Color;
import java.awt.Graphics;
import java.io.File;
import java.net.URL;
import java.util.*;
import java.awt.Image;
import javax.imageio.ImageIO;


public class Terrain {

  private ArrayList<Level> levels = new ArrayList<Level>();
  private int selectedLevel = 0;

  public Terrain(){
    initLevelMap();
  }

  public void initLevelMap(){
    levels.add(new Level("Level1.txt"));
    System.out.println(levels.size());
    System.out.println(levels.get(selectedLevel));
  }

  public void setSelectedLevel(int level){
    selectedLevel = level;
  }

  public void draw(Graphics window){
    try{
      ArrayList<Block> map = levels.get(selectedLevel).asBlockMap();
      for(Block b : map){
        b.draw(window);
      }
    } catch(Exception e) {
      e.getStackTrace();
    }
  }

  public ArrayList<Block> getTerrain(){
    return levels.get(selectedLevel).asBlockMap();
  }
}