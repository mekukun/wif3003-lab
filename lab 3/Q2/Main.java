package Q2;

import java.util.Random;

public class Main {
    public static void main(String[] args) throws InterruptedException {

        Random rand = new Random();

        Bank bank = new Bank();

        for (int i = 0; i < 10; i++) {

            int amount = rand.nextInt(100);

            Thread user = new Thread(() -> {
                if (rand.nextInt(2) == 0) {
                    bank.withdraw(amount);
                } else {
                    bank.deposit(amount);
                }
            });
            user.start();
        }
    }
}
