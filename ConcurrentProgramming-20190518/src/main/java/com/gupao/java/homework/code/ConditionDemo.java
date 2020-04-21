package com.gupao.java.homework.code;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Auther: lixiaotian
 * @Date: 2020/4/7 22:07
 * @Description:
 */
public class ConditionDemo {
    public static void await(Lock lock, Condition condition) {
        System.out.println("===start await===");
        try {
            lock.lock();
            condition.await();
            System.out.println(Thread.currentThread().isInterrupted());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
        System.out.println("===end await===");
    }

    public static void signal(Lock lock, Condition condition) {

        System.out.println("===start signal===");
        try {
            lock.lock();
            condition.signal();
        } finally {
            lock.unlock();
        }
        System.out.println("===end signal===");
    }

    public static void main(String[] args) {
        Lock lock = new ReentrantLock();
        Condition condition = lock.newCondition();
        new Thread(() -> await(lock, condition)).start();
        new Thread(() -> signal(lock, condition)).start();
    }
}
