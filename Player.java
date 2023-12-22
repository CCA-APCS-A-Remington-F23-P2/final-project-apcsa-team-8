import java.awt.Color;
import Common.*;
import java.io.FileWriter;
import java.awt.Graphics;

public class Player extends Moveable{

  private int lives;
  private int score;
  private static int HIGH_SCORE;
  private int speed;

  private Timer jumpDelay;


  public Player(int x, int y, int w, int h){
    super(x, y, w, h, new Vector(0,0));
    setImage("standingStarman.png");
    jumpDelay = new Timer(500);
    lives = 3;
    speed = 1;
  }

  public void setScore(int s){
    score = s;
  }

  public void addScore(){
    score++;
  }

  public void setHIGHScore(int s){
      HIGH_SCORE = s;
     String h = String.valueOf(HIGH_SCORE);
    try{
      FileWriter writer = new FileWriter("highScore.txt");

      for (int i = 0; i<h.length(); i++){
        writer.write(h.charAt(i));
      }
      System.out.println("High score updated");
      writer.close();
    }
    catch(Exception e){
    }
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

  public void walkForwards(){
    setVelocityX(speed);
    reflectImageToForwards();
  }

  public void walkBackwards(){
    setVelocityX(-speed);
    reflectImageToBackwards();
  }

  public void jump(){
    if(jumpDelay.isFinished()){
      setVelocityY(-1 * 1.5);
    }
  }

  public void fall(){
    if(getPosition().getY() < 600){
      setVelocityY(getVelocity().getY() + 50);
    }
  }

  public void draw(Graphics g){
    super.draw(g);
    g.setColor(Color.RED);
    g.drawString("Lives: " + getLives(), 20, 20);
  }
  
}