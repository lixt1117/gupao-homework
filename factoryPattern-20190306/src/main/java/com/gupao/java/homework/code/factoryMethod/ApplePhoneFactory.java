package com.gupao.java.homework.code.factoryMethod;

import com.gupao.java.homework.code.ApplePhone;
import com.gupao.java.homework.code.IPhone;

/**
 * @Auther: lixiaotian
 * @Date: 2019/11/3 00:49
 * @Description:
 */
public class ApplePhoneFactory implements IPhoneFactory {
    @Override
    public IPhone createPhone() {
        return new ApplePhone();
    }
}
