import java.util.*;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

public class  Main {
    public static AtomicInteger sum = new AtomicInteger(0);
    public static AtomicInteger num = new AtomicInteger(0);
    public static long clock;

    public static void main(String[] args) {
        System.out.println("Enter number of students: ");
        Scanner scanner = new Scanner(System.in);
        int N = scanner.nextInt();
        scanner.close();

        CountDownLatch countDownLatch = new CountDownLatch(2);
        Professor professor = new Professor(countDownLatch);
        Assistant assistant = new Assistant(countDownLatch);

        ExecutorService executorService = Executors.newFixedThreadPool(3);
        executorService.execute(professor);
        executorService.execute(assistant);

        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        clock = System.currentTimeMillis();

        ScheduledExecutorService studentsService = Executors.newScheduledThreadPool(N);

        for (int i = 0; i < N; i++) {
            long start = System.currentTimeMillis();

            Student student = new Student();
            String studentName = student.getName() + (i + 1);
            long arrival = student.getArrivalTime();
            long TTC = student.getDefenceTime();
            int score = student.getScore();

            Result studentResult = new Result(studentName, professor, assistant, arrival, TTC, start, score);
            studentsService.schedule(studentResult, arrival, TimeUnit.MILLISECONDS);

            try {
                Thread.sleep(TTC);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        studentsService.shutdownNow();
        executorService.shutdownNow();

        System.out.println("\nRan out of time: " + num);
        System.out.println("Sum: " + sum);
        System.out.println("Average: " + sum.doubleValue() / (N - num.intValue()));
    }
}
