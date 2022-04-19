import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class CallableExercise {
    public static void main(String[] args) throws ExecutionException, InterruptedException, TimeoutException {
        ExecutorService threadPool = Executors.newFixedThreadPool(3);

        Future<String> task1Future = threadPool.submit(run("task1"));

        String task1 = task1Future.get(1, TimeUnit.MINUTES);
        System.out.println(task1);
    }

    private static Callable<String> run(String threadName) {
        return new Callable<String>() {
            @Override
            public String call() {
                return threadName + ": " + System.currentTimeMillis();
            }
        };
    }
}
