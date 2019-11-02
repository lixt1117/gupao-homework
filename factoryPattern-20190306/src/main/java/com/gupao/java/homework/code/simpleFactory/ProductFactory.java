package com.gupao.java.homework.code.simpleFactory;

import com.gupao.java.homework.code.IComputer;
import com.gupao.java.homework.code.IPhone;

/**
 * @Auther: lixiaotian
 * @Date: 2019/11/2 18:32
 * @Description:手机创建工厂
 */
public class ProductFactory {
    /**
     *
     * @Description:手机创建方法
     * @Param:
     * @Return:
     * @auther: lixiaotian
     * @date: 2019/11/2 18:46
     */
    public static IPhone createPhone(Class<? extends IPhone> clazz) {
        if (null != clazz) {
            try {
                return clazz.newInstance();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    /**
     *
     * @Description:电脑创建方法
     * @Param:
     * @Return:
     * @auther: lixiaotian
     * @date: 2019/11/2 18:47
     */
    public static IComputer createComputer(Class<? extends IComputer> clazz) {
        if (null != clazz) {
            try {
                return clazz.newInstance();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return  null;
    }

}
