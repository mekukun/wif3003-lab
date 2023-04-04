package Q1A;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        Room room = new Room();

        for (int i = 0; i < 10; i++) {

            int amount = rand.nextInt(100);

            Thread user = new Thread(() -> {
                if (rand.nextInt(2) == 0) {
                    bank.withdraw(amount);
                } else {
                    bank.deposit(amount);
                }
            });
            user.start();
        }

        for (int i = 0; i < 10; i++) {
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
        }

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
