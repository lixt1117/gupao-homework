package com.gupao.java.homework.code.abstractFactory;

import com.gupao.java.homework.code.AppleComputer;
import com.gupao.java.homework.code.ApplePhone;
import com.gupao.java.homework.code.IComputer;
import com.gupao.java.homework.code.IPhone;

/**
 * @Auther: lixiaotian
 * @Date: 2019/11/3 00:58
 * @Description:
 */
public class AppleProductFactory implements IProductFactory {
    @Override
    public IPhone createPhone() {
        return new ApplePhone();
    }

    @Override
    public IComputer createComputer() {
        return new AppleComputer();
    }
}
