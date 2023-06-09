import java.util.Random;

public class QuestionOne {
    public static void main(String[] args) {
        int[] numbers = new int[1000000];
        Random r = new Random();
        int max = 0;

        // fill the array with random numbers
        for (int i = 0; i < numbers.length; i++) {
            numbers[i] = r.nextInt(50000) + 1;
        }

        // find the largest number
        for (int i = 0; i < numbers.length; i++) {
            if (numbers[i] > max) {
                max = numbers[i];
            }
        }

        System.out.println("The largest number is: " + max);
    }
}