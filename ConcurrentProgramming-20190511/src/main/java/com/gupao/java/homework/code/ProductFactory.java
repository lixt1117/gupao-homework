package com.gupao.java.homework.code;

import java.util.Queue;
import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * @Auther: lixiaotian
 * @Date: 2020/3/19 10:33
 * @Description:
 */
public class ProductFactory {
    // 生产产品的资源数量
    private Integer resources = 30;
    // 产品仓库队列
    volatile Queue<Product> productQueue = new LimitLinkedList<>(5);

    // 一切正常，生产成功
    static int CAN_PRODUCE = 1;
    // 仓库已满，暂停生产
    static int SUSPEND_PRODUCE = 2;
    // 资源不足，停止生产
    static int STOP_PRODUCE = 3;

    // 一切正常，消费成功
    static int CAN_CONSUMER = 1;
    // 仓库已空，暂停消费
    static int SUSPEND_CONSUMER = 2;
    // 资源不足，停止消费
    static int STOP_CONSUMER = 3;

    public int createProduct(int index) {
        System.out.println("=======准备生产第" + index + "件产品=======");
        if (resources > 0) {
            resources--;
            try {
                System.out.println("=======开始生产第" + index + "件产品=======");
                boolean result = productQueue.offer(new Product("产品" + index));
                if (result) {
                    // 睡个几秒钟，代表生产产品的时间消耗
                    TimeUnit.SECONDS.sleep(new Random().nextInt(5));
                    System.out.println("=======完成生产第" + index + "件产品=======");
                    return CAN_PRODUCE;
                } else {
                    resources++;
                    System.out.println("=======仓库已满=======");
                    return SUSPEND_PRODUCE;
                }

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("=======资源已耗尽，停止生产=======");
        return STOP_PRODUCE;
    }

    public int consumerProduct(int index, int count) {
        System.out.println("=======开始成第" + index + "消费，共消费" + count + "件产品=======");
        if (productQueue.size() >= count) {
            // 睡个几秒钟，代表消费产品的时间消耗
            try {
                TimeUnit.SECONDS.sleep(new Random().nextInt(5));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            for (int i = 0; i < count; i++) {
                productQueue.remove();
            }
            System.out.println("=======已完成第" + index + "消费，共消费" + count + "件产品=======");
            return CAN_CONSUMER;
        } else if (this.resources > 0) {
            System.out.println("=======仓库产品不足=======");
            return SUSPEND_CONSUMER;
        }

        System.out.println("=======资源已耗尽,停止消费=======");
        return STOP_CONSUMER;
    }

}
