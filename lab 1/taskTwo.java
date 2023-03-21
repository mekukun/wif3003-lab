class Printer {
    boolean flag = false;
    int turn = 1;

    public synchronized void printnum(int limit) {
        for (int i = 1; i <= limit; i++) {
            while (turn != 2) {
                try {
                    wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.print(i + " ");

            if (!flag) {
                turn = 1;
                notifyAll();
            }
        }
    }

    public synchronized void printchar(int limit) {
        for (int i = 0; i < limit; i++) {
            while (turn != 1) {
                try {
                    wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.print("A ");
            turn = 2;
            if (i == limit - 1) {
                flag = true;
            }
            notifyAll();
        }
    }
}

public class taskTwo {
    public static void main(String[] args) {
        final Printer c = new Printer();
        Thread t1 = new Thread() {
            public void run() {
                System.out.println(Thread.currentThread().getName() + " initiating...");
                c.printchar(5);
            }
        };
        Thread t2 = new Thread() {
            public void run() {
                System.out.println(Thread.currentThread().getName() + " initiating...");
                c.printnum(10);
            }
        };
        t1.start();
        t2.start();
    }
}