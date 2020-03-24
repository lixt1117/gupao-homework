package com.gupao.java.classcode;

/**
 * 腾讯课堂搜索 咕泡学院
 * 加群获取视频：608583947
 * 风骚的Michael 老师
 */
public class SyncDemo {
    private App app=new App();
    public App getApp(){
        return app;
    }


    public void demo() {
        synchronized (this) {//ThreadA / ThreadB
        }
    }
}
