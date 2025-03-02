import java.io.PrintStream;

public class SingleSlotMailBox {

    private int value;
    private boolean availability = false;

    public SingleSlotMailBox() {
    }

    public synchronized void put(int value) {
        while(this.availability) {
            try {
                this.wait();
                PrintStream var10000 = System.out;
                String var10001 = Thread.currentThread().getName();
                var10000.println(var10001 + " waiting " + String.valueOf(Thread.currentThread().getState()));
            } catch (InterruptedException var3) {
                var3.printStackTrace();
                throw new RuntimeException(var3);
            }
        }

        this.value = value;
        this.availability = true;
        this.notifyAll();
    }

    public synchronized int get() {
        while(!this.availability) {
            try {
                this.wait();
            } catch (InterruptedException var2) {
                var2.printStackTrace();
                throw new RuntimeException(var2);
            }
        }

        this.availability = false;
        this.notifyAll();
        return this.value;
    }

}
