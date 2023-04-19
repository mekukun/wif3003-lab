
package lab04;

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
            node.setValue(value);
        }
    }
}
