package com.gupao.java.homework.code;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Auther: lixiaotian
 * @Date: 2020/4/20 16:12
 * @Description:
 */
public class MyBlockingQueueTest {
    static MyBlockingQueue myBlockingQueue = new MyBlockingQueue(5);

    public void put(int tName) {
        Thread.currentThread().setName(String.valueOf(tName));
        for (;;) {
            // System.out.println("put线程" + tName + "开始put===");
            myBlockingQueue.put((int)(Math.random() * 10));
            // System.out.println("put线程" + tName + "结束put===");
        }
    }

    public void get(int tName) {
        Thread.currentThread().setName(String.valueOf(tName));
        for (;;) {
            // System.out.println("take线程" + tName + "开始take===");
            myBlockingQueue.take();
            // System.out.println("take线程" + tName + "结束take===");
        }
    }

    public static void main(String[] args) {
        MyBlockingQueueTest myBlockingQueueTest = new MyBlockingQueueTest();
        for (AtomicInteger i = new AtomicInteger(1); i.get() <= 5; i.getAndIncrement()) {
            int tName = i.get();
            new Thread(() -> myBlockingQueueTest.put(tName)).start();
            new Thread(() -> myBlockingQueueTest.get(tName)).start();
            // System.out.println(i.get());
        }
    }
}
