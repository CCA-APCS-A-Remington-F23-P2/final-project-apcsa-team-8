public class Timer{
  private long startTime;
  private long currTime;
  private long delay;
  private long delayRandParamMin;
  private long delayRandParamMax;

  public Timer() {
    this(0);
  }

  public Timer(long delay) {
    reset();
    this.delay = delay;
  }

  public Timer(long delayRandParamMin, long delayRandParamMax) {
    reset();
    this.delayRandParamMin = delayRandParamMin;
    this.delayRandParamMax = delayRandParamMax;
    this.delay = getRandDelay();
  }

  public void reset() {
    startTime = System.currentTimeMillis();
  }

  public long getElapsedTime() {
    currTime = System.currentTimeMillis();
    return (int)(currTime - startTime);
  }

  public void setDelay(long delay) {
    this.delay = delay;
  }

  private long getRandDelay() {
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

  public static void sleep(long delay) {
    try{
      Thread.currentThread().sleep(delay);
    }
    catch(Exception e) {}
  }
}