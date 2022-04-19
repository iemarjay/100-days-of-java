import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLockExercise {
    public static void main(String[] args) {
        ReentrantLock reentrantLock = new ReentrantLock();

        reentrantLock.lock();
        try {
            reentrantLock.lock();
            int a = 1+2;
            System.out.println(a);
        } finally {
            reentrantLock.unlock();
        }
    }
}
