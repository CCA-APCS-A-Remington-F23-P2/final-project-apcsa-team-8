import java.awt.Color;
import java.awt.Graphics;
import java.io.File;
import java.net.URL;
import java.awt.Image;
import javax.imageio.ImageIO;
import java.util.ArrayList;

public class Collidable extends Graphable{

  public Collidable(int x, int y, int w, int h, String imageUrl){
    super(x, y, w, h);
    setImage(imageUrl);
  }

  public Collidable(int x, int y, int w, int h, Color color){
    super(x, y, w, h);
    setColor(color);
  }

  public Collidable(int x, int y, int w, int h){
    super(x, y, w, h);
  }

  public Collidable(Coordinate position, Coordinate size){
    super(position.getX(), position.getY(), size.getX(), size.getY());
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

  public boolean collidesWithX(ArrayList<Block> blocks){
    for(Block b: blocks)
      if(b.getType() == 2 && collidesWithX(b) &&  collidesWith(b))
        return true;
    return false;
  }

  public boolean collidesWithX(Collidable other) {
    double tw = this.getSize().getX();
    double rw = other.getSize().getX();

    if(rw <= 0 || tw <= 0) {
      return false;
    }

    double tx = this.getPosition().getX();
    double rx = other.getPosition().getX();

    return ((rw < tx || rw > rx) &&
            (tw < tx || tw > rx));
  }

  public boolean collidesWithY(ArrayList<Block> blocks){
    for(Block b: blocks)
      if(b.getType() == 2 && collidesWithY(b) && collidesWith(b))
        return true;
    return false;
  }

  public boolean collidesWithY(Collidable other) {
    double th = this.getSize().getY();
    double rh = other.getSize().getY();

    if(rh <= 0 || th <= 0) {
      return false;
    }

    double ty = this.getPosition().getY();
    double ry = other.getPosition().getY();

    return ((rh < ty || rh > ry) &&
            (th < ty || th > ry));
  }

  public boolean outOfBounds(){
    double x = this.getPosition().getX();
    double y = this.getPosition().getY();
    return (x < 0) ||
           (x > GameManager.WIDTH) ||
           (y > GameManager.HEIGHT);
  }
}