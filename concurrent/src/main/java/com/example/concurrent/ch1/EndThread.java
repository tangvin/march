package com.example.concurrent.ch1;

import lombok.extern.slf4j.Slf4j;

/**
 * @className:
 * @description:
 * @author: Bruce_T
 * @date: 2022/03/02   21:42
 * @version: 1.0
 * @modified:
 */
@Slf4j
public class EndThread {

    public static class UseThread extends Thread{

        public UseThread(String name) {
            super(name);
        }

        /**
         * 调用interrupt() 中断线程之后，中断标志位被置为true
         * 注意此处的区别：
         * isInterrupted(): 判断当前线程是否被中断
         * Thread.interrupted() :行判断当前线程是否被中断,同时将中断标识位改写为 false
         */
        @Override
        public void run() {
            super.run();
            String threadName = Thread.currentThread().getName();
            log.info(threadName + " interrupt flag :{}" , isInterrupted());
//            while (true) {
//            while (!isInterrupted()){
            while(!Thread.interrupted()){
                log.info(threadName + " is running!");
                log.info(threadName + " inner interrupt flag :{}" , isInterrupted());
            }
            log.info(threadName + " last interrupt flag :{}" , isInterrupted());

        }

        public static void main(String[] args) throws InterruptedException {
            UseThread endThread = new UseThread("endThread");
            endThread.start();
            Thread.sleep(20);
            endThread.interrupt();
        }
    }
}
