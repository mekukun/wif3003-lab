package Q2;

import java.util.stream.IntStream;

public class PrimeNumberCounter {
    public static void main(String[] args) {
        System.out.println("Prime numbers:");

        IntStream.rangeClosed(1, 50)
                .parallel()
                .filter(PrimeNumberCounter::isPrime)
                .forEach(num -> {
                    String threadName = Thread.currentThread().getName();
                    System.out.println(threadName + ": " + num);
                });
    }

    public static boolean isPrime(int number) {
        if (number <= 1)
            return false;

        for (int i = 2; i <= Math.sqrt(number); i++) {
            if (number % i == 0)
                return false;
        }

        return true;
    }
}
