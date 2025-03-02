import java.io.PrintStream;

public class Demo {
    public Demo() {
    }

    public static void main(String[] args) {
        SingleSlotMailBox mailbox = new SingleSlotMailBox();
        Runnable producer = new Producer(mailbox);
        Runnable consumer = new Consumer(mailbox);
        Thread producerThread = new Thread(producer, "Producer");
        PrintStream var10000 = System.out;
        String var10001 = producerThread.getName();
        var10000.println("After creating the new Thread " + var10001 + " Current State " + String.valueOf(producerThread.getState()));
        Thread consumerThread = new Thread(consumer, "Consumer");
        producerThread.start();
        consumerThread.start();
        var10000 = System.out;
        var10001 = producerThread.getName();
        var10000.println("After calling the start() " + var10001 + " Current State " + String.valueOf(producerThread.getState()));
        var10000 = System.out;
        var10001 = consumerThread.getName();
        var10000.println("After calling the start() " + var10001 + " Current State " + String.valueOf(consumerThread.getState()));

        try {
            Thread.sleep(1000L);
        } catch (InterruptedException var13) {
            var13.printStackTrace();
        }

        var10000 = System.out;
        var10001 = producerThread.getName();
        var10000.println("After 1000ms sleep() of main thread " + var10001 + " Current State " + String.valueOf(producerThread.getState()));
        Object o1 = new Object();
        Object o2 = new Object();
        Thread t1 = new Thread(() -> {
            synchronized(o1) {
                try {
                    Thread.sleep(1000L);
                } catch (InterruptedException var5) {
                    var5.printStackTrace();
                }

                synchronized(o2) {
                    System.out.println(Thread.currentThread().getName() + " Successfully obtained the lock on object 01 and 02");
                }

            }
        }, "T1");
        Thread t2 = new Thread(() -> {
            synchronized(o2) {
                try {
                    Thread.sleep(1000L);
                } catch (InterruptedException var5) {
                    var5.printStackTrace();
                }

                synchronized(o1) {
                    System.out.println(Thread.currentThread().getName() + " Successfully obtained the lock on object 01 and 02");
                }

            }
        }, "T2");
        t1.start();
        t2.start();
        var10000 = System.out;
        var10001 = t1.getName();
        var10000.println("The current state of Thread " + var10001 + " " + String.valueOf(t1.getState()));
        var10000 = System.out;
        var10001 = t2.getName();
        var10000.println("The current state of Thread " + var10001 + " " + String.valueOf(t2.getState()));

        try {
            Thread.sleep(1000L);
        } catch (InterruptedException var12) {
            var12.printStackTrace();
        }

        var10000 = System.out;
        var10001 = t1.getName();
        var10000.println("The current state of Thread after sleep() " + var10001 + " " + String.valueOf(t1.getState()));
        var10000 = System.out;
        var10001 = t2.getName();
        var10000.println("The current state of Thread after sleep() " + var10001 + " " + String.valueOf(t2.getState()));
        Thread producer1 = new Thread(() -> {
            for(int i = 20; i < 30; ++i) {
                mailbox.put(i);
                PrintStream var100000 = System.out;
                String var100001 = Thread.currentThread().getName();
                var100000.println(var100001 + " puts " + i);
            }

        }, "producer1");
        Thread consumer1 = new Thread(() -> {
            for(int i = 20; i < 30; ++i) {
                int value = mailbox.get();
                PrintStream var100000 = System.out;
                String var100001 = Thread.currentThread().getName();
                var100000.println(var100001 + " gets " + value);
                var100000 = System.out;
                var100001 = producer1.getName();
                var100000.println(var100001 + " Current State " + String.valueOf(producer1.getState()));
            }

        }, "consumer1");
        producer1.start();
        consumer1.start();
    }
}
