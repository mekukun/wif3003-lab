
package lab04;

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
