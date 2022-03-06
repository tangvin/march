package com.example.concurrent.ch1.threadlocal;

import com.example.concurrent.utils.SleepTools;

/**
 * @className:
 * @description: 类说明：ThreadLocal的线程不安全演示
 * @author: Bruce_T
 * @data: 2022/03/06   9:18
 * @version: 1.0
 * @modified:
 */
public class ThreadLocalUnsafe implements Runnable{

    //static 导致所有的ThreadLocal 中存贮 的是同一个对像的引用
//    public static Number number = new Number(0);
    //此时的 number 是 不共享的
    public  Number number = new Number(0);

    public static ThreadLocal<Number> value = new ThreadLocal<Number>() {

        @Override
        protected Number initialValue() {
            return new Number(0);
        }
    };

    public void run() {
        //每个线程计数加一
        number.setNum(number.getNum()+1);
        //将其存储到ThreadLocal中
        value.set(number);
        SleepTools.ms(2);
        //输出num值
        System.out.println(Thread.currentThread().getName()+"="+value.get().getNum());
    }

    public static void main(String[] args) {
        for (int i = 0; i < 5; i++) {
            new Thread(new ThreadLocalUnsafe()).start();
        }
    }

    private static class Number {
        public Number(int num) {
            this.num = num;
        }

        private int num;

        public int getNum() {
            return num;
        }

        public void setNum(int num) {
            this.num = num;
        }

        @Override
        public String toString() {
            return "Number [num=" + num + "]";
        }
    }
}
