import java.awt.Color;
import java.awt.Graphics;
import java.io.File;
import java.net.URL;
import java.awt.Image;
import javax.imageio.ImageIO;

public class Collidable {
  private int x;
  private int y;
  private int width;
  private int height;

  private Image image;

  public static int frameWidth;
  public static int frameHeight;

  public Collidable(int x, int y, int w, int h){
    this.x = x;
    this.y = y;
    this.width = w;
    this.height = h;
  }

  public Collidable(int x, int y, int w, int h, String imageUrl){
    this(x, y, w, h);
    setImage(imageUrl);
  }

  public void setImage(String imageName){
    try{
      URL url = getClass().getResource(imageName);
      image = ImageIO.read(url);
    }
    catch(Exception e){
      System.out.println("Error loading image. /nCould not load: " + imageName);
    }
  }

  public int getLeft() {
    return this.x;
  }

  public int getRight() {
    return this.x + this.width;
  }

  public int getTop() {
    return this.y;
  }

  public int getBottom() {
    return this.y + this.height;
  }

  public void setPos( int x, int y) {
    this.x = x;
    this.y = y;
  }

  public void setX(int x) {
    this.x = x;
  }

  public void setY(int y) {
    this.y = y;
  }

  public int getX() {
    return x;   //finish this method
  }

  public int getY() {
    return y;  //finish this method
  }

  public void setWidth(int w) {

    width = w;
  }

  public void setHeight(int h) {
    height = h;
  }

  public int getWidth() {
    return width;
  }

  public int getHeight() {
    return height;
  }

  public static void setFrameSize(int w, int h){
    Collidable.frameWidth = w;
    Collidable.frameHeight = h;
  }

  public void draw(Graphics window){
    if(image != null){
      //generate inputed image
      window.drawImage(image, getX(), getY(), getWidth(), getHeight(), null);
    }
    else{
      //generate red bounding box if no image is provided
      window.setColor(Color.YELLOW);
      window.fillRect(getX(), getY(), getWidth(), getHeight());
    }
  }

  public void clear(Graphics window){
    window.clearRect(getX(), getY(), getWidth(), getHeight());
  }


  public boolean didCollide(Collidable other){
    int thisBottom = this.getY() + this.getHeight();
    int thisRight = this.getX() + this.getWidth();
    int otherBottom = other.getY() + other.getHeight();
    int otherRight = other.getX() + other.getWidth();


    if (thisBottom < other.getY() || this.getY() > otherBottom || thisRight < other.getX() || this.getX() > otherRight) {
      return false;
    }
    else{
      return true;
    }
  }
}