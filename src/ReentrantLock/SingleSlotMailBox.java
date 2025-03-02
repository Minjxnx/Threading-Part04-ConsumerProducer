package ReentrantLock;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;
// an instance of SingleSlotMailBox is the shared resource

public class SingleSlotMailBox {


    // value is the shared variable between producer and consumer
    private int value;
    // availability is to check if the value is available or not
    private boolean availability = false;

    ReentrantLock lock = new ReentrantLock(true);

    Condition empty = lock.newCondition();// Consumers to be in wait state
    Condition full = lock.newCondition();// Producers to be in wait state
    // there will be a waitset on each of the above condition object


    // Producer call the put method
    public void put(int value) {
        try {
            lock.lock();
            while (availability) {
                try {
                    full.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    throw new RuntimeException(e);
                }
            }
            // value variable is empty i.e availability is false
            // as a result producer can produce the value

            this.value = value;
            this.availability = true;
            empty.signalAll();// all threads i.e consumer that is in WAITSET on empty condition will be given a signal
        } finally {
            lock.unlock();
        }

    }

    // Consumer call the get method
    public int get() {
        try {
            lock.lock();
            while(!availability) {
                try {
                    empty.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    throw new RuntimeException(e);
                }
            }
            // if availability is true then only the consumer thread reaches this point
            this.availability = false;
            full.signalAll();
            return this.value;
        } finally {
            lock.unlock();
        }

    }
}
