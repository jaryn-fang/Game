package com.jhcoder.test;

import java.util.Random;

public class RandomTest {

    public static void main(String[] args) {
        // for (int i = 0; i < 2500; i++) {// 生成指定个数的字符串
        // StringBuilder sb = new StringBuilder();
        // sb.append(createRandomString(10));
        // sb.append("16");
        // sb.append(createRandomString(7));
        // System.out.println(sb.toString());
        // }
        Random ran = new Random(10);
        Random ran2 = new Random(10);
        long start = System.currentTimeMillis();
        for (int i = 0; i <= 100000; i++) {
            int a = ran.nextInt();
            // System.out.print(ran2.nextInt() + " ");
            // System.out.println();
            if (i == 100000) {
                System.out.println(a);
            }
        }
        long end = System.currentTimeMillis();
        System.out.println(end - start + "ms");
    }
}
