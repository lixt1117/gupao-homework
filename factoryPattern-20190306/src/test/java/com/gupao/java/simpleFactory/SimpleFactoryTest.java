package com.gupao.java.simpleFactory;

import com.gupao.java.homework.code.AppleComputer;
import com.gupao.java.homework.code.ApplePhone;
import com.gupao.java.homework.code.HuaweiComputer;
import com.gupao.java.homework.code.HuaweiPhone;
import com.gupao.java.homework.code.simpleFactory.ProductFactory;
import org.junit.Test;

/**
 * @Auther: lixiaotian
 * @Date: 2019/11/11 20:05
 * @Description:
 */
public class SimpleFactoryTest {
    // private ProductFactory productFactory = new ProductFactory();
    @Test
    public void useAppleComputer() {
        ProductFactory.createComputer(AppleComputer.class).program();
    }

    @Test
    public void useHuaweiComputer() {
        ProductFactory.createComputer(HuaweiComputer.class).program();
    }

    @Test
    public void useApplePhone() {
        ProductFactory.createPhone(ApplePhone.class).call("111");
    }

    @Test
    public void useHuaweiPhone() {
        ProductFactory.createPhone(HuaweiPhone.class).answerCall("111");
    }

}
