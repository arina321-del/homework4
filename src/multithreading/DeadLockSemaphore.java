package multithreading;

import java.util.concurrent.Semaphore;

public class DeadLockSemaphore {

    private static final Semaphore SEM1 = new Semaphore(1);
    private static final Semaphore SEM2 = new Semaphore(1);

    public static void main(String[] args) {

        Runnable task1 = () -> {
            try {
                SEM1.acquire();
                System.out.println("Thread-1 захватил SEM1");

                Thread.sleep(100);

                SEM2.acquire();
                System.out.println("Thread-1 захватил SEM2");
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        };

        Runnable task2 = () -> {
            try {
                SEM2.acquire();
                System.out.println("Thread-2 захватил SEM2");

                Thread.sleep(100);

                SEM1.acquire();
                System.out.println("Thread-2 захватил SEM1");
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        };

        new Thread(task1).start();
        new Thread(task2).start();
    }
}