package com.rocka.lambda;


import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

/**
 * @author: Rocka
 * @version: 1.0
 * @description: Lambda双冒号::用法
 * @time:2019/1/16
 */
public class Lambda_2 {

    static List<String> strs = Arrays.asList("a", "b", "c", "d");

    public static void printStr(String s1) {
        System.out.println("output:" + s1);
    }

    @Test
    public static void main(String args[]) {
        //写法一.普通
        for (String s : strs) {
            System.out.println("output:" + s);
        }


        //写法二.Lambda
        strs.forEach(x -> System.out.println(x));


        //写法三.双冒号
        strs.forEach(Lambda_2::printStr);


        //写法四.另外一种写法
        Consumer<String> methodParams = Lambda_2::printStr;
        strs.forEach(methodParams);
    }
}
