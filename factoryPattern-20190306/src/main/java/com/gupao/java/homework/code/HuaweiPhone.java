package com.gupao.java.homework.code;

/**
 * @Auther: lixiaotian
 * @Date: 2019/11/2 18:18
 * @Description:华为手机
 */
public class HuaweiPhone implements IPhone {

    @Override
    public void call(String phoneNum) {

        System.out.println("用华为手机打电话");

    }

    @Override
    public void answerCall(String phoneNum) {

        System.out.println("用华为手机接电话");

    }
}
