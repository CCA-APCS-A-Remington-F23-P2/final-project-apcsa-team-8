import java.awt.Color;
import java.awt.Graphics;
import java.io.File;
import java.net.URL;
import java.awt.Image;
import javax.imageio.ImageIO;

public class Collidable {
  private double x;
  private double y;
  private int width;
  private int height;

  private Image image;
  private Color color;

  public Collidable(double x, double y, int w, int h) {
    this.x = x;
    this.y = y;
    this.width = w;
    this.height = h;
  }

  public Collidable(double x, double y, int w, int h, String imageUrl) {
    this(x, y, w, h);
    setImage(imageUrl);
  }

  public Collidable(double x, double y, int w, int h, Color color) {
    this(x, y, w, h);
    setColor(color);
  }

  public void setImage(String imageName) {
    try {
      URL url = getClass().getResource(imageName);
      image = ImageIO.read(url);
    } catch (Exception e) {
      System.out.println("Error loading image. /nCould not load: " + imageName);
    }
  }

  public void setColor(Color color) {
    this.color = color;
  }

  public Color getColor() {
    return color;
  }

  public double getLeft() {
    return this.x;
  }

  public double getRight() {
    return this.x + this.width;
  }

  public double getTop() {
    return this.y;
  }

  public double getBottom() {
    return this.y + this.height;
  }

  public void setPos(double x, double y) {
    this.x = x;
    this.y = y;
  }

  public void setX(double x) {
    this.x = x;
  }

  public void setY(double y) {
    this.y = y;
  }

  public double getX() {
    return x; // finish this method
  }

  public double getY() {
    return y; // finish this method
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

  public void draw(Graphics window) {
    if (image != null) {
      // generate inputed image
      window.drawImage(image, (int)(getX() - GameContainer.camera.getX()), (int)(getY() - GameContainer.camera.getY()), getWidth(), getHeight(), null);
    }
    else {
      // generate box if no image is provided
      window.setColor(color != null ? color : Color.RED);
      window.fillRect((int)(getX() - GameContainer.camera.getX()), (int)(getY() - GameContainer.camera.getY()), getWidth(), getHeight());
    }
  }

  public void clear(Graphics window) {
    window.clearRect((int)getX(), (int)getY(), getWidth(), getHeight());
  }

  public boolean didCollide(Collidable other) {
    // int thisBottom = this.getY() + this.getHeight();
    // int thisRight = this.getX() + this.getWidth();
    // int otherBottom = other.getY() + other.getHeight();
    // int otherRight = other.getX() + other.getWidth();

    // if (thisBottom < other.getY() || this.getY() > otherBottom || thisRight < other.getX()
    //     || this.getX() > otherRight) {
    //   return false;
    // } else {
    //   return true;
    // }
    return false;
  }
}