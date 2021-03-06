package com.gupao.java.homework.code.factoryMethod;

import com.gupao.java.homework.code.HuaweiComputer;
import com.gupao.java.homework.code.IComputer;

/**
 * @Auther: lixiaotian
 * @Date: 2019/11/3 00:51
 * @Description:
 */
public class HuaweiComputerFactory implements IComputerFactory {
    @Override
    public IComputer createComputer() {
        return new HuaweiComputer();
    }
}
