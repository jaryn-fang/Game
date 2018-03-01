package com.jhcoder.test;

public class TestException {
    public static void main(String[] args) {
        try {
            int c = 1 / 0;
            for (int i = 0; i < 5; i++) {
                try {
                    int b = i / 0;
                } catch (Exception e) {
                    System.out.println("a异常" + e);
                }
            }
        } catch (Exception e) {
            System.out.println("b异常 ");
        }
    }
}
