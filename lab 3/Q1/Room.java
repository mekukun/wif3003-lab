import java.util.concurrent.Semaphore;

public class Room {
    private Semaphore guestsSem = new Semaphore(6);
    private Semaphore cleanersSem = new Semaphore(1);
    private int numGuests = 0;
    private boolean isClean = true;

    public void enterAsGuest() throws InterruptedException {
        guestsSem.acquire();
        synchronized (this) {
            while (!isClean || numGuests >= 6) {
                wait();
            }
            numGuests++;
            System.out.println("\nGuest " + Thread.currentThread().getName() + " entered the room.");
            System.out.println("Number of guests: " + numGuests);
        }
        guestsSem.release();
    }

    public void exitAsGuest() {
        synchronized (this) {
            numGuests--;
            if (numGuests == 0) {
                notifyAll();
            }
            System.out.println("\nGuest " + Thread.currentThread().getName() + " left the room.");
            System.out.println("Number of guests: " + numGuests);
        }
    }

    public void enterAsCleaner() throws InterruptedException {
        cleanersSem.acquire();
        synchronized (this) {
            while (!isClean || numGuests > 0) {
                wait();
            }
            isClean = false;
            System.out.println("\nCleaner " + Thread.currentThread().getName() + " entered the room.");
            System.out.println("Number of guests: " + numGuests);
        }
    }

    public void exitAsCleaner() {
        synchronized (this) {
            isClean = true;
            cleanersSem.release();
            notifyAll();
            System.out.println("\nCleaner " + Thread.currentThread().getName() + " left the room.");
            System.out.println("Number of guests: " + numGuests);
        }
    }
}
