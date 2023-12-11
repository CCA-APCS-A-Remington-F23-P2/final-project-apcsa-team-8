import java.util.*;
import java.io.*;

public class Level {
  String level;
  
  public Level(String level){
    this.level = level;
  }

  public List<String> getLevelArray(){
    List<String> out = new ArrayList<String>();
    Scanner parse = new Scanner(new File(level));
    while(parse.hasNextLine()){
      String line = parse.nextLine();
      for(String s : line.split(",")){
        out.add(s);
      }
    }
    return out;
  }
}