package com.gupao.java.classcode;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * 腾讯课堂搜索 咕泡学院
 * 加群获取视频：608583947
 * 风骚的Michael 老师
 */
public class DataImportThread extends Thread{

    private CyclicBarrier cyclicBarrier;

    private String path;

    public DataImportThread(CyclicBarrier cyclicBarrier, String path) {
        this.cyclicBarrier = cyclicBarrier;
        this.path = path;
    }

    @Override
    public void run() {
        System.out.println("开始导入："+path+" 数据");
        //TODO
        try {
            cyclicBarrier.await(); //阻塞 condition.await()
//            cyclicBarrier.await(); //阻塞 condition.await()
        System.out.println("完成导入："+path+" 数据");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (BrokenBarrierException e) {
            e.printStackTrace();
        }
    }
}
