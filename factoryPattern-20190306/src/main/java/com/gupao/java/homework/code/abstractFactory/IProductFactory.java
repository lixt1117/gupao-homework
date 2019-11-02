package com.gupao.java.homework.code.abstractFactory;

import com.gupao.java.homework.code.IComputer;
import com.gupao.java.homework.code.IPhone;

/**
 * @Auther: lixiaotian
 * @Date: 2019/11/3 00:53
 * @Description:
 */
public interface IProductFactory {
    IPhone createPhone();

    IComputer createComputer();
}
