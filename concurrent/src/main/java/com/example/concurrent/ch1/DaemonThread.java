package com.example.concurrent.ch1;

/*守护线程的使用*/
public class DaemonThread {

    private static class DaemonThreadTest extends Thread {
        @Override
        public void run() {
            try {
                while (!isInterrupted()) {
                    System.out.println("[" + Thread.currentThread().getName() + "] :I am extend Thread!");
                }
                System.out.println("[" + Thread.currentThread().getName() + "]:interrupt flag is " + isInterrupted());
            } finally {
                //守护线程中finally不一定起作用
                System.out.println("finally.................");
            }

        }
    }

    public static void main(String[] args) throws InterruptedException {
        DaemonThreadTest daemonThreadTest = new DaemonThreadTest();
        daemonThreadTest.setDaemon(true);
        daemonThreadTest.start();
        Thread.sleep(1000);
        daemonThreadTest.interrupt();
        System.out.println("name:"+daemonThreadTest.getName()+ "  interrupt:"+daemonThreadTest.isInterrupted());
    }
}
