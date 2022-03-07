package com.example.concurrent.ch2.threadtools;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CyclicBarrier;

/**
 * @className:
 * @description: 演示CyclicBarrier用法, 共4个子线程，他们全部完成工作后，交出自己结果，
 * 再被统一释放去做自己的事情，而交出的结果被另外的线程拿来拼接字符串
 * 注意：线程数量 = 设置的
 * @author: Bruce_T
 * @date: 2022/03/07   20:52
 * @version: 1.0
 * @modified:
 */
public class UseCyclicBarrier {

    //定义一个CyclicBarrier实例
    private static CyclicBarrier barrier
            = new CyclicBarrier(4, new CollectThread());

    //存放子线程工作结果的容器
    private static ConcurrentHashMap<String, Long> resultMap
            = new ConcurrentHashMap<>();


    public static void main(String[] args) {
        for (int i = 0; i < 4; i++) {
            Thread thread = new Thread(new SubThread());
            thread.start();
        }
    }

    /*汇总的任务*/
    private static class CollectThread implements Runnable {

        @Override
        public void run() {
            StringBuilder result = new StringBuilder();
            for (Map.Entry<String, Long> workResult : resultMap.entrySet()) {
                result.append("[" + workResult.getValue() + "]");
            }
            System.out.println(" the result = " + result);
            System.out.println("do other business........");
        }
    }

    /*相互等待的子线程*/
    private static class SubThread implements Runnable {

        @Override
        public void run() {
            long id = Thread.currentThread().getId();
            resultMap.put(Thread.currentThread().getId() + "", id);
            try {
                Thread.sleep(1000 + id);
                System.out.println("Thread_" + id + " ....do something ");
                //工作线程一旦调用了await ，会在barrier上面等待，知道所有的线程全部调用await之后
                barrier.await();
                Thread.sleep(1000 + id);
                System.out.println("Thread_" + id + " ....do its business ");
//                barrier.await();
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }
}
