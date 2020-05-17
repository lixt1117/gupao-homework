package com.gupao.java.classcode;

import java.util.concurrent.ArrayBlockingQueue;

/**
 * 腾讯课堂搜索 咕泡学院
 * 加群获取视频：608583947
 * 风骚的Michael 老师
 */
public class Demo {


    ArrayBlockingQueue ab=new ArrayBlockingQueue(10);

    public void init(){ //ThreadB ->worker

        new Thread(()->{
            while(true){
                try {
                    ab.take(); //阻塞获取
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();

        new Thread(()->{
            while(true){
                try {
                    ab.take(); //阻塞获取
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();

        new Thread(()->{
            while(true){
                try {
                    ab.take(); //阻塞获取
                    //task.run();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();

    }

    public void sendData(){ //ThreadA
        ab.add("task");
    }
}
