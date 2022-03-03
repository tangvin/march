package com.example.concurrent.ch1;

/**
 * startAndRun的区别
 * 线程的run()方法是由java虚拟机直接调用的，如果我们没有启动线程（没有调用线程的start()方法）而是在应用代码中直接调用run()方法，
 * 那么这个线程的run()方法其实运行在当前线程（即run()方法的调用方所在的线程）之中，而不是运行在其自身的线程中，从而违背了创建线程的初衷

 */
public class StartAndRun {

    private static class ThreadRun extends Thread {

        @Override
        public void run() {
            super.run();
            int i = 5;
            while (i > 0) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("I am [" + Thread.currentThread().getName() + "]====i===" + i--);
            }
        }
    }


    public static void main(String[] args) {
        ThreadRun threadRun = new ThreadRun();
        threadRun.setName("HelloWorld");
//        threadRun.run();
        threadRun.start();
    }

}
