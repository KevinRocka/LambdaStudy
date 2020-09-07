package com.rocka.lambda;

import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 其他时候使用到的对Lambda学习有帮助的地方添加到此次,数据操作合集
 */
public class OtherLambda {


    /**
     * Map用来筛选成为合逻辑的Map
     */
    @Test
    public void lambdaMap() {
        Map<Integer, Integer> s = new HashMap<>();
        s.put(3, 3);
        s.put(3, 20);
        s.put(4, 4);
        s.put(7, 15);
        s.put(33, 20);

        Map<Integer, Integer> finalM;

        //筛选Value大于10的数据结构
        //finalM = s.entrySet().stream().filter(a -> a.getValue() > 10).map(a -> a.getKey()).collect(Collectors.toMap(Integer::intValue, a -> s.get(a.intValue())));
        //筛选KEY大于10的数据结构
        finalM = s.keySet().stream().filter(a -> a > 10).collect(Collectors.toMap(Integer::intValue, a -> s.get(a.intValue())));
        System.out.println(finalM);
    }

    /**
     * Map用来筛选成为合逻辑的Map
     */
    @Test
    public void lambdaRemove() {
        //EG2. List<Integer>的removeIf 删除制定条件的值
        List<Integer> s1 = new ArrayList<>();
        s1.add(33);
        s1.add(2);
        s1.add(15);
        System.out.println(s1);
        s1.removeIf(a -> a.intValue() > 10);
        System.out.println(s1);
    }

}
