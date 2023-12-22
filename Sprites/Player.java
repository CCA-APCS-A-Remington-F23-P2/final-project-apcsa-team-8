import java.awt.Color;
import Common.*;
import java.io.FileWriter;
import java.io.File;
import java.util.Scanner;
import java.awt.Graphics;

public class Player extends Moveable{

  public int lives;
  public static int score;
  public static int HIGH_SCORE;
  private int speed;

  private Timer jumpDelay;

  private static Timer levelTimer = new Timer(25000);


  public Player(int x, int y, int w, int h){
    super(x, y, w, h, new Vector(0,0));
    setImage("standingStarman.png");
    jumpDelay = new Timer(500);
    lives = 3;
    score = 2500;
    levelTimer.reset();
    HIGH_SCORE = getHighScore();
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
      e.printStackTrace();
    }
  }

  public static int getHighScore(){
    int output = -1;
      try{
        Scanner scan = new Scanner(new File("highScore.txt"));
        output = scan.nextInt();
        scan.close();
      } catch(Exception e) {
        e.printStackTrace();
      }
    return output;
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

  public boolean isDead(){
    return lives < 1;
  }

  public void fall(){
    if(getPosition().getY() < 600){
      setVelocityY(getVelocity().getY() + 50);
    }
  }

  public void draw(Graphics g){
    super.draw(g);

    if(score > HIGH_SCORE){
      setHIGHScore(score);
    }
    
    g.setColor(Color.RED);
    g.drawString("Lives: " + getLives(), 20, 20);
    g.drawString("" + HIGH_SCORE, 20, 40);
    score = (int) levelTimer.getElapsedTime();
    g.drawString("Score: " + score, 120, 20);
  }
  
}