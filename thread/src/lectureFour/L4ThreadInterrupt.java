package lectureFour;

/**
 * @Author Muhammad Saimon
 * @since Sep 04, 2024 9:49 PM
 */

 // Another way of Interrupt without using Exception
class L4Task implements Runnable {
    @Override
    public void run() {
        System.out.println("Start Task...");

        long count = 0;

        // Difference between isInterrupted() and interrupted()
        // isInterrupted()::
        // The isInterrupted method simply returns the value of this interrupted field. If the thread has been interrupted, the method returns true; otherwise, it returns false.
        // This method is useful for checking the interrupted status of a thread without clearing the interrupted status flag.
        // This is in contrast to the static interrupted method, which not only checks but also clears the interrupted status of the current thread.

        // interrupted()::
        // The interrupted method is a static method that checks the interrupted status of the current thread.
        // If the current thread has been interrupted, the method returns true; otherwise, it returns false.
        // This method clears the interrupted status of the current thread. If the interrupted status is cleared, the method returns false.
        // This is different from the isInterrupted method, which only checks the interrupted status without clearing it.

        System.out.println("Interrupted status beginning: " + Thread.interrupted());
        while (!Thread.interrupted()) {
//            System.out.println("Interrupted status inside: " + Thread.interrupted());
            count++;
        }

        // return false because the interrupted status is cleared by interrupted() method
        System.out.println("Interrupted status: " + Thread.interrupted());
        System.out.println("Do some task..." + count);
        System.out.println("End Task...");
    }
}

public class L4ThreadInterrupt {

    public static int value = 0;

    public static void main(String[] args) throws Exception {


        // Interrupt using Exception
        Thread t1 = new Thread(() -> {
            System.out.println("Start Task inside...");
            try {
                Thread.sleep(10_000);
            } catch (InterruptedException e) {
                System.out.println("Thread is interrupted...");
                e.printStackTrace();
            }
            System.out.println("Do some task..." + value);
            System.out.println("End Task inside...");
        }, "Thread-1");



        System.out.println("Start Main...");

        L4Task task = new L4Task();
        Thread t2 = new Thread(task, "Thread-2");

        // t1.start();
        t2.start();

        Thread.sleep(4_000);

        value = 20;

        // t1.interrupt();
         t2.interrupt(); // flag is set to true

        System.out.println("state: " + t2.getState());
        System.out.println("Main is completed...");
    }
}
