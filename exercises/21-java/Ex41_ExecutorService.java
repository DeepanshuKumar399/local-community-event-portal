import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class Ex41_ExecutorService {

    static class SquareTask implements Callable<Integer> {
        private final int number;
        SquareTask(int number) { this.number = number; }

        @Override
        public Integer call() throws Exception {
            Thread.sleep(100);
            return number * number;
        }
    }

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        ExecutorService executor = Executors.newFixedThreadPool(4);
        List<Future<Integer>> futures = new ArrayList<>();

        for (int i = 1; i <= 10; i++) {
            futures.add(executor.submit(new SquareTask(i)));
        }

        System.out.println("Results:");
        for (int i = 0; i < futures.size(); i++) {
            System.out.println("  Square of " + (i + 1) + " = " + futures.get(i).get());
        }

        executor.shutdown();
        System.out.println("All tasks completed.");
    }
}
