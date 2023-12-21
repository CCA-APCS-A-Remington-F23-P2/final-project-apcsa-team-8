import java.util.*;
import java.io.*;
import java.awt.*;

public class Level {
  private String level;
  private ArrayList<Integer> map = new ArrayList<Integer>();
  public static HashMap<Integer, Color> blocks = new HashMap<Integer, Color>();

  File file;

  private int blocksPerRow;
  private int blockSize;

  public Level(String level){
    this.level = "TerrainGeneration/Levels/" + level;
    file = new File(this.level);

    init();
    parseLevel();

    System.out.println("Blocks Per Row: " + blocksPerRow);
    System.out.println("Window Width: " + GameManager.WIDTH);
    System.out.println("Block Width Precentage: " + (1.0 / (GameManager.WIDTH / blocksPerRow)));
  }

  public void init(){
    try{
      Scanner scan = new Scanner(file);
      String firstRow = scan.nextLine();
      blocksPerRow = firstRow.split(",").length;
      blockSize = GameManager.WIDTH / (blocksPerRow) + 2;
      scan.close();
    } catch(Exception e) {
      e.printStackTrace();
    }

    blocks.put(1, Color.BLACK);
    blocks.put(2, Color.GRAY);
  }

  public void parseLevel(){
    try{
      Scanner parse = new Scanner(new File(level));
      parse.useDelimiter(",");
      
      while(parse.hasNextLine()){
        String line = parse.nextLine();
        String[] splitLine = line.split(",");
        for(int i = 0; i < splitLine.length; i++){
          map.add(Integer.parseInt(splitLine[i]));
        }
      }
      parse.close();
    } catch(Exception e){}
  }

  public ArrayList<Integer> getMap() {
    return map;
  }

  public int getBlockSize(){
    return blockSize;
  }

  public int getBlocksPerRow(){
    return blocksPerRow;
  }

  public ArrayList<Block> asBlockMap(){
    ArrayList<Block> blockMap = new ArrayList<Block>();
    int blockSize = getBlockSize();
    int blocksPerRow = getBlocksPerRow();
    int blocksPerCol = map.size() / blocksPerRow;

    for(int row = 0; row < blocksPerCol; row++){
      for(int col = 0; col < blocksPerRow; col++){
        int type = map.get(row * blocksPerRow + col);

        
        Block b = new Block(
            col * blockSize, 
            row * blockSize, 
            blockSize, 
            blockSize,
            blocks.get(type),
            type);
        blockMap.add(b);
      }
    }
    return blockMap;
  }

  public String toString(){

    int count = 0;
    String output = "";
    for(int i : map){
      output += i + " ";
      count++;
      if(count == blocksPerRow){
        output += "\n";
        count = 0;
      }
    }
    return output;
  }
}