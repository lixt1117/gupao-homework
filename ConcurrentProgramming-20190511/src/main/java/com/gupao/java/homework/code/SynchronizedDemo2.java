package com.gupao.java.homework.code;

import java.io.IOException;

/**
 * @Auther: lixiaotian
 * @Date: 2020/3/19 21:21
 * @Description:
 */
public class SynchronizedDemo2 {
    static Integer count=0;
    public static void incr(){
        synchronized (count) {
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            count++;
        }
    }
    public static void main(String[] args) throws IOException, InterruptedException {
        for(int i=0;i<1000;i++){
            new Thread(()->SynchronizedDemo2.incr()).start();
        }
        Thread.sleep(5000);
        System.out.println("result:"+count);
    }
}
