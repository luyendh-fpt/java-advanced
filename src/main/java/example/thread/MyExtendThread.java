package example.thread;

import java.util.Calendar;

public class MyExtendThread extends Thread {

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
}
