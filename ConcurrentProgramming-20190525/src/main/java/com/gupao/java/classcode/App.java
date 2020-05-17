package com.gupao.java.classcode;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * Hello world!
 *
 */
public class App implements Runnable
{

    @Override
    public void run() { //run执行完毕意味着线程被终止了
        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName());

    }

    static ExecutorService service=Executors.newFixedThreadPool(3);


    public static void main( String[] args ){
        App app=new App();
        app.run();

        ThreadPoolExecutor threadPoolExecutor=(ThreadPoolExecutor)service;
        threadPoolExecutor.prestartAllCoreThreads();

        service.execute(new App());



        /*for(int i=0;i<100;i++){
            service.execute(new App());
        }*/
        service.shutdown();
        service.shutdownNow();
    }
}
