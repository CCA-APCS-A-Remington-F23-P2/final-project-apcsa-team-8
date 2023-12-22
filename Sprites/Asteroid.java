import java.io.File;
import java.net.URL;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import javax.imageio.ImageIO;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.*;

import Common.*;
import TerrainGeneration.*;


public class Asteroid extends Moveable{
  private int s;
  public static int id = 0;

  public Asteroid(int s){
    super(0, 0, 10, 10, new Vector(0,0));
    this.s = s;
    setRandPos();
    setRandVelocity();
    setImage("asteroid.png");
    id++;
  }

  public void setRandPos(){
    int x = (int)(Math.random() * GameManager.WIDTH);

    getPosition().setX(x);
    getPosition().setY(-20);
  }

  public void setRandVelocity(){
    int angle = (int)(Math.random() * 50) + 20;

    getVelocity().setAngle(angle);
    getVelocity().setMagnitude(s);
    if(getVelocity().getX() > 0) reflectImageToBackwards();
    else reflectImageToForwards();
  }

  public boolean collidesWithSides(){
    return collidesWith(GameManager.RIGHT_EDGE) ||
           collidesWith(GameManager.LEFT_EDGE) || 
           collidesWith(GameManager.BOTTOM_EDGE) ||
           outOfBounds();
  }

  public void reset(){
    setRandPos();
    setRandVelocity();
  }

  public void update(Graphics window, ArrayList<Block> blocks, Player player){
    super.update(window, blocks);
    if(collidesWith(player)){
      player.removeLife();
      reset();
    }
    if(collidesWithSides() || !validPose(getVelocity(), blocks)){
      reset();
    }
  }

  public String toString() {
    return "Asteroid ID: " + id + " Position: " + getPosition() + " Velocity: " + getVelocity();
  }
}