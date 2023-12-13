import java.awt.Color;
import java.awt.Graphics;
import java.io.File;
import java.net.URL;
import java.awt.Image;
import javax.imageio.ImageIO;
import java.util.*;

public class Moveable extends Collidable{

  private int xSpeed;
  private double ySpeed;

  private String[] movements;

  public Moveable(int x, int y, int w, int h, int s){
    super(x, y, w, h);
  }

  public Moveable(int x, int y, int w, int h, int s, String image){
    super(x, y, w, h, image);
    this.xSpeed = s;
  }

  public Moveable(){
    this(50, 50, 10, 10, 1);
    setMovements("up", "down", "left", "right");
  }

  public void setMovements(String... movments){
    this.movements = movments;
  }

  public String[] getMovements(){
    return this.movements;
  }

  public void setSpeed(int x, int y){
    this.xSpeed = x;
    this.ySpeed = y;
  }

  public void setXSpeed(int x){
    this.xSpeed = x;
  }

  public void setYSpeed(double y){
    this.ySpeed = y;
  }

  public double getYSpeed(){
    return this.ySpeed;
  }

  public int getXSpeed(){
    return this.xSpeed;
  }

  public boolean touchingEdge() {
    return getLeft() <= 0 || getRight() >= GameManager.WIDTH || getTop() <= 0 || getBottom() >= GameManager.HEIGHT; 
  }

  public boolean touchingFloor() {
    return getBottom() >= 450; 
  }

  public boolean pastEdge() {
    return getRight() <= 0 || getLeft() >= GameManager.WIDTH || getBottom() <= 0 || getTop() >= GameManager.HEIGHT;
  }

  public void move(String direction, Floor floor){
    direction.toLowerCase();

    for(String movement : movements){

      if(!direction.contains(movement)) continue;
      else if(movement.contains("down")){
        ySpeed = -5;
      }

      if(movement.contains("left")){
        xSpeed = -2;
      }
      else if(movement.contains("right")){
        xSpeed = 2;
      }
    }
  }

  public void jump(){
    if(ySpeed <= 0){
      ySpeed = 5;
    }
  }

  public void movementIterator(){
    int prevX = getX();
    int prevY = getY();

    setX(getX() + xSpeed);
    setY((int)(getY() - ySpeed));

    setXSpeed(0);

    if(touchingEdge() || touchingFloor()){
      setPos(prevX, prevY);
      setYSpeed(0);
    }
    else{
      if(ySpeed > 3) ySpeed -= 0.3;
      else ySpeed -= 0.05;
    }
  }

  @Override
  public void draw(Graphics window){
    super.draw(window);
    movementIterator();
  }
}