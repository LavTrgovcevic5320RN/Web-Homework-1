import java.util.Random;

public class Student implements Runnable{

    private long arrivalTime;
    private long startTime = System.currentTimeMillis();
    private long defenceTime;

    private String name = "Student";
    private int score;

    public Student(){}

    @Override
    public void run() {

    }

    public long getArrivalTime() {
        return new Random().nextInt((1000));
    }

    public long getDefenceTime() {
        return new Random().nextInt(500) + 500;
    }

    public String getName() {
        return name;
    }

    public int getScore() {
        return new Random().nextInt((10 - 5) + 1) + 5;
    }
}
