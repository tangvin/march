package com.example.concurrent.ch1.sync;

/**
 * @className:
 * @description: 类说明：错误的加锁和原因分析
 * @author: Bruce_T
 * @data: 2022/03/05   8:17
 * @version: 1.0
 * @modified:
 */
public class TestIntegerSyn {


    public static void main(String[] args) {
        Worker worker = new Worker(1);
        for (int i = 0; i < 5; i++) {
            new Thread(worker).start();
        }
    }


    private static class Worker implements Runnable {

        private Integer i;

        private Object object = new Object();

        public Worker(Integer i) {
            this.i = i;
        }

        @Override
        public void run() {
            synchronized (i) {
                Thread thread = Thread.currentThread();
                System.out.println(thread.getName() + " 第一次打印-" + System.identityHashCode(i) + "   i==" + i);
                i++;
                System.out.println(thread.getName() + " 第二次打印-" + System.identityHashCode(i) + "   i==" + i);
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(thread.getName() + " 第三次打印-" + System.identityHashCode(i) + "   i==" + i);
            }
        }
    }
}
