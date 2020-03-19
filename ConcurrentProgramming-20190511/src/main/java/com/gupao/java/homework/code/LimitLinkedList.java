package com.gupao.java.homework.code;

import java.util.LinkedList;

/**
 * @Auther: lixiaotian
 * @Date: 2020/3/19 16:06
 * @Description:
 */
public class LimitLinkedList<E> extends LinkedList<E> {
    private int maxSize;

    public LimitLinkedList(int maxSize) {
        this.maxSize = maxSize;
    }

    @Override
    public boolean offer(E e) {
        if (this.maxSize > this.size()) {
            return super.offer(e);
        }
        return false;
    }
}
