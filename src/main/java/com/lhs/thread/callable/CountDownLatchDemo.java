package com.lhs.thread.callable;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.CountDownLatch;

/**
 * Created by abel on 16-7-17.
 */
public class CountDownLatchDemo {
    final static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    public static void main(String[] args) throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(2);
        Worker worker1 = new Worker("work1",3000,latch);
        Worker worker2 = new Worker("work2",2000,latch);
        worker1.start();
        worker2.start();
        latch.await();

        System.out.println("all work done");
    }

    static class Worker extends Thread{
        String workName;
        int workTime;
        CountDownLatch latch;
        public Worker(String name,int time,CountDownLatch latch){
            this.workName = name;
            this.workTime = time;
            this.latch = latch;
        }

        @Override
        public void run() {
            System.out.println("work "+workName +" do work begin at "+sdf.format(System.currentTimeMillis()));
            doWork();
            System.out.println("work "+workName +" end at "+sdf.format(System.currentTimeMillis()));
            latch.countDown();
        }

        private void doWork(){
            try {
                Thread.sleep(workTime);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
