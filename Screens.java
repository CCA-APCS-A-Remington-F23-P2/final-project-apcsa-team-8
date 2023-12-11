public class Screens{
  private static final int WIDTH = 800;
  private static final int HEIGHT = 600;
  Jframe window = new Jframe("Screens");

  public Screens(){
    window.setSize(WIDTH,HEIGHT);
    window.setColor(Color.BLACK);
    window.setVisible(true);
  }

  public void startScreen(){
    window.setColor(Color.BLACK);
    draw(window);
    window.drawString("Welcome to StarMan!", 300, 300);
    window.drawString("Press Enter to start.", 300, 350);
    window.drawString("Level " + getLevel(), 300, 400);
    window.drawString("Lives: " + getLives(), 300, 450);
    window.drawString("High score: " + getHighScore(), 300, 500);
  }

  public void pauseScreen(){
    //if you lose a life
    window.setColor(Color.BLACK);
    window.drawString("You lost a life!", 300, 300);
    window.drawString("Lives remaining: " + getLives(), 300, 350);
    window.drawString("Score: " + getScore(), 300, 400);
    window.drawString("Press Enter to resume.", 300, 450);
  }

  public void endLevelScreen(){
    //if level one or two is completed
    window.setColor(Color.BLACK);
    window.drawString("Congrats! You finished Level " + getLevel(), 300, 300);
    window.drawString("Lives remaining: " + getLives(), 300, 350);
    window.drawString("Final Score: " + getScore(), 300, 400);
    window.drawString("High Score: " + getHighScore(), 300, 450);
    //add congrats if a new high score is set
    window.drawString("Press Enter to continue to the next level.", 300, 500);
  }

  public void endGameScreen(){
    //if last level is completed
    window.setColor(Color.BLACK);
    window.drawString("Congrats! You completed all levels of StarMan.", 300, 300);
    window.drawString("Lives remaining: " + getLives(), 300, 350);
    window.drawString("Final Score: " + getScore(), 300, 400);
    window.drawString("High Score: " + getHighScore(), 300, 450);
    //add congrats if a new high score is set
    window.drawString("Press Enter to restart.", 300, 500);
  }
}