package com.gupao.java.homework.code.factoryMethod;

import com.gupao.java.homework.code.HuaweiPhone;
import com.gupao.java.homework.code.IPhone;

/**
 * @Auther: lixiaotian
 * @Date: 2019/11/3 00:47
 * @Description:
 */
public class HuaweiPhoneFactory implements IPhoneFactory {
    @Override
    public IPhone createPhone() {
        return new HuaweiPhone();
    }
}
