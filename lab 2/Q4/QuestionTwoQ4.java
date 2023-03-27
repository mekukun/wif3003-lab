import java.util.Random;

public class QuestionTwoQ4 {
    public static void main(String[] args) {

        int[] numbers = new int[1000000];
        Random r = new Random();

        // fill the array with random numbers
        for (int i = 0; i < numbers.length; i++) {
            numbers[i] = r.nextInt(50000) + 1;
        }

        Thread t1 = new Thread(new FindMaxRunnable(numbers));
        Thread t2 = new Thread(new FindMaxRunnable(numbers));
        Thread t3 = new Thread(new FindMaxRunnable(numbers));
        Thread t4 = new Thread(new FindMaxRunnable(numbers));
        t1.start();
        t2.start();
        t3.start();
        t4.start();
    }

    private static class FindMaxRunnable implements Runnable {

        private int[] numbers;

        public FindMaxRunnable(int[] numbers) {
            this.numbers = numbers;
        }

        public void run() {

            Timer t = new Timer();

            int max = 0;

            t.start();
            for (int i = 0; i < numbers.length; i++) {
                if (numbers[i] > max) {
                    max = numbers[i];
                }
            }
            t.stop();

            System.out.println(Thread.currentThread().getName() + ": " + max);
            System.out.println(Thread.currentThread().getName() + "'s performance (ns): " + t.getDuration());
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
            return (endTime - startTime);
        }
    }

}
