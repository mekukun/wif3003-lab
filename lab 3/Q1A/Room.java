package Q1A;

public class Room {
    private int numGuests = 0;
    private int numCleaner = 0;

    public synchronized void enterAsGuest() throws InterruptedException {
        while (numCleaner != 0 || numGuests >= 6) {
            wait();
        }
        numGuests++;
        System.out.println("\nGuest " + Thread.currentThread().getName() + " entered the room.");
        System.out.println("Number of guests: " + numGuests);
        System.out.println("Number of cleaner: " + numCleaner);
    }

    public synchronized void exitAsGuest() {
        numGuests--;
        notifyAll();
        // if (numGuests == 0) {
        // notifyAll();
        // }
        System.out.println("\nGuest " + Thread.currentThread().getName() + " left the room.");
        System.out.println("Number of guests: " + numGuests);
        System.out.println("Number of cleaner: " + numCleaner);
    }

    public synchronized void enterAsCleaner() throws InterruptedException {
        while (numGuests > 0 || numCleaner != 0) {
            wait();
        }
        numCleaner++;
        System.out.println("\nCleaner " + Thread.currentThread().getName() + " entered the room.");
        System.out.println("Number of guests: " + numGuests);
        System.out.println("Number of cleaner: " + numCleaner);
    }

    public synchronized void exitAsCleaner() {
        numCleaner--;
        notifyAll();
        System.out.println("\nCleaner " + Thread.currentThread().getName() + " left the room.");
        System.out.println("Number of guests: " + numGuests);
        System.out.println("Number of cleaner: " + numCleaner);
    }
}
