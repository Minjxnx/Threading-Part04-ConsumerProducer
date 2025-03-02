package BlockingQueue;

public class Producer implements Runnable{

    private SingleSlotMailBox mailBox;

    public Producer(SingleSlotMailBox mailbox) {
        this.mailBox = mailbox;
    }

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            mailBox.put(i);
            System.out.println(Thread.currentThread().getName()+" produces "+i);

        }

    }
}
