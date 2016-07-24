package com.lhs.thread.callable;

import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

/**
 * Created by abel on 16-7-14.
 */
public class CallableAndFeatureTest {
    public static void main(String[] args){
        //创建callable
        Callable<Integer> callable = new Callable<Integer>() {
            public Integer call() throws Exception {
                return new Random().nextInt(100);
            }
        };

        //创建futureTask，并将callable传进去
        FutureTask<Integer> futureTask = new FutureTask<Integer>(callable);

        //开启线程
        new Thread(futureTask).start();

        //其他的作业
        try{
            Thread.sleep(5000);
            System.out.println(futureTask.get());
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
