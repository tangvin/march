package com.example.concurrent.utils;

import java.util.concurrent.TimeUnit;

/*线程休眠辅助工具类*/
public class SleepTools {

    /**
     * 按秒休眠
     *
     * @param seconds 秒数
     */
    public static void second(int seconds) {
        try {
            TimeUnit.SECONDS.sleep(seconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * 按毫秒数休眠
     *
     * @param seconds 毫秒数
     */
    public static void ms(int seconds) {
        try {
            TimeUnit.MILLISECONDS.sleep(seconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
