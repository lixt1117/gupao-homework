package com.gupao.java.classcode;

/**
 * 腾讯课堂搜索 咕泡学院
 * 加群获取视频：608583947
 * 风骚的Michael 老师
 */
public class StartRule {

    static int x=0;

    public static void main(String[] args) {
        Thread t1=new Thread(()->{
            //use x=10
        });

        x=10;

        t1.start();
    }
}
