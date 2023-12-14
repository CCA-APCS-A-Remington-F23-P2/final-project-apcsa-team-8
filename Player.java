public class Player extends Moveable{
  double yVel;

  public Player(double x, double y){
    super(x, y, 10, 10, 0);
  }

  void update(){
    yVel += 0.1;
    setY(getY() + yVel);
  }

  
  
}