package com.example.demo;

import org.junit.jupiter.api.Test;

import java.util.Random;

public class SomeTest {

    private final Random random = new Random();

    /**
     * java中有三种移位运算符
     *
     * <<      :     左移运算符，num << 1,相当于num乘以2
     *
     * >>      :     右移运算符，num >> 1,相当于num除以2
     *
     * >>>    :     无符号右移，忽略符号位，空位都以0补齐
     */
    @Test
    public void test1() {
        System.out.println(1 << 1);
        System.out.println(1 << 2);
        System.out.println(1 << 3);
        System.out.println(1 << 4);
        System.out.println(1 << 5);
        System.out.println(random.nextInt(1 << (1 + 1)));
        System.out.println(random.nextInt(1 << (2 + 1)));
        System.out.println(random.nextInt(1 << (3 + 1)));
        System.out.println(random.nextInt(1 << (4 + 1)));
        System.out.println(random.nextInt(1 << (5 + 1)));
        System.out.println(random.nextInt(1 << (6 + 1)));
        System.out.println(random.nextInt(1 << (7 + 1)));
        System.out.println(random.nextInt(1 << (8 + 1)));
        System.out.println(random.nextInt(1 << (9 + 1)));

        System.out.println("------------------------");
        System.out.println(Math.max(1, random.nextInt(1 << (1 + 1))));
        System.out.println(Math.max(1, random.nextInt(1 << (2 + 1))));
        System.out.println(Math.max(1, random.nextInt(1 << (3 + 1))));
        System.out.println(Math.max(1, random.nextInt(1 << (4 + 1))));
        System.out.println(Math.max(1, random.nextInt(1 << (5 + 1))));
    }
}
