import java.util.Random;

public class QuestionOneQ3 {
    public static void main(String[] args) {
        int[] numbers = new int[1000000];
        Random r = new Random();
        int max = 0;

        // fill the array with random numbers
        for (int i = 0; i < numbers.length; i++) {
            numbers[i] = r.nextInt(50000) + 1;
        }

        Timer t = new Timer();

        // find the largest number
        t.start();
        for (int i = 0; i < numbers.length; i++) {
            if (numbers[i] > max) {
                max = numbers[i];
            }
        }
        t.stop();

        System.out.println("The largest number is: " + max);
        System.out.println("Performance (ns): " + t.getDuration());
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