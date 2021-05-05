package org.example.base;


public class HelloClassWrapper {
    static void test(Integer num) {
        System.out.printf("Integer %d\n", num);
    }

    static void test(long num) {
        System.out.printf("long %d\n", num);
    }

    public static void main(String[] args) {
        // 扩展优先包装
        int i = 123;
        test(i);

        // 原始包装
        Integer j = i;
        test(j);
    }
}
