package com.gupao.java.classcode;

import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.TimeUnit;

/**
 * 腾讯课堂搜索 咕泡学院 加群获取视频：608583947 风骚的Michael 老师
 */
public class CycliBarrierDemo extends Thread {
    @Override
    public void run() {
        System.out.println("开始进行数据分析" + Thread.currentThread().getName());
    }

    // 循环屏障
    // 可以使得一组线程达到一个同步点之前阻塞.

    public static void main(String[] args) {
        CyclicBarrier cyclicBarrier = new CyclicBarrier(3, new CycliBarrierDemo());
        new Thread(new DataImportThread(cyclicBarrier, "file1"), "f1").start();
        new Thread(new DataImportThread(cyclicBarrier, "file2"), "f2").start();
        new Thread(new DataImportThread(cyclicBarrier, "file3"), "f3").start();
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        new Thread(new DataImportThread(cyclicBarrier, "file4"), "f4").start();
        new Thread(new DataImportThread(cyclicBarrier, "file5"), "f5").start();
        new Thread(new DataImportThread(cyclicBarrier, "file6"), "f6").start();

    }

}
