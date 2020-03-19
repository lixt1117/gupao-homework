package com.gupao.java.homework.code;

import java.util.Random;

/**
 * @Auther: lixiaotian
 * @Date: 2020/3/19 10:15
 * @Description:利用wait和notify实现生产者消费者模式
 */
public class WaitAndNotifyDemo {

    static ProductFactory productFactory = new ProductFactory();

    // 产品生产者
    public static void productProvider() {
        int index = 0;
        while (true) {
            synchronized (productFactory.productQueue) {
                index++;
                // 调用消费
                int result = productFactory.createProduct(index);
                if (result == ProductFactory.SUSPEND_PRODUCE) {
                    try {
                        System.out.println("-------暂停生产,恢复消费-------");
                        productFactory.productQueue.notifyAll();
                        productFactory.productQueue.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                if (result == ProductFactory.STOP_PRODUCE) {
                    System.out.println("-------停止生产-------");
                    productFactory.productQueue.notifyAll();
                    break;
                }
            }

        }
    }

    // 产品消费者
    static public void productConsumer() {
        int index = 0;
        while (true) {
            synchronized (productFactory.productQueue) {
                index++;
                int count = new Random().nextInt(4) + 1;
                int result = productFactory.consumerProduct(index, count);
                if (result == ProductFactory.SUSPEND_CONSUMER) {
                    try {
                        System.out.println("-------暂停消费,恢复生产-------");
                        productFactory.productQueue.notifyAll();
                        productFactory.productQueue.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                if (result == ProductFactory.STOP_CONSUMER) {
                    System.out.println("-------停止消费-------");
                    break;
                }
            }
        }
    }

    public static void main(String[] args) {
        Thread threadA = new Thread(() -> productProvider());
        Thread threadB = new Thread(() -> productConsumer());
        threadA.start();
        threadB.start();
    }

}
