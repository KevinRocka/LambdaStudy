package com.rocka.lambda;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author: Rocka
 * @version: 1.0
 * @description: Lambda的Stream用法
 * @time:2019/1/16
 */
public class Lambda_3 {

    public static void main(String args[]) {
        //PEEK用法
//        List<String> str = new ArrayList<>();
//        str.add("s");
//        str.add("csb");
//        str.add("what fuck");
//        Consumer<String> stringConsumer = x -> System.out.println("做一些输出：" + x);
//        List<String> strFinal = str.stream().peek(stringConsumer).collect(Collectors.toList());
//        System.out.println("strFinal : " + strFinal);

        //flatMap和Map
        List<List<Integer>> integers = new ArrayList<>();
        integers.add(Arrays.asList(3, 4, 5));
        integers.add(Arrays.asList(1, 2, 3));
        integers.add(Arrays.asList(6, 7, 8));

        Stream<List<Integer>> intStreams = integers.stream();

        Stream<Stream<Integer>> mapStream = intStreams.map(Collection::stream);
        

//        Stream<Integer> flatMapStream = intStreams.flatMap(l -> l.stream());
//        flatMapStream.forEach(y -> System.out.println(y));
    }
}
