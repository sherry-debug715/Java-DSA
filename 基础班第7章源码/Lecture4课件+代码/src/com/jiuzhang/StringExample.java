package com.jiuzhang;

public class StringExample {

    public static void main(String[] args) {
        runStringExample2();
    }

    static void runStringExample1() {
        System.out.println("String example 1");

        System.out.println("12345".substring(0, 4));   // [0, 4)

        String str = "12345";

        String substr = str.substring(0, 4);
        System.out.println(substr);

        System.out.println();

        StringBuilder sb = new StringBuilder("abcd");
        sb.setCharAt(0, '1');
        System.out.println(sb.toString());
    }

    static void runStringExample2() {
        System.out.println("String example 2");

        String str = "1234";
        String str2 = "123" + 4;   // "1234"

        System.out.println(str);
        System.out.println(str2);
        System.out.println(str == str2);

        System.out.println(str.equals(str2));

        System.out.println();
    }

}
