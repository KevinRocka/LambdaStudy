package com.rocka.lambda;

import org.junit.Test;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author: Rocka
 * @version: 1.0
 * @description: Lambda常规用法
 * @time:2019/1/16
 */
public class Lambda_1 {

    static String name[] = {"Json", "Wang", "Jackson", "Jack"};

    /**
     * 1.1 原始方法启动线程
     */
    @Test
    public static void startThreadNormal() {
        Thread t1 = new Thread(new Runnable() {
            public void run() {
                System.out.println("....原始方法启动线程....");
            }
        });
        t1.start();
    }

    /**
     * 1.2 使用Lambda表达式启动线程
     */
    @Test
    public static void startThreadLambda() {
        Thread t1 = new Thread(() -> System.out.println("......使用Lambda表达式启动线程....."));
        t1.start();
    }

    /**
     * 2.1 传统方法排序
     * <p>
     * 输出为1，代表o1大于o2;
     * 输出为-1，代表o1小于o2;
     * 输出为0,代表相等;
     */
    @Test
    public static void normalSort() {
        Student s1 = new Student(27, "小卡");
        Student s2 = new Student(25, "小雨");


        Comparator<Student> comparator = new Comparator<Student>() {
            @Override
            public int compare(Student o1, Student o2) {
                return Integer.compare(o1.getAge(), o2.getAge());
            }
        };
        int result = comparator.compare(s1, s2);
        System.out.println("普通比较结果为: " + result);
    }


    /**
     * 2.2 使用Lambda进行排序
     */
    @Test
    public static void lambdaSort() {
        Student s1 = new Student(27, "小卡");
        Student s2 = new Student(25, "小雨");

        Comparator<Student> comparator = (x, y) -> Integer.compare(s1.getAge(), s2.getAge());
        int result = comparator.compare(s1, s2);
        System.out.println("lambda比较结果为: " + result);
    }

    /**
     * 3.1 自定义匿名内部类
     */
    @Test
    public static void normalOutput() {
        SumFactory foodFactory = new SumFactory() {
            @Override
            public int makeFood(int a, int b) {
                return a + b;
            }
        };
        foodFactory.makeFood(3, 4);
    }

    /**
     * 3.2 使用Lambda表达式
     */
    @Test
    public static void lambdaOutput() {
        SumFactory factory = (x, y) -> (x + y);
        factory.makeFood(3, 4);
    }

    /**
     * 4.1 使用普通方式操作集合
     */
    @Test
    public static void normalCollections() {
        List<String> names = Arrays.asList(name);
        for (String tmp : names) {
            System.out.println(tmp);
        }
    }

    /**
     * 4.2 使用Lambda表达式操作集合
     */
    @Test
    public static void lambdaCollection() {
        List<String> names = Arrays.asList(name);
        names.forEach((name) -> System.out.println(name));

        System.out.println("---------------------");
        Map<String, String> maps = new HashMap<>();
        maps.put("An", "Ka");
        maps.put("Rocka", "Kevin");
        maps.keySet().forEach(tempName -> System.out.println(tempName));
        maps.values().forEach(tempName -> System.out.println(tempName));
    }

    static class Student {

        public Student(int age, String name) {
            this.age = age;
            this.name = name;
        }

        int age;
        String name;

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
