package com.example.concurrent.ch1;

/**
 * @className:
 * @description: 类说明：测试Sleep对锁的影响
 *    yield():让当前运行线程回到可运行状态，以允许具有相同优先级的其他线程获得运行机会
 *    sleep(): 不会释放锁
 *    wait():会释放当前线程所持有的锁
 *
 *    notify()和notifyAll() 一般放到同步代码块的最后
 *
 * @author: Bruce_T
 * @data: 2022/03/06   16:56
 * @version: 1.0
 * @modified:
 */
public class SleepLock {

    private Object lock = new Object();

    public static void main(String[] args) {
        SleepLock sleepTest = new SleepLock();
        Thread threadA = sleepTest.new ThreadSleep();
        threadA.setName("ThreadSleep");
        Thread threadB = sleepTest.new ThreadNotSleep();
        threadB.setName("ThreadNotSleep");
        threadA.start();
        try {
            Thread.sleep(1000);
            System.out.println(" Main slept!");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        threadB.start();
    }

    private class ThreadSleep extends Thread{

        @Override
        public void run() {
            String threadName = Thread.currentThread().getName();
            System.out.println(threadName+" will take the lock");
            try {

                synchronized(lock) {
                    System.out.println(threadName+" taking the lock");
                    Thread.sleep(5000);
                    System.out.println("Finish the work: "+threadName);
                }
            } catch (InterruptedException e) {
                //e.printStackTrace();
            }
        }
    }

    private class ThreadNotSleep extends Thread{

        @Override
        public void run() {
            String threadName = Thread.currentThread().getName();
            System.out.println(threadName+" will take the lock time="+System.currentTimeMillis());
            synchronized(lock) {
                System.out.println(threadName+" taking the lock time="+System.currentTimeMillis());
                System.out.println("Finish the work: "+threadName);
            }
        }
    }
}
