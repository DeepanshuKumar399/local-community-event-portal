import java.util.concurrent.atomic.AtomicInteger;

public class Ex40_VirtualThreads {
    public static void main(String[] args) throws InterruptedException {
        int THREAD_COUNT = 100_000;
        AtomicInteger counter = new AtomicInteger(0);

        long start = System.currentTimeMillis();

        Thread[] threads = new Thread[THREAD_COUNT];
        for (int i = 0; i < THREAD_COUNT; i++) {
            threads[i] = Thread.ofVirtual().start(() -> counter.incrementAndGet());
        }

        for (Thread t : threads) t.join();

        long elapsed = System.currentTimeMillis() - start;
        System.out.println("Virtual threads completed : " + counter.get());
        System.out.println("Time taken                : " + elapsed + " ms");
    }
}
