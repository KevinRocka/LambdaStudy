package com.rocka.concurrent;

import java.util.ArrayList;
import java.util.List;

/**
 * Synchronized
 */
public class SynchronizedTest {

    public static void main(String[] args){
        //方法同步
        InsertData insertData = new InsertData();
        new Thread() {
            @Override
            public void run() {
                System.out.println("thread - 1 start");
                setName("thread - 1 ");
                insertData.insertTwo(this);//insertOne
            }
        }.start();


        new Thread() {
            @Override
            public void run() {
                System.out.println("thread - 2 start");
                setName("thread - 2");
                insertData.insertTwo(this);//insertOne
            }
        }.start();

    }


    public static class InsertData {
        private List<Integer> s1 = new ArrayList<>();

        public synchronized void insertOne(Thread thread) { // 加上synchronized 和 没有加上synchronized区别
            for (int i = 0; i < 5; i++) {
                System.out.println("threadName : " + thread.getName() + ",  aka : "+ Thread.currentThread().getId() + " , insert data : " + i);
                s1.add(i);
            }
        }

        public void insertTwo(Thread thread){
            synchronized (this){ // 加上同步代码块 和 去掉同步代码块查看效果
                for(int i = 0; i < 100 ; i ++){
                    System.out.println("threadName : " + thread.getName() + ",  aka : "+ Thread.currentThread().getId() + " , insert data : " + i);
                    s1.add(i);
                }
            }
        }
    }

}
