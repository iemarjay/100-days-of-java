import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;

public class ExecutorServiceExercise {

    public AtomicLong value =  new AtomicLong(0);

    public static void main(String[] args) throws InterruptedException {
        ExecutorServiceExercise instance = new ExecutorServiceExercise();

        Runnable task1 = run(instance, "a");
        Runnable task2 = run(instance, "b");
        Runnable task3 = run(instance, "c");

        ExecutorService threadPool = Executors.newFixedThreadPool(3);

        threadPool.execute(task1);
        threadPool.execute(task2);
        threadPool.execute(task3);

        threadPool.shutdown();
        threadPool.awaitTermination(2, TimeUnit.HOURS);
        System.out.println(instance.value);
    }

    private static Runnable run(ExecutorServiceExercise msg, String threadName) {
        return new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 1000; i++) {
                    long newValue = msg.value.get();

                    while (!msg.value.compareAndSet(newValue, newValue + 1)) {
                        newValue = msg.value.get();
                    }
                }
            }
        };
    }

}
