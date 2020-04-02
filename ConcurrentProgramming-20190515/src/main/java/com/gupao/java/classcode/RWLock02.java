package com.gupao.java.classcode;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @Auther: lixiaotian
 * @Date: 2020/4/1 15:14
 * @Description:
 */
public class RWLock02 {
    static ReadWriteLock lock = new ReentrantReadWriteLock();
    static Lock readLock = lock.readLock();
    static Lock writeLock = lock.writeLock();

    public static void write() {
        writeLock.lock();
        try {
            System.out.println(Thread.currentThread().getName() + "开始写数据===");
            TimeUnit.SECONDS.sleep(5);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            System.out.println(Thread.currentThread().getName() + "结束写数据===");
            writeLock.unlock();
        }
    }

    public static void read() {
        readLock.lock();
        try {
            System.out.println(Thread.currentThread().getName() + "开始读数据===");
            for (;;) {
                System.out.println(Thread.currentThread().getName() + "正在读数据===");
                TimeUnit.SECONDS.sleep(1);

            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            System.out.println(Thread.currentThread().getName() + "结束读数据===");

        }
    }

    public static void main(String[] args) {
        new Thread(() -> read(), "A").start();
        new Thread(() -> read(), "B").start();
        new Thread(() -> read(), "C").start();
        try {
            TimeUnit.SECONDS.sleep(5);
            new Thread(() -> write(), "D").start();

        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
