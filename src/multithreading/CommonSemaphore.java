package multithreading;

import java.util.concurrent.Semaphore;

public class CommonSemaphore implements Runnable {

    public enum Mode {
        DEADLOCK,
        LIVELOCK,
        PRINT
    }

    private final Mode mode;
    private final Semaphore first;
    private final Semaphore second;
    private final String value;

    public CommonSemaphore(Semaphore first, Semaphore second, String name) {
        this.mode = Mode.DEADLOCK;
        this.first = first;
        this.second = second;
        this.value = name;
    }

    public CommonSemaphore(Semaphore semaphore, String name) {
        this.mode = Mode.LIVELOCK;
        this.first = semaphore;
        this.second = null;
        this.value = name;
    }

    public CommonSemaphore(String value, Semaphore current, Semaphore next) {
        this.mode = Mode.PRINT;
        this.first = current;
        this.second = next;
        this.value = value;
    }

    @Override
    public void run() {
        switch (mode) {
            case DEADLOCK -> runDeadLock();
            case LIVELOCK -> runLiveLock();
            case PRINT -> runPrint();
        }
    }

    private void runDeadLock() {
        try {
            first.acquire();
            System.out.println(value + " захватил первый семафор");
            Thread.sleep(100);
            second.acquire();
            System.out.println(value + " захватил второй семафор");
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    private void runLiveLock() {
        while (true) {
            if (first.tryAcquire()) {
                System.out.println(value + " получил семафор и уступает");
                first.release();
            }
            Thread.yield();
        }
    }

    private void runPrint() {
        while (true) {
            try {
                first.acquire();
                System.out.println(value);
                second.release();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}
