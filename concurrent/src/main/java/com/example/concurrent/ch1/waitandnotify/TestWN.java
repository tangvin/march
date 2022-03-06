package com.example.concurrent.ch1.waitandnotify;

/**
 * @className:
 * @description: 测试wait/notify/notifyAll
 * @author: Bruce_T
 * @data: 2022/03/06   10:08
 * @version: 1.0
 * @modified:
 */
public class TestWN {


    private static Express express = new Express(0,Express.CITY);

    /*检查里程数变化的线程,不满足条件，线程一直等待*/
    private static class CheckKm extends Thread{
        @Override
        public void run() {
            express.waitKm();
        }
    }

    /*检查地点变化的线程,不满足条件，线程一直等待*/
    private static class CheckSite extends Thread{
        @Override
        public void run() {
            express.waitSite();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        for(int i=0;i<3;i++){
            new CheckSite().start();
        }
        for(int i=0;i<3;i++){
            new CheckKm().start();
        }

        Thread.sleep(1000);
        express.changeSite();//目的地发生变化
        express.changeKm();//快递地点变化

    }
}
