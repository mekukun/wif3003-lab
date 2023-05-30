package Q1;

@FunctionalInterface
interface MathOperation {
    int operation(int a, int b);
}

public class Q1 {
    public static void main(String[] args) {
        // Lambda expressions for addition, subtraction, multiplication, and division
        MathOperation addition = (a, b) -> a + b;
        MathOperation subtraction = (a, b) -> a - b;
        MathOperation multiplication = (a, b) -> a * b;
        MathOperation division = (a, b) -> a / b;

        // Sample usage of the lambda expressions
        int num1 = 10;
        int num2 = 5;

        System.out.println("Addition: " + addition.operation(num1, num2)); // Output: 15
        System.out.println("Subtraction: " + subtraction.operation(num1, num2)); // Output: 5
        System.out.println("Multiplication: " + multiplication.operation(num1, num2)); // Output: 50
        System.out.println("Division: " + division.operation(num1, num2)); // Output: 2
    }
}
