import java.io.File;
import java.net.URL;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import javax.imageio.ImageIO;

public class Points extends Collidable{
private Image image;

  public Points(int x, int y, int w, int h){
  super(x, y, w, h);
  /*try{
  URL url = getClass().getResource("point.png");
  image = ImageIO.read(url);
  }
  catch(Exception e){
  
  }
  */
  }

}