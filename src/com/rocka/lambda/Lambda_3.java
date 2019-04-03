package com.rocka.lambda;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author: Rocka
 * @version: 1.0
 * @description: Lambda的Stream 操作方法
 * @time:2019/1/16
 */
public class Lambda_3 {

    public static void main(String args[]) {
//        limit();
//        skip();
//        distinct();
//        filter();
//        map();
//        flatMap();
//        peek();
    }

    /**
     * limit操作，获取其前N个元素，如果原Stream中包含的元素个数小于N，那就获取其所有的元素
     */
    private static void limit() {
        List<String> str = new ArrayList<>();
        str.add("rocka");
        str.add("fever");
        str.add("fuck");
        str.add("rock");
        str.add("n");
        str.add("roll");
        List<String> strFinal = str.stream().limit(3).collect(Collectors.toList());
        System.out.println("strFinal : " + strFinal);
    }

    /**
     * skip操作,丢弃掉前n个元素,取剩下的元素
     */
    private static void skip() {
        List<String> str = new ArrayList<>();
        str.add("l");
        str.add("x");
        str.add("f");
        str.add("u");
        str.add("n");
        str.add("roll");
        List<String> strFinal = str.stream().skip(3).collect(Collectors.toList());
        System.out.println("strFinal : " + strFinal);
    }

    /**
     * distinct操作,去除流里重复的元素
     */
    private static void distinct(){
        List<String> str = new ArrayList<>();
        str.add("a");
        str.add("a");
        str.add("c");
        str.add("b");
        str.add("d");
        str.add("e");
        List<String> strFinal = str.stream().distinct().collect(Collectors.toList());
        System.out.println("strFinal : " + strFinal);
    }

    /**
     * filter操作,过滤流里的元素,新生成的流只包含符合条件的元素
     */
    private static void filter(){
        List<String> str = new ArrayList<>();
        str.add("coco");
        str.add("static");
        str.add("ez");
        str.add("hate");
        str.add("damn");
        str.add("u");
        List<String> strFinal = str.stream().filter(s -> !s.equals("hate")).collect(Collectors.toList());
        System.out.println("strFinal : " + strFinal);
    }

    /**
     * map操作,map主要是用于遍历每个参数，然后进行参数合并或者返回新类型的集合
     */
    private static void map(){
        List<String> str = new ArrayList<>();
        str.add("meg");
        str.add("ivan");
        str.add("ray");
        str.add("charles");
        str.add("maybe");
        str.add("u");
        Stream<Boolean> s = str.stream().map(a -> a.startsWith("m"));
        List<Boolean> s2 = s.collect(Collectors.toList());
        System.out.println(s2);
    }

    /**
     * flatmap操作,stream合并，所以合并集合优先实用这种方式
     */
    private static void flatMap(){
        List<String> s1 = new ArrayList<>();
        s1.add("r");
        s1.add("o");
        s1.add("t");
        s1.add("k");

        List<String> s2 = new ArrayList<>();
        s2.add("k");
        s2.add("s");
        s2.add("s");

        List<String> s3 = Stream.of(s1,s2).flatMap(u -> u.stream()).collect(Collectors.toList());
        System.out.println(s3);
    }

    /**
     * peek操作, 生成一个包含原Stream的所有元素的新Stream，同时会提供一个消费函数（Consumer实例），新Stream每个元素被消费的时候都会执行给定的消费函数；
     */
    private static void peek() {
        List<String> str = new ArrayList<>();
        str.add("rocka");
        str.add("fever");
        str.add("fuck");
        Consumer<String> stringConsumer = x -> System.out.println("printStr : " + x);
        List<String> strFinal = str.stream().peek(stringConsumer).collect(Collectors.toList());
        System.out.println("strFinal : " + strFinal);
    }
}
