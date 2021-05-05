package org.example.base;

import java.util.Arrays;


public class HelloString {
    public static void main(String[] args) {
        StringBuilder buffer = new StringBuilder(32);
        buffer.append(0);
        buffer.append("123");
        buffer.append(456);

        System.out.printf("str = %s\n", buffer);
        System.out.printf("capacity = %d\n", buffer.capacity()); // 缓冲区容量
        System.out.printf("length = %d\n", buffer.length());
        System.out.printf("sub[2: 5] = %s\n", buffer.substring(2, 5)); // 0始，左闭右开
        System.out.printf("chars = %s\n", Arrays.toString(buffer.chars().toArray()));
        System.out.println("setChart"); buffer.setCharAt(0, 'A');
        System.out.printf("char[0] = %c\n", buffer.charAt(0));
        System.out.printf("str = %s\n", buffer);
        System.out.printf("reverse = %s\n", buffer.reverse());
    }
}
