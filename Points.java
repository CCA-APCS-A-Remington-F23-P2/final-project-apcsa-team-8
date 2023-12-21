import java.io.File;
import java.net.URL;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import javax.imageio.ImageIO;
import java.util.ArrayList;

public class Points extends MovingThing{
private Image image;
private ArrayList<MovingThing> pointsArray;

private int speed = 1;

  public Points(int x, int y, int w, int h){
  super(x, y, w, h);
  pointsArray = new ArrayList<MovingThing>();
  try{
  URL url = getClass().getResource("point.png");
  image = ImageIO.read(url);
  }
  catch(Exception e){
  
  }
  }

  public void addPoints(MovingThing pointsObject){
    pointsArray.add(pointsObject);
  }

  public void removePoints(MovingThing pointsObject){
    pointsArray.remove(pointsObject);
  }

  public void setSpeed(int s){
    speed = s;
  }

  public int getSpeed(){
    return speed;
  }

  public void shuffle(){
    int size = pointsArray.size();
    if(size>1){
      MovingThing lastElement = pointsArray.get(size-1);
      
      for(int i = size - 1; i>0; i--){
        pointsArray.get(i).setPos(pointsArray.get(i-1).getX(), pointsArray.get(i-1).getY());
      }
      pointsArray.get(0).setPos(lastElement.getX(), lastElement.getY());
    }
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

  public void setRandPos(){
    int x = (int)(Math.random() * 801);

    setX(x);
    setY(0);
  }

  public void draw(Graphics window){
    window.drawImage(image, getX(), getY(), getWidth(), getHeight(), null);
  }


}