import java.util.Random;

public class ComparisonFindMax {
    public static void main(String[] args) {
        int[] numbers = new int[100000000];
        Random r = new Random();

        // fill the array with random numbers
        for (int i = 0; i < numbers.length; i++) {
            numbers[i] = r.nextInt(50000) + 1;
        }

        Timer timerS = new Timer();
        Timer timerC1 = new Timer();
        Timer timerC2 = new Timer();

        timerS.start();
        SequentialFindMax s1 = new SequentialFindMax(numbers);
        int maxSequential = s1.printMax();
        timerS.stop();

        System.out.println("Max Value (Sequential): " + maxSequential);
        System.out.println("Performance (ms): " + timerS.getDuration());
        System.out.println("--------------------------------");

        timerC1.start();
        ConcurrentFindMax t1 = new ConcurrentFindMax(numbers, 0, numbers.length / 2);
        ConcurrentFindMax t2 = new ConcurrentFindMax(numbers, numbers.length / 2, numbers.length);

        t1.start();
        t2.start();

        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
        }
        timerC1.stop();

        System.out.println("Max Value (Concurrent-2): " + Math.max(t1.printMax(), t2.printMax()));
        System.out.println("Performance (ms): " + timerC1.getDuration());
        System.out.println("--------------------------------");

        timerC2.start();
        ConcurrentFindMax th1 = new ConcurrentFindMax(numbers, 0, numbers.length / 4);
        ConcurrentFindMax th2 = new ConcurrentFindMax(numbers, numbers.length / 4, numbers.length / 2);
        ConcurrentFindMax th3 = new ConcurrentFindMax(numbers, numbers.length / 2, 3 * numbers.length / 4);
        ConcurrentFindMax th4 = new ConcurrentFindMax(numbers, 3 * numbers.length / 4, numbers.length);

        th1.start();
        th2.start();
        th3.start();
        th4.start();

        try {
            th1.join();
            th2.join();
            th3.join();
            th4.join();
        } catch (InterruptedException e) {
        }
        timerC2.stop();

        System.out.println("Max Value (Concurrent-4): "
                + Math.max(Math.max(Math.max(th1.printMax(), th2.printMax()), th3.printMax()), th4.printMax()));
        System.out.println("Performance (ms): " + timerC2.getDuration());
        System.out.println("--------------------------------");
    }

    private static class ConcurrentFindMax extends Thread {
        private int[] numbers;
        private int start;
        private int end;
        private int max = 0;

        public ConcurrentFindMax(int[] numbers, int start, int end) {
            this.numbers = numbers;
            this.start = start;
            this.end = end;
        }

        public void run() {

            // find the largest number
            for (int i = this.start; i < this.end; i++) {
                if (this.numbers[i] > this.max) {
                    this.max = this.numbers[i];
                }
            }
        }

        public int printMax() {
            return this.max;
        }
    }

    private static class SequentialFindMax {
        private int[] numbers;

        public SequentialFindMax(int[] numbers) {
            this.numbers = numbers;
        }

        public int printMax() {

            // find the largest number
            int max = 0;
            for (int i = 0; i < this.numbers.length; i++) {
                if (this.numbers[i] > max) {
                    max = this.numbers[i];
                }
            }

            return max;
        }
    }

    private static class Timer {
        private long startTime;
        private long endTime;

        public void start() {
            startTime = System.nanoTime();
        }

        public void stop() {
            endTime = System.nanoTime();
        }

        public long getDuration() {
            return (endTime - startTime) / 1000000;
        }
    }
}