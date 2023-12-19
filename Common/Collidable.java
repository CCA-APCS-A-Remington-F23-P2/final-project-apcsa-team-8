import java.awt.Color;
import java.awt.Graphics;
import java.io.File;
import java.net.URL;
import java.awt.Image;
import javax.imageio.ImageIO;

public class Collidable extends Graphable{

  public Collidable(int x, int y, int w, int h, String imageUrl){
    super(x, y, w, h);
    setImage(imageUrl);
  }

  public Collidable(int x, int y, int w, int h, Color color){
    super(x, y, w, h);
    setColor(color);
  }

  public Collidable(int x, int y, int w, int h){
    super(x, y, w, h);
  }
}