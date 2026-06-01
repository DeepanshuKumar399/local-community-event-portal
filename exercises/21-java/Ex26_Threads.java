public class Ex26_Threads {

    static class MyThread extends Thread {
        private final String threadName;
        MyThread(String name) { this.threadName = name; }

        @Override
        public void run() {
            for (int i = 1; i <= 5; i++) {
                System.out.println(threadName + " → message " + i);
                try { Thread.sleep(100); } catch (InterruptedException e) { Thread.currentThread().interrupt(); }
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new MyThread("Thread-A");
        Thread t2 = new MyThread("Thread-B");

        t1.start();
        t2.start();

        t1.join();
        t2.join();
        System.out.println("Both threads finished.");
    }
}
