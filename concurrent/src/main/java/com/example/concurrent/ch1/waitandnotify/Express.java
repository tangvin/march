package com.example.concurrent.ch1.waitandnotify;

/**
 * @className:
 * @description: 类说明：快递实体类
 * @author: Bruce_T
 * @data: 2022/03/06   10:02
 * @version: 1.0
 * @modified:
 */
public class Express {


    public final static String CITY = "ShangHai";
    private int km;/*快递运输里程数*/
    private String site;/*快递到达地点*/

    public Express() {
    }

    public Express(int km, String site) {
        this.km = km;
        this.site = site;
    }


    /* 变化公里数，然后通知处于wait状态并需要处理公里数的线程进行业务处理*/
    public synchronized void changeKm() {
        this.km = 101;
        notifyAll();
//        notify();
    }

    /* 变化地点，然后通知处于wait状态并需要处理地点的线程进行业务处理*/
    public synchronized void changeSite() {
        this.site = "Beijing";
        notifyAll();
    }

    //线程等待公里数的变化
    public synchronized void waitKm() {
        while(this.km <= 100){
            try {
                wait();
                System.out.println("检查公里数的线程[" + Thread.currentThread().getId()
                        + "] is be notified");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("公里数大于100，是 " + this.km + " 公里 ,I will change db");
    }

    public synchronized void waitSite() {
        while (this.site.equals(CITY)) {//快递到达目的地
            try {
                wait();
                System.out.println("检查目的地的线程[" + Thread.currentThread().getId()
                        + "] is be notified");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("目的地有变化，目的地是 [ " + this.site + " ] , I will call user");
    }
}
