package Q2;

import java.util.List;
import java.util.function.Predicate;

public class Q2 {
    public static void evaluate(List<Integer> list, Predicate<Integer> predicate) {
        for (Integer num : list) {
            if (predicate.test(num)) {
                System.out.print(num + " ");
            }
        }
        System.out.println();
    }

    public static void main(String[] args) {
        List<Integer> numbers = List.of(2, 5, 8, 10, 12, 15, 20, 25, 30, 35);

        System.out.println("All the elements:");
        evaluate(numbers, num -> true);
        
        System.out.println("All the odd elements:");
        evaluate(numbers, num -> num % 2 != 0);

        System.out.println("All the even elements:");
        evaluate(numbers, num -> num % 2 == 0);

        System.out.println("All the elements that are greater than 5:");
        evaluate(numbers, num -> num > 5);
    }
}
