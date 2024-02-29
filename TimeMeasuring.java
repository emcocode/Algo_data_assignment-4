public class TimeMeasuring {

    private long startTime;
    private long stopTime;

    public TimeMeasuring() {}

    public void startTimer() {
        startTime = System.nanoTime();
    }

    public long stopTimer() {
        stopTime = System.nanoTime();
        return (stopTime - startTime);
    }
    
}