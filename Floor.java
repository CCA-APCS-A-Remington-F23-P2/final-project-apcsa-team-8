import java.awt.Color;
import java.awt.Graphics;
import java.io.File;
import java.net.URL;
import java.awt.Image;
import javax.imageio.ImageIO;
import java.util.*;

public class Floor extends Collidable{

  public Floor(int x, int y, int w, int h){
    super(x, y, w, h);
  }

  public Floor(int x, int y, int w, int h, String img) {
    super(x, y, w, h, img);
  }

  public Floor(){
    this(0, 450, 800, 150);
  }
}