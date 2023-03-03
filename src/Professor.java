import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;

public class Professor implements Runnable{

    private String name = "Professor";
    CyclicBarrier cyclicBarrier = new CyclicBarrier(2);;
    CountDownLatch countDownLatch;
    Result result;

    public Professor(CountDownLatch countDownLatch) {
        this.countDownLatch = countDownLatch;
    }

    @Override
    public void run() {
        countDownLatch.countDown();
    }

    public CyclicBarrier getCyclicBarrier() {
        return cyclicBarrier;
    }

    public String getName() {
        return name;
    }
}
