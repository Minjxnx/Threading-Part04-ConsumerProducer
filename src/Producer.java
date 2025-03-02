import java.io.PrintStream;

public class Producer implements Runnable{

    private SingleSlotMailBox mailBox;

    public Producer(SingleSlotMailBox mailbox) {
        this.mailBox = mailbox;
    }

    public void run() {
        for(int i = 0; i < 10; ++i) {
            this.mailBox.put(i);
            PrintStream var10000 = System.out;
            String var10001 = Thread.currentThread().getName();
            var10000.println(var10001 + " produces " + i);
        }

    }
}
