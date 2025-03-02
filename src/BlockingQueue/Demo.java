package BlockingQueue;

public class Demo {

    public static void main(String[] args) {
        // shared object between Producer and Consumer
        SingleSlotMailBox mailbox = new SingleSlotMailBox();

        Runnable producer = new Producer(mailbox);
        Runnable consumer = new Consumer(mailbox);

        Thread producerThread = new Thread(producer, "Producer");
        // Thread enters into the NEW state
        Thread consumerThread = new Thread(consumer, "Consumer");
        // Thread enters into the NEW state

        producerThread.start();
        consumerThread.start();
        // Thread enters into the RUNNABLE state
    }
}
