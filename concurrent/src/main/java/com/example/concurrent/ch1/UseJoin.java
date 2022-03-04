package com.example.concurrent.ch1;

import com.example.concurrent.utils.SleepTools;

/**
 * @className:
 * @description:
 * @author: Bruce_T
 * @data: 2022/03/04   18:22
 * @version: 1.0
 * @modified:
 */
public class UseJoin {

    static class Goddess implements Runnable {

        private Thread thread;

        public Goddess(Thread thread) {
            this.thread = thread;
        }

        public Goddess() {

        }

        @Override
        public void run() {
            System.out.println("Goddess开始排队打饭.....");
//            if (thread != null) {
                try {
                    if(thread != null)
                    thread.join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                SleepTools.second(2);
                System.out.println(Thread.currentThread().getName() + " Goddess打饭完成.");
//            }
        }
    }

    static class GoddessBoyfriend implements Runnable {
        @Override
        public void run() {
            SleepTools.second(2);//休眠2秒
            System.out.println("GoddessBoyfriend开始排队打饭.....");
            System.out.println(Thread.currentThread().getName() + " GoddessBoyfriend打饭完成.");
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Thread thread1 = Thread.currentThread();
        GoddessBoyfriend goddessBoyfriend = new GoddessBoyfriend();

        Thread gbf = new Thread(goddessBoyfriend);
        Goddess goddess = new Goddess(gbf);
//        Goddess goddess = new Goddess();
        Thread g = new Thread(goddess);
        g.start();
        gbf.start();
        System.out.println("lison开始排队打饭.....");
        g.join();
        SleepTools.second(2);//让主线程休眠2秒
        System.out.println(Thread.currentThread().getName() + " lison打饭完成.");

    }
}
