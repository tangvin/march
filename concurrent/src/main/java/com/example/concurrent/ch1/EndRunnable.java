package com.example.concurrent.ch1;

import lombok.extern.slf4j.Slf4j;

/**
 * @className: EndRunnable
 * @description:
 * @author: Bruce_T
 * @date: 2022/03/02   22:21
 * @version: 1.0
 * @modified:
 */
@Slf4j
public class EndRunnable {
    private static class UseRunnable implements Runnable {

        @Override
        public void run() {
//            while(!Thread.currentThread().interrupted()){
            while (!Thread.currentThread().isInterrupted()) {
                log.info(Thread.currentThread().getName() + " I am implements Runnable!");
            }
            log.info(Thread.currentThread().getName() + " last interrupt flag is " + Thread.currentThread().isInterrupted());
        }
    }


    public static void main(String[] args) throws InterruptedException {
        UseRunnable useRunnable = new UseRunnable();
        Thread endThread = new Thread(useRunnable, "endThread");
        endThread.start();
        Thread.sleep(2);
        endThread.interrupt();
    }
}
