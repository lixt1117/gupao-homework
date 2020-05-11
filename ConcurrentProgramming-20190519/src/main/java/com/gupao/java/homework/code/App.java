package com.gupao.java.homework.code;

public class App {
    static int MAXIMUM_CAPACITY = 1 << 30;

    static final int tableSizeFor(int cap) {
        int n = cap - 1;
        System.out.println(n);
        n |= n >>> 1;
        System.out.println(n);
        n |= n >>> 2;
        System.out.println(n);
        n |= n >>> 4;
        System.out.println(n);
        n |= n >>> 8;
        System.out.println(n);
        n |= n >>> 16;
        System.out.println(n);
        return (n < 0) ? 1 : (n >= MAXIMUM_CAPACITY) ? MAXIMUM_CAPACITY : n + 1;
    }
    static final int resizeStamp(int n) {
        return Integer.numberOfLeadingZeros(n) | (1 << (16 - 1));
    }
    public static void main(String[] args) {
//        System.out.println(MAXIMUM_CAPACITY);
        // System.out.println(0x7fffffff);
//        System.out.println(tableSizeFor(6));
        System.out.println(resizeStamp(16));
        System.out.println(resizeStamp(32));
        System.out.println((resizeStamp(16)<<16)&0x7fffffff);
        System.out.println((resizeStamp(16)<<16)+2);
        System.out.println(((resizeStamp(16)<<16)+2)>>>16);
    }

}
