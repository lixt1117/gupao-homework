package com.gupao.java.classcode;

import java.util.Iterator;
import java.util.concurrent.ArrayBlockingQueue;

/**
 * 腾讯课堂搜索 咕泡学院
 * 加群获取视频：608583947
 * 风骚的Michael 老师
 */
public class BlockingDemo {

    ArrayBlockingQueue<String> ab=new ArrayBlockingQueue(10);//FIFO的队列
    {
        init(); //构造块初始化
    }

    public void init(){
        Iterator<String> it=ab.iterator();




        new Thread(()->{
            while(true) {
                try {
                    String data = ab.take(); //阻塞方式获得元素
                    System.out.println("receive:" + data);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    public void addData(String data) throws InterruptedException {
        ab.add(data);
        System.out.println("sendData:"+data);
        Thread.sleep(1000);
    }

    public static void main(String[] args) throws InterruptedException {
        BlockingDemo blockingDemo=new BlockingDemo();

        /*for(int i=0;i<1000;i++){
            blockingDemo.addData("data:"+i);
        }*/
    }

}
