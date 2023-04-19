
package lab04;

public class Lab04 {

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
