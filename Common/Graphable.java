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
  private boolean reflected = false;

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

  public void reflectImageToForwards(){
    if(image == null) return;
    reflected = false;
  }  

  public void reflectImageToBackwards(){
    if(image == null) return;
    reflected = true;
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

      int xOffset = 8;
      int yOffset = 1;

      int width = (size.getX() + 2*xOffset);
      int height = size.getY() + 2*yOffset;;

      int x = position.getX() - xOffset + (reflected ? width: 0);
      int y = position.getY() - yOffset;

      window.drawImage(image, x, y, (reflected ? -width : width), height, null);
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