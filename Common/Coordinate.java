public class Coordinate {
  private double x;
  private double y;

  public Coordinate(double x, double y) {
    this.x = x;
    this.y = y;
  }

  public int getX() {
    return (int) x;
  }

  public int getY() {
    return (int) y;
  }

  public void setX(double x) {
    this.x = x;
  }

  public void setY(double y) {
    this.y = y;
  }

  public Vector asVector(){
    return new Vector(x, y);
  }

  public String toString(){
    return "Coordinate: (" + x + ", " + y + ")";
  }
}