import java.awt.Color;
import java.awt.Graphics;
import java.io.File;
import java.net.URL;
import java.awt.Image;
import javax.imageio.ImageIO;

public class Graphable {
  protected Coordinate position;
  private Coordinate size;

  private Image image;
  private Color color;

  public Graphable(int x, int y, int width, int height) {
    position = new Coordinate(x, y);
    size = new Coordinate(width, height);
  }

  public Graphable(Coordinate position, Coordinate size) {
    this.position = position;
    this.size = size;
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

  public void setColor(Color color){
    this.color = color;
  }

  public Color getColor(){
    return this.color;
  }

  public void setPosition(Coordinate position) {
    this.position = position;
  }
  
  public Coordinate getPosition() {
    return this.position;
  }
  
  public void setSize(Coordinate size) {
    this.size = size;
  }
  
  public Coordinate getSize() {
    return this.size;
  }

  public void draw(Graphics window) {
    if(image != null){
      //generate inputed image
      window.drawImage(image, position.getX(), position.getY(), size.getX(), size.getY(), null);
    }
    else{
      //use color provides is not null, or just default to yellow
      window.setColor(color != null ? color : Color.YELLOW);
      window.fillRect(position.getX(), position.getY(), size.getX(), size.getY());
    }
  }

  public void clear(Graphics window){
    window.clearRect(position.getX(), position.getY(), size.getX(), size.getY());
  }
}