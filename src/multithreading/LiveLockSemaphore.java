package multithreading;

import java.util.concurrent.Semaphore;

public class LiveLockSemaphore {

    public static void main(String[] args) throws InterruptedException {

        Semaphore semaphore = new Semaphore(1);

        Thread t1 = new Thread(new CommonSemaphore(semaphore, "Поток-1"));
        Thread t2 = new Thread(new CommonSemaphore(semaphore, "Поток-2"));

        t1.start();
        t2.start();

        t1.join();
        t2.join();
    }
}
