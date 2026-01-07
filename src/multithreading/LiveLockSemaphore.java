package multithreading;

import java.util.concurrent.Semaphore;

public class LiveLockSemaphore {

    private static final Semaphore SEM = new Semaphore(1);

    public static void main(String[] args) {

        Runnable task1 = () -> {
            while (true) {
                if (SEM.tryAcquire()) {
                    System.out.println("Thread-1 получил семафор и уступает");
                    SEM.release();
                }
                Thread.yield();
            }
        };

        Runnable task2 = () -> {
            while (true) {
                if (SEM.tryAcquire()) {
                    System.out.println("Thread-2 получил семафор и уступает");
                    SEM.release();
                }
                Thread.yield();
            }
        };

        new Thread(task1).start();
        new Thread(task2).start();
    }
}