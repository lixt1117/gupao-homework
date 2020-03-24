package com.gupao.java.classcode;

/**
 * 腾讯课堂搜索 咕泡学院
 * 加群获取视频：608583947
 * 风骚的Michael 老师
 */
public class Demo {

    int a=0;
    volatile  boolean flag=false;

    public void writer(){ //线程A
        a=1;             //1
        flag=true;       //2

    }
    public void reader(){

        if(flag){  //3
            int x=a; //4
        }
    }

}
