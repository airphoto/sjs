package com.lhs.thread.callable;

/**
 * Created by abel on 16-7-19.
 */
public class DeadLockTest implements Runnable{
    private int flag;
    static Object o1 = new Object(),o2 = new Object(); //静态的对象，被DeadLockTest的所有实例对象所公用

    public void run() {
        System.out.println(flag);
        if(flag == 0){
            synchronized (o1){
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (o2){

                }
            }
        }

        if(flag == 1){
            synchronized (o2){
                try {
                    Thread.sleep(200);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (o1){

                }
            }
        }
    }

    /**
     * 解释：在main方法中，实例化了两个实现了Runnable接口的DeadLockTest对象test1和test2，test1的flag等于1，
     * 所以在thread1线程执行的时候执行的是run()方法后半部分的代码，test2的flag等于2，
     * 所以在thread2线程启动的时候执行的是run()方法前半部分的代码，
     * 此时，出现了下列现象：thread1线程占有了o1对象并等待o2对象，
     * 而thread2线程占有了o2对象并等待o1对象，而o1和o2又被这俩个线程所共享，所以就出现了死锁的问题了。
     * @param args
     */
    public static void main(String[] args){
        DeadLockTest test1 = new DeadLockTest();
        DeadLockTest test2 = new DeadLockTest();
        test1.flag = 0;
        test2.flag = 1;
        new Thread(test1).start();
        new Thread(test2).start();
    }
}
