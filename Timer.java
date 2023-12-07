public class Timer{
  private long startTime;
  private long currTime;
  private int delay;
  private int delayRandParamMin;
  private int delayRandParamMax;

  public Timer() {
    this(0);
  }

  public Timer(int delay) {
    reset();
    this.delay = delay;
  }

  public Timer(int delayRandParamMin, int delayRandParamMax) {
    reset();
    this.delayRandParamMin = delayRandParamMin;
    this.delayRandParamMax = delayRandParamMax;
    this.delay = getRandDelay();
  }

  public void reset() {
    startTime = System.currentTimeMillis();
  }

  public int getElapsedTime() {
    currTime = System.currentTimeMillis();
    return (int)(currTime - startTime);
  }

  public void setDelay(int delay) {
    this.delay = delay;
  }

  private int getRandDelay() {
    return (int)(Math.random()*(delayRandParamMax - delayRandParamMin) + delayRandParamMin);
  }

  private boolean useRandDelay() {
    return delayRandParamMax != 0;
  }

  public boolean isFinished() {
    if(getElapsedTime() >= delay) {
      setDelay(useRandDelay() ? getRandDelay() : delay);
      reset();
      return true;
    }
    return false;
  }

  public static void sleep(int delay) {
    try{
      Thread.currentThread().sleep(delay);
    }
    catch(Exception e) {}
  }
}