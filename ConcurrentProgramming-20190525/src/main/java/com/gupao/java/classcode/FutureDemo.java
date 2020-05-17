package com.gupao.java.classcode;

import java.util.concurrent.*;

/**
 * 腾讯课堂搜索 咕泡学院
 * 加群获取视频：608583947
 * 风骚的Michael 老师
 */
public class FutureDemo implements Callable<String> {



    @Override
    public String call() throws Exception {
        System.out.println("execute:call");
        Thread.sleep(5000);
        return "Hello Call";
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        FutureDemo futureDemo=new FutureDemo();
        /*FutureTask futureTask=new FutureTask(futureDemo);
        new Thread(futureTask).start();*/
        ExecutorService executorService= Executors.newFixedThreadPool(3);
        Future future =executorService.submit(futureDemo);
        System.out.println(future.get()); //阻塞获取结果
    }
}
