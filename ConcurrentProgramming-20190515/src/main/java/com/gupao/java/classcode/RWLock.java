package com.gupao.java.classcode;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 腾讯课堂搜索 咕泡学院 加群获取视频：608583947 风骚的Michael 老师
 */
public class RWLock {

    static ReentrantReadWriteLock wrl = new ReentrantReadWriteLock();

    static Map<String, Object> cacheMap = new HashMap<>();

    static Lock read = wrl.readLock();
    static Lock write = wrl.writeLock();

    // 线程B/C/D
    public static final Object get(String key) {
        Object result = null;
        read.lock(); // 获得读锁-> 阻塞
        System.out.println("begin read data:" + key);
        try {
            TimeUnit.SECONDS.sleep(2);
            result = cacheMap.get(key);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            System.out.println("end read data:" + key);
            read.unlock();
        }
        return result;
    }

    // 线程A
    public static final void put(String key, Object val) {
        write.lock();// 获得了写锁
        System.out.println("begin write data:" + key);
        try {
            TimeUnit.SECONDS.sleep(2);
            cacheMap.put(key, val);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            System.out.println("end write data:" + key);
            write.unlock();
        }
    }

    public static void main(String[] args) {
        wrl.readLock();// B线程 ->阻塞

        wrl.writeLock(); // A线程

        Thread threadA1 = new Thread(() -> get("B1"));
        Thread threadA2 = new Thread(() -> get("B2"));
        Thread threadB1 = new Thread(() -> put("B1", "B1"));
        Thread threadB2 = new Thread(() -> put("B2", "B2"));

        threadB1.start();
        threadB2.start();

        threadA1.start();
        threadA2.start();

        // 读->读是可以共享
        // 读->写 互斥
        // 写->写 互斥
        // 读多写少的场景
    }

}
