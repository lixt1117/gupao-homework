package com.gupao.java.classcode;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 腾讯课堂搜索 咕泡学院
 * 加群获取视频：608583947
 * 风骚的Michael 老师
 */
public class ReentrantLockDemo {

    static Lock lock=new ReentrantLock();
    //synchronized的原子操作改造成Lock

    public void demo() throws InterruptedException { //N线程来访问
        lock.lock(); //获得一个锁
        lock.unlock();// 释放锁
    }

    public static void main(String[] args) {

    }
}
