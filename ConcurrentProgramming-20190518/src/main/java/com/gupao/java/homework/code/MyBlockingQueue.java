package com.gupao.java.homework.code;

import java.util.LinkedList;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Auther: lixiaotian
 * @Date: 2020/4/20 15:49
 * @Description:利用ReentrantLock和Condition实现阻塞队列
 */
public class MyBlockingQueue {
    // 存储队列的上限
    private int maxCount;
    // 存储队列
    private LinkedList queue = new LinkedList();
    // 等待队列的锁
    private final Lock lock = new ReentrantLock();
    // put等待队列
    private Condition putCondition = lock.newCondition();
    // take等待队列
    private Condition takeCondition = lock.newCondition();

    public MyBlockingQueue(int maxCount) {
        this.maxCount = maxCount;
    }

    public void put(int name) {
        lock.lock();
        System.out.println("put线程" + Thread.currentThread().getName() + "开始put" + name + "---");
        try {
            for (;;) {
                if (queue.size() < maxCount) {
                    queue.add(name);
                    TimeUnit.SECONDS.sleep((int)(Math.random() * 2));
                    if (queue.size() == maxCount) {
                        takeCondition.signalAll();
                    }
                    System.out.println("put线程" + Thread.currentThread().getName() + "完成put" + name + "+++");
                    return;
                } else {
                    System.out.println("put线程" + Thread.currentThread().getName() + "队列已满，暂停put" + name + "｜｜｜");
                    putCondition.await();

                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }

    }

    public void take() {
        lock.lock();
        System.out.println("take线程" + Thread.currentThread().getName() + "开始take" + "---");
        try {
            for (;;) {
                if (queue.size() != 0) {
                    int name = (int)queue.removeFirst();
                    TimeUnit.SECONDS.sleep((int)(Math.random() * 2));
                    if (queue.size() == 0) {
                        putCondition.signalAll();
                    }
                    System.out.println("take线程" + Thread.currentThread().getName() + "完成take" + name + "---");
                    return;
                } else {
                    System.out.println("take线程" + Thread.currentThread().getName() + "队列已空，暂停take｜｜｜");
                    takeCondition.await();
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
}
