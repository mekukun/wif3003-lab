
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class lab4 {

    public static void main(String[] args) {
        Node<Integer> node = new Node<>();
        Dummy dummy = new Dummy();
        
        Write write = new Write(node);
        Operate operate = new Operate(node, 3, dummy);

        Thread writeThread = new Thread(write);
        Thread operateThread = new Thread(operate);

        writeThread.start();
        operateThread.start();

        try {
            operateThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        write.stop();
    }

}

class Dummy implements Runnable {

    @Override
    public void run() {
        System.out.println("The desired value is found!");
    }
}

class Node<T> {

    private T value;
    private Lock lock;
    private Condition valueChanged;

    public Node() {
        this.value = null;
        this.lock = new ReentrantLock();
        this.valueChanged = lock.newCondition();
    }

    public void setValue(T newValue) {
        lock.lock();
        try {
            value = newValue;
            valueChanged.signalAll();
        } finally {
            lock.unlock();
        }
    }

    public void executeOnValue(T desiredValue, Runnable task) {
        lock.lock();
        try {
            while (!desiredValue.equals(value)) {
                valueChanged.await();
            }
            Thread t = new Thread(task);
            // task.run();
            t.start();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
}

class Operate implements Runnable {

    private Node<Integer> node;
    private int target;
    private Runnable dummy;
    private int count;

    public Operate(Node<Integer> node, int target, Runnable dummy) {
        this.node = node;
        this.target = target;
        this.dummy = dummy;
        this.count = 0;
    }

    @Override
    public void run() {
        while (count < 2) {
            node.executeOnValue(target, () -> {
                dummy.run();
                count++;
            });
        }
    }
}

class Write implements Runnable {

    private Node<Integer> node;
    private volatile boolean isRunning;

    public Write(Node<Integer> node) {
        this.node = node;
        this.isRunning = true;
    }

    public void stop() {
        this.isRunning = false;
    }

    @Override
    public void run() {
        while (isRunning) {
            int value = (int) ((Math.random() * (5 - 0)) + 0);
            System.out.print(value + " ");
            node.setValue(value);
        }
    }
}