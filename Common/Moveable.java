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

  public boolean collidesWith(ArrayList<Block> blocks){
    for(Block b: blocks){
      if(b.getType() == 1) continue;
      if(b.getType() == 2){
        if(collidesWith(b)) return true;
      }
    }
    return false;
  }

  public boolean collidesWith(Collidable other) {
    double tw = this.getSize().getX();
    double th = this.getSize().getY();
    double rw = other.getSize().getX();
    double rh = other.getSize().getY();

    if(rw <= 0 || rh <= 0 || tw <= 0 || th <= 0) {
      return false;
    }

    double tx = this.getPosition().getX();
    double ty = this.getPosition().getY();
    double rx = other.getPosition().getX();
    double ry = other.getPosition().getY();

    tw += tx;
    th += ty;
    rw += rx;
    rh += ry;

    return ((rw < rx || rw > tx) &&
            (rh < ry || rh > ty) &&
            (tw < tx || tw > rx) &&
            (th < ty || th > ry));
  }

  public void updatePose(ArrayList<Block> blocks){
    //double prevPosition = getPosition();
    Coordinate prevPositon = this.getPosition();
    position.setX(position.getX() + this.velocity.getX());
    position.setY(position.getY() + this.velocity.getY());
    velocity.append(gravity);

    if(collidesWith(blocks)){
      this.setPosition(prevPositon);
      setVelocityY(0);
    }
  }
  
  public void update(Graphics window, ArrayList<Block> blocks){
    updatePose(blocks);
    draw(window);
  }
}