package example.thread;

import java.util.Calendar;

public class DemoThreadAgainV4 {
    public static void main(String[] args) {
//        // Way 1
//        MyExtendThread t = new MyExtendThread();
//        t.start();
//        MyExtendThread t1 = new MyExtendThread();
//        t1.start();
//        MyExtendThread t2 = new MyExtendThread();
//        t2.start();

//        // Way 2
        MyImplementRunable t1 = new MyImplementRunable();
//        Thread myThread1 = new Thread(t1);
//        myThread1.start();
//        Thread myThread2 = new Thread(t1);
//        myThread2.start();
//        Thread myThread3 = new Thread(t1);
//        myThread3.start();

        // Way 3, không chính thống.
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    System.out.println(Thread.currentThread().getName() + ":" + Calendar.getInstance().getTime());
                    try {
                        Thread.sleep(5 * 1000);
                    } catch (Exception ex) {
                        System.out.println("Mất ngủ.");
                    }
                }
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    System.out.println(Thread.currentThread().getName() + ":" + Calendar.getInstance().getTime());
                    try {
                        Thread.sleep(5 * 1000);
                    } catch (Exception ex) {
                        System.out.println("Mất ngủ.");
                    }
                }
            }
        }).start();

        new Thread(() -> {
            while (true) {
                System.out.println(Thread.currentThread().getName() + ":" + Calendar.getInstance().getTime());
                try {
                    Thread.sleep(5 * 1000);
                } catch (Exception ex) {
                    System.out.println("Mất ngủ.");
                }
            }
        }).start();
    }
}
