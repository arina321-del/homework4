package multithreading;

import java.util.concurrent.Semaphore;

public class OneTwoSemaphore {

    private static final Semaphore SEM_ONE = new Semaphore(1);
    private static final Semaphore SEM_TWO = new Semaphore(0);

    public static void main(String[] args) {

        Runnable printOne = () -> {
            while (true) {
                try {
                    SEM_ONE.acquire();
                    System.out.println("1");
                    SEM_TWO.release();
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        };

        Runnable printTwo = () -> {
            while (true) {
                try {
                    SEM_TWO.acquire();
                    System.out.println("2");
                    SEM_ONE.release();
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        };

        new Thread(printOne).start();
        new Thread(printTwo).start();
    }
}