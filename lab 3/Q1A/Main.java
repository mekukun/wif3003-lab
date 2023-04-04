package Q1A;

import java.util.Random;

public class Main {
    public static void main(String[] args) throws InterruptedException {

        Random rand = new Random();
        Room room = new Room();

        for (int i = 0; i < 10; i++) {

            if (rand.nextInt(2) == 0) {
                Thread guestThread = new Thread(() -> {
                    try {
                        room.enterAsGuest();
                        Thread.sleep(500); // Give time for guests to occupy longer in the room
                        room.exitAsGuest();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                });
                guestThread.start();
            } else {
                Thread cleanerThread = new Thread(() -> {
                    try {
                        room.enterAsCleaner();
                        // do the cleaning
                        room.exitAsCleaner();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                });
                cleanerThread.start();
            }
        }

    }
}
