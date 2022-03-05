package com.example.concurrent.ch1.sync;

/**
 * @className:
 * @description: 类说明：synchronized关键字的使用方法
 * @author: Bruce_T
 * @data: 2022/03/04   19:52
 * @version: 1.0
 * @modified:
 */
public class SyncTest {

    private long count = 0;
    private Object obj = new Object();//作为一个锁


    public long getCount() {
        return count;
    }

    public void setCount(long count) {
        this.count = count;
    }

    /**
     * 1-用在同步块上
     */
    public void incrementCount() {
        synchronized (obj){
            count++;
        }
    }


    /**
     * 2-用在方法上
     */
    public synchronized void incrementCount2() {
        count++;
    }

    /**
     * 用在同步块上
     */
    public void incrementCount3() {
        synchronized (this) {
            count++;
        }
    }

    private static class Count extends Thread {

        private SyncTest syncTest;

        public Count(SyncTest syncTest) {
            this.syncTest = syncTest;
        }

        @Override
        public void run() {
            for (int i = 0; i < 1000; i++) {
                syncTest.incrementCount();
            }
        }
    }


    public static void main(String[] args) throws InterruptedException {
        SyncTest syncTest = new SyncTest();
        //2个线程
        Count count1 = new Count(syncTest);
        Count count2 = new Count(syncTest);

        count1.start();
        count2.start();

        Thread.sleep(50);

        System.out.println(syncTest.count);
    }
}
