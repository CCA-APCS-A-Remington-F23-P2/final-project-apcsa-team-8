import java.awt.*;
public class TerrainBlock extends Collidable {

  private String name;

  public TerrainBlock(String name, int x, int y){
    super(x, y, 20, 20);
    this.name = name;
  }

  public TerrainBlock(String name, int x, int y, String image){
    super(x, y, 20, 20, image);
  }

  public TerrainBlock(String name, int x, int y, Color color){
    super(x, y, 20, 20, color);
  }

  public void setName(String n){
    this.name = n;
  }

  public String getName(){
    return this.name;
  }
}