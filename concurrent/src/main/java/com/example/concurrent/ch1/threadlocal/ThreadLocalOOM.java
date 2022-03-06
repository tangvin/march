package com.example.concurrent.ch1.threadlocal;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @className:
 * @description: 类说明：ThreadLocal造成的内存泄漏演示
 * @author: Bruce_T
 * @data: 2022/03/06   9:16
 * @version: 1.0
 * @modified:
 */
public class ThreadLocalOOM {

    private static final int TASK_LOOP_SIZE = 500;

    final static ThreadPoolExecutor poolExecutor
            = new ThreadPoolExecutor(5, 5, 1,
            TimeUnit.MINUTES,
            new LinkedBlockingQueue<>());

    static class LocalVariable {
        private byte[] a = new byte[1024*1024*5];/*5M大小的数组*/
    }

    final static ThreadLocal<LocalVariable> localVariable = new ThreadLocal<>();

    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i < TASK_LOOP_SIZE; ++i) {
            poolExecutor.execute(new Runnable() {
                public void run() {
                    localVariable.set(new LocalVariable());
//                    new LocalVariable();
                    System.out.println("use local variable");
                    //使用完一定要remove
                    localVariable.remove();
                }
            });

            Thread.sleep(100);
        }
        System.out.println("pool execute over");
    }

}
