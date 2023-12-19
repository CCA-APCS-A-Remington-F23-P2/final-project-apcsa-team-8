import java.io.File;
import java.net.URL;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import javax.imageio.ImageIO;


public class Character extends MovingThing{
  private int lives;
  private int score;
  private static int HIGH_SCORE;

  private Image image;
  private int speed;

  public Character(){
    this(10,10,10,10,10);
  }

  public Character(int x, int y){
    this(x, y, 10,10,10);
  }

  public Character(int x, int y, int w, int h, int s){
    super(x, y, w, h);
    speed = s;
    lives = 3;
    score = 0;
    try{
      URL url = getClass().getResource("standingStarman.png");
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

  public void setImage(Image i){
    image = i;
  }

  public Image getImage(){
    return image;
  }

  public void setScore(int s){
    score = s;
  }

  public void setHIGHScore(int s){
      HIGH_SCORE = s;
  }

  public int getHighScore(){
      return HIGH_SCORE;
  }

  public int getScore(){
    return score;
  }

  public void setLives(int L){
    lives = L;
  }

  public int getLives(){
    return lives;
  }

  public void removeLife(){
    if(getLives() > 0){
      lives--;
    }
  }

  public void move(String direction){
    if(direction.equals("LEFT")){
      setX(getX() - speed);
    }
    else if (direction.equals("RIGHT")){
      setX(getX() + speed);
  
    }
    else if(direction.equals("UP")){
      setY(getY() - speed);
  
    }
    else if(direction.equals("DOWN"))
      setY(getY() + speed);
  }

public void draw(Graphics window){
  window.drawImage(image, getX(), getY(), getWidth(), getHeight(), null);
}

  public String toString(){
    return super.toString() + " " + getSpeed();
  }

}