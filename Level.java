import java.util.*;
import java.io.*;
import java.awt.*;

public class Level {
  private String level;
  private HashMap<Integer, TerrainBlock> blockList = new HashMap<Integer, TerrainBlock>();
  private ArrayList<Integer> rawInfo = new ArrayList<Integer>();
  
  public Level(String level){
    this.level = level;
    initBlockList();
    parseInfo();
    System.out.println(Arrays.toString(rawInfo.toArray()));
  }

  public void initBlockList(){
    blockList.put(1, new TerrainBlock("Grass", 1, 1, Color.GREEN));
    blockList.put(2, new TerrainBlock("Dirt", 2, 1, Color.BLACK));
    blockList.put(3, new TerrainBlock("Stone", 3, 1, Color.GRAY));
    blockList.put(4, new TerrainBlock("Water", 4, 1, Color.BLUE));
    blockList.put(5, new TerrainBlock("Sand", 5, 1, Color.YELLOW));
  }

  public void parseInfo(){
    try{
      Scanner parse = new Scanner(new File(level));
      parse.useDelimiter(",");
      while(parse.hasNextLine()){
        while(parse.hasNextInt()){
          rawInfo.add(parse.nextInt());
        }
        parse.next();
      }
      parse.close();
    } catch(Exception e){}
  }

  public TerrainBlock[][] getTerrain(){
    int windowHeight = GameManager.HEIGHT / 20;
    
    TerrainBlock[][] terrain = new TerrainBlock[5][40];
    System.out.println(rawInfo.size());
    System.out.println(terrain.length);
    System.out.println(terrain[0].length);
    //new TerrainBlock[windowHeight][rawInfo.size() / windowHeight];
    
    for(int r = 0; r < terrain.length; r++){
      for(int c = 0; c < terrain[0].length; c++){
        int blockId = rawInfo.get(r * terrain[0].length + c);
        terrain[r][c] = blockList.get(blockId).clone(c * 20, r * 20);
      }  
    }
    
    return terrain;
  }
}