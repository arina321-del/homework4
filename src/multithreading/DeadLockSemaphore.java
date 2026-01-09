package multithreading;

import java.util.concurrent.Semaphore;

public class DeadLockSemaphore {

    public static void main(String[] args) throws InterruptedException {

        Semaphore sem1 = new Semaphore(1);
        Semaphore sem2 = new Semaphore(1);

        Thread t1 = new Thread(new CommonSemaphore(sem1, sem2, "Поток-1"));
        Thread t2 = new Thread(new CommonSemaphore(sem2, sem1, "Поток-2"));

        t1.start();
        t2.start();

        t1.join();
        t2.join();
    }
}
