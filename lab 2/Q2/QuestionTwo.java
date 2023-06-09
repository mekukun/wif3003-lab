package Q2;

import java.util.Random;

public class QuestionTwo {
    public static void main(String[] args) {

        int[] numbers = new int[1000000];
        Random r = new Random();

        // fill the array with random numbers
        for (int i = 0; i < numbers.length; i++) {
            numbers[i] = r.nextInt(50000) + 1;
        }

        Thread t1 = new Thread(new FindMaxRunnable(numbers));
        Thread t2 = new Thread(new FindMaxRunnable(numbers));
        t1.start();
        t2.start();
    }

    private static class FindMaxRunnable implements Runnable {

        private int[] numbers;

        public FindMaxRunnable(int[] numbers) {
            this.numbers = numbers;
        }

        public void run() {

            int max = 0;
            for (int i = 0; i < numbers.length; i++) {
                if (numbers[i] > max) {
                    max = numbers[i];
                }
            }

            System.out.println(Thread.currentThread().getName() + ": " + max);
        }
    }
}
