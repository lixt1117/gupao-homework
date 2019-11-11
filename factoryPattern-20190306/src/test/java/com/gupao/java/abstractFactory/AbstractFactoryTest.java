package com.gupao.java.abstractFactory;

import com.gupao.java.homework.code.abstractFactory.AppleProductFactory;
import com.gupao.java.homework.code.abstractFactory.HuaweiProductFactory;
import com.gupao.java.homework.code.abstractFactory.IProductFactory;
import org.junit.Test;

/**
 * @Auther: lixiaotian
 * @Date: 2019/11/11 20:51
 * @Description:
 */
public class AbstractFactoryTest {
    IProductFactory appleProductFactory = new AppleProductFactory();
    IProductFactory huaweiProductFactory = new HuaweiProductFactory();
    @Test
    public void useAppleComputer() {
        appleProductFactory.createComputer().program();
    }

    @Test
    public void useHuaweiComputer() {
        huaweiProductFactory.createComputer().program();
    }

    @Test
    public void useApplePhone() {
        appleProductFactory.createPhone().call("111");
    }

    @Test
    public void useHuaweiPhone() {
        huaweiProductFactory.createPhone().answerCall("111");
    }
}
