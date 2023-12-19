import java.awt.*;
public class Block extends Collidable {

  private int type;

  public Block(int x, int y, int w, int h, Color color, int type) {
    super(x, y, w, h, color);
    this.type = type;
  }

  public void setType(int type){
    this.type = type;
  }

  public int getType(){
    return type;
  }

  public String toString(){
    return "Block: " + getType() + " at (" + position.getX() + ", " + position.getY() + ") as " + getColor();
  }

}