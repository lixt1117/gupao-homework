package com.gupao.java.factoryMethod;

import com.gupao.java.homework.code.factoryMethod.*;
import org.junit.Test;

/**
 * @Auther: lixiaotian
 * @Date: 2019/11/11 20:46
 * @Description:
 */
public class FactoryMethodTest {
    IComputerFactory appleComputerFactory = new AppleComputerFactory();
    IComputerFactory huaweiComputerFactory = new HuaweiComputerFactory();
    IPhoneFactory applePhoneFactory = new ApplePhoneFactory();
    IPhoneFactory huaweiPhoneFactory = new HuaweiPhoneFactory();
    @Test
    public void useAppleComputer() {
        appleComputerFactory.createComputer().program();
    }

    @Test
    public void useHuaweiComputer() {
        huaweiComputerFactory.createComputer().program();
    }

    @Test
    public void useApplePhone() {
        applePhoneFactory.createPhone().call("111");
    }

    @Test
    public void useHuaweiPhone() {
        huaweiPhoneFactory.createPhone().answerCall("111");
    }
}
