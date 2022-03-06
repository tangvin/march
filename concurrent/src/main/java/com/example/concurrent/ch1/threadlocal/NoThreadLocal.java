package com.example.concurrent.ch1.threadlocal;

/**
 * @className:
 * @description: 没有使用THreadLocal
 * @author: Bruce_T
 * @data: 2022/03/06   9:05
 * @version: 1.0
 * @modified:
 */
public class NoThreadLocal {

    static Integer count = new Integer(1);

    /**
     * 运行3个线程
     */
    public void StartThreadArray(){
        Thread[] runs = new Thread[3];
        for(int i=0;i<runs.length;i++){
            runs[i]=new Thread(new TestTask(i));
        }
        for(int i=0;i<runs.length;i++){
            runs[i].start();
        }
    }


    /**
     *类说明：
     */
    public static class TestTask implements Runnable{
        int id;
        public TestTask(int id){
            this.id = id;
        }
        public void run() {
            System.out.println(Thread.currentThread().getName()+":start");
            count = count+id;
            System.out.println(Thread.currentThread().getName()+":"
                    +count);
        }
    }

    public static void main(String[] args){
        NoThreadLocal test = new NoThreadLocal();
        test.StartThreadArray();
    }
}
