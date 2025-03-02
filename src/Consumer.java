import java.io.PrintStream;

public class Consumer implements Runnable{

    private SingleSlotMailBox mailBox;

    public Consumer(SingleSlotMailBox mailbox) {
        this.mailBox = mailbox;
    }

    public void run() {
        int value;
        do {
            value = this.mailBox.get();
            PrintStream var10000 = System.out;
            String var10001 = Thread.currentThread().getName();
            var10000.println(var10001 + " consumes " + value);
        } while(value != 9);

    }

}
