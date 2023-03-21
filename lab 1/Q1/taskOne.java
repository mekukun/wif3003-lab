package Q1;
class PrintChar implements Runnable {
    private char ch;
    private int count;
    private int delay;

    public PrintChar(char ch, int count, int delay) {
        this.ch = ch;
        this.count = count;
        this.delay = delay;
    }

    public void run() {
        for (int i = 0; i < count; i++) {
            System.out.print(ch);
            try {
                Thread.sleep(delay);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

class PrintNum extends Thread {
    private int max;

    public PrintNum(int max) {
        this.max = max;
    }

    public void run() {
        for (int i = 1; i <= max; i++) {
            System.out.print(i + " ");
        }
    }
}

public class taskOne {
    public static void main(String[] args) {
        Thread thread1 = new Thread(new PrintChar('C', 25, 100));
        Thread thread2 = new Thread(new PrintChar('G', 15, 500));
        PrintNum thread3 = new PrintNum(67);

        thread1.start();
        thread2.start();
        thread3.start();
    }
}
