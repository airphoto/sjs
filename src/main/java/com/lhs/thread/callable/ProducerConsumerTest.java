package com.lhs.thread.callable;

import com.sun.org.apache.xpath.internal.SourceTree;

/**
 * Created by abel on 16-7-18.
 */
public class ProducerConsumerTest {
    public static void main(String[] args){
        PublicResource resource = new PublicResource();
        new Thread(new ProducerThread(resource)).start();
        new Thread(new ConsumerThread(resource)).start();
        new Thread(new ProducerThread(resource)).start();
        new Thread(new ConsumerThread(resource)).start();
        new Thread(new ProducerThread(resource)).start();
        new Thread(new ConsumerThread(resource)).start();
        System.out.println("ok");
    }
}

class PublicResource {
    private int number = 0;

    /**
     * 增加公共资源
     */
    public synchronized void increace() {
        while (number != 0) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        number++;
        System.out.println(number);
        notify();
    }

    /**
     * 减少公共资源
     */
    public synchronized void decreace() {
        while (number == 0) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        number--;
        System.out.println(number);
        notify();
    }
}


class ProducerThread implements Runnable {
    private PublicResource resource;

    public ProducerThread(PublicResource resource) {
        this.resource = resource;
    }

    public void run() {
        for (int i = 0; i < 10; i++) {
            try {
                Thread.sleep((long) (Math.random() * 1000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            resource.increace();
        }
    }
}


class ConsumerThread implements Runnable {
    private PublicResource resource;

    public ConsumerThread(PublicResource resource) {
        this.resource = resource;
    }

    public void run() {
        for (int i = 0; i < 10; i++) {
            try {
                Thread.sleep((long) (Math.random() * 1000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            resource.decreace();
        }
    }
}