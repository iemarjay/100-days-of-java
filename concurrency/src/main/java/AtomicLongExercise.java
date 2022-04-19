import java.util.Random;
import java.util.concurrent.atomic.AtomicLong;

public class AtomicLongExercise {

    public AtomicLong value =  new AtomicLong(0);

    public static void main(String[] args) throws InterruptedException {
        AtomicLongExercise instance = new AtomicLongExercise();

        Thread thread1 = new Thread(run(instance, "a"));
        Thread thread2 = new Thread(run(instance, "b"));
        Thread thread3 = new Thread(run(instance, "c"));

        thread1.start();
        thread2.start();
        thread3.start();

        thread1.join();
        thread2.join();
        thread3.join();

        System.out.println(instance.value);
    }

    private static Runnable run(AtomicLongExercise msg, String threadName) {
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
