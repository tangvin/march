package com.example.concurrent.ch2.forkjoin.sum;

import com.example.concurrent.utils.SleepTools;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

public class SumArray {
    private static class SumTask extends RecursiveTask<Integer>{

        //阈值：拆分到多小之后就可以不用再拆分
        private final static int THRESHOLD = MakeArray.ARRAY_LENGTH/10;
        private int[] src;
        private int fromIndex;
        private int toIndex;

        public SumTask(int[] src, int fromIndex, int toIndex) {
            this.src = src;
            this.fromIndex = fromIndex;
            this.toIndex = toIndex;
        }

        @Override
        protected Integer compute() {
            //判断任务的大小是否合适
            if (toIndex - fromIndex < THRESHOLD){
                System.out.println(" from index = "+fromIndex+" toIndex="+toIndex);
                int count = 0;
                for(int i= fromIndex;i<=toIndex;i++){
                	SleepTools.ms(1);
                     count = count + src[i];
                }
                return count;
            }else{
                //fromIndex....mid.....toIndex
                int mid = (fromIndex+toIndex)/2;
                SumTask left = new SumTask(src,fromIndex,mid);
                SumTask right = new SumTask(src,mid+1,toIndex);
                invokeAll(left,right);
                return left.join()+right.join();
            }
        }
    }


    public static void main(String[] args) {

        ForkJoinPool pool = new ForkJoinPool();
        //new出池的实例
        int[] src = MakeArray.makeArray();
        //new出Task实例
        SumTask innerFind = new SumTask(src,0,src.length-1);

        long start = System.currentTimeMillis();

        pool.invoke(innerFind);
        //System.out.println("Task is Running.....");

        System.out.println("The count is "+innerFind.join()
                +" spend time:"+(System.currentTimeMillis()-start)+"ms");

    }
}
