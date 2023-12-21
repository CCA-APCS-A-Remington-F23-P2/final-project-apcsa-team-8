
import Common.*;

public class Player extends Moveable{

  String standing = "standingStarman.png"; 
  String walking =  "leftForwardWalk.png";
  String jumping = "jumpingStarman.png";


  public Player(int x, int y, int w, int h, Vector velocity){
    super(x, y, w, h, velocity);
    setImage("leftForwardWalk.png");
  }

  public void walk() {
    
  }
}