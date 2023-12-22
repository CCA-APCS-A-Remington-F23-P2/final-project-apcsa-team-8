import java.awt.Color;
import java.awt.Graphics;
import java.io.File;
import java.net.URL;
import javax.imageio.ImageIO;
import java.util.*;

import TerrainGeneration.*;

public class Moveable extends Collidable {
  private Vector velocity;
  private Vector gravity = new Vector(0, 0.1);
  private Coordinate prevPosition;
  int collisionTick = 0;
  

  public Moveable(int x, int y, int w, int h, String imageUrl, Vector velocity) {
    super(x, y, w, h, imageUrl);
    this.velocity = velocity;
  }
  
  public Moveable(int x, int y, int w, int h, Color color, Vector velocity) {
    super(x, y, w, h, color);
    this.velocity = velocity;
  }
  
  public Moveable(int x, int y, int w, int h, Vector velocity) {
    super(x, y, w, h);
    this.velocity = velocity;
  }
  
  public void setVelocity(Vector velocity) {
    this.velocity = velocity;
  }
  
  public Vector getVelocity() {
    return this.velocity;
  }

  public void setVelocityX(double velX){
    velocity.setX(velX);
  }

  public void setVelocityY(double velY){
    velocity.setY(velY);
  }

  public void updateGravity(){
    double terminalVelocity = 5;

    if(velocity.getY() < terminalVelocity){
      velocity.append(gravity);
    }
  }

  public Coordinate nextPose(){
    return new Coordinate(
      position.getX() + this.velocity.getX(), 
      position.getY() + this.velocity.getY());
  }

  public boolean validPose(Vector velocity, ArrayList<Block> blocks){

    Collidable futurePose = new Collidable(nextPose(), getSize());
    return !(futurePose.collidesWith(blocks));
  }

  public void updatePose(ArrayList<Block> blocks){
    Coordinate prev = this.getPosition();

    Coordinate next = nextPose();
    updateGravity();

    Collidable futurePose = new Collidable(next, getSize());
    

    if(futurePose.collidesWithX(blocks)){
      collisionTick++;
      next.setX(prev.getX());
      if(collisionTick > 5){
        collisionTick = 0;
        velocity.setX(0);
      }
    }
    if(futurePose.collidesWithY(blocks)){
      setVelocityY(0);
      next.setY(prev.getY());
    }
    
    setPosition(next);
    
    // if(validPose(this.velocity, blocks)){
    //   setPosition(nextPose());
    //   updateGravity();
    // }
  }
  
  public void update(Graphics window, ArrayList<Block> blocks){
    updatePose(blocks);
    draw(window);
  }
}