
package lab04;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Node<T> {

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
            task.run();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
}