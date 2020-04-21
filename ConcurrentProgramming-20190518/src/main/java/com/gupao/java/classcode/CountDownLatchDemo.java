package com.gupao.java.classcode;

import java.util.concurrent.CountDownLatch;

/**
 * 腾讯课堂搜索 咕泡学院 加群获取视频：608583947 风骚的Michael 老师
 *
 *
 */
public class CountDownLatchDemo extends Thread {

    static CountDownLatch countDownLatch = new CountDownLatch(3);

    // public static void main(String[] args) {
    // for(int i=0;i<3;i++){
    // new CountDownLatchDemo().start();
    // }
    // countDownLatch.countDown();
    // }

    @Override
    public void run() {
        try {
            countDownLatch.await(); // 阻塞 3个线程 Thread.currentThread
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // TODO
        System.out.println("ThreadName:" + Thread.currentThread().getName());
    }

    public static void main(String[] args) throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(3);
        Thread thread1 = new Thread(() -> {
            System.out.println("Thread1");
            countDownLatch.countDown(); // 3-1=2
        });
        Thread thread2 = new Thread(() -> {
            System.out.println("Thread2");
            countDownLatch.countDown();// 2-1=1
        });
        Thread thread3 = new Thread(() -> {
            System.out.println("Thread3");
            countDownLatch.countDown();// 1-1=0
        });
        Thread threadMain1 = new Thread(() -> {
            try {
                countDownLatch.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Thread-main1 is over");
        });
        Thread threadMain2 = new Thread(() -> {
            try {
                countDownLatch.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Thread-main2 is over");
        });
        threadMain1.start();
        threadMain2.start();
        thread1.start();
        thread2.start();
        thread3.start();
        countDownLatch.await();
        System.out.println("Thread-main is over");
    }
}
