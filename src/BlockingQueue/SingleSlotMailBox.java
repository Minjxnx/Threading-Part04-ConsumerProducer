package BlockingQueue;


import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

// an instance of SingleSlotMailBox is the shared resource
public class SingleSlotMailBox {

    // value is the shared variable between producer and consumer
    BlockingQueue<Integer> queue = new LinkedBlockingQueue<Integer>(1);
    // as long as you pass the capacity as the parameter it is fixed in size - fixed capacity
    // if you do not specify a value to capacity then the queue will grow dynamically


    // Producer call the put method
    public void put(int value) {
        try {
            queue.put(value);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    // Consumer call the get method
    public int get() {
        int value;
        try {
            value = queue.take();
        } catch (InterruptedException e) {
            value = -1;
            e.printStackTrace();
        }
        return value;
    }
}
