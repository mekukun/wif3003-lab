package Q2;

import java.util.Random;

public class Main {
    public static void main(String[] args) throws InterruptedException {

        Random rand = new Random();

        Bank bank = new Bank();

        for (int i = 0; i < 10; i++) {

            int amount = rand.nextInt(100);

            if (rand.nextInt(2) == 0) {
                Thread user = new Thread(() -> {
                    bank.withdraw(amount);
                });
                user.start();
            } else {
                Thread user = new Thread(() -> {
                    bank.deposit(amount);
                });
                user.start();
            }
        }
    }
}
