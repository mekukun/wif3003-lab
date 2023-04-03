public class Main {
    public static void main(String[] args) {
        Room room = new Room();

        for (int i = 0; i < 20; i++) {
            Thread guestThread = new Thread(() -> {
                try {
                    room.enterAsGuest();
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
