public class Vector {
  private double x;
  private double y;

  private double angle;
  private double magnitude;

  public Vector(double x, double y) {
    this.x = x;
    this.y = y;
    this.angle = Math.atan2(y, x);
    this.magnitude = Math.sqrt(x * x + y * y);
  }

  public double getX() {
    return x;
  }

  public double getY() {
    return y;
  }

  public void setX(double x) {
    this.x = x;
    this.angle = Math.atan2(y, x);
    this.magnitude = Math.sqrt(x * x + y * y);
  }

  public void setY(double y) {
    this.y = y;
    this.magnitude = Math.sqrt(x * x + y * y);
    this.angle = Math.atan2(y, x);
  }

  public double getMagnitude() {
    return this.magnitude;
  }

  public double getAngle() {
    return this.angle;
  }

  public void setMagnitude(double magnitude) {
    this.magnitude = magnitude;
    this.x = magnitude * Math.cos(angle);
    this.y = magnitude * Math.sin(angle);
  }

  public void setAngle(double angle) {
    this.angle = angle;
    this.x = magnitude * Math.cos(angle);
    this.y = magnitude * Math.sin(angle);
  }

  public void append(Vector other) {
    this.x += other.getX();
    this.y += other.getY();
    this.magnitude = Math.sqrt(x * x + y * y);
    this.angle = Math.atan2(y, x);
  }

  public Vector plus(Vector other) {
    return new Vector(this.x + other.x, this.y + other.y);
  }

  public Vector minus(Vector other) {
    return new Vector(this.x - other.x, this.y - other.y);
  }

  public void multiply(double scalar) {
    setMagnitude(getMagnitude() * scalar);
  }

  public Vector multiply(Vector other) {
    return new Vector(this.x * other.x, this.y * other.y);
  }

  public void reflectX(){
    this.x *= -1;
  }

  public void reflectY(){
    this.y *= -1;
  }

  public Coordinate asCoordinate(){
    return new Coordinate(this.x, this.y);
  }

  public String toString() {
    return "Vector: <" + this.x + ", " + this.y + ">";
  }
}