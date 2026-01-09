package multithreading;

import java.util.concurrent.Semaphore;

public class OneTwoSemaphore {

    public static void main(String[] args) throws InterruptedException {

        Semaphore semOne = new Semaphore(1);
        Semaphore semTwo = new Semaphore(0);

        Thread t1 = new Thread(new CommonSemaphore("1", semOne, semTwo));
        Thread t2 = new Thread(new CommonSemaphore("2", semTwo, semOne));

        t1.start();
        t2.start();

        t1.join();
        t2.join();
    }
}
