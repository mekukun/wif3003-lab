class PrintCharTwo implements Runnable {
    private char c;
    private int count;
    private Object lock;

    public PrintCharTwo(char c, int count, Object lock) {
        this.c = c;
        this.count = count;
        this.lock = lock;
    }

    public void run() {
        for (int i = 0; i < count; i++) {
            synchronized (lock) {
                System.out.print(c);
                lock.notify();
                try {
                    if (c == 'A') {
                        lock.wait();
                    } else {
                        Thread.sleep(100);
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

class PrintNumTwo implements Runnable {
    private int count;
    private Object lock;

    public PrintNumTwo(int count, Object lock) {
        this.count = count;
        this.lock = lock;
    }

    public void run() {
        synchronized (lock) {
            for (int i = 1; i <= count; i++) {
                System.out.print(i + " ");
                lock.notify();
                try {
                    if (i == 10) {
                        lock.wait();
                    } else {
                        Thread.sleep(100);
                        lock.wait();
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            lock.notifyAll();
        }
    }
}

public class taskTwo {
    public static void main(String[] args) {
        Object lock = new Object();
        Thread t1 = new Thread(new PrintCharTwo('A', 10, lock));
        Thread t2 = new Thread(new PrintNumTwo(20, lock));
        t1.start();
        t2.start();
    }
}