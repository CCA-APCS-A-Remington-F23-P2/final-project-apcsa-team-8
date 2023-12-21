import java.io.File;
import java.net.URL;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import javax.imageio.ImageIO;

public class PowerUp extends Points{
  private Image image;
  private int speed;
  
  public PowerUp(int x, int y, int w, int h){
    super(x, y, w, h);
    try{
      URL url = getClass().getResource("powerup.png");
      image = ImageIO.read(url);
    }
    catch(Exception e){
      
    }
  }

  public PowerUp(int x, int y, int w, int h, int s){
    super(x, y, w, h);
    try{
      URL url = getClass().getResource("powerup.png");
      image = ImageIO.read(url);
    }
    catch(Exception e){

    }
    speed = s;
  }

  public int getSpeed(){
  return speed;
  }
  
  public void setSpeed(int s){
    speed = s;
  }

  public void move(String x){
    if(x.equals("RIGHT")){
      setX(getX() + speed);
      setY(getY() + speed);
    }
    else if (x.equals("LEFT")){
      setX(getX() - speed);
      setY(getY() - speed);
    }
  }
 
}