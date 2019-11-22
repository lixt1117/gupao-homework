package com.gupao.java.homework.code.abstractFactory;

import com.gupao.java.homework.code.HuaweiComputer;
import com.gupao.java.homework.code.HuaweiPhone;
import com.gupao.java.homework.code.IComputer;
import com.gupao.java.homework.code.IPhone;

/**
 * @Auther: lixiaotian
 * @Date: 2019/11/3 00:57
 * @Description:
 */
public class HuaweiProductFactory implements IProductFactory {
    @Override
    public IPhone createPhone() {
        return new HuaweiPhone();
    }

    @Override
    public IComputer createComputer() {
        return new HuaweiComputer();
    }
}
