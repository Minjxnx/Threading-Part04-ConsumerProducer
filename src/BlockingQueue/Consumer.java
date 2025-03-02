package BlockingQueue;

public class Consumer implements Runnable{

    private SingleSlotMailBox mailBox;

    public Consumer(SingleSlotMailBox mailbox) {
        this.mailBox = mailbox;
    }

    @Override
    public void run() {
        while(true) {
            int value = mailBox.get();
            System.out.println(Thread.currentThread().getName()+" consumes "+value);
            if (value == 9) {
                break;
            }
        }

    }
}
