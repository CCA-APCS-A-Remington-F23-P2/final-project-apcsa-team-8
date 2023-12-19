import java.io.File;
import java.net.URL;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import javax.imageio.ImageIO;


public class Asteroid extends MovingThing{
  private int speed;
  private Image image;

  public Asteroid(int x, int y, int w, int h, int s){
    super(x, y, w, h);
    speed = s;
    try{
      URL url = getClass().getResource("asteroid.png");
      image = ImageIO.read(url);
    }
    catch(Exception e){

    }
  }

  public void setSpeed(int s){
    speed = s;
  }

  public int getSpeed(){
    return speed;
  }

  public void move(String s){
    if(s.equals("RIGHT")){
      setX(getX() - speed);
      setY(getY() + speed);
    }
    else if (s.equals("LEFT")){
      setX(getX() + speed);
      setY(getY() + speed);
    }
  }

  //random x value
  public void setRandPos(){
    int x = (int)(Math.random() * 801);
    
    setX(x);
    setY(0);
  }

  public void draw(Graphics window){
    window.drawImage(image, getX(), getY(), getWidth(), getHeight(), null);
  }

  public String toString(){
    return super.toString() + " " + getSpeed();
  }
}