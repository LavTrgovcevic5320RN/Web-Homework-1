import java.util.concurrent.CountDownLatch;

public class Assistant implements Runnable{

    private String name = "Assistant";
    Result result;
    CountDownLatch countDownLatch;

    public Assistant(CountDownLatch countDownLatch) {
        this.countDownLatch = countDownLatch;
    }

    @Override
    public void run() {
        countDownLatch.countDown();
    }

    public String getName() {
        return name;
    }
}
