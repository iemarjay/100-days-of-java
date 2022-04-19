public class Synchronizer {
    volatile int value;
    volatile int counter;

    public static void main(String[] args) {
        Synchronizer sync = new Synchronizer();

        new Thread(run(sync, "a")).start();
        new Thread(run(sync, "b")).start();
        new Thread(run(sync, "c")).start();
        new Thread(run(sync, "d")).start();
    }

    private static Runnable run(Synchronizer msg, String threadName) {
        return new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 1000; i++) {
                    boolean notEqual;
                    msg.add(1);

                    notEqual = msg.isNotEqual();

                    if (notEqual) {
                        System.out.println("value: " + msg.getValue() + ", name: " + threadName + ", iteration: " + i);
                        System.out.println("counter: " + msg.getCounter() + ", name: " + threadName + ", iteration: " + i);
                    }
                }
            }
        };
    }

    synchronized private boolean isNotEqual() {
        return value != counter;
    }

    static void whatever(String msg) throws InterruptedException {
        synchronized (Synchronizer.class) {
            Thread.sleep(500);
            System.out.println(msg);
        }
    }

    synchronized void add(int diff) {
        this.value = this.value + diff;
        counter++;
    }

    void subtract(int diff) {
        this.value -= diff;
        counter++;
    }

    synchronized int getValue() {
        return value;
    }

    public int getCounter() {
        return counter;
    }
}
