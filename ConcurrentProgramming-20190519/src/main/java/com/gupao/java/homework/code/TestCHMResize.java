package com.gupao.java.homework.code;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Auther: lixiaotian
 * @Date: 2020/5/10 16:10
 * @Description:
 */
public class TestCHMResize {
    public static void main(String[] args) {
        Map<String, String> chm = new ConcurrentHashMap<>();
        chm.put("1", "1");
        chm.put("2", "1");
        chm.put("3", "1");
        chm.put("4", "1");
        chm.put("5", "1");
        chm.put("6", "1");
        chm.put("7", "1");
        chm.put("8", "1");
        chm.put("9", "1");
        chm.put("10", "1");
        chm.put("11", "1");
    }
}
