package com.gupao.java.homework.code;

/**
 * @Auther: lixiaotian
 * @Date: 2019/11/2 18:25
 * @Description:
 */
public class ApplePhone implements IPhone {
    @Override
    public void call(String phoneNum) {
        System.out.println("用苹果手机打电话");
    }

    @Override
    public void answerCall(String phoneNum) {
        System.out.println("用苹果手机接电话");
    }
}
