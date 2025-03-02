package Synchronized;

// an instance of SingleSlotMailBox is the shared resource

public class SingleSlotMailBox {

    // value is the shared variable between producer and consumer
    private int value;
    // availability is to check if the value is available or not
    private boolean availability = false;

    // Producer call the put method
    public synchronized void put(int value) {
        while (availability) {
            try {
                wait();
                System.out.println(Thread.currentThread().getName()+" waiting "+Thread.currentThread().getState());
            } catch (InterruptedException e) {
                e.printStackTrace();
                throw new RuntimeException(e);
            }
        }
        // value variable is empty i.e availability is false
        // as a result producer can produce the value

        this.value = value;
        this.availability = true;
        notifyAll(); // notifies all the threads in the WAITING state on this monitor object
    }

    // Consumer call the get method
    public synchronized int get() {
        while(!availability) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
                throw new RuntimeException(e);
            }
        }
        // if availability is true then only the consumer thread reaches this point
        this.availability = false;
        this.notifyAll();
        return this.value;
    }
}
