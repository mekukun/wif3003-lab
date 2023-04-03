package Q1A;

public class Room {
    private int numGuests = 0;
    private boolean isCleanerPresent = false;

    public synchronized void enterAsGuest() throws InterruptedException {
        while (isCleanerPresent || numGuests >= 6) {
            wait();
        }
        numGuests++;
        System.out.println("\nGuest " + Thread.currentThread().getName() + " entered the room.");
        System.out.println("Number of guests: " + numGuests);
    }

    public synchronized void exitAsGuest() {
        numGuests--;
        notifyAll();
        // if (numGuests == 0) {
        // notifyAll();
        // }
        System.out.println("\nGuest " + Thread.currentThread().getName() + " left the room.");
        System.out.println("Number of guests: " + numGuests);
    }

    public synchronized void enterAsCleaner() throws InterruptedException {
        while (numGuests > 0) {
            wait();
        }
        isCleanerPresent = true;
        System.out.println("\nCleaner " + Thread.currentThread().getName() + " entered the room.");
        System.out.println("Number of guests: " + numGuests);
    }

    public synchronized void exitAsCleaner() {
        isCleanerPresent = false;
        notifyAll();
        System.out.println("\nCleaner " + Thread.currentThread().getName() + " left the room.");
        System.out.println("Number of guests: " + numGuests);
    }
}
