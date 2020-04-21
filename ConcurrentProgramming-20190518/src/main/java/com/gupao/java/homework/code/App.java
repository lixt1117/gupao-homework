package com.gupao.java.homework.code;

/**
 * @Auther: lixiaotian
 * @Date: 2020/4/3 19:45
 * @Description:测试synchronized是否支持重入
 */
public class App {

    public void run1() {
        synchronized (this) {
            System.out.println("===run1 is running");
            this.run2();
        }
    }

    public void run2() {
        synchronized (this) {
            System.out.println("===run2 is running");
        }
    }

    public static void main(String[] args) {
        App app = new App();
        new Thread(() -> app.run1()).start();

    }
}
