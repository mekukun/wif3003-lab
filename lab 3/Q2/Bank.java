package Q2;

public class Bank {
    private int balance = 1000;

    public synchronized void deposit(int deposit) {
        balance = balance + deposit;
        System.out.println();
        System.out.println(Thread.currentThread().getName() + " deposits " + deposit);
        System.out.println("Balance: " + balance);
    }

    public synchronized void withdraw(int withdraw) {
        balance = balance - withdraw;
        System.out.println();
        System.out.println(Thread.currentThread().getName() + " withdraws " + withdraw);
        System.out.println("Balance: " + balance);
    }
}
