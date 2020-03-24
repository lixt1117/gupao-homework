package com.gupao.java.classcode;

import java.util.concurrent.TimeUnit;

/**
 * @Auther: lixiaotian
 * @Date: 2020/3/24 11:11
 * @Description:
 */
public class VolatileDemo {

    public /*volatile*/ static boolean stop = false;

    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(() -> {
            int i = 0;
            while (!stop) {
                i++;
            }
        });
        thread.start();
        System.out.println("begin start thread");
        TimeUnit.SECONDS.sleep(5);
        stop = true;
    }
}
